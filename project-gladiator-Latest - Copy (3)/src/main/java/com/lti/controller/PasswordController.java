package com.lti.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.ForgotPasswordStatus;
import com.lti.dto.OtpDto;
import com.lti.dto.StatusDto;
import com.lti.dto.StatusDto.StatusType;
import com.lti.entity.User;
import com.lti.exception.UserServiceException;
import com.lti.service.EmailService;
import com.lti.service.UserService;

@RestController
@CrossOrigin
public class PasswordController {
	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	@PostMapping(path = "/forgotPassword", consumes = "application/json", produces = "application/json")
	public ForgotPasswordStatus forgotPassword(@RequestBody Map model) {

		User user = userService.getUserByEmail((String)model.get("emailId"));

		try {
			if (!userService.isUserPresent((String)model.get("emailId"))) {
				throw new UserServiceException("User does not exists!");
			}

			String generatedOtp = UserService.generateOtp();
			if (generatedOtp == null) {
				throw new UserServiceException("OTP could not generated, try again!");
			}

			user.setOtp(generatedOtp);

			userService.addOrUpdateUser(user);

			SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
			passwordResetEmail.setFrom("nk.theraja@gmail.com"); // email of sender
			passwordResetEmail.setTo(user.getEmailId());
			passwordResetEmail.setSubject("Reset your password");
			passwordResetEmail.setText("Your otp to reset password is " + generatedOtp);

			emailService.sendEmail(passwordResetEmail);

			ForgotPasswordStatus status = new ForgotPasswordStatus();
			status.setEmail(user.getEmailId());
			status.setMessage("Otp for password reset is send to " + user.getEmailId());
			status.setStatus(StatusType.SUCCESS);

			return status;

		} catch (UserServiceException e) {
			ForgotPasswordStatus status = new ForgotPasswordStatus();
			status.setEmail(user.getEmailId());
			status.setMessage(e.getMessage());
			status.setStatus(StatusType.FAILURE);

			return status;
		}
	}

	@PostMapping(path = "/verifyOtp", consumes = "application/json", produces = "application/json")
	public StatusDto verifyOtp(@RequestBody OtpDto otpDto) {
		try {
			User user = userService.getUserByOtp(otpDto.getOtp());
			if (user == null) {
				throw new UserServiceException("Invalid token");
			}
			StatusDto status = new StatusDto();
			status.setMessage("Registered successfully!");
			status.setStatus(StatusDto.StatusType.SUCCESS);
			
			return status;

		} catch (UserServiceException e) {
			StatusDto status = new StatusDto();
			status.setMessage(e.getMessage());
			status.setStatus(StatusDto.StatusType.FAILURE);
			return status;
		}
	}
	
	@PostMapping(path = "/updatePassword", consumes = "application/json", produces = "application/json")  // emailId, oldPassword, newPassword
	public StatusDto updatePassword(@RequestBody Map model) {
		try {
			User user = userService.getUserByEmail((String)model.get("emailId"));
			
			if (user == null) {
				throw new UserServiceException("Unknown email address");
			}
			
			user.setPassword(UserService.getHashedString((String)model.get("newPassword")));
			user.setOtp(null);
			
			userService.addOrUpdateUser(user);
			
			StatusDto status = new StatusDto();
			status.setMessage("Updated successfully!");
			status.setStatus(StatusDto.StatusType.SUCCESS);
			
			return status;

		} catch (UserServiceException e) {
			StatusDto status = new StatusDto();
			status.setMessage(e.getMessage());
			status.setStatus(StatusDto.StatusType.FAILURE);
			return status;
		}
	}
	
}

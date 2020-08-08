package com.lti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lti.dao.util.ConnManager;
import com.lti.entity.Product;

//classes which contains DB code are commonly referred to as 
//Data Access Objects (DAO)
public class ProductDao {
	
	public Product select(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnManager.connect();
			String sql = "SELECT * from tbl_product WHERE id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				return product;
			}
			return null;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			try {
				conn.close();
			}catch(Exception e) { }
		}
	}
	
	public List<Product> selectAll(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnManager.connect();
			String sql = "SELECT * from tbl_product";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			List<Product> list = new ArrayList<>();
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				list.add(product);
			}
			return list;
	}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			try {
				conn.close();
			}catch(Exception e) { }
		}
	}
	
	public void insert(Product product) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnManager.connect();
			String sql = "INSERT INTO tbl_product VALUES(?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, product.getId());
			stmt.setString(2, product.getName());
			stmt.setDouble(3, product.getPrice());
			int count = stmt.executeUpdate();
			
	}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			}catch(Exception e) { }
		}
	}
}

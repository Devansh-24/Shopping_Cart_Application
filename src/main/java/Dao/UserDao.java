package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Result;

import Model.User;

public class UserDao {

	private Connection con;
	private String query;
	private PreparedStatement ps;
	private ResultSet rs;

	public UserDao(Connection con) {

		this.con = con;
	}

	public User userlogin(String email, String password) {
		User user = null;
		
		try {
			query = "select * from users where email=? and password=?";  
			ps=this.con.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next())
			{
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;

	}

}

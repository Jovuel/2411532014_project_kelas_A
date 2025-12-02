package service;

import java.sql.*;
import config.Database;
import model.User;

public class LoginService {
	public boolean authenticate(User user) {
		String query = "SELECT * FROM user WHERE nama = ? AND password = ?";
		
		try(Connection connection = Database.koneksi();
				PreparedStatement statement = connection.prepareStatement(query)){
			statement.setString(1, user.getNama());
			statement.setString(2, user.getPassword());
			
			ResultSet resultSet = statement.executeQuery();
			return resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
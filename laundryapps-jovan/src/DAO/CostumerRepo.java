package DAO;

import config.Database;
import model.Costumer;
import model.CustomerBuilder;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CostumerRepo implements CostumerDAO {
    private Connection connection;
    private final String insert = "INSERT INTO customer(nama, email, alamat, hp) VALUES(?,?,?,?)";
    private final String select = "SELECT * FROM customer";
    private final String delete = "DELETE FROM customer WHERE id=?";
    private final String update = "UPDATE customer SET nama=?, email=?, alamat=?, hp=? WHERE id=?";

    public CostumerRepo() {
        connection = Database.koneksi();
    }

    @Override
    public List<Costumer> show() {
        List<Costumer> ls = null;
        try {
		ls = new ArrayList<Costumer>();
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(select);
		while (rs.next()) {
			Costumer cs = new CustomerBuilder()
					.setId(rs.getString("id"))
					.setNama(rs.getString("nama"))
					.setEmail(rs.getString("email"))
					.setAlamat(rs.getString("alamat"))
					.setHp(rs.getString("hp"))
					.build();
			ls.add(cs);
		}
	} catch (SQLException e) {
		Logger.getLogger(CostumerDAO.class.getName()).log(Level.SEVERE, null, e);
    }
        return ls;
    }
    
    public void save(Costumer cs) {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(insert);
			st.setString(1, cs.getNama());
			st.setString(2, cs.getEmail());
			st.setString(3, cs.getAlamat());
			st.setString(4, cs.getHp());
			st.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
    }
    
    public void update(Costumer cs) {
    	
    }
    
    public void delete(String id) {
    	
    }
}

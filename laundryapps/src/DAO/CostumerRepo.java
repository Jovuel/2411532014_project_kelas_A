package DAO;

import config.Database;
import model.Costumer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CostumerRepo implements CostumerDAO {
    private Connection conn;

    public CostumerRepo() {
        conn = Database.koneksi();
    }

    @Override
    public void save(Costumer c) {
        String sql = "INSERT INTO customer (id, nama, alamat, nomor_hp) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getId());
            ps.setString(2, c.getNama());
            ps.setString(3, c.getAlamat());
            ps.setString(4, c.getNomor_hp());
            ps.executeUpdate();
            System.out.println("Customer berhasil ditambahkan");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Costumer> show() {
        List<Costumer> list = new ArrayList<>();
        String sql = "SELECT * FROM customer";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Costumer c = new Costumer();
                c.setId(rs.getString("id"));
                c.setNama(rs.getString("nama"));
                c.setAlamat(rs.getString("alamat"));
                c.setNomor_hp(rs.getString("nomor_hp"));
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM customer WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("Customer berhasil dihapus");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Costumer c) {
        String sql = "UPDATE customer SET nama=?, alamat=?, nomor_hp=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNama());
            ps.setString(2, c.getAlamat());
            ps.setString(3, c.getNomor_hp());
            ps.setString(4, c.getId());
            ps.executeUpdate();
            System.out.println("Customer berhasil diperbarui");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

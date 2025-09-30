package DAO;

import config.Database;
import model.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceRepo implements ServiceDAO {
    private Connection conn;

    public ServiceRepo() {
        conn = Database.koneksi();
    }

    @Override
    public void save(Service s) {
        String sql = "INSERT INTO service (id, jenis, harga, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getId());
            ps.setString(2, s.getJenis());
            ps.setInt(3, s.getHarga());
            ps.setBoolean(4, s.getStatus());
            ps.executeUpdate();
            System.out.println("Service berhasil ditambahkan");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Service> show() {
        List<Service> list = new ArrayList<>();
        String sql = "SELECT * FROM service";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Service s = new Service();
                s.setId(rs.getString("id"));
                s.setJenis(rs.getString("jenis"));
                s.setHarga(rs.getInt("harga"));
                s.setStatus(rs.getBoolean("status"));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM service WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("Service berhasil dihapus");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Service s) {
        String sql = "UPDATE service SET jenis=?, harga=?, status=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getJenis());
            ps.setInt(2, s.getHarga());
            ps.setBoolean(3, s.getStatus());
            ps.setString(4, s.getId());
            ps.executeUpdate();
            System.out.println("Service berhasil diperbarui");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

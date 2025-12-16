package config;

import javax.swing.JOptionPane;

public class TestDatabase {

	public static void main(String[] args) {
		Database conn = new Database();
		if (conn.koneksi() != null) {
			JOptionPane.showMessageDialog(null, "Koneksi Berhasil");
		} else {
			JOptionPane.showMessageDialog(null, "Koneksi Gagal");
	}
}}
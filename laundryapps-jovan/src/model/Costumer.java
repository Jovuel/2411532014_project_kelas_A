package model;

public class Costumer {
	private String id;
	private String nama;
	private String email;
	private String alamat;
	private String hp;
	
	public Costumer(String id, String nama, String email, String alamat, String hp) {
		this.id = id;
		this.nama = nama;
		this.alamat = alamat;
		this.email = email;
		this.hp = hp;
	}
	
	public String getId() {
		return id;
	}
	
	public String getNama() {
		return nama;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getAlamat() {
		return alamat;
	}
	
	public String getHp() {
		return hp;
	}
}
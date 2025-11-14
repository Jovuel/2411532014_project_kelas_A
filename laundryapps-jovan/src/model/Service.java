package model;

public class Service {
	String id, jenis;
	int harga;
	boolean status;
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setJenis(String jenis) {
		this.jenis = jenis;
	}
	
	public String getJenis() {
		return jenis;
	}
	
	public void setHarga(int harga) {
		this.harga = harga;
	}
	
	public int getHarga() {
		return harga;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}	
	
	public boolean getStatus() {
		return status;
	}
	
}

package pekan5;

public class Pesawat extends Kendaraan implements TransportasiUdara, Maskapai {
	private String jenisPenerbangan;
	private String namaMaskapai;
	private int kapasitasBahanBakar;

	public Pesawat(String merk, String model, int tahunProduksi) {
		super(merk, model, tahunProduksi);
	}

	@Override
	public void nyalakanMesin() {
		System.out.println("Nyalakan Mesin : Bersiap lepas landas");
	}

	@Override
	public String jenisPenerbangan() {
		return jenisPenerbangan;
	}

	@Override
	public String namaMaskapai() {
		return namaMaskapai;
	}

	public int kapasitasBahanBakar() {
		return kapasitasBahanBakar;
	}

	@Override
	public String jenisBahanBakar() {
		return "Avtur";
	}
}

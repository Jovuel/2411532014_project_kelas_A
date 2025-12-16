package pekan5;

import pekan5.Bus.JadwalPerjalanan;

public class MainApp {

	public static void main(String[] args) {
		Mobil mobilSaya = new Mobil("Toyota", "Avanza", 2021, "Otomatis");
		mobilSaya.tampilkanInfo();
		mobilSaya.nyalakanMesin();
		System.out.println("Jenis Bahan Bakar: " + mobilSaya.jenisBahanBakar());
		mobilSaya.infoKonsumsi();
		mobilSaya.fiturMobil();
		System.out.println();
		Bus busKota = new Bus("Mercedes-Benz", "Bus Pariwisata", 2018, "Ekonomi");
		busKota.tampilkanInfo();
		busKota.nyalakanMesin();
		System.out.println("Jenis Bahan Bakar: " + busKota.jenisBahanBakar());
		System.out.println("Kapasitas Penumpang: " + busKota.kapasitasPenumpang());
		busKota.fiturBus();
		JadwalPerjalanan jadwal = busKota.new JadwalPerjalanan("Jakarta - Bandung", "08:00 AM");
		jadwal.tampilkanJadwal();
		System.out.println();
		Pesawat pesawatSaya = new Pesawat("Garuda", "Boeing 737", 100);
		pesawatSaya.tampilkanInfo();
		pesawatSaya.nyalakanMesin();
		System.out.println("Jenis Bahan Bakar: " + pesawatSaya.jenisBahanBakar());
	}
}

package pekan5;

public class Mobil {
    private String merk;
    private String model;
    private int tahun;
    private String transmisi;

    public Mobil(String merk, String model, int tahun, String transmisi) {
        this.merk = merk;
        this.model = model;
        this.tahun = tahun;
        this.transmisi = transmisi;
    }

    public void tampilkanInfo() {
        System.out.println("Merk: " + merk);
        System.out.println("Model: " + model);
        System.out.println("Tahun: " + tahun);
        System.out.println("Transmisi: " + transmisi);
    }

    public void nyalakanMesin() {
        System.out.println("Mesin dinyalakan.");
    }

    public String jenisBahanBakar() {
        return "Bensin"; // or determine dynamically
    }

    // Make sure this method is public and has this exact name/signature
    public void infoKonsumsi() {
        System.out.println("Konsumsi bahan bakar: 12 km/l (perkiraan).");
    }

    public void fiturMobil() {
        System.out.println("Fitur: AC, Audio, Airbag.");
    }
}

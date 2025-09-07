package model;

public class Order {
    int id, idCostumer, idService, idUser;
    double total;
    String tanggal, tanggalSelesai, statusPembayaran;

    public void setId(int id) {
        this.id = id;
    }

    public void setIdCostumer(int idCostumer) {
        this.idCostumer = idCostumer;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public void setTanggalSelesai(String tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    public void setStatusPembayaran(String statusPembayaran) {
        this.statusPembayaran = statusPembayaran;
    }

    public int getId() {
        return id;
    }

    public int getIdCostumer() {
        return idCostumer;
    }

    public int getIdService() {
        return idService;
    }

    public int getIdUser() {
        return idUser;
    }

    public double getTotal() {
        return total;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getTanggalSelesai() {
        return tanggalSelesai;
    }

    public String getStatusPembayaran() {
        return statusPembayaran;
    }
}
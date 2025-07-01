package com.example;

public class Rent {
    private int id;
    private String nama;
    private String nohp;
    private String tanggal;
    private String jammulai;
    private String jamselesai;
    private String lapangan;

    public Rent(int id, String nama, String nohp, String tanggal, String jammulai, String jamselesai, String lapangan) {
        this.id = id;
        this.nama = nama;
        this.nohp = nohp;
        this.tanggal = tanggal;
        this.jammulai = jammulai;
        this.jamselesai = jamselesai;
        this.lapangan = lapangan;
    }

    public int getId() { return id; }
    public String getNama() { return nama; }
    public String getNohp() { return nohp; }
    public String getTanggal() { return tanggal; }
    public String getJamMulai() { return jammulai; }
    public String getJamSelesai() { return jamselesai; }
    public String getLapangan() { return lapangan; }

    public void setNama(String nama) { this.nama = nama; }
    public void setNohp(String nohp) { this.nohp = nohp; }
    public void setTanggal(String tanggal) { this.tanggal = tanggal; }
    public void setJamMulai(String jammulai) { this.jammulai = jammulai; }
    public void setJamSelesai(String jamselesai) { this.jamselesai = jamselesai; }
    public void setLapangan(String lapangan) { this.lapangan = lapangan; }
}

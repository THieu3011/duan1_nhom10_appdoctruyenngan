package com.example.duan1_nhom10.model;

public class Truyen {
    private String tenTruyen,tacGia,noiDung;

    public Truyen() {
    }

    public Truyen(String tenTruyen, String tacGia, String noiDung) {
        this.tenTruyen = tenTruyen;
        this.tacGia = tacGia;
        this.noiDung = noiDung;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }
}

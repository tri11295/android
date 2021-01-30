package com.ssv.usefirebase;

import androidx.annotation.NonNull;

import java.util.PrimitiveIterator;

public class SinhVien {
    private String id;
    private String Email;
    private String HoTen;
    private String Sdt;
    private int Tuoi;
    private int a;

    public SinhVien( String email, String hoTen, String sdt, int tuoi) {
        Email = email;
        HoTen = hoTen;
        Sdt = sdt;
        Tuoi = tuoi;
    }

    public SinhVien() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String sdt) {
        Sdt = sdt;
    }

    public int getTuoi() {
        return Tuoi;
    }

    public void setTuoi(int tuoi) {
        Tuoi = tuoi;
    }
}

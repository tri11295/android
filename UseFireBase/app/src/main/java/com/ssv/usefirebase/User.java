package com.ssv.usefirebase;

public class User {
    String Id,HoTen,Lop,UrlImg;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public User(){

    }

    public User(String hoTen, String lop, String urlImg) {
        HoTen = hoTen;
        Lop = lop;
        UrlImg = urlImg;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getLop() {
        return Lop;
    }

    public void setLop(String lop) {
        Lop = lop;
    }

    public String getUrlImg() {
        return UrlImg;
    }

    public void setUrlImg(String urlImg) {
        UrlImg = urlImg;
    }
}
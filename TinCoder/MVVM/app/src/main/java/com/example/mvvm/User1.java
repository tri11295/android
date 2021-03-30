package com.example.mvvm;

public class User1 {

    private int imgAvatar;
    private String email;
    private String address;

    public User1(int imgAvatar, String email, String address) {
        this.imgAvatar = imgAvatar;
        this.email = email;
        this.address = address;
    }

    public int getImgAvatar() {
        return imgAvatar;
    }

    public void setImgAvatar(int imgAvatar) {
        this.imgAvatar = imgAvatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

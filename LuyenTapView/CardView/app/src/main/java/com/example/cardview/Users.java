package com.example.cardview;

public class Users {
    private String urlImage;
    private String name;

    public Users(String urlImage, String name) {
        this.urlImage = urlImage;
        this.name = name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//    public Users(int resourceImage, String name) {
//        this.resourceImage = resourceImage;
//        this.name = name;
//    }
//
//    public int getResourceImage() {
//        return resourceImage;
//    }
//
//    public String getName() {
//        return name;
//    }
}

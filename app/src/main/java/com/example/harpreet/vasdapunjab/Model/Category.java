package com.example.harpreet.vasdapunjab.Model;

public class Category {

    private String Name;
    private String Image;

    public Category(){}

    public Category(String name, String images) {
        Name = name;
        Image = images;
    }

    public String getName() {
        return Name;
    }
    public String getImage() {
        return Image;
    }

}

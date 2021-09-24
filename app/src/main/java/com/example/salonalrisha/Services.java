package com.example.salonalrisha;

public class Services {
    String name, price, duration ,image;

    Services(){}

    public Services(String name, String price, String duration, String image) {
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

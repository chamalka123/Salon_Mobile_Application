package com.example.salonalrisha;

public class Products {
    String name, brand, category ,image;
   int qty;
   int total;
   String price;

    Products(){}

    public Products(String name, String price, String brand, String category, String image ) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.category = category;
        this.image = image;

    }

    public Products(int qty, int total) {
        this.qty = qty;
        this.total=total;

    }


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}

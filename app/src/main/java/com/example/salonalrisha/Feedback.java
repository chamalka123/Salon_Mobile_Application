package com.example.salonalrisha;

public class Feedback {
    private String name;
    private String comment;
    private String email;
    private String image;


    public Feedback(){}
    public Feedback(String name, String comment, String email, String image) {
        this.name = name;
        this.comment = comment;
        this.email = email;
        this.image = image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setComment(String comment) {

        this.comment = comment;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {

        return name;
    }

    public String getComment() {

        return comment;
    }

    public String getEmail() {

        return email;
    }

    public String getImage() {
        return image;
    }

}

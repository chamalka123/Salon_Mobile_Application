package com.example.salonalrisha;

public class Feedback {
    private String name;
    private String comment;
    private String email;


    public Feedback(){}
    public Feedback(String name, String comment, String email) {
        this.name = name;
        this.comment = comment;
        this.email = email;
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

}

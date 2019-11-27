package com.injila.firebaseapp;

public class ColumnContructor {
    String id, name, email, country;
    //generate a constructor

    public ColumnContructor(String id, String name, String email, String country) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.country = country;
    }
//    Firebase needs an empty constructor


    public ColumnContructor() {
    }
//    generete setter and getter methods


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

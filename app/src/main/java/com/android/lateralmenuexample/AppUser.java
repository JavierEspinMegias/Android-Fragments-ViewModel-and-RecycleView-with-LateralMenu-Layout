package com.android.lateralmenuexample;

public class AppUser{
    public String name, id, photo;
    public int age;


    public AppUser() {
        this.name = "";
        this.id = "";
        this.photo = "";
        this.age = 0;
    }


    public AppUser(String name, String id, String photo, int age) {
        this.name = name;
        this.id = id;
        this.photo = photo;
        this.age = age;
    }
}

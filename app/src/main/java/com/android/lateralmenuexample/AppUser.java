package com.android.lateralmenuexample;

import android.net.Uri;

public class AppUser{
    public String name, id;
    public int age;
    public Uri photo;

    public AppUser() {
        this.name = "";
        this.id = "";
        this.photo = null;
        this.age = 0;
    }


    public AppUser(String name, String id, Uri photo, int age) {
        this.name = name;
        this.id = id;
        this.photo = photo;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Uri getPhoto() {
        return photo;
    }

    public void setPhoto(Uri photo) {
        this.photo = photo;
    }
}

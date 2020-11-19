package com.example.passwordmanager;

public class Entity {

    public String username;
    public String password;
    public String identifier;

    public Entity(String i, String u, String p) {
        this.identifier=i;
        this.username=u;
        this.password=p;
    }
}
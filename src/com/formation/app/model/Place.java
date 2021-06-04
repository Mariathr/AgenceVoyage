package com.formation.app.model;

public class Place {
    private Long id;
    private String name;

    public Place() {
        this.name = name;
    }
    public Place(String nom) {
        this.name = nom;
    }
    public Place( Long id,String nom) {
        this.name = nom;
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }







}

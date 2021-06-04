package com.formation.app.model;

public class Trip {
    private Long id;
    private  Long idDepart;
    private  String nomDepart;
    private Long idArrivee;
    private  String nomArrivee;
    private Float prix;


    public Trip(){

    }
    public Trip(Long id,Long idDepart, Long idArrivee, Float prix) {
        this.id = id;
        this.idDepart = idDepart;
        this.idArrivee = idArrivee;
        this.prix = prix;
    }
    public Trip(Long idDepart, Long idArrivee, Float prix) {
        this.idDepart = idDepart;
        this.idArrivee = idArrivee;
        this.prix = prix;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDepart() {
        return idDepart;
    }

    public void setIdDepart(Long idDepart) {
        this.idDepart = idDepart;
    }

    public String getNomDepart() {
        return nomDepart;
    }

    public void setNomDepart(String nomDepart) {
        this.nomDepart = nomDepart;
    }

    public Long getIdArrivee() {
        return idArrivee;
    }

    public void setIdArrivee(Long idArrivee) {
        this.idArrivee = idArrivee;
    }

    public String getNomArrivee() {
        return nomArrivee;
    }

    public void setNomArrivee(String nomArrivee) {
        this.nomArrivee = nomArrivee;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }











}

package com.formation.app.model;

public class Trip {
    private Long id;

    public Trip(Long id, Long idDepart, Long idArrivee, Float prix) {
        this.id = id;
        this.idDepart = idDepart;
        this.idArrivee = idArrivee;
        this.prix = prix;
    }

    private  Long idDepart;
    private Long idArrivee;
    private Float prix;



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

    public Long getIdArrivee() {
        return idArrivee;
    }

    public void setIdArrivee(Long idArrivee) {
        this.idArrivee = idArrivee;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }



}

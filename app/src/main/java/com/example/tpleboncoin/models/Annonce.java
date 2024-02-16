package com.example.tpleboncoin.models;

public class Annonce {
    private String titre;
    private String  adresse;
    private int image;

    //Constructeur
    public Annonce(String titre, String adresse, int image) {
        this.titre = titre;
        this.adresse = adresse;
        this.image = image;
    }

    // Getter and Setter
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }


}

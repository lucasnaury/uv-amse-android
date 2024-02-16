package com.example.tpleboncoin.models;

public class Annonce {
    static int nbAnnonces = 0;
    private int id;
    private String titre;
    private String  adresse;
    private double prix;
    private int image;

    // Constructeur
    public Annonce(String titre, String adresse, int image, double prix) {
        this.titre = titre;
        this.adresse = adresse;
        this.image = image;
        this.prix = prix;

        // Génération d'un id automatique
        this.id = Annonce.nbAnnonces;
        Annonce.nbAnnonces++;
    }

    // Getter et Setter
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }

    public double getPrix(){return prix;}
    public String getAdresse(){return adresse;}
    public int getImage(){return image;}


}

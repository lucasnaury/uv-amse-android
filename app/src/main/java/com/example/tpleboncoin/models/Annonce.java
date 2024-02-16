package com.example.tpleboncoin.models;

public class Annonce {
    static int nbAnnonces = 0;
    private int id;
    private String titre;
    private String  adresse;
    private int image;

    // Constructeur
    public Annonce(String titre, String adresse, int image) {
        this.titre = titre;
        this.adresse = adresse;
        this.image = image;

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


}

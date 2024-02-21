package com.example.tpleboncoin.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Annonce implements Parcelable {
    static int nbAnnonces = 0;
    private int id;
    private String titre;
    private String  adresse;
    private double prix;
    private String image;

    // Constructeur
    public Annonce(String titre, String adresse, String image, double prix) {
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
    public String getImage(){return image;}


    // Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(titre);
        parcel.writeString(adresse);

        parcel.writeDouble(prix);
        parcel.writeString(image);
        parcel.writeInt(id);
    }
    public static final Parcelable.Creator<Annonce> CREATOR
            = new Parcelable.Creator<Annonce>() {
        public Annonce createFromParcel(Parcel in) {
            return new Annonce(in);
        }

        public Annonce[] newArray(int size) {
            return new Annonce[size];
        }
    };
    private Annonce(Parcel in) {
        titre = in.readString();
        adresse = in.readString();
        prix = in.readDouble();
        image = in.readString();
        id = in.readInt();
    }
}

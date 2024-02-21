package com.example.tpleboncoin.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Annonce implements Parcelable {
    static int nbAnnonces = 0;
    private int id;
    private String titre;
    private String  adresse;
    private double prix;
    private byte[] image;
    private String description;

    // Constructeur
    public Annonce(String titre, String adresse, byte[] image, double prix, String description) {
        this.titre = titre;
        this.adresse = adresse;
        this.image = image;
        this.prix = prix;
        this.description = description;

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
    public byte[] getImage(){return image;}
    public Bitmap getImageAsBitmap(){
        byte[] img = this.getImage();
        if(img == null){
            return null;
        }

        InputStream is = new ByteArrayInputStream(img);
        Bitmap bmpImage = BitmapFactory.decodeStream(is);

        return bmpImage;
    }
    public String getDescription(){return description;}


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

        int imageLength = image != null ? image.length : 0;
        parcel.writeInt(imageLength);
        if(imageLength>0){
            parcel.writeByteArray(image);
        }

        parcel.writeString(description);
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

        int imageLength = in.readInt();

        if(imageLength > 0){
            image = new byte[imageLength];
            in.readByteArray(image);
        }else{
            image = null;
        }


        description = in.readString();
        id = in.readInt();
    }
}

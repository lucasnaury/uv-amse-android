package com.example.tpleboncoin.db;

// Package + Import

import static java.lang.Math.abs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.tpleboncoin.R;
import com.example.tpleboncoin.models.Annonce;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class DBManager {

    public static DBManager DBManager;

    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    private DBManager(Context c) {
        context = c;
    }

    public static DBManager getDBManager(Context context) {
        if (DBManager == null){
            return new DBManager(context);
        }
        return DBManager;
    }

    public DBManager open(Context context) throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();

        //S'il n'y a pas d'annonce, on initialise une liste d'annonces
        Cursor cursor = fetch();
        if(cursor != null && cursor.getCount() == 0){
            init(context);
        }

        return this;
    }

    public void close() {
        dbHelper.close();
    }

    // Add ads manually.
    public void init(Context c){
        insert(new Annonce("Wood", "Douai", imageToByteArray(R.drawable.mat1, c),24.99, "High-quality seasoned firewood for sale! Perfect for cozy nights by the fireplace or powering your wood-burning stove. Our carefully selected wood is sourced sustainably, ensuring both quality and environmental responsibility. Available for delivery to your doorstep, our firewood is split and ready to use, saving you time and effort. Whether you're a homeowner, camper, or outdoor enthusiast, our firewood is ideal for all your needs. Stock up now and enjoy the warmth and ambiance of natural wood burning. Don't miss out on this opportunity to elevate your fire experience. Contact us today to place your order!", "0123456789", "test@gmail.com"));
        insert(new Annonce("Steel", "Lille", imageToByteArray(R.drawable.mat2, c), 18.9, "Premium steel available for sale! Engineered for strength and durability, our high-grade steel is suitable for a wide range of applications. Whether you're a contractor, builder, or DIY enthusiast, our steel products are perfect for construction projects, fabrication, or repairs. With various sizes and shapes available, we cater to diverse needs and specifications. Our steel undergoes rigorous quality checks to ensure top-notch performance and reliability. Conveniently order online and have it delivered to your location. Upgrade your projects with our superior steel today. Contact us now to place your order and experience the difference!", "0123456789", "test@gmail.com"));
        insert(new Annonce("Clay", "Douai", imageToByteArray(R.drawable.mat3, c), 7.88, "Quality clay for sale! Ideal for pottery, sculpting, and crafting projects, our premium-grade clay offers versatility and excellent molding properties. Sourced from reliable suppliers, our clay is carefully processed to ensure consistency and workability. Whether you're a professional artist or a hobbyist, our clay is perfect for bringing your creative visions to life. Available in various quantities, we cater to both small-scale and large-scale projects. Order online for convenient delivery to your doorstep. Elevate your crafting experience with our top-quality clay. Contact us today to place your order and unleash your artistic potential!", "0123456789", "test@gmail.com"));
        insert(new Annonce("Metal", "Lyon", imageToByteArray(R.drawable.mat4, c), 12.99, "Discover the perfect metal for your projects! Our high-quality metal is meticulously crafted to meet the demands of various industries and applications. Whether you're in construction, manufacturing, or metalworking, our premium-grade metal offers unparalleled strength, durability, and versatility. From stainless steel to aluminum and everything in between, we provide a wide range of metal options to suit your specific needs. Our metals are sourced from trusted suppliers and undergo rigorous quality checks to ensure superior performance. Conveniently order online and have it delivered to your location. Elevate your projects with our exceptional metal products. Contact us today to place your order and experience the difference!", "0123456789", "test@gmail.com"));
        insert(new Annonce("Glass", "Valenciennes", imageToByteArray(R.drawable.mat5, c), 12.0, "Explore the brilliance of our premium glass products! Crafted with precision and quality in mind, our glass offerings are perfect for a variety of applications. Whether you're in need of durable windows for your home, sleek glass tabletops for your office, or intricate glassware for your dining table, we have you covered. Our extensive range includes tempered glass, laminated glass, decorative glass, and more, ensuring we meet your specific requirements. Sourced from trusted manufacturers, our glass products undergo stringent quality control measures to guarantee clarity, strength, and safety. Order online for hassle-free delivery to your desired location. Elevate your spaces with our exquisite glass solutions. Contact us today to place your order and bring sophistication to your projects!", "0123456789", "test@gmail.com"));
        insert(new Annonce("Wood", "Orchies", imageToByteArray(R.drawable.mat6, c), 3, "Experience the unparalleled warmth and charm of Orchies wood – the epitome of quality and elegance. Our carefully curated selection of Orchies wood is renowned for its superior craftsmanship and timeless appeal. Sourced from sustainably managed forests in the scenic city of Orchies, France, our wood exudes a unique character and richness that sets it apart. Whether you're crafting bespoke furniture or enhancing your living space with exquisite wood accents, Orchies wood promises unmatched beauty and durability. Elevate your projects with the finest Orchies wood and indulge in the rustic allure of this picturesque city. Transform your space with Orchies wood – where timeless elegance meets sustainable craftsmanship.", "0123456789", "test@gmail.com"));
    }

    //Convert drawable image to byteArray
    public byte[] imageToByteArray(int img, Context c) {
        // Convert image to Bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(c.getResources(), img);

        //Convert Bitmap to byte array
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);

        return stream.toByteArray();
    }

    public void insert(Annonce ad) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.TITLE, ad.getTitre());
        contentValue.put(DBHelper.ADDRESS, ad.getAdresse());
        contentValue.put(DBHelper.IMAGE, ad.getImage());
        contentValue.put(DBHelper.PRICE, ad.getPrix());
        contentValue.put(DBHelper.DESCRIPTION, ad.getDescription());
        contentValue.put(DBHelper.TELEPHONE, ad.getNumeroTelephone());
        contentValue.put(DBHelper.EMAIL, ad.getEmail());

        database.insert(DBHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DBHelper._ID, DBHelper.TITLE, DBHelper.ADDRESS, DBHelper.IMAGE, DBHelper.PRICE, DBHelper.DESCRIPTION, DBHelper.TELEPHONE, DBHelper.EMAIL};
        Cursor cursor = database.query(DBHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, Annonce ad) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.TITLE, ad.getTitre());
        contentValues.put(DBHelper.ADDRESS, ad.getAdresse());
        contentValues.put(DBHelper.IMAGE, ad.getImage());
        contentValues.put(DBHelper.PRICE, ad.getPrix());
        contentValues.put(DBHelper.DESCRIPTION, ad.getDescription());
        contentValues.put(DBHelper.TELEPHONE, ad.getNumeroTelephone());
        contentValues.put(DBHelper.EMAIL, ad.getEmail());

        int i = database.update(DBHelper.TABLE_NAME, contentValues, DBHelper._ID + " = " + _id, null);

        return i;
    }

    public void delete(long _id) {
        database.delete(DBHelper.TABLE_NAME, DBHelper._ID + "=" + _id, null);
    }

    public Annonce getById(int id){
        return dbHelper.getById(id);
    }

    public ArrayList<Annonce> getAll(){
        ArrayList<Annonce> annonces = new ArrayList<Annonce>();

        Cursor cursor = this.fetch();

        if(cursor == null || cursor.getCount() == 0){
            return annonces;
        }

        do {
            String titre = cursor.getString(abs(cursor.getColumnIndex(DBHelper.TITLE)));
            String adresse = cursor.getString(abs(cursor.getColumnIndex(DBHelper.ADDRESS)));
            byte[] image = cursor.getBlob(abs(cursor.getColumnIndex(DBHelper.IMAGE)));
            double prix = cursor.getDouble(abs(cursor.getColumnIndex(DBHelper.PRICE)));
            String description = cursor.getString(abs(cursor.getColumnIndex(DBHelper.DESCRIPTION)));
            String telephone = cursor.getString(abs(cursor.getColumnIndex(DBHelper.TELEPHONE)));
            String email = cursor.getString(abs(cursor.getColumnIndex(DBHelper.EMAIL)));

            annonces.add(new Annonce(titre, adresse, image, prix, description, telephone, email));
        } while (cursor.moveToNext());

        return annonces;
    }

}

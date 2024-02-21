package com.example.tpleboncoin.db;

// Package + Import

import static java.lang.Math.abs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.tpleboncoin.models.Annonce;

import java.util.ArrayList;

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

    public DBManager open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();

        //S'il n'y a pas d'annonce, on initialise une liste d'annonces
        Cursor cursor = fetch();
        if(cursor != null && cursor.getCount() == 0){
            init();
        }

        return this;
    }

    public void close() {
        dbHelper.close();
    }

    // Add ads manually.
    public void init(){
        insert(new Annonce("Wood", "Douai", "https://media.istockphoto.com/id/134253640/photo/construction-of-a-wooden-roof-frame-underway.jpg?s=612x612&w=0&k=20&c=e5gUkic9LGQWahIdHozOsEzHKy_HtsmvmtOHmYsejSU=",24.99, "High-quality seasoned firewood for sale! Perfect for cozy nights by the fireplace or powering your wood-burning stove. Our carefully selected wood is sourced sustainably, ensuring both quality and environmental responsibility. Available for delivery to your doorstep, our firewood is split and ready to use, saving you time and effort. Whether you're a homeowner, camper, or outdoor enthusiast, our firewood is ideal for all your needs. Stock up now and enjoy the warmth and ambiance of natural wood burning. Don't miss out on this opportunity to elevate your fire experience. Contact us today to place your order!"));
        insert(new Annonce("Steel", "Lille", "https://as2.ftcdn.net/v2/jpg/03/91/83/87/1000_F_391838708_4HFADW5beay2VVlnoual6Qi5fWeIaD9V.jpg", 18.9, "Premium steel available for sale! Engineered for strength and durability, our high-grade steel is suitable for a wide range of applications. Whether you're a contractor, builder, or DIY enthusiast, our steel products are perfect for construction projects, fabrication, or repairs. With various sizes and shapes available, we cater to diverse needs and specifications. Our steel undergoes rigorous quality checks to ensure top-notch performance and reliability. Conveniently order online and have it delivered to your location. Upgrade your projects with our superior steel today. Contact us now to place your order and experience the difference!"));
        insert(new Annonce("Clay", "Douai", "https://constrofacilitator.com/wp-content/uploads/2020/02/clay-in-construction.jpg", 7.88, "Quality clay for sale! Ideal for pottery, sculpting, and crafting projects, our premium-grade clay offers versatility and excellent molding properties. Sourced from reliable suppliers, our clay is carefully processed to ensure consistency and workability. Whether you're a professional artist or a hobbyist, our clay is perfect for bringing your creative visions to life. Available in various quantities, we cater to both small-scale and large-scale projects. Order online for convenient delivery to your doorstep. Elevate your crafting experience with our top-quality clay. Contact us today to place your order and unleash your artistic potential!"));
        insert(new Annonce("Metal", "Lyon", "https://www.meto-constructions.fr/wp-content/uploads/2018/12/IMG_6067.jpg", 12.99, "Discover the perfect metal for your projects! Our high-quality metal is meticulously crafted to meet the demands of various industries and applications. Whether you're in construction, manufacturing, or metalworking, our premium-grade metal offers unparalleled strength, durability, and versatility. From stainless steel to aluminum and everything in between, we provide a wide range of metal options to suit your specific needs. Our metals are sourced from trusted suppliers and undergo rigorous quality checks to ensure superior performance. Conveniently order online and have it delivered to your location. Elevate your projects with our exceptional metal products. Contact us today to place your order and experience the difference!"));
        insert(new Annonce("Glass", "Valenciennes", "https://i0.wp.com/www.tipsnepal.com/wp-content/uploads/2020/09/simple-float-glass-1505049573-3306125.jpeg?resize=500%2C317&quality=100&strip=all&ssl=1", 12.0, "Explore the brilliance of our premium glass products! Crafted with precision and quality in mind, our glass offerings are perfect for a variety of applications. Whether you're in need of durable windows for your home, sleek glass tabletops for your office, or intricate glassware for your dining table, we have you covered. Our extensive range includes tempered glass, laminated glass, decorative glass, and more, ensuring we meet your specific requirements. Sourced from trusted manufacturers, our glass products undergo stringent quality control measures to guarantee clarity, strength, and safety. Order online for hassle-free delivery to your desired location. Elevate your spaces with our exquisite glass solutions. Contact us today to place your order and bring sophistication to your projects!"));
        insert(new Annonce("Wood", "Orchies", "https://yieldpro.com/wp-content/uploads/2020/08/lumber1.jpg", 3, "Experience the unparalleled warmth and charm of Orchies wood – the epitome of quality and elegance. Our carefully curated selection of Orchies wood is renowned for its superior craftsmanship and timeless appeal. Sourced from sustainably managed forests in the scenic city of Orchies, France, our wood exudes a unique character and richness that sets it apart. Whether you're crafting bespoke furniture or enhancing your living space with exquisite wood accents, Orchies wood promises unmatched beauty and durability. Elevate your projects with the finest Orchies wood and indulge in the rustic allure of this picturesque city. Transform your space with Orchies wood – where timeless elegance meets sustainable craftsmanship."));
    }

    public void insert(Annonce ad) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.TITLE, ad.getTitre());
        contentValue.put(DBHelper.ADDRESS, ad.getAdresse());
        contentValue.put(DBHelper.IMAGE, ad.getImage());
        contentValue.put(DBHelper.PRICE, ad.getPrix());
        contentValue.put(DBHelper.DESCRIPTION, ad.getDescription());
        database.insert(DBHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DBHelper._ID, DBHelper.TITLE, DBHelper.ADDRESS, DBHelper.IMAGE, DBHelper.PRICE, DBHelper.DESCRIPTION};
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
            String image = cursor.getString(abs(cursor.getColumnIndex(DBHelper.IMAGE)));
            double prix = cursor.getDouble(abs(cursor.getColumnIndex(DBHelper.PRICE)));
            String description = cursor.getString(abs(cursor.getColumnIndex(DBHelper.DESCRIPTION)));

            annonces.add(new Annonce(titre, adresse, image, prix, description));
        } while (cursor.moveToNext());

        return annonces;
    }

}

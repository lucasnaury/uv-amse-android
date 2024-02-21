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
        //init(); // Useful for adding ads for the first time.
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
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    // Add ads manually.
    public void init(){
        open();
        insert(new Annonce("Wood", "Douai", "https://media.istockphoto.com/id/134253640/photo/construction-of-a-wooden-roof-frame-underway.jpg?s=612x612&w=0&k=20&c=e5gUkic9LGQWahIdHozOsEzHKy_HtsmvmtOHmYsejSU=",24.99));
        insert(new Annonce("Steel", "Lille", "https://as2.ftcdn.net/v2/jpg/03/91/83/87/1000_F_391838708_4HFADW5beay2VVlnoual6Qi5fWeIaD9V.jpg", 18.9));
        insert(new Annonce("Clay", "Douai", "https://constrofacilitator.com/wp-content/uploads/2020/02/clay-in-construction.jpg", 7.88));
        insert(new Annonce("Metal", "Lyon", "https://www.meto-constructions.fr/wp-content/uploads/2018/12/IMG_6067.jpg", 12.99));
        insert(new Annonce("Glass", "Valenciennes", "https://i0.wp.com/www.tipsnepal.com/wp-content/uploads/2020/09/simple-float-glass-1505049573-3306125.jpeg?resize=500%2C317&quality=100&strip=all&ssl=1", 12.0));
        insert(new Annonce("Wood", "Orchies", "https://yieldpro.com/wp-content/uploads/2020/08/lumber1.jpg", 3));
    }

    public void insert(Annonce ad) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.TITLE, ad.getTitre());
        contentValue.put(DBHelper.ADDRESS, ad.getAdresse());
        contentValue.put(DBHelper.IMAGE, ad.getImage());
        contentValue.put(DBHelper.PRICE, ad.getPrix());
        database.insert(DBHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DBHelper._ID, DBHelper.TITLE, DBHelper.ADDRESS, DBHelper.IMAGE};
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

        if (cursor.moveToFirst()) {
            do {
                String titre = cursor.getString(abs(cursor.getColumnIndex(DBHelper.TITLE)));
                String adresse = cursor.getString(abs(cursor.getColumnIndex(DBHelper.ADDRESS)));
                String image = cursor.getString(abs(cursor.getColumnIndex(DBHelper.IMAGE)));
                double prix = cursor.getDouble(abs(cursor.getColumnIndex(DBHelper.PRICE)));

                annonces.add(new Annonce(titre, adresse, image, prix));
            } while (cursor.moveToNext());
        }

        return annonces;
    }

}

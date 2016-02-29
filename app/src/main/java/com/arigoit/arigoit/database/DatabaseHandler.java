package com.arigoit.arigoit.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.arigoit.arigoit.model.ProductInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dhiraj on 28-02-2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "productsManager";

    // Contacts table name
    private static final String TABLE_PRODUCTS = "products";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PRICE = "price";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + KEY_NAME + " TEXT,"
                + KEY_PRICE + "  INTEGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);

        // Create tables again
        onCreate(db);
    }

    public void addProduct(ProductInfo productInfo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, productInfo.getName()); // Contact Name
        values.put(KEY_PRICE, productInfo.getPrice()); // Contact Phone Number

        // Inserting Row
        db.insert(TABLE_PRODUCTS, null, values);
        db.close(); // Closing database connection
    }

    public void addProducts(ArrayList<ProductInfo> productInfos) {
        SQLiteDatabase db = this.getWritableDatabase();
        for(ProductInfo productInfo : productInfos) {
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, productInfo.getName());
            values.put(KEY_PRICE, productInfo.getPrice());
            // Inserting Row
            db.insert(TABLE_PRODUCTS, null, values);
        }
        db.close(); // Closing database connection
    }

    public ArrayList<ProductInfo> searchProduct(String searchString) {
        ArrayList<ProductInfo> productInfos = new ArrayList<ProductInfo>();
        // Select All Query
//        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS + "WHERE" + " " + KEY_NAME + "LIKE" + "'%" + searchString + "%'";
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS + " WHERE " + KEY_NAME + " LIKE '%" + searchString + "%'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,  null);
        try {
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    ProductInfo productInfo = new ProductInfo(
                            cursor.getString(1),
                            Float.valueOf(cursor.getString(2))
                    );
                    productInfos.add(productInfo);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
            db.close();
        }

        return productInfos;
    }

    public ArrayList<ProductInfo> getAllProducts() {
        ArrayList<ProductInfo> productInfos = new ArrayList<ProductInfo>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        try {
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    ProductInfo productInfo = new ProductInfo(
                            cursor.getString(1),
                            Float.valueOf(cursor.getString(2))
                    );
                    productInfos.add(productInfo);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
            db.close();
        }

        return productInfos;
    }

    public int getProductsCount() {
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        try {
            if (cursor != null) {
                return cursor.getCount();
            } else {
                return 0;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
            db.close();
        }
    }

}
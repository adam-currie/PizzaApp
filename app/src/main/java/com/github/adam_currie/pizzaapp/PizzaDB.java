package com.github.adam_currie.pizzaapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Adam on 2016-03-09.
 */
public class PizzaDB {
    public static final String DB_NAME = "pizza-app.db";
    public static final int DB_VERSION = 1;

    public static final String  SIDE_TABLE = "Side";
    public static final String  SIDE_NAME = "name";
    public static final int     SIDE_NAME_COL = 0;
    public static final String  SIDE_PRICE = "price";//price in cents
    public static final int     SIDE_PRICE_COL = 1;
    public static final String  SIDE_DETAILS = "details";
    public static final int     SIDE_DETAILS_COL = 2;

    public static final String CREATE_SIDE_TABLE =
            "CREATE TABLE " + SIDE_TABLE + " (" +
                    SIDE_NAME + " TEXT PRIMARY KEY, " +
                    SIDE_PRICE + " INTEGER, " + //price in cents
                    SIDE_DETAILS + " TEXT)";

    public static final String DROP_SIDE_TABLE =
            "DROP TABLE IF EXISTS " + SIDE_TABLE;

    private PizzaDBHelper dbHelper;

    public PizzaDB(Context context) {
        dbHelper = new PizzaDBHelper(context, DB_NAME, null, DB_VERSION);
    }

    public ArrayList<Side> getSides() {
        SQLiteDatabase readableDb = dbHelper.getReadableDatabase();
        ArrayList<Side> sides = new ArrayList<Side>();

        Cursor cursor = readableDb.query(
                SIDE_TABLE,
                null,
                null,
                null,
                null,
                null,
                null
        );
        if(cursor.moveToFirst()){
            do {
                sides.add(new Side(
                        cursor.getString(SIDE_NAME_COL),
                        cursor.getLong(SIDE_PRICE_COL),
                        cursor.getString(SIDE_DETAILS_COL)
                ));
            }while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        readableDb.close();
        return sides;
    }


    private static class PizzaDBHelper extends SQLiteOpenHelper {

        public PizzaDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //create Side table
            db.execSQL(CREATE_SIDE_TABLE);

            //populate Side table
            db.execSQL("INSERT INTO " + SIDE_TABLE +
                    " VALUES ('Bread Sticks', 499, 'Garlic Butter bread sticks covered in freshly grated parmesan cheese.')");
            db.execSQL("INSERT INTO " + SIDE_TABLE +
                    " VALUES ('Wings', 699, 'Delicious double deep fried hand breaded wings.')");
            db.execSQL("INSERT INTO " + SIDE_TABLE +
                    " VALUES ('Spaghetti', 599, 'Homemade spaghetti and meatballs in a fresh tasting tomato sauce.')");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i("debug", "Upgrading database from version " + oldVersion + " to " + newVersion + ".");
            //drop old table and recreate
            db.execSQL(DROP_SIDE_TABLE);
            onCreate(db);
        }
    }

}

package com.youcode.Achaoub.brief2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Staff.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "staffs";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NOM = "staff_nom";
    private static final String COLUMN_PRENOM = "staff_prenom";
    private static final String COLUMN_LIEU = "staff_lieu";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NOM + " TEXT, " +
                        COLUMN_PRENOM + " TEXT, " +
                        COLUMN_LIEU + " TEXT);";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addStaff(String nom, String prenom, String lieu){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NOM, nom);
        cv.put(COLUMN_PRENOM, prenom);
        cv.put(COLUMN_LIEU, lieu);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Operation incomplete.", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Ajout fait avec succes.", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void ModicationDonnees(String row_id, String nom, String prenom, String lieu){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NOM, nom);
        cv.put(COLUMN_PRENOM, prenom);
        cv.put(COLUMN_LIEU, lieu);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Operation incomplete.", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Modification faite avec succes.", Toast.LENGTH_SHORT).show();
        }

    }

    void SupprimerEnregistrement(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Operation incomplete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Suppression faite avec succes.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}

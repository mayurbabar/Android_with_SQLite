package com.example.mayur.myfirstapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mayur on 4/11/2018.
 */

public class DBManager extends SQLiteOpenHelper {

    private static final int Db_VERSION = 1;
    private static final String DB_NAME = "users_db";
    private static final String TABLE_NAME = "users_data";
    private static final String KEY_ID = "user_id";
    private static final String KEY_FNAME = "user_fname";
    private static final String KEY_LNAME = "user_lname";
    private static final String KEY_EMAIL = "user_email";
    private static final String KEY_PASS = "user_pass";


    public DBManager(Context context) {
        super(context, DB_NAME, null, Db_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_Q = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + KEY_FNAME + " TEXT," + KEY_LNAME + " TEXT," + KEY_EMAIL + " TEXT," + KEY_PASS + " TEXT )";
        sqLiteDatabase.execSQL(CREATE_TABLE_Q);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addNewUser(UserModel user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FNAME, user.getUserFName());
        values.put(KEY_LNAME, user.getUserLName());
        values.put(KEY_EMAIL, user.getUserEmail());
        values.put(KEY_PASS, user.getUserPassword());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public UserModel retrieveUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * from " + TABLE_NAME + " WHERE " + KEY_EMAIL + " = \""
                + email + "\" AND " + KEY_PASS + " = \"" + password + "\";";
        Cursor cursor = db.rawQuery(query, null);

        UserModel model = new UserModel();

        if (cursor.moveToFirst()) {
            model.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            model.setUserFName(cursor.getString(cursor.getColumnIndex(KEY_FNAME)));
            model.setUserLName(cursor.getString(cursor.getColumnIndex(KEY_LNAME)));
            model.setUserEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
            model.setUserPassword(cursor.getString(cursor.getColumnIndex(KEY_PASS)));
        } else {
            model = null;
        }
        return model;
    }
}

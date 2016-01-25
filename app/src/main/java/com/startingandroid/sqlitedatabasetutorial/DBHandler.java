package com.startingandroid.sqlitedatabasetutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zeeshan Ahmed on 1/25/2016.
 */
public class DBHandler extends SQLiteOpenHelper {

    // Static Variables
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "usersManager";
    private static final String TABLE_USER = "user";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
/*Override this function to create a new table*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);
    }

    /*Override this function to upgrade your table design / structure*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop the old table if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        // Create tables again
        onCreate(db);
    }
/*addUser() will add a new User to database*/
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_EMAIL, user.getEmail());

        db.insert(TABLE_USER, null, values); //Insert query to store the record in the database
        db.close();
    }

    /*getUser() will return he user's object if id matches*/
    public User getUser(int user_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER, new String[]{KEY_ID,
                        KEY_NAME, KEY_EMAIL}, KEY_ID + "=?",
                new String[]{String.valueOf(user_id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(cursor.getString(1), cursor.getString(2));
        return user;
    }

    /*getAllUsers() will return the list of all users*/
    public ArrayList<User> getAllUsers() {
        ArrayList<User> usersList = new ArrayList<User>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User(cursor.getString(1), cursor.getString(2));
                usersList.add(user);
            } while (cursor.moveToNext());
        }
        return usersList;
    }
/*getUsersCount() will give the total number of records in the table*/
    public int getUsersCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }
/*updateUser() will be used to update the existing user record*/
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_EMAIL, user.getEmail());
        // updating record
        return db.update(TABLE_USER, values, KEY_ID + " = ?", // update query to make changes to the existing record
                new String[]{String.valueOf(user.getId())});
    }

    /*deleteContact() to delete the record from the table*/
    public void deleteContact(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }
}

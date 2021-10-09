package com.example.cacheit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalit on 9/12/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Data";

    // User table name
    private static final String TABLE_USER = "Information";

    // User Table Columns names
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_CARD = "card";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_PLACE = "place";
    //  private static final String COLUMN_TIME = "time";

    /*DATETIME DEFAULT CURRENT_TIMESTAMP*/
    // create table sql query
    private String CREATE_USER_TABLE =
            "CREATE TABLE " + TABLE_USER + " ("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_AMOUNT + " TEXT,"
                    + COLUMN_DATE + " DATETIME DEFAULT (DATETIME('now','localtime')),"
                    + COLUMN_CARD + " TEXT,"
                    + COLUMN_TYPE + " TEXT,"
                    + COLUMN_PLACE + " TEXT"
                    + ")";


    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);

        // Create tables again
        onCreate(db);

    }

    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(COLUMN_ID, user.getId());
        values.put(COLUMN_AMOUNT, user.getAmount());
        //values.put(COLUMN_DATE, /*user.getDate()*/" time('now') ");
        values.put(COLUMN_CARD, user.getCard());
        values.put(COLUMN_TYPE, user.getType());
        values.put(COLUMN_PLACE, user.getPlace());
        // values.put(COLUMN_TIME, user.getTime());
        // Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        Log.d("id ", String.valueOf(id));
        int count_of_records = getRecordsCount();
        Log.d("records count: ", String.valueOf(count_of_records));
        List<User> users = getAllNotes();
        for (int i = 0; i < users.size(); i++) {
            Log.d("User table:", users.get(i).date + "         " + users.get(i).id + "         " + users.get(i).card + "         " + users.get(i).getAmount() + "         " + users.get(i).getType());
        }

        db.close();
    }

    public List getDepartmentStores() {
        Cursor cursor = null;
        SQLiteDatabase db = this.getWritableDatabase();
        String queryDepartment = "SELECT * FROM Information where " + COLUMN_TYPE + "= 'Department Stores' ";
        cursor = db.rawQuery(queryDepartment, null);
        List<DepartmentCard> departmentCardList = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                DepartmentCard card;
                card = new DepartmentCard(cursor.getString(cursor.getColumnIndex(COLUMN_AMOUNT)), cursor.getString(cursor.getColumnIndex(COLUMN_DATE)), String.valueOf(cursor.getInt(cursor.getColumnIndex(COLUMN_CARD))), String.valueOf(cursor.getColumnIndex(COLUMN_TYPE)));
                departmentCardList.add(card);

            } while (cursor.moveToNext());
            cursor.close();
        }
        return departmentCardList;
    }

    public List getUtilities() {
        Cursor cursor = null;
        SQLiteDatabase db = this.getWritableDatabase();
        String queryUtilities = "SELECT * FROM Information where " + COLUMN_TYPE + "= 'Utilities and Bills' ";
        cursor = db.rawQuery(queryUtilities, null);
        List<UtilitiesCard> utilitiesCardList = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                UtilitiesCard card;
                card = new UtilitiesCard(cursor.getString(cursor.getColumnIndex(COLUMN_AMOUNT)), cursor.getString(cursor.getColumnIndex(COLUMN_DATE)), String.valueOf(cursor.getInt(cursor.getColumnIndex(COLUMN_CARD))), String.valueOf(cursor.getColumnIndex(COLUMN_TYPE)));
                utilitiesCardList.add(card);

            } while (cursor.moveToNext());
            cursor.close();
        }
        return utilitiesCardList;
    }

    public List getFuel() {
        Cursor cursor = null;
        SQLiteDatabase db = this.getWritableDatabase();
        String queryFuel = "SELECT * FROM Information where " + COLUMN_TYPE + "= 'Fuel' ";
        cursor = db.rawQuery(queryFuel, null);
        List<FuelCard> fuelCardList = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                FuelCard card;
                card = new FuelCard(cursor.getString(cursor.getColumnIndex(COLUMN_AMOUNT)), cursor.getString(cursor.getColumnIndex(COLUMN_DATE)), String.valueOf(cursor.getInt(cursor.getColumnIndex(COLUMN_CARD))), String.valueOf(cursor.getColumnIndex(COLUMN_TYPE)));
                fuelCardList.add(card);

            } while (cursor.moveToNext());
            cursor.close();
        }
        return fuelCardList;
    }

    public List getEducation() {
        Cursor cursor = null;
        SQLiteDatabase db = this.getWritableDatabase();
        String queryEducation = "SELECT * FROM Information where " + COLUMN_TYPE + "= 'Education' ";
        cursor = db.rawQuery(queryEducation, null);
        List<EducationCard> educationCardList = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                EducationCard card;
                card = new EducationCard(cursor.getString(cursor.getColumnIndex(COLUMN_AMOUNT)), cursor.getString(cursor.getColumnIndex(COLUMN_DATE)), String.valueOf(cursor.getInt(cursor.getColumnIndex(COLUMN_CARD))), String.valueOf(cursor.getColumnIndex(COLUMN_TYPE)));
                educationCardList.add(card);

            } while (cursor.moveToNext());
            cursor.close();
        }
        return educationCardList;
    }

    public List getLeisure() {
        Cursor cursor = null;
        SQLiteDatabase db = this.getWritableDatabase();
        String queryLeisure = "SELECT * FROM Information where " + COLUMN_TYPE + "= 'Leisure and Entertainment' ";
        cursor = db.rawQuery(queryLeisure, null);
        List<LeisureCard> leisureCardList = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                LeisureCard card;
                card = new LeisureCard(cursor.getString(cursor.getColumnIndex(COLUMN_AMOUNT)), cursor.getString(cursor.getColumnIndex(COLUMN_DATE)), String.valueOf(cursor.getInt(cursor.getColumnIndex(COLUMN_CARD))), String.valueOf(cursor.getColumnIndex(COLUMN_TYPE)));
                leisureCardList.add(card);

            } while (cursor.moveToNext());
            cursor.close();
        }
        return leisureCardList;
    }

    public List getOthers() {
        Cursor cursor = null;
        SQLiteDatabase db = this.getWritableDatabase();
        String queryOthers = "SELECT * FROM Information where " + COLUMN_TYPE + "= 'Others' ";
        cursor = db.rawQuery(queryOthers, null);
        List<OthersCard> othersCardList = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                OthersCard card;
                card = new OthersCard(cursor.getString(cursor.getColumnIndex(COLUMN_AMOUNT)), cursor.getString(cursor.getColumnIndex(COLUMN_DATE)), String.valueOf(cursor.getInt(cursor.getColumnIndex(COLUMN_CARD))), String.valueOf(cursor.getColumnIndex(COLUMN_TYPE)));
                othersCardList.add(card);

            } while (cursor.moveToNext());
            cursor.close();
        }
        return othersCardList;
    }


    public List<RecentCard> getRecent() {
        Cursor cursor = null;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM Information ORDER BY " + COLUMN_DATE + " DESC Limit 10";
        String queryRecent = "SELECT * FROM Information where " + COLUMN_TYPE + "= 'Entertainment' ";
        cursor = db.rawQuery(query, null);
        List<RecentCard> recentCardList = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                RecentCard card = new RecentCard(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)), cursor.getString(cursor.getColumnIndex(COLUMN_AMOUNT)), cursor.getString(cursor.getColumnIndex(COLUMN_DATE)), String.valueOf(cursor.getInt(cursor.getColumnIndex(COLUMN_CARD))), cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)), cursor.getString(cursor.getColumnIndex(COLUMN_PLACE)));
                recentCardList.add(card);

            } while (cursor.moveToNext());
            cursor.close();
        }
        return recentCardList;
    }

    public List<cc1_Card> getcc1(int cc1) {
        Cursor cursor = null;
        SQLiteDatabase db = this.getWritableDatabase();
        //String query = "SELECT * FROM Information ORDER BY date DESC Limit 10";
        String querycc1 = "SELECT * FROM Information where " + COLUMN_CARD + "= " + cc1;
        cursor = db.rawQuery(querycc1, null);
        List<cc1_Card> cc1CardList = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                cc1_Card card = new cc1_Card(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)), cursor.getString(cursor.getColumnIndex(COLUMN_AMOUNT)), cursor.getString(cursor.getColumnIndex(COLUMN_DATE)), String.valueOf(cursor.getInt(cursor.getColumnIndex(COLUMN_CARD))), cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)), cursor.getString(cursor.getColumnIndex(COLUMN_PLACE)));
                cc1CardList.add(card);

            } while (cursor.moveToNext());
            cursor.close();
        }
        return cc1CardList;
    }

    public List<cc2_card> getcc2(int cc2) {
        Cursor cursor = null;
        SQLiteDatabase db = this.getWritableDatabase();
        //String query = "SELECT * FROM Information ORDER BY date DESC Limit 10";
        String querycc2 = "SELECT * FROM Information where " + COLUMN_CARD + "= " + cc2;
        cursor = db.rawQuery(querycc2, null);
        List<cc2_card> cc2CardList = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                cc2_card card = new cc2_card(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)), cursor.getString(cursor.getColumnIndex(COLUMN_AMOUNT)), cursor.getString(cursor.getColumnIndex(COLUMN_DATE)), String.valueOf(cursor.getInt(cursor.getColumnIndex(COLUMN_CARD))), cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)), cursor.getString(cursor.getColumnIndex(COLUMN_PLACE)));
                cc2CardList.add(card);

            } while (cursor.moveToNext());
            cursor.close();
        }
        return cc2CardList;
    }

    public List<cc3_card> getcc3(int cc3) {
        Cursor cursor = null;
        SQLiteDatabase db = this.getWritableDatabase();
        // String query = "SELECT * FROM Information ORDER BY date DESC Limit 10";
        String querycc3 = "SELECT * FROM Information where " + COLUMN_CARD + "= " + cc3;
        cursor = db.rawQuery(querycc3, null);
        List<cc3_card> cc3CardList = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                cc3_card card = new cc3_card(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)), cursor.getString(cursor.getColumnIndex(COLUMN_AMOUNT)), cursor.getString(cursor.getColumnIndex(COLUMN_DATE)), String.valueOf(cursor.getInt(cursor.getColumnIndex(COLUMN_CARD))), cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)), cursor.getString(cursor.getColumnIndex(COLUMN_PLACE)));
                cc3CardList.add(card);

            } while (cursor.moveToNext());
            cursor.close();
        }
        return cc3CardList;
    }

    public List<cc4_card> getcc4(int cc4) {
        Cursor cursor = null;
        SQLiteDatabase db = this.getWritableDatabase();
        // String query = "SELECT * FROM Information ORDER BY date DESC Limit 10";
        String querycc4 = "SELECT * FROM Information where " + COLUMN_CARD + "= " + cc4;
        cursor = db.rawQuery(querycc4, null);
        List<cc4_card> cc4CardList = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                cc4_card card = new cc4_card(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)), cursor.getString(cursor.getColumnIndex(COLUMN_AMOUNT)), cursor.getString(cursor.getColumnIndex(COLUMN_DATE)), String.valueOf(cursor.getInt(cursor.getColumnIndex(COLUMN_CARD))), cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)), cursor.getString(cursor.getColumnIndex(COLUMN_PLACE)));
                cc4CardList.add(card);

            } while (cursor.moveToNext());
            cursor.close();
        }
        return cc4CardList;
    }



    public List<User> getAllNotes() {
        List<User> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USER + " ORDER BY " +
                COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User note = new User();
                note.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                note.setCard(cursor.getString(cursor.getColumnIndex(COLUMN_CARD)));
                note.setAmount(cursor.getString(cursor.getColumnIndex(COLUMN_AMOUNT)));
                note.setType(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)));
                note.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
                note.setPlace(cursor.getString(cursor.getColumnIndex(COLUMN_PLACE)));
                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        //db.close();

        // return notes list
        return notes;
    }


    public int getRecordsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public boolean getRowFromIndex(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PLACE, user.getPlace());
        values.put(COLUMN_TYPE, user.getType());
        // updating row
        db.update(TABLE_USER, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
        return true;
    }

}



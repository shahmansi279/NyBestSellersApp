package com.bestreads;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.bestreads.model.BSImagesContract;


/**
 * Created by Mansi on 1/16/18.
 */

public class BSImageDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION= 1;
    public static final String DATABASE_NAME = "BSImages.db";


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + BSImagesContract.BSImagesEntry.TABLE_NAME + " (" +
                    BSImagesContract.BSImagesEntry._ID + " INTEGER PRIMARY KEY," +
                    BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + " TEXT," +
                    BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " TEXT);";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + BSImagesContract.BSImagesEntry.TABLE_NAME;

    public BSImageDBHelper(Context context){

        super(context,DATABASE_NAME,null, DATABASE_VERSION);
    }

    public void insertImagesOnStartUp(SQLiteDatabase db){

        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('combined-print-and-e-book-fiction','fiction')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('combined-print-and-e-book-nonfiction','non_fiction')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('hardcover-fiction','fiction')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('hardcover-nonfiction','non_fiction')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('trade-fiction-paperback','fiction')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('mass-market-paperback','non_fiction')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('paperback-nonfiction','non_fiction')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('e-book-fiction','fiction')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('e-book-nonfiction','non_fiction')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('hardcover-advice','business')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('paperback-advice','business')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('advice-how-to-and-miscellaneous','business')");

        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('chapter-books','children')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('childrens-middle-grade','children')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('childrens-middle-grade-e-book','children')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('childrens-middle-grade-hardcover','children')");

        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('paperback-books','children')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('picture-books','children')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('series-books','children')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('young-adult','youngadult')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('young-adult-e-book','youngadult')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('young-adult-hardcover','youngadult')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('young-adult-paperback','youngadult')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('hardcover-graphic-books','children')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('paperback-graphic-books','children')");

        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('manga','fiction')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('combined-print-fiction','fiction')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('combined-print-nonfiction','fiction')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('animals','children')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('audio-fiction','fiction')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('audio-nonfiction','non_fiction')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('business-books','business')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('celebrities','fashion')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('crime-and-punishment','crime')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('culture','culture')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('education','education')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('espionage','non_fiction')");


        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('manga','fiction')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('expeditions-disasters-and-adventures','travel')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('fashion-manners-and-customs','fashion')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('food-and-fitness','foodfitness')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('games-and-activities','children')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('hardcover-business-books','business')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('health','health')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('humor','humor')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('indigenous-americans','family')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('relationships','family')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('paperback-business-books','business')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('family','family')");

        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('hardcover-political-books','politics')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('race-and-civil-rights','politics')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('religion-spirituality-and-faith','religion')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('science','non_fiction')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('sports','sports')");
        db.execSQL("INSERT INTO " + BSImagesContract.BSImagesEntry.TABLE_NAME + " ( " +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + "," +  BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_NAME + " ) VALUES ('travel','travel')");



    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        insertImagesOnStartUp(db);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    public String getImageForCategory(String category) {

        SQLiteDatabase db = this.getWritableDatabase();
        String imageName = "";

        try{
            String query = "SELECT * FROM " + BSImagesContract.BSImagesEntry.TABLE_NAME + " where " + BSImagesContract.BSImagesEntry.COLUMN_NAME_IMG_CATEGORY + " = '" + category + "'";

            Cursor cursor = db.rawQuery(query,null);
             if(cursor.getCount() > 0){
                 cursor.moveToFirst();
                 imageName = cursor.getString(2);
             }

             db.close();

        } catch(SQLiteException e){
            e.printStackTrace();
            db.close();
            return imageName;
        }

        return imageName;

    }
}


package project.com.nybestsellerbooksapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

import project.com.nybestsellerbooksapp.model.BSFavoriteBookItem;
import project.com.nybestsellerbooksapp.model.BSFavoritesContract;

/**
 * Created by Mansi on 1/16/18.
 */

public class BSFavoritesDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION= 1;
    public static final String DATABASE_NAME = "BSFavorites.db";


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + BSFavoritesContract.BSFavoriteBookEntry.TABLE_NAME + " (" +
                    BSFavoritesContract.BSFavoriteBookEntry._ID + " INTEGER PRIMARY KEY," +
                    BSFavoritesContract.BSFavoriteBookEntry.COLUMN_NAME_FAV_TITLE + " TEXT," +
                    BSFavoritesContract.BSFavoriteBookEntry.COLUMN_NAME_FAV_AUTHOR + " TEXT," +
                    BSFavoritesContract.BSFavoriteBookEntry.COLUMN_NAME_FAV_ISBN + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + BSFavoritesContract.BSFavoriteBookEntry.TABLE_NAME;

    public BSFavoritesDBHelper(Context context){

        super(context,DATABASE_NAME,null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);


    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over

        if(oldVersion != newVersion){
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void onDelete(SQLiteDatabase db){}


    public List<BSFavoriteBookItem> allFavoriteBooks() {

        List<BSFavoriteBookItem> favBooks = new LinkedList<BSFavoriteBookItem>();
        String query = "SELECT  * FROM " + BSFavoritesContract.BSFavoriteBookEntry.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        try {
            Cursor cursor = db.rawQuery(query, null);
            BSFavoriteBookItem favBook = null;

            if (cursor.moveToFirst()) {
                do {
                    favBook = new BSFavoriteBookItem();
                    favBook.setBookTitle(cursor.getString(1));
                    favBook.setBookAuthor(cursor.getString(2));
                    favBook.setBookIsbn(cursor.getString(3));


                    favBooks.add(favBook);
                } while (cursor.moveToNext());
                db.close();
            }
        } catch(SQLiteException e){
            e.printStackTrace();
            db.close();
        }

        return favBooks;
    }

    public boolean addFavoriteBook(String isbn, String title, String author){

        if(isbn == null || isbn.isEmpty() || title == null || title.isEmpty() || author == null || author.isEmpty())
            return false;

        BSFavoriteBookItem bsFavoriteBookItem = new BSFavoriteBookItem();
        bsFavoriteBookItem.setBookTitle(title);
        bsFavoriteBookItem.setBookIsbn(isbn);
        bsFavoriteBookItem.setBookAuthor(author);
        SQLiteDatabase db = this.getWritableDatabase();

        try{
            ContentValues values = new ContentValues();
            values.put(BSFavoritesContract.BSFavoriteBookEntry.COLUMN_NAME_FAV_TITLE, title);
            //TODO : Remove DB table
            values.put(BSFavoritesContract.BSFavoriteBookEntry.COLUMN_NAME_FAV_AUTHOR,  author);
            values.put(BSFavoritesContract.BSFavoriteBookEntry.COLUMN_NAME_FAV_ISBN, isbn);

            // insert
            db.insert(BSFavoritesContract.BSFavoriteBookEntry.TABLE_NAME,null, values);
            db.close();
        }
        catch(SQLiteException e){
            e.printStackTrace();
            db.close();
            return false;
        }

        return true;
    }


    public boolean isFavoriteBook(String isbn){

        boolean isFav = false;

        if(isbn == null || isbn.isEmpty())
            return isFav;

        SQLiteDatabase db = this.getReadableDatabase();

        try{
            //TODO: Put some try catch here
            String query = " SELECT * FROM "+ BSFavoritesContract.BSFavoriteBookEntry.TABLE_NAME + " where "+ BSFavoritesContract.BSFavoriteBookEntry.COLUMN_NAME_FAV_ISBN +" = "+ isbn;
            Cursor cursor = db.rawQuery(query,null);

            if (cursor.getCount() > 0){
                isFav = true;
            } else{
                isFav = false;
            }

            db.close();

        } catch (SQLiteException e){
            e.printStackTrace();
            db.close();
            return isFav;

        }

        return isFav;

        }


    public boolean removeFavoriteBook(String isbn) {

        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.delete(BSFavoritesContract.BSFavoriteBookEntry.TABLE_NAME, " fav_book_isbn = ?", new String[] { isbn });
            db.close();
        }
        catch(SQLiteException e){
            e.printStackTrace();
            db.close();
            return false;

        }
        return true;
    }
  }


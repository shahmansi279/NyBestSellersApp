package project.com.nybestsellerbooksapp.svc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

import project.com.nybestsellerbooksapp.svc.model.BSFavoriteBookItem;
import project.com.nybestsellerbooksapp.svc.model.BSFavoritesContract;

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
                    BSFavoritesContract.BSFavoriteBookEntry.COLUMN_NAME_FAV_LINK + " TEXT," +
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
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    public List<BSFavoriteBookItem> allFavoriteBooks() {

        List<BSFavoriteBookItem> favBooks = new LinkedList<BSFavoriteBookItem>();
        String query = "SELECT  * FROM " + BSFavoritesContract.BSFavoriteBookEntry.TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        BSFavoriteBookItem favBook = null;

        if (cursor.moveToFirst()) {
            do {
                favBook = new BSFavoriteBookItem();
                favBook.setBookIsbn(cursor.getString(0));
                favBook.setBookTitle(cursor.getString(1));
                favBook.setBookReviewLink(cursor.getString(2));

                favBooks.add(favBook);
            } while (cursor.moveToNext());
        }

        return favBooks;
    }

    public void addFavoriteBook(String mIsbn, String title){

         BSFavoriteBookItem bsFavoriteBookItem = new BSFavoriteBookItem();
         bsFavoriteBookItem.setBookTitle(title);
        // bsFavoriteBookItem.setBookReviewLink(favoriteBookItem.getAmazonProductUrl());
         bsFavoriteBookItem.setBookIsbn(mIsbn);

         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues values = new ContentValues();
         values.put(BSFavoritesContract.BSFavoriteBookEntry.COLUMN_NAME_FAV_TITLE, bsFavoriteBookItem.getBookTitle());
         //TODO : Remove DB table
         //values.put(BSFavoritesContract.BSFavoriteBookEntry.COLUMN_NAME_FAV_LINK,  bsFavoriteBookItem.getBookReviewLink());
         values.put(BSFavoritesContract.BSFavoriteBookEntry.COLUMN_NAME_FAV_ISBN,  bsFavoriteBookItem.getBookIsbn());

        // insert
         db.insert(BSFavoritesContract.BSFavoriteBookEntry.TABLE_NAME,null, values);
         db.close();
    }


    public boolean isFavoriteBook(String isbn){

            SQLiteDatabase db = this.getReadableDatabase();
            boolean isFav = false;

            String query = " SELECT * FROM "+ BSFavoritesContract.BSFavoriteBookEntry.TABLE_NAME + " where "+ BSFavoritesContract.BSFavoriteBookEntry.COLUMN_NAME_FAV_ISBN +" = "+ isbn;
            Cursor cursor = db.rawQuery(query,null);

            if (cursor.getCount() > 0){
                isFav = true;
            } else{
                isFav = false;
            }
            return isFav;

        }


    public boolean removeFavoriteBook(String isbn) {

        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(BSFavoritesContract.BSFavoriteBookEntry.TABLE_NAME, " fav_book_isbn = ?", new String[] { isbn });
        db.close();

        return true;
    }
  }


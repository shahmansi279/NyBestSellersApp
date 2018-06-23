package project.com.nybestsellerbooksapp.svc.model;

import android.provider.BaseColumns;

/**
 * Created by Mansi on 1/16/18.
 */

public final class BSFavoritesContract {

    private BSFavoritesContract(){}


    public static class BSFavoriteBookEntry implements BaseColumns {

        public static final String TABLE_NAME = "fav_book_entry";
        public static final String COLUMN_NAME_FAV_TITLE="fav_book_title";
        public static final String COLUMN_NAME_FAV_LINK="fav_book_review_link";
        public static final String COLUMN_NAME_FAV_ISBN = "fav_book_isbn";
    }
}
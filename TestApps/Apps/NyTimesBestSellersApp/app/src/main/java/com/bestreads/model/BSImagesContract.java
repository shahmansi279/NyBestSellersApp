package com.bestreads.model;

import android.provider.BaseColumns;

/**
 * Created by Mansi on 1/16/18.
 */

public final class BSImagesContract {

    private BSImagesContract(){}


    public static class BSImagesEntry implements BaseColumns {

        public static final String TABLE_NAME = "image_entry";
        public static final String COLUMN_NAME_IMG_CATEGORY="img_category";
        public static final String COLUMN_NAME_IMG_NAME="img_name";

    }
}
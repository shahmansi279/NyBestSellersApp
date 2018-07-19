package project.com.nybestsellerbooksapp;

import android.app.Application;

/**
 * Created by mansshah on 7/4/18.
 */

public class NyBsApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        NyBsClient nyBsClient = NyBsClient.getInstance();

    }
}
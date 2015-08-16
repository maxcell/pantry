package com.example.android.materialtest;

import android.app.Application;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.text.ParseException;

import bolts.AppLink;

/**
 * Created by stavr on 8/15/2015.
 */
public class BaseApplication extends Application {
    private static BaseApplication singleton;

    @Override
    public void onCreate(){
        super.onCreate();
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "BbZ5esE5qeTKLI5P2a6aid862WtHvM590BKLfbMS", "fEzwzYSVs8RAhrB9gmaXjOIg2DKR6lV7yoD8MU4W");
        ParseObject.registerSubclass(ParsePantry.class);


        singleton = this;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public BaseApplication getInstance(){
        return singleton;
    }
}
package com.example.android.materialtest;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by jtmyles on 8/10/15.
 * To be used with fragment that implements CursorLoader.
 * Communicates with SQLite database that stores JSON data
 */
public class GroceryService extends IntentService {

    public GroceryService() {
        super("GroceryService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}

package com.example.android.materialtest;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Prince on 7/28/15.
 */

// A Future class that might need to be taken advantage of to put items into
// Definitely will come back to this
public class GroceryStore {

    private TreeMap<String, ArrayList<String>> categories;
    private static GroceryStore mInstance = null;



    public static GroceryStore getInstance(){
        if(mInstance == null)
        {
            mInstance = new GroceryStore();
        }
        return mInstance;
    }

    public TreeMap<String, ArrayList<String>> getCategories() {
        return categories;
    }

    public void setCategories(TreeMap<String, ArrayList<String>> categories) {
        this.categories = categories;
    }
}

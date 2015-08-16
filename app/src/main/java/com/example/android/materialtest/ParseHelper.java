package com.example.android.materialtest;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import com.parse.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prince on 8/15/15.
 */
public class ParseHelper {


    public static ArrayList<String> getUserData(){
        return loadFromParse();
    }

    // Load user data from parse
    private static ArrayList<String> loadFromParse() {

        final ArrayList<String> userList = new ArrayList<>();

        // Make a query, where the items belong to the user and return an arraylist of the items
        ParseQuery<ParsePantry> query = ParsePantry.getQuery();
        query.fromLocalDatastore();
        query.whereEqualTo("author", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<ParsePantry>() {
            public void done(List<ParsePantry> pantryItems, ParseException e) {
                if (e == null) {

                    for(ParsePantry item : pantryItems){

                        userList.add(item.getTitle());

                    }

                } else {
                    Log.i("TodoListActivity",
                            "loadFromParse: Error finding pinned todos: "
                                    + e.getMessage());
                }
            }
        });

        return userList;
    }

    public static void deleteFromParse(String item) {

        ParseQuery<ParsePantry> query = ParsePantry.getQuery();
        query.fromLocalDatastore();
        query.whereEqualTo("title", item);
        query.findInBackground(new FindCallback<ParsePantry>() {
            public void done(List<ParsePantry> pantryItems, ParseException e) {
                if (e == null) {

                    for (ParsePantry item : pantryItems) {

                        try {
                            item.delete();
                        }catch (ParseException ex){
                            ex.printStackTrace();
                        }
                    }

                    FragmentGroceryList.mAdapter.notifyDataSetChanged();

                } else {
                    Log.i("TodoListActivity",
                            "loadFromParse: Error finding pinned todos: "
                                    + e.getMessage());
                }
            }
        });
    }
}

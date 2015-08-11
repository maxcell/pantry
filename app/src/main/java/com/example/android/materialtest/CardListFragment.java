package com.example.android.materialtest;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Prince on 7/28/15.
 * Access MainActivity.jsonString added 8/11/15
 */
public class CardListFragment extends Fragment {


    public interface ITalkToFragment { //the fragment to which this interface communicates implements CursorLoader
        void handleOutputStream(OutputStream stream);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Setting up the RecyclerView with the given view
        View view = inflater.inflate(R.layout.fragment_cardlist, container, false);
        RecyclerView recList = (RecyclerView) view.findViewById(R.id.Recycler_CardList);
        recList.setLayoutManager(new LinearLayoutManager(super.getActivity()));
        recList.setHasFixedSize(true);


        // Our Object that takes in a map for the constructor
        GroceryStore groceryStore = GroceryStore.getInstance();
        groceryStore.setCategories((TreeMap) parseJSON());

        // Makes use of the parsing method
        // Will sort a sorted Map into here
//        Map<String, ArrayList<String>> categories = parseJSON();


        // Makes us use the CardListAdapter
        // How we will convert the information for the app
        recList.setAdapter(new CardListAdapter(getActivity(), groceryStore));

        // Return the view of our fragment
        return view;
    }

    // An accessory method that will do the reading of the file for us
    // Will only open it up and store everything and pass the built string
    // to parseJSON method

    private String readFile(){

        String json;
        try{
            InputStream is = getActivity().getAssets().open("grocerydata.json");

            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch(IOException ex){
            ex.printStackTrace();
            return null;
        }

        return json;
    }


    // Method that will take in the string built from readFile
    // It will do the parsing for us and storing the JSON into a TreeMap
    private Map<String, ArrayList<String>> parseJSON(){
        Map<String, ArrayList<String>> categories = new TreeMap<>();
        try{

            // Build a JSON Object from the file
            //JSONObject obj = new JSONObject(readFile());

            // Build a JSON Object from the MainActivity input
            JSONObject obj = new JSONObject(MainActivity.jsonString);

            // Read the array from the object
            JSONArray mJSONArray = obj.getJSONArray("groceries");

            // Get each title of the category and the containing items
            // Store the title in a String and the items in an ArrayList
            for(int i = 0; i < mJSONArray.length(); i++){
                ArrayList<String> itemList = new ArrayList<>();

                // Get each object (containing title and items)
                JSONObject inArray = mJSONArray.getJSONObject(i);

                // Store the title
                String title = inArray.getString("category");

                JSONArray categoryList = inArray.getJSONArray("items");

                // Store the items
                for(int j = 0; j < categoryList.length(); j++){
                    itemList.add(categoryList.getString(j));
                }

                // Add them onto the TreeMap to keep them sorted
                categories.put(title, itemList);
            }

        }catch(JSONException e){
            e.printStackTrace();
        }

        finally {

            // Return the resulting map
            return categories;
        }

    }
}

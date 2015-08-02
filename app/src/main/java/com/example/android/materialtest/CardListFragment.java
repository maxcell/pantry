package com.example.android.materialtest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Prince on 7/28/15.
 */
public class CardListFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Setting up the RecyclerView with the given view
        View view = inflater.inflate(R.layout.fragment_cardlist, container, false);
        RecyclerView recList = (RecyclerView) view.findViewById(R.id.Recycler_View);
        recList.setLayoutManager(new LinearLayoutManager(super.getActivity()));
        recList.setHasFixedSize(true);



        // Hard Coded Data so far
        // Adds content onto the ArrayList
//        ArrayList<String> categories = setupList();

        Map<String, ArrayList<String>> categories = parseJSON();


        // Makes us use the CardListAdapter
        // How we will convert the information for the app
        recList.setAdapter(new CardListAdapter(categories));

        // Return the view of our fragment
        return view;
    }

    // Function used to create our categorical list
    private ArrayList<String> setupList(){
        ArrayList<String> categories = new ArrayList<>();

//        Not sure how I feel about this one yet
//        categories.add("Baby Items");

//        categories.add("Bakery");
//        categories.add("Beverages");
//        categories.add("Cereal");
//        categories.add("Deli");
//        categories.add("Fruits");
//        categories.add("Vegetables");
//        categories.add("Meat");
//        categories.add("Grains");
//        categories.add("Dairy");

        Map<String, ArrayList<String>> category = parseJSON();
        for(int i = 0; i < category.keySet().size(); i++){
            categories.add((String) (category.keySet().toArray())[i]);
        }


        // Sort the contents
        Collections.sort(categories);

        // Add this last to keep any user added items out of the way
        categories.add("Miscellaneous");

//        Log.v("INSIDE", (String) (category.keySet().toArray())[0]);

        return categories;
    }


    private String readJSON(){

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

    private Map<String, ArrayList<String>> parseJSON(){
        Map<String, ArrayList<String>> categories = new TreeMap<>();
        try{
            JSONObject obj = new JSONObject(readJSON());
            JSONArray mJSONArray = obj.getJSONArray("groceries");




            for(int i = 0; i < mJSONArray.length(); i++){
                ArrayList<String> itemList = new ArrayList<>();
                JSONObject inArray = mJSONArray.getJSONObject(i);
                String title = inArray.getString("category");

                JSONArray categoryList = inArray.getJSONArray("items");

                for(int j = 0; j < categoryList.length(); j++){
                itemList.add(categoryList.getString(j));
                }

                categories.put(title, itemList);
            }

        }catch(JSONException e){
            e.printStackTrace();
        }

        finally {

            return categories;
        }

    }
}

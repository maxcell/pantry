package com.example.android.materialtest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;

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
        ArrayList<String> categories = setupList();


        // Makes us use the CardListAdapter
        // How we will convert the information for the app
        recList.setAdapter(new CardListAdapter(categories));

        // Return the view of our fragment
        return view;
    }

    // Function used to create our categorical list
    private ArrayList<String> setupList(){
        ArrayList<String> categories = new ArrayList<>();

        categories.add("Baby Items");
        categories.add("Bread/Bakery");
        categories.add("Beverages");
        categories.add("Cereal");
        categories.add("Deli");
        categories.add("Fruits");
        categories.add("Vegetables");
        categories.add("Meat");
        categories.add("Grains");
        categories.add("Dairy");

        // Sort the contents
        Collections.sort(categories);

        // Add this last to keep any user added items out of the way
        categories.add("Miscellaneous");

        return categories;
    }
}

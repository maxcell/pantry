package com.example.android.materialtest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Prince on 7/28/15.
 */
public class CardFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Setting up the RecyclerView
        View view = inflater.inflate(R.layout.fragment_cardlist, container, false);




        RecyclerView recList = (RecyclerView) view.findViewById(R.id.Recycler_View);
        recList.setLayoutManager(new LinearLayoutManager(super.getActivity()));
        recList.setHasFixedSize(true);





        ArrayList<String> categories = setupList();


        recList.setAdapter(new MyAdapter(categories));

        return view;
    }

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
        categories.add("Miscellaneous");

        return categories;
    }
}

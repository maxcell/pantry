package com.example.android.pantry;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends ActionBarActivity {


    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    private ArrayList<String> parentItems = new ArrayList<String>();
    private ArrayList<Object> childItems  = new ArrayList<Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Get the listView
        expListView = (ExpandableListView) findViewById(R.id.list);

        // Prepare the list Data
        addListData();

        listAdapter = new MyExpandableAdapter(this, listDataHeader, listDataChild);


        // Setting listAdapter
        expListView.setAdapter(listAdapter);

    }

    private void addListData(){

        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        // Adding Header Data
        listDataHeader.add("Fruit");
        listDataHeader.add("Vegetables");
        listDataHeader.add("Dairy");

        // Fruit Group
        List<String> fruit = new ArrayList<>();
        fruit.add("Apple");
        fruit.add("Banana");
        fruit.add("Cherry");
        fruit.add("Strawberry");
        fruit.add("Orange");

        // Vegetables Group
        List<String> vegies = new ArrayList<>();
        vegies.add("Brussel Sprouts");
        vegies.add("Broccoli");
        vegies.add("Okra");
        vegies.add("Celery");
        vegies.add("Cabbage");
        vegies.add("Asparagus");


        // Dairy Group
        List<String> dairy = new ArrayList<>();
        dairy.add("Milk");
        dairy.add("Cheese");
        dairy.add("Yogurt");


        listDataChild.put(listDataHeader.get(0), fruit);
        listDataChild.put(listDataHeader.get(1), vegies);
        listDataChild.put(listDataHeader.get(2), dairy);
    }


}

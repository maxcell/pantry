package com.example.android.materialtest;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar();

        RecyclerView recList = (RecyclerView) findViewById(R.id.Recycler_View);
        recList.setHasFixedSize(true);


        recList.setLayoutManager(new LinearLayoutManager(this));


        ArrayList<String> categories = setupList();


        recList.setAdapter(new MyAdapter(categories));

    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Show menu icon
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private ArrayList<String> setupList(){
        ArrayList<String> categories = new ArrayList<>();

        categories.add("Fruits");
        categories.add("Vegetables");
        categories.add("Grains");
        categories.add("Dairy");

        return categories;
    }
}
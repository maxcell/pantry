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

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new CardFragment(), "fragment_cardlist")
                .commit();


    }

    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate the menu, which will add items to the action bar if present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Pantry");
        setSupportActionBar(toolbar);
        // Show menu icon
//        final ActionBar ab = getSupportActionBar();
//        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
//        ab.setDisplayHomeAsUpEnabled(true);
    }

}
package com.example.android.materialtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Standard Necessities for the app
        super.onCreate(savedInstanceState);

        // Shows the Main Activity (just background at first)
        setContentView(R.layout.activity_main);

        // Sets up Toolbar with these components:
        // (Currently) Name, Location, Settings
        setupToolbar();

        // Loads in our Fragment
        // CardList (first)
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new CardListFragment(), "fragment_cardlist")
                .commit();


    }

    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu, which will add items to the action bar if present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // Function meant to setup the ToolBar for our application
    // Looks at the Toolbar to get an idea of background
    private void setupToolbar(){
        // Finds layout in the res/layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        // Makes sure the Title is correct
        toolbar.setTitle("Pantry");


        // Puts our toolbar on
        setSupportActionBar(toolbar);
    }

}
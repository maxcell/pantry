package com.example.android.materialtest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Prince on 8/2/15.
 */
public class TotalListActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        // Standard Necessities for the app
        super.onCreate(savedInstanceState);

        // Shows the Main Activity (just background at first)
        setContentView(R.layout.total_list);
        setupToolbar();



        RecyclerView recList = (RecyclerView) findViewById(R.id.Recycler_ItemList);
        recList.addItemDecoration(new TotalDividerItemDecoration(getBaseContext().getApplicationContext()));
        recList.setHasFixedSize(true);
        LinearLayoutManager mRecentLayoutManager = new LinearLayoutManager(this);
        recList.setLayoutManager(mRecentLayoutManager);

        recList.setAdapter(new ItemListAdapter(getBaseContext(), GroceryStore.getInstance(), getIntent().getExtras().getString("category")));


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu, which will add items to the action bar if present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

//        if(id == R.id.action_settings){
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    // Function meant to setup the ToolBar for our application
    // Looks at the Toolbar to get an idea of background
    private void setupToolbar() {
        // Finds layout in the res/layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        // Makes sure the Title is correct
        toolbar.setTitle(getIntent().getExtras().getString("category"));


        // Puts our toolbar on
        setSupportActionBar(toolbar);
    }
}


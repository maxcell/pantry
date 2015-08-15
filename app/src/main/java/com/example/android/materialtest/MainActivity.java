package com.example.android.materialtest;

import android.content.Context;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import tabs.SlidingTabLayout;


public class MainActivity extends AppCompatActivity implements CardListFragment.ITalkToFragment{

    private ViewPager mPager;
    private ViewPagerAdapter adapter;
    private SlidingTabLayout mTabs;
    private CharSequence titles[] = {"Stores", "List"};
    int n = 2;      // Where n is the number of tabs
    public static String jsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Standard Necessities for the app
        super.onCreate(savedInstanceState);

        // Shows the Main Activity (just background at first)
        setContentView(R.layout.activity_main);

        // Sets up Toolbar with these components:
        // (Currently) Name, Location, Settings
        setupToolbar();


            mPager = (ViewPager) findViewById(R.id.pager);
            adapter = new ViewPagerAdapter(getSupportFragmentManager(), titles, n, this);
            mPager.setAdapter(adapter);

            mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
            mTabs.setCustomTabView(R.layout.custom_tab, 0);
            mTabs.setDistributeEvenly(true);

            mTabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {

                public int getIndicatorColor(int position) {
                    return getResources().getColor(R.color.my_secondary_text);
                }
            });

        if (!((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE)) {
            mTabs.setViewPager(mPager);
        }else{
            if (getResources().getConfiguration().orientation == 2){

            } else {
                mTabs.setViewPager(mPager);
            }
        }

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        ParseObject.registerSubclass(ParsePantry.class);
        Parse.initialize(this, "Ntyum0Q9BoJ5TPTumeztgJTNM7ErmYhdfYVDe2lu", "wRWMqxsHldxfP6Rfs4mjlPDd8kdcybOBX59lu5mx");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        ParseACL.setDefaultACL(defaultACL, true);

        ParsePantry testObject = new ParsePantry();
        testObject.setTitle("test");
        testObject.setAuthor(ParseUser.getCurrentUser());
        testObject.saveInBackground();

            String dataUrl = "http://maxwilson.me/materialTest/grocerydata.json";

            //Download grocery database JSON from internet
            final DownloadDataTask downloadGroceries = new DownloadDataTask(MainActivity.this);

            try {
                jsonString = downloadGroceries.execute(dataUrl).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }




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
        toolbar.setTitle("Pantry");


        // Puts our toolbar on
        setSupportActionBar(toolbar);
    }

    @Override
    public void handleOutputStream(OutputStream stream) {

    }


    private class DownloadDataTask extends AsyncTask<String, Integer, String> {
        private Context context;

        public DownloadDataTask(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... urls) {
            String json;

            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(urls[0]);
                Log.v("url",urls[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                Log.v("connection", connection.toString());

                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage();
                }


                // this will be useful to display download percentage
                // might be -1: server did not report the length
                int fileLength = connection.getContentLength();

                Log.v("FileLength:", new String(fileLength +""));
                // download the file
                input = connection.getInputStream();


                int size = input.available();
                byte[] buffer = new byte[4000000];

                input.read(buffer);
                input.close();
                json = new String(buffer, "UTF-8");

            } catch (Exception e) {
                return e.toString();
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                if (connection != null)
                    connection.disconnect();
            }
            return json;
        }
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress[0]);
            //setProgressPercent(progress[0]);
        }
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
        }


    }
}

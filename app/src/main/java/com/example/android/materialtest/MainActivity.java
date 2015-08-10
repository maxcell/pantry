package com.example.android.materialtest;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import tabs.SlidingTabLayout;


public class MainActivity extends AppCompatActivity {

    private ViewPager mPager;
    private ViewPagerAdapter adapter;
    private SlidingTabLayout mTabs;
    private CharSequence titles[] = {"Stores", "List"};
    int n = 2;      // Where n is the number of tabs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Standard Necessities for the app
        super.onCreate(savedInstanceState);

        // Shows the Main Activity (just background at first)
        setContentView(R.layout.activity_main);

        // Sets up Toolbar with these components:
        // (Currently) Name, Location, Settings
        setupToolbar();

        adapter = new ViewPagerAdapter(getSupportFragmentManager(), titles, n, this);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(adapter);

        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mTabs.setCustomTabView(R.layout.custom_tab, 0);
        mTabs.setDistributeEvenly(true);

        mTabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {

            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.my_secondary_text);
            }
        });

        mTabs.setViewPager(mPager);

        //Download grocery database JSON from internet
        final DownloadDataTask downloadGroceries = new DownloadDataTask(MainActivity.this);
        downloadGroceries.execute("https://github.com/maxcell/pantry/blob/develop/grocerydata.json");

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

    private class DownloadDataTask extends AsyncTask<URL, Integer, String> {
        private Context context;

        public DownloadDataTask(Context context) {
            this.context = context;
        }

        protected String doInBackground(URL... urls) {
            String json;

            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
                URL url = urls[0];
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage();
                }

                // this will be useful to display download percentage
                // might be -1: server did not report the length
                int fileLength = connection.getContentLength();

                // download the file
                input = connection.getInputStream();
                System.out.println(input);
                output = new FileOutputStream("~/Desktop/");

                byte data[] = new byte[4096];
                long total = 0;
                int count;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    if (fileLength > 0) // only if total length is known
                        publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                }
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
            return null;
        }
        protected void onProgressUpdate(Integer... progress) {
            //setProgressPercent(progress[0]);
        }
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            //showDialog("Downloaded " + result + " bytes");
        }
    }
}

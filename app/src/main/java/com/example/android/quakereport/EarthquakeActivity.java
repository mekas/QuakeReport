/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final String USGS_URL_TOP10="http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<Earthquake>>{


        @Override
        protected List<Earthquake> doInBackground(String... urls) {
            // Create a fake list of earthquake locations.
            List<Earthquake> earthquakes = QueryUtils.extractEarthquakes(urls[0]);
            return earthquakes;
        }

        /**
         * This method is invoked on the main UI thread after the background work has been
         * completed.
         */
        protected void onPostExecute(List<Earthquake> earthquakes) {
            // If there is no result, do nothing.
            if (earthquakes == null) {
                return;
            }

            // Find a reference to the {@link ListView} in the layout
            ListView earthquakeListView = (ListView) findViewById(R.id.list);

            // Create a new {@link ArrayAdapter} of earthquakes
            final EarthquakeAdapter adapter = new EarthquakeAdapter(
                    EarthquakeActivity.this, earthquakes);

            // Set the adapter on the {@link ListView}
            // so the list can be populated in the user interface
            earthquakeListView.setAdapter(adapter);
            earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Earthquake earthquake = adapter.getItem(position);
                    Uri earthquakeUri = Uri.parse(earthquake.getUrl());

                    Intent webIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                    startActivity(webIntent);
                }
            });
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        new EarthquakeAsyncTask().execute(USGS_URL_TOP10);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();

    }
}

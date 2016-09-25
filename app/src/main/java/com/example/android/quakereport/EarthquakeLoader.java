package com.example.android.quakereport;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by eka on 24/09/16.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    String url;
    public EarthquakeLoader(Context context, String url) {
        super(context);
        this.url=url;
    }

    @Override
    protected void onStartLoading(){
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        if(this.url==null)
            return null;
        //return here
        List<Earthquake> earthquakes = QueryUtils.extractEarthquakes(this.url);
        return earthquakes;
    }
}

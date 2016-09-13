package com.example.android.quakereport;

/**
 * Created by pustikom on 07/09/16.
 */

public class Earthquake {

    private double mMagnitude;
    private String mLocation;
    private String mDate;
    private long mTimeInMilliseconds;
    private String mUrl;

    public Earthquake(double mMagnitude, String mLocation, long mTimeInMilliseconds){
        this.mMagnitude=mMagnitude;
        this.mLocation=mLocation;
        this.mTimeInMilliseconds=mTimeInMilliseconds;
        this.mUrl=null;
    }

    public Earthquake(double mMagnitude, String mLocation, long mTimeInMilliseconds, String mUrl){
        this.mMagnitude=mMagnitude;
        this.mLocation=mLocation;
        this.mTimeInMilliseconds=mTimeInMilliseconds;
        this.mUrl=mUrl;
    }

    public double getMagnitude(){
        return mMagnitude;
    }

    public String getLocation(){
        return mLocation;
    }

    public long getTimeInMilliseconds(){
        return mTimeInMilliseconds;
    }

    public String getUrl(){
        return mUrl;
    }
}

package br.com.bakingapp;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class BakingAplication extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("FmcXcCpVXJyHU8UYitywQb0LBGIwFY9vGo0CvTNd")
                // if desired
                .clientKey("EdQEPPIKE6SK9FbOFLbNqvaJe9F1Vo2d4iQxPkGa")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}

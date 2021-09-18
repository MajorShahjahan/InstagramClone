package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseUser;

public class WelcomeActivity extends AppCompatActivity {

    private TextView welcomeTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcomeTxt = findViewById(R.id.welcometxt);

        welcomeTxt.setText(  "Welcome"+ " " + ParseUser.getCurrentUser().get("username").toString() +"!");
        findViewById(R.id.btnLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                finish();
            }
        });



    }
}
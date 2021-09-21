package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Login extends AppCompatActivity {

    private EditText edtUserNameLogin , edtPasswordLogin;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login");

        edtUserNameLogin = findViewById(R.id.edtUserNameLogin);
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);
        btnLogin = findViewById(R.id.btnLogin);

        if (ParseUser.getCurrentUser() !=null){
            ParseUser.getCurrentUser().logOut();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProgressDialog progressDialog = new ProgressDialog(Login.this);
                progressDialog.setMessage("Logging In");
                progressDialog.show();

                ParseUser.logInInBackground(edtUserNameLogin.getText().toString(), edtPasswordLogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if (user != null && e == null){

                            Toast.makeText(Login.this, user.getUsername() + " " + "Successfully Login", Toast.LENGTH_SHORT).show();

                        }else {

                            Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        progressDialog.dismiss();
                    }
                });
            }
        });
    }

    public void signUpTransition(View view){

        Intent intent = new Intent(Login.this,SignUpLoginActivity.class);
        startActivity(intent);

    }
}
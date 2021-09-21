package com.example.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpLoginActivity extends AppCompatActivity  {

    private EditText edtEmailSignUp, edtUserNameSignUp , edtPasswordSignUp;
    private Button btnSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);

        setTitle("Sign Up");

        edtEmailSignUp = findViewById(R.id.edtEmailSignUp);
        edtUserNameSignUp = findViewById(R.id.edtUsernameSignUp);
        edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);
        btnSignUp = findViewById(R.id.btnSignUp);

        if (ParseUser.getCurrentUser() != null){
            ParseUser.getCurrentUser().logOut();
        }

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ParseUser appUser = new ParseUser();
                appUser.setEmail(edtEmailSignUp.getText().toString());
                appUser.setUsername(edtUserNameSignUp.getText().toString());
                appUser.setPassword(edtPasswordSignUp.getText().toString());

                ProgressDialog progressDialog = new ProgressDialog(SignUpLoginActivity.this);
                progressDialog.setMessage("Signing Up" + edtUserNameSignUp.getText().toString());
                progressDialog.show();

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){

                            Toast.makeText(SignUpLoginActivity.this,"Signed Up Successfully",Toast.LENGTH_SHORT).show();
                        }else {

                            Toast.makeText(SignUpLoginActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }

                        progressDialog.dismiss();
                    }
                });


            }
        });

    }

    public void LoginTransition (View view){
        Intent intent = new Intent(SignUpLoginActivity.this,Login.class);
        startActivity(intent);

    }


}

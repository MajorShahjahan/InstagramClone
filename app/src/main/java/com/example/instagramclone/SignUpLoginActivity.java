package com.example.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

        edtPasswordSignUp.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){

                    onClick(btnSignUp);
                }
                return false;
            }
        });



        btnSignUp = findViewById(R.id.btnSignUp);

        if (ParseUser.getCurrentUser() != null){
            //ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaActivity();
        }

    }

    public void onClick(View v) {

        if (edtEmailSignUp.getText().toString().equals("") ||
                edtUserNameSignUp.getText().toString().equals("") ||
                edtPasswordSignUp.getText().toString().equals("")){

            Toast.makeText(SignUpLoginActivity.this,"Email, UserName, Password is required!",Toast.LENGTH_SHORT).show();
        }else {

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
                    if (e == null) {

                        Toast.makeText(SignUpLoginActivity.this, "Signed Up Successfully", Toast.LENGTH_SHORT).show();
                        transitionToSocialMediaActivity();
                    } else {

                        Toast.makeText(SignUpLoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    progressDialog.dismiss();

                }
            });
        }


    }

    public void LoginTransition (View view){
        Intent intent = new Intent(SignUpLoginActivity.this,Login.class);
        startActivity(intent);

    }

    public void rootLayoutTapped(View view){

        try {

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

        }catch (Exception e){

            e.printStackTrace();
        }

    }

    public void transitionToSocialMediaActivity(){
        Intent intent = new Intent(SignUpLoginActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }


}

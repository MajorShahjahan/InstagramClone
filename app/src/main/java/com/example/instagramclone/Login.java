package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Login extends AppCompatActivity {

    private EditText edtUserNameLogin, edtPasswordLogin;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login");

        edtUserNameLogin = findViewById(R.id.edtUserNameLogin);
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);

        edtPasswordLogin.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {

                    onClickLogin(
                            btnLogin);
                }

                return false;
            }
        });
        btnLogin = findViewById(R.id.btnLogin);

        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }


    }

    public void onClickLogin(View view) {

        if (edtUserNameLogin.getText().toString().equals("") || edtPasswordLogin.getText().toString().equals("")) {

            Toast.makeText(Login.this, "Username, Password is required!", Toast.LENGTH_SHORT).show();
        } else {

            ProgressDialog progressDialog = new ProgressDialog(Login.this);
            progressDialog.setMessage("Logging In");
            progressDialog.show();

            ParseUser.logInInBackground(edtUserNameLogin.getText().toString(), edtPasswordLogin.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {

                    if (user != null && e == null) {

                        Toast.makeText(Login.this, user.getUsername() + " " + "Successfully Login", Toast.LENGTH_SHORT).show();
                        transitionToSocialMediaActivity();

                    } else {

                        Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    progressDialog.dismiss();
                }
            });
        }
    }


    public void signUpTransition(View view){

        Intent intent = new Intent(Login.this,SignUpLoginActivity.class);
        startActivity(intent);

    }

    public void rootLayoutTappedLogin(View view){

        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (Exception e){

            e.printStackTrace();
        }
    }

    public void transitionToSocialMediaActivity(){
        Intent intent = new Intent(Login.this,SocialMediaActivity.class);
        startActivity(intent);
    }

}
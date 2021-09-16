package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private EditText edtBoxerName, edtPunchPower, edtPunchSpeed;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtBoxerName = findViewById(R.id.edtBoxerName);
        edtPunchPower = findViewById(R.id.edtPuchPower);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(SignUp.this);




    }

    @Override
    public void onClick(View v) {

        try {


            final ParseObject kickBoxer = new ParseObject("KickBoxer");
            kickBoxer.put("Name", edtBoxerName.getText().toString());
            kickBoxer.put("PunchPower", Integer.parseInt(edtPunchPower.getText().toString()));
            kickBoxer.put("PunchSpeed", Integer.parseInt(edtPunchSpeed.getText().toString()));
            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {

                    if (e == null) {

                        FancyToast.makeText(SignUp.this, kickBoxer.get("Name") + " is Saved", Toast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();

                    } else {

                        FancyToast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_SHORT,FancyToast.ERROR,true).show();
                    }

                }
            });
        } catch (Exception e){

            FancyToast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_SHORT,FancyToast.ERROR,true).show();


        }

    }
}
package com.example.instagramclone;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;


public class ProfileTab extends Fragment {

    private EditText edtProfileName, edtBio , edtProfession , edtHobbies;
    private Button btnUpdateInfo;
    private ConstraintLayout rootLayout;


    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);
        edtProfileName = view.findViewById(R.id.edtProfileName);
        edtBio = view.findViewById(R.id.edtBio);
        edtProfession = view.findViewById(R.id.edtProfession);
        edtHobbies = view.findViewById(R.id.edtHobbies);
        btnUpdateInfo = view.findViewById(R.id.btnUpdateInfo);
        rootLayout = view.findViewById(R.id.rootLayoutIsTapped);

        ParseUser parseUser = ParseUser.getCurrentUser();

        if (parseUser.get("ProfileName") == null){

            edtProfileName.setText("");
        }else{

            edtProfileName.setText(parseUser.get("ProfileName") + "");
        }
        if (parseUser.get("Bio") == null){
            edtBio.setText("");
        }else{
            edtBio.setText(parseUser.get("Bio") + "");
        }
        if (parseUser.get("Profession") == null){
            edtProfession.setText("");
        }else {
            edtProfession.setText(parseUser.get("Profession") + "");
        }
        if (parseUser.get("Hobbies") == null){
            edtProfession.setText("");
        }else {
            edtHobbies.setText(parseUser.get("Hobbies") + "");
        }

        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                parseUser.put("ProfileName", edtProfileName.getText().toString());
                parseUser.put("Bio",edtBio.getText().toString());
                parseUser.put("Profession",edtProfession.getText().toString());
                parseUser.put("Hobbies",edtHobbies.getText().toString());

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {

                        if (e == null){

                            Toast.makeText(getContext(), "Info Updated", Toast.LENGTH_SHORT).show();
                        }else {

                            e.printStackTrace();
                        }
                    }
                });

            }
        });

        return view;

    }

}
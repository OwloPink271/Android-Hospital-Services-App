package com.example.testare_proiect_licenta.authentification;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.testare_proiect_licenta.Main_User_Page;
import com.example.testare_proiect_licenta.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class New_user_data extends AppCompatActivity {


    TextInputEditText first_middle_name;
    TextInputEditText last_name;

    RadioGroup gender;

    Button cancel_btn;
    Button finish_btn;
    FirebaseUser user;
    FirebaseAuth auth;
    FirebaseFirestore db_fs=FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_data);

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        first_middle_name=findViewById(R.id.input_new_first_middle_name);
        last_name=findViewById(R.id.input_last_name);
        gender=findViewById(R.id.radioGroup_genders);
        cancel_btn=findViewById(R.id.button_back_to_register);
        finish_btn=findViewById(R.id.button_finish_registration);

        //cancel button pressed
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                user.delete();
                Intent intent= new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
                finish();
            }
        });


        //finish button pressed

        finish_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> utilizatori= new HashMap<>();

                String email=user.getEmail().toString();
                utilizatori.put("Email",email);

                int selected_gender=gender.getCheckedRadioButtonId();
                if(selected_gender==R.id.radioButton_male_select){
                    utilizatori.put("Gender","Male");
                }
                else if(selected_gender==R.id.radioButton_female_select){
                    utilizatori.put("Gender","Female");
                }

                utilizatori.put("Last name", last_name.getText().toString());
                utilizatori.put("First-Middle name", first_middle_name.getText().toString());

                db_fs.collection("users-final").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(utilizatori).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                        user.sendEmailVerification();
                        Intent intent= new Intent(getApplicationContext(), Main_User_Page.class);
                        startActivity(intent);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });








            }
        });
    }
}
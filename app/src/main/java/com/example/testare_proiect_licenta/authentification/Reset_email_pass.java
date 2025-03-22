package com.example.testare_proiect_licenta.authentification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.testare_proiect_licenta.R;


public class Reset_email_pass extends AppCompatActivity {

    Button reset_email;
    Button reset_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_email_pass);

        initializare();


    }

    private void initializare() {
        reset_email=findViewById(R.id.button_reset_email);
        reset_pass=findViewById(R.id.button_reset_password);

       reset_email.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent intent=new Intent(getApplicationContext(), Reset_password.class);
               startActivity(intent);
           }
       });

    }
}
package com.example.testare_proiect_licenta.authentification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.testare_proiect_licenta.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;


public class Reset_password extends AppCompatActivity {

    TextInputEditText your_email;
    Button send_reset_email;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        initializare();
    }

    private void initializare() {
        your_email=findViewById(R.id.input_reset_email);
        send_reset_email=findViewById(R.id.button_reset_email);
        String regex_email="[a-zA-Z0-9!#$%^&*()_=+]+@[a-zA-Z0-9!#$%^&*()_=+]+\\.[a-zA-Z0-9]+";
        String email=your_email.getText().toString();

        send_reset_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Reset_password.this, "Campul email este gol!", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(!email.matches(regex_email)){
                    Toast.makeText(Reset_password.this, "Ai introdus un character care nu se aflat in aceasta lista:!#$%^&*()_=+@ ", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!email.contains("@")){
                    Toast.makeText(Reset_password.this, "Nu ai introdus un email valid!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!email.isEmpty()){

                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(complet->{
                        if(complet.isSuccessful())
                        Toast.makeText(Reset_password.this, "Email trimis!", Toast.LENGTH_SHORT).show();
                        else Toast.makeText(Reset_password.this, "Nu s-a trimis emailul!", Toast.LENGTH_SHORT).show();
                    });
                }



            }
        });


    }
}
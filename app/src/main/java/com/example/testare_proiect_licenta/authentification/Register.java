package com.example.testare_proiect_licenta.authentification;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testare_proiect_licenta.Main_User_Page;
import com.example.testare_proiect_licenta.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

     TextInputEditText inputEmail, inputPassword;
     Button register_button;
     FirebaseAuth mAuth;

     TextView textView_to_login;
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), Main_User_Page.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();
        inputEmail=findViewById(R.id.input_email_register);
        inputPassword=findViewById(R.id.input_password_register);
        register_button=findViewById(R.id.register_button);

        textView_to_login=findViewById(R.id.textView_to_login);

        textView_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String regex_email="[a-zA-Z0-9!#$%^&*()_=+.]+@[a-zA-Z0-9!#$%^&*()_=+]+\\.[a-zA-Z0-9]+";



                String email, password;
                email = String.valueOf(inputEmail.getText());
                password=String.valueOf(inputPassword.getText());



                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this, "Campul email este gol!", Toast.LENGTH_SHORT).show();
                     return;
                }


                if(!email.matches(regex_email)){
                    Toast.makeText(Register.this, "Ai introdus un character care nu se aflat in aceasta lista:!#$%^&*()_=+@. ", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!email.contains("@")){
                    Toast.makeText(Register.this, "Nu ai introdus un email valid!", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Register.this, "Campul password este gol!", Toast.LENGTH_SHORT).show();
                return;
                }


                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                         Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user=mAuth.getCurrentUser();
                            Intent intent = new Intent(getApplicationContext(), New_user_data.class);
                            startActivity(intent);
                            finish();
                            //updateUI(user);
                            Toast.makeText(Register.this, "Registration successful.",
                                    Toast.LENGTH_SHORT).show();


                        }
                        else{
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(Register.this, "The email address is already in use by another account.",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Register.this, "Registration failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                });
            }
        });
    }
}
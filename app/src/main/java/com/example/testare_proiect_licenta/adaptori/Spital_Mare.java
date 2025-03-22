package com.example.testare_proiect_licenta.adaptori;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.testare_proiect_licenta.Listare_Departamente;
import com.example.testare_proiect_licenta.Main_User_Page;
import com.example.testare_proiect_licenta.R;
import com.example.testare_proiect_licenta.cautare.CautareSpital;
import com.example.testare_proiect_licenta.claseObiecte.Departament;
import com.example.testare_proiect_licenta.claseObiecte.Spital;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Spital_Mare extends AppCompatActivity {

    Button departamente_btn;
    Button doctori_btn;

    FloatingActionButton fab_burger;
    DrawerLayout drawerLayout_1;
    NavigationView navigationView_1;
    List<Departament> departamente_lista=new ArrayList<>();

    FirebaseFirestore db=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spital_mare);

        initializare();

        ImageView poza=findViewById(R.id.imageView_spital_mare_poza);
        TextView nume=findViewById(R.id.textView_spital_mare_nume);
        TextView telefon=findViewById(R.id.textView_spital_mare_telefon);
        TextView adresa=findViewById(R.id.textView_spital_mare_email);
        TextView distanta=findViewById(R.id.textView_spital_mare_distanta);
        TextView email=findViewById(R.id.textView_spital_mare_email);
        TextView asigurare=findViewById(R.id.textView_spital_mare_asigurare);
        TextView public_privat=findViewById(R.id.textView_spital_mare_public_privat);

        Intent intent=getIntent();
        Spital spital_nou=(Spital) intent.getSerializableExtra("spitalSelectat");

        db.collection("Spitale").document(spital_nou.getId()).collection("Departamente").get().addOnCompleteListener(task->{

            if(task.isSuccessful()){
                for(QueryDocumentSnapshot document:task.getResult()){
                    String nume_dep=document.getString("Nume");
                    Departament nou_dep=new Departament(nume_dep);
                    departamente_lista.add(nou_dep);

                }
            }
        });


        String imagine_link=spital_nou.getPoza();
        Glide.with(poza.getContext()).load(imagine_link).into(poza);

        nume.setText(spital_nou.getNume());
        telefon.setText(spital_nou.getTelefon());
        adresa.setText(spital_nou.getAdresa());
        distanta.setText(String.valueOf(spital_nou.getDistanta()));
        email.setText(spital_nou.getEmail());

        if(spital_nou.isAsigurare()){
            asigurare.setText("Da");
        }
        else
            asigurare.setText("Nu");

        if(spital_nou.isPublic_privat())
            public_privat.setText("Public");

        else
            public_privat.setText("Privat");


    }

    private void initializare() {
        fab_burger=findViewById(R.id.floatingActionButton_burger);
        drawerLayout_1=findViewById(R.id.included_drawer_layout_spital_mare);
        navigationView_1=findViewById(R.id.nav_view_1_spital_mare);

        departamente_btn=findViewById(R.id.button_spital_departamente);
        doctori_btn=findViewById(R.id.button_spital_doctori);

        departamente_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_nou=new Intent(Spital_Mare.this, Listare_Departamente.class);
                intent_nou.putExtra("listaDepartamente",(ArrayList<Departament>)departamente_lista);
                startActivity(intent_nou);
            }
        });

        fab_burger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout_1.openDrawer(GravityCompat.START);
            }
        });


        navigationView_1.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id=item.getItemId();
                if(id==R.id.cautare){
                    Intent intent=new Intent(getApplicationContext(), CautareSpital.class);
                    startActivity(intent);
                }

                else if(id==R.id.setari){}

                else if(id==R.id.credits){
                    Intent intent=new Intent(getApplicationContext(), Main_User_Page.class);
                    startActivity(intent);
                }

                drawerLayout_1.closeDrawer(GravityCompat.START);

                return true;
            }
        });


    }
}
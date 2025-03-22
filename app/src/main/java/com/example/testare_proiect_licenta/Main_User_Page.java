package com.example.testare_proiect_licenta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.testare_proiect_licenta.cautare.CautareSpital;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class Main_User_Page extends AppCompatActivity {

    TextView nume_user;
    FloatingActionButton fab_burger;
    DrawerLayout drawerLayout_1;
    NavigationView navigationView_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user_page);
        initializare();
    }

    private void initializare() {
        nume_user=findViewById(R.id.textView_nume_salut);
        fab_burger = findViewById(R.id.floatingActionButton_burger);
        drawerLayout_1 = findViewById(R.id.included_drawer_layout);
        navigationView_1 = findViewById(R.id.nav_view_1);
        drawerLayout_1.setScrimColor(Color.TRANSPARENT);


        //




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
                    drawerLayout_1.closeDrawer(GravityCompat.START);
                }

               drawerLayout_1.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

}

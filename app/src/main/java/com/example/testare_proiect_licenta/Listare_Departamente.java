package com.example.testare_proiect_licenta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.testare_proiect_licenta.adaptori.Departament_mic;
import com.example.testare_proiect_licenta.cautare.CautareSpital;
import com.example.testare_proiect_licenta.claseObiecte.Departament;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class Listare_Departamente extends AppCompatActivity {

    FloatingActionButton fab_burger;
    DrawerLayout drawerLayout_1;
    NavigationView navigationView_1;
   List<Departament>listaDepartamente=new ArrayList<>();
    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listare_departamente);
        initializare();
    }

    private void initializare() {
        fab_burger=findViewById(R.id.floatingActionButton_burger);
        drawerLayout_1=findViewById(R.id.included_drawer_layout_listare_departamente);
        navigationView_1 = findViewById(R.id.nav_view_1_listare_departamente);

        listView=findViewById(R.id.listView_listare_departamente);

        getAndDisplayListView();


        Departament_mic adaptor=new Departament_mic(getApplicationContext(),R.layout.departament_mic,listaDepartamente,getLayoutInflater());
        listView.setAdapter(adaptor);





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



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LinearLayout subItems=view.findViewById(R.id.sub_items);
            }
        });

    }

    private void getAndDisplayListView() {
        Intent intent=getIntent();
        listaDepartamente=(List<Departament>)intent.getSerializableExtra("listaDepartamente");
       // display_ListView_Custom();

    }

    private void display_ListView_Custom() {
        Departament_mic adaptor=(Departament_mic) listView.getAdapter();
        adaptor.notifyDataSetChanged();
    }
}
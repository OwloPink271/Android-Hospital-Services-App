package com.example.testare_proiect_licenta.cautare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.graphics.Color;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.testare_proiect_licenta.Main_User_Page;
import com.example.testare_proiect_licenta.R;
import com.example.testare_proiect_licenta.adaptori.Spital_Mare;
import com.example.testare_proiect_licenta.adaptori.Spital_mic;
import com.example.testare_proiect_licenta.claseObiecte.Spital;
import com.example.testare_proiect_licenta.old.Custom_adapter;
import com.example.testare_proiect_licenta.old.Fragment_test;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class CautareSpital extends AppCompatActivity {

    double latitudine;
    double longitudine;

    //Location

    LocationManager locationManager;
    LocationListener locationListener;
    //

    FloatingActionButton fab_burger;
    DrawerLayout drawerLayout_1;
    NavigationView navigationView_1;

    FirebaseFirestore db=FirebaseFirestore.getInstance();
    ListView listView;
    List<Spital>lista_initiala_firestore=new ArrayList<>();
    List<Spital>lista_prelucrata=new ArrayList<>();
    SearchView searchView;
    FragmentManager fragmentManager=getSupportFragmentManager();
    Fragment_test fragmentTest=new Fragment_test();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cautare_spital);
        initializare();
    }

    private void initializare() {
        fab_burger=findViewById(R.id.floatingActionButton_burger);
        drawerLayout_1=findViewById(R.id.included_drawer_layout_cautare);
        navigationView_1 = findViewById(R.id.nav_view_1_cautare);

        //locatie
        locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                latitudine=location.getLatitude();
                longitudine=location.getLongitude();
                Log.i("LocationListener", "Latitude: " + latitudine + ", Longitude: " + longitudine);

            }
        };


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // request the permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            // permission has been granted
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }


        //

        listView=findViewById(R.id.listView_cautare_spital);

        //
        //Setting custom adapter
        Spital_mic adaptor= new Spital_mic(getApplicationContext(),R.layout.adaptor_spital_mic,lista_prelucrata,getLayoutInflater());
        listView.setAdapter(adaptor);

        //





        getAndDisplayListView();


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

                Spital spital_selectat=(Spital) parent.getItemAtPosition(position);
                Intent intent=new Intent(CautareSpital.this, Spital_Mare.class);
                intent.putExtra("spitalSelectat",spital_selectat);
                startActivity(intent);


            }
        });

    }

    private void getAndDisplayListView() {

        db.collection("Spitale").get().addOnCompleteListener(task -> {

            if(task.isSuccessful()){
                for(QueryDocumentSnapshot document: task.getResult()){

                    String id=document.getId();

                    String nume=document.getString("Nume");
                    //int telefon=Integer.parseInt(document.getString("Telefon"));
                    String telefon=document.getString("Telefon");
                    String adresa=document.getString("Adresa");
                    String locatie=document.getString("Locatie");


                    //Distanta

                    String[]locatie_split=locatie.split(", ");
                    double lat_loc=Double.parseDouble(locatie_split[0]);
                    double lon_loc=Double.parseDouble(locatie_split[1]);

                    double distanta= calculareDistanta(latitudine,longitudine, lat_loc, lon_loc);
                    Log.i("calculareDistanta", "lat_user: " + latitudine + ", lon_user: " + longitudine + ", lat_loc: " + lat_loc + ", lon_loc: " + lon_loc);
                    //Distanta

                    //distanta

                    String email=document.getString("Email");
                    boolean asigurare=document.getBoolean("Asigurare");
                    boolean public_privat=document.getBoolean("Public");
                    String poza_URL=document.getString("Poza");




                    Spital spital_nou=new Spital(id,nume,telefon,adresa,distanta,email,asigurare,public_privat,poza_URL);

                    lista_initiala_firestore.add(spital_nou);

                }
            }
            lista_prelucrata.addAll(lista_initiala_firestore);
            display_ListView_Custom();
        });
    }

    private void display_ListView_Custom() {
        Spital_mic adaptor=(Spital_mic) listView.getAdapter();
        adaptor.notifyDataSetChanged();
    }

    public static double calculareDistanta(double lat_user,double lon_user, double lat_loc,double lon_loc){
        int R=6371;

        double latDistanta=Math.toRadians(lat_loc-lat_user);
        double lonDistanta=Math.toRadians(lon_loc-lon_user);
        double a = Math.sin(latDistanta/ 2) * Math.sin(latDistanta / 2)
                + Math.cos(Math.toRadians(lat_user)) * Math.cos(Math.toRadians(lat_loc))
                * Math.sin(lonDistanta / 2) * Math.sin(lonDistanta / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distanta = R * c; // convert to kilometers
        distanta = Math.pow(distanta, 2);
        return Math.sqrt(distanta);


    }

}
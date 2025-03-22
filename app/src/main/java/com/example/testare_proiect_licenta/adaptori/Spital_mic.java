package com.example.testare_proiect_licenta.adaptori;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.testare_proiect_licenta.R;
import com.example.testare_proiect_licenta.claseObiecte.Spital;


import java.util.List;

public class Spital_mic extends ArrayAdapter<Spital> {

    private Context context;
    private int resource;
    private List<Spital> spitale;
    private LayoutInflater layoutInflater;


    public Spital_mic(@NonNull Context context, int resource,  @NonNull List<Spital> objects, LayoutInflater layoutInflater) {
        super(context, resource,  objects);

        this.context=context;
        this.resource=resource;
        this.spitale=objects;
        this.layoutInflater=layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        View view=layoutInflater.inflate(resource,parent,false);

        TextView tvNume=view.findViewById(R.id.textView_nume_spital_mic);
        TextView tvTelefon=view.findViewById(R.id.textView_telefon_spital_mic);
        TextView tvAdresa=view.findViewById(R.id.textView_adresa_spital_mic);
        TextView tvDistanta=view.findViewById(R.id.textView_distanta_spital_mic);
        ImageView imgView=view.findViewById(R.id.imageView_poza_spital_mic);

        Spital spital_view=spitale.get(position);
        tvNume.setText(spital_view.getNume());
        tvAdresa.setText(spital_view.getAdresa());
        tvTelefon.setText(spital_view.getTelefon());
        ////to do distanta
        String dist= String.valueOf(spital_view.getDistanta());
        tvDistanta.setText(dist);



        String imagine=spital_view.getPoza();
        Glide.with(imgView.getContext()).load(imagine).into(imgView);



        return view;
    }
}

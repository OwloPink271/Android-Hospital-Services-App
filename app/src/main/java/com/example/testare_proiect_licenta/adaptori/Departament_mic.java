package com.example.testare_proiect_licenta.adaptori;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.testare_proiect_licenta.R;
import com.example.testare_proiect_licenta.claseObiecte.Departament;
import com.example.testare_proiect_licenta.claseObiecte.Spital;

import java.util.List;

public class Departament_mic extends ArrayAdapter<Departament> {

    private Context context;
    private int resource;
    private List<Departament> departamente;
    private LayoutInflater layoutInflater;

    public Departament_mic(@NonNull Context context, int resource, @NonNull List<Departament> objects, LayoutInflater layoutInflater) {
        super(context, resource,  objects);

        this.context=context;
        this.resource=resource;
        this.departamente=objects;
        this.layoutInflater=layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        View view=layoutInflater.inflate(resource,parent,false);

        TextView tvNume=view.findViewById(R.id.textView_departament_mic_nume);
        Departament departament=departamente.get(position);
        tvNume.setText(departament.getNume());

        



        return view;
    }


}

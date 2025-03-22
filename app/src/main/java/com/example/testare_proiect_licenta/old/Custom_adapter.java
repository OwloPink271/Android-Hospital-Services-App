package com.example.testare_proiect_licenta.old;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.testare_proiect_licenta.R;

import java.util.ArrayList;
import java.util.List;

public class Custom_adapter extends ArrayAdapter<test_item_class> {

    private Context context;
    private int resource;
    private List<test_item_class> test_items;
    private LayoutInflater layoutInflater;

    public Custom_adapter(@NonNull Context context, int resource, @NonNull List<test_item_class>objects,LayoutInflater layoutInflater){
        super(context,resource,objects);
        this.context=context;
        this.resource=resource;
        this.test_items=objects;
        this.layoutInflater=layoutInflater;

    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        View view=layoutInflater.inflate(resource,parent,false);
        TextView tvNume=view.findViewById(R.id.textView_nume_adapter);
        TextView tvPhone=view.findViewById(R.id.textView_tel_test);
        ImageView imgView=view.findViewById(R.id.imageView_bee);


        test_item_class testItem=test_items.get(position);

        tvNume.setText(testItem.getNume());
        tvPhone.setText(String.valueOf(testItem.getTelefon()));

        String imagine=testItem.getURL_imagine();
        Glide.with(imgView.getContext()).load(imagine).into(imgView);


        return view;
    }

    @Override
    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results=new FilterResults();
                List<test_item_class>lista_filtrata=new ArrayList<>();
                if(constraint==null || constraint.length()==0){
                    lista_filtrata.addAll(test_items);
                }
                else{
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for(test_item_class obiect:test_items){
                        if(obiect.getNume().toLowerCase().contains(filterPattern))
                            lista_filtrata.add(obiect);

                        if(obiect.getSpec().toLowerCase().contains(filterPattern))
                            lista_filtrata.add(obiect);
                    }


                }

                results.values=lista_filtrata;
                results.count=lista_filtrata.size();

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                clear();
                addAll((List<test_item_class>)results.values);
                notifyDataSetChanged();
            }
        };
    }



}
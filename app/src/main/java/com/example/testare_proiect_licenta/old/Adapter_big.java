package com.example.testare_proiect_licenta.old;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.testare_proiect_licenta.R;

public class Adapter_big extends AppCompatActivity {

    ImageView img;
    TextView name;
    TextView phone;
    TextView spec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.big_custom_adapter);

        ImageView imgView=findViewById(R.id.imageView_big);
        TextView tvNume=findViewById(R.id.textView_big_nume);
        TextView tvPhone=findViewById(R.id.textView_big_phone);
        TextView tvSpec=findViewById(R.id.textView_big_spec);

        Intent intent=getIntent();
        test_item_class nouItem=(test_item_class) intent.getSerializableExtra("itemNou");

        String imagine=nouItem.getURL_imagine();

        Glide.with(imgView.getContext()).load(imagine).into(imgView);

        tvNume.setText(nouItem.getNume());
        tvPhone.setText(String.valueOf(nouItem.getTelefon()));
        tvSpec.setText(nouItem.getSpec());






    }

}
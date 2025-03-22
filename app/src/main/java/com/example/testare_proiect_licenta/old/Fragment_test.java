package com.example.testare_proiect_licenta.old;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.testare_proiect_licenta.R;


public class Fragment_test  extends Fragment {

    private CheckBox box1;
   private  CheckBox box2;
   private MainActivity mainActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView= inflater.inflate(R.layout.fragment_left_test,container,false);

        Context context=getContext();
        box1= rootView.findViewById(R.id.checkBox_nume_test);
        box2=rootView.findViewById(R.id.checkBox_phone_size_test);

       box1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked){

                   mainActivity.filtrareCheckbox("Coi");
               }

               if(isChecked && !(box2.isChecked())){
                   mainActivity.lista_refresh();
                   mainActivity.filtrareCheckbox("Coi");
               }

               if(!isChecked && !(box2.isChecked())){
                   mainActivity.lista_refresh();
               }
           }
       });


       box2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked){
                   mainActivity.filtrareCheckbox("Cartof");
               }
               if(isChecked && !(box1.isChecked())){
                   mainActivity.lista_refresh();
                   mainActivity.filtrareCheckbox("Cartof");
               }

               if(!isChecked && !(box1.isChecked())){
                   mainActivity.lista_refresh();
               }
           }
       });

return rootView;

    }
    public void setMainActivity(MainActivity mainActivity){
       this.mainActivity=mainActivity;
    }

}

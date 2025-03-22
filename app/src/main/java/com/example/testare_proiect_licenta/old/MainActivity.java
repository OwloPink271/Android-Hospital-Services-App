package com.example.testare_proiect_licenta.old;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.testare_proiect_licenta.authentification.Login;
import com.example.testare_proiect_licenta.R;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;

    Button logout_button;
    Button firestore_test;
    Button firestore_image_test;
    Button map_test;
    TextView text_test;
    FirebaseUser user;
    FirebaseFirestore db_fs = FirebaseFirestore.getInstance();
    DocumentReference docRef;
    ListView listView;
    List<String> documentIds=new ArrayList<>();
    List<test_item_class>test_items=new ArrayList<>();
    List<test_item_class>items_refresh=new ArrayList<>();
    SearchView search_bar;

    FragmentManager fragmentManager=getSupportFragmentManager();
    //Fragment test_fragment=new Fragment_test();
    Fragment_test fragmentTest =new Fragment_test();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize your ImageView
        ImageView imageView = findViewById(R.id.imageTestView);

        // Load the image using Glide
        //String imageUrl = "https://firebasestorage.googleapis.com/v0/b/proiect-licenta-cb636.appspot.com/o/1713900964571314.jpg?alt=media&token=fec50bc0-2816-424b-835d-28f22619c85c";
//
//        Glide.with(MainActivity.this)
//                .load(imageUrl)
//                .placeholder(R.mipmap.ic_launcher) // Replace with your placeholder drawable
//                .into(imageView);

        auth = FirebaseAuth.getInstance();

        listView=findViewById(R.id.listView_test);

        logout_button = findViewById(R.id.button_logout);

        search_bar=findViewById(R.id.search_bar);

        //uhhh
        firestore_test = findViewById(R.id.button_test_firestore);

        //test de imagine
        firestore_image_test = findViewById(R.id.test_imagine);

        //textview normal
        text_test = findViewById(R.id.textView_main_test);

        //pentru user details gen email
        user = auth.getCurrentUser();

        //fragment filtre
        fragmentTest.setMainActivity(this);

        //map
        map_test=findViewById(R.id.button_map_test);




        //Set adapter-----------------------------------------
        Custom_adapter adapter_custom=new Custom_adapter(getApplicationContext(),R.layout.test_custom_adapter,items_refresh,getLayoutInflater());
        listView.setAdapter(adapter_custom);

        //Set adapter-----------------------------------------
        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        } else {
            text_test.setText(user.getEmail());
        }

        display_ListView();
       // display_ListView_part2();

        map_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), map_test_first.class);
                startActivity(intent);
            }
        });


        search_bar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter_custom.getFilter().filter(query);
                return true;

            }


            @Override
            public boolean onQueryTextChange(String newText) {

                lista_refresh();
                if(newText.length()>=1){
                    adapter_custom.getFilter().filter(newText);
                }
                return true;
            }
        });

        search_bar.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                adapter_custom.getFilter().filter("");

                lista_refresh();
                return false;
            }
        });

        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        firestore_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lista_refresh();
                // Create a new user with a first and last name
//                Map<String, Object> user = new HashMap<>();
//                user.put("first", "Ada");
//                user.put("last", "Lovelace");
//                user.put("born", 1815);
//
//                // Add a new document with ID as the authenticated user's uid
//                db_fs.collection("users")
//                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                        .set(user)
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                Log.d(TAG, "DocumentSnapshot successfully written!");
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Log.w(TAG, "Error writing document", e);
//                            }
//                        });
            }
        });

        firestore_image_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST Box", "OARE MERGE");

                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

               fragmentTransaction.setCustomAnimations(R.anim.slide_left,R.anim.slide_back);




               fragmentTransaction.replace(R.id.fragmentContainerView,fragmentTest);

               //fragmentTransaction.addToBackStack(null);

               fragmentTransaction.commit();
                View fragmentRootView=findViewById(R.id.fragmentContainerView);
                fragmentRootView.setVisibility(View.VISIBLE);



//
//             docRef=db_fs.collection("utilizatori").document("KgNheemr0tYfyEUN6VnEdPgfyE13");
//             docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                 @Override
//                 public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                     if(task.isSuccessful()){
//                         DocumentSnapshot document=task.getResult();
//                         if(document.exists()){
//                             String imageUrl=document.getString("LinkImagine");
//
//
//
//                             Glide.with(imageView.getContext()).
//                                     load(imageUrl).into(imageView);
//                         }else{
//                             Log.d(TAG, "No such document");
//                         }
//                     }else{Log.d(TAG, "get failed with ", task.getException());}
//                 }
//             });

                // Load the image again (if needed)

//
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                test_item_class selectedItem=(test_item_class)parent.getItemAtPosition(position);

                Intent intent=new Intent(MainActivity.this,Adapter_big.class);

                intent.putExtra("itemNou",selectedItem);
                startActivity(intent);

            }
        });


    }



    private void display_ListView() {
        db_fs.collection("testItems").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    String nume = document.getString("Name");
                   // long phoneValue =  document.getLong("Phone");
                    long phoneValue=Long.parseLong(document.getString("Phone"));
                    String url_Imagine=document.getString("Imagine");
                    String spec=document.getString("Spec");


                    // Handle null values (if needed)


                        // Create your test_item_class instance
                        test_item_class new_item = new test_item_class(nume, phoneValue,url_Imagine,spec);
                        test_items.add(new_item);
                    }
                }
                // Display the data
                items_refresh.addAll(test_items);
                display_ListView_Custom();

        });
    }

    public void lista_refresh(){

        if(!items_refresh.isEmpty()){
            items_refresh.clear();
        }
        items_refresh.addAll(test_items);
        display_ListView_Custom();
    }

    private void display_ListView_Custom() {
         Custom_adapter adapter=(Custom_adapter) listView.getAdapter();
         adapter.notifyDataSetChanged();
    }

    private void display_ListView_part2() {
        ArrayAdapter<String>adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,documentIds);
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        if(fragmentTest!=null && fragmentTest.isVisible()){
            Rect r= new Rect(0,0,0,0);
            fragmentTest.getView().getHitRect(r);
            boolean intersects=r.contains((int)event.getX(),(int)event.getY());
            if(!intersects){
                FragmentTransaction fragmentTransaction;
                fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.remove(fragmentTest).commit();
                return true;
            }
        }
        return super.onTouchEvent(event);
    }


    public void filtrareCheckbox(String checkbox){
        Custom_adapter adapter=(Custom_adapter) listView.getAdapter();
        adapter.getFilter().filter(checkbox);
    }

}

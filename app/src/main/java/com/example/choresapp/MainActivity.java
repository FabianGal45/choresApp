package com.example.choresapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.choresapp.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    Firebase connection to the realtime database
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://choresapp-5b1ce-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference myRef = database.getReference("House1");

//    Setting the binding variable which makes it easier to reference objects from the UI
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        myRef.child("Fabian").child("task1").setValue("Wash Dishes");
        myRef.child("Fabian").child("task2").setValue("Wash Dishes");
//        Used to remove items from the database
//        myRef.child("Fabian").removeValue();



        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });


//        Sets up an array list with all the checkboxes
        ArrayList<CheckBox> myCheckBox;
        myCheckBox= new ArrayList<>();
        myCheckBox.add(binding.checkBox);
        myCheckBox.add(binding.checkBox2);
        myCheckBox.add(binding.checkBox3);
        myCheckBox.add(binding.checkBox4);
        myCheckBox.add(binding.checkBox5);
        myCheckBox.add(binding.checkBox6);

//        Deletes any checkbox from the list that has been selected
        for(int i=0;i< myCheckBox.size();i++) {
            int finalI = i;
            myCheckBox.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (myCheckBox.get(finalI).isChecked()) {
                        myCheckBox.get(finalI).setVisibility(View.GONE);
                    }
                }
            });
        }

    }
}
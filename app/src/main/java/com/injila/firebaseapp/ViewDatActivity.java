package com.injila.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class ViewDatActivity extends AppCompatActivity {
    ListView listyangu;
    CustomAdapter adapter;
    ArrayList<ColumnConstructor> users;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dat);
        listyangu = findViewById(R.id.listyangu);
        users = new ArrayList<>();
        adapter = new CustomAdapter(this, users);
        dialog = new ProgressDialog(this);

        dialog.setTitle("Loading...");
        dialog.setMessage("Please wait");
//        connect to the database
        DatabaseReference ref = FirebaseDatabase.
                getInstance().getReference().child("users");
        dialog.show();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users.clear();
                for(DataSnapshot  snap : dataSnapshot.getChildren()){
                    ColumnConstructor user  = snap.getValue((ColumnConstructor.class));
                    users.add(user);
                    Collections.reverse(users);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ViewDatActivity.this, "Sorry DB is locked", Toast.LENGTH_SHORT).show();
            }
        });
        listyangu.setAdapter(adapter);


    }
}

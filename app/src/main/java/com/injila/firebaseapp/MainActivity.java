package com.injila.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText etName, etEmail, etCountry;
    Button btnSave, btnView;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etCountry = findViewById(R.id.etCountry);
        btnSave = findViewById(R.id.btnSave);
        btnView = findViewById(R.id.btnView);

//        initialize dialog box
        dialog = new ProgressDialog(this);
        dialog.setTitle("Saving data");
        dialog.setMessage("Please wait as data is being saved");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jina = etName.getText().toString().trim();
                String arafa = etEmail.getText().toString().trim();
                String nchi = etCountry.getText().toString().trim();

                long time = System.currentTimeMillis();
                String convertedTime = String.valueOf(time);

                if(jina.isEmpty() || arafa.isEmpty() || nchi.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();

                }else{
//                    create a table/child in the db
                    DatabaseReference ref =
                    FirebaseDatabase.getInstance().getReference().child("users/" + convertedTime);
                    ColumnConstructor data = new ColumnConstructor(convertedTime,jina,arafa,nchi);
//                save data
                    dialog.show();
                    ref.setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                                dialog.dismiss();
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Savedd successful", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(MainActivity.this, "Not saved", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

            }
        });
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ViewDatActivity.class));
            }
        });















    }
}

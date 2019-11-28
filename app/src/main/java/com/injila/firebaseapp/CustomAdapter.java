package com.injila.firebaseapp;

//CustomAdapter

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<ColumnConstructor> data;//modify here

    public CustomAdapter(Context mContext, ArrayList<ColumnConstructor> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();// # of items in your arraylist
    }
    @Override
    public Object getItem(int position) {
        return data.get(position);// get the actual item
    }
    @Override
    public long getItemId(int id) {
        return id;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_layout, parent,false);//modify here
            viewHolder = new ViewHolder();
            //modify this block of code
            viewHolder.tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);
            viewHolder.tvEmail = (TextView) convertView.findViewById(R.id.tvEmail);
            viewHolder.tvCountry = (TextView) convertView.findViewById(R.id.tvCountry);
            viewHolder.mBtnDelete = convertView.findViewById(R.id.btndel);
            viewHolder.mBtnUpdate = convertView.findViewById(R.id.btnupdate);
            //viewHolder.imageView=(ImageView) convertView.findViewById(R.id.imageView);
            //Up to here
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //MODIFY THIS BLOCK OF CODE
        final ColumnConstructor person = data.get(position);//modify here
        viewHolder.tvUsername.setText(person.getName());//modify here
        viewHolder.tvEmail.setText(person.getEmail());//modify here
        viewHolder.tvCountry.setText(person.getCountry());//modify here
        //viewHolder.imageView.setImageResource(person.getImage());
        viewHolder.mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users/"+person.getId());
                ref.removeValue();
                Toast.makeText(mContext, "deleted", Toast.LENGTH_SHORT).show();

            }
        });
        viewHolder.mBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
                View view = inflater.inflate(R.layout.update_layout,null);
                final EditText u_name = view.findViewById(R.id.edtUpdateName);
                final EditText u_email = view.findViewById(R.id.edtUpdateEmail);
                final EditText u_country = view.findViewById(R.id.edtUpdateCountry);

                u_name.setText(person.getName());
                u_email.setText(person.getEmail());
                u_country.setText(person.getCountry());

                builder.setView(view);
                builder.setTitle("Updating Records");
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      //Get data from the edit texts
                      String jina = u_name.getText().toString().trim();
                      String arafa = u_email.getText().toString().trim();
                      String nchi = u_country.getText().toString().trim();
                      //Check if they are empty
                        person.setName(jina);
                        person.setEmail(arafa);
                        person.setCountry(nchi);
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users/"+person.getId());
                        ref.setValue(person).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(mContext, "Success", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(mContext, "FAil", Toast.LENGTH_SHORT).show();

                                }
                            }

                        });
                         
                    }
                });builder.create().show();
            }
        });
        return convertView;

    }
    static class ViewHolder {
        TextView tvUsername,tvEmail,tvCountry;
        Button mBtnDelete,mBtnUpdate;
    }

}
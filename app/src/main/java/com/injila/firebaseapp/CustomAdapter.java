package com.injila.firebaseapp;

//CustomAdapter

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
            //viewHolder.imageView=(ImageView) convertView.findViewById(R.id.imageView);
            //Up to here
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //MODIFY THIS BLOCK OF CODE
        ColumnConstructor person = data.get(position);//modify here
        viewHolder.tvUsername.setText(person.getName());//modify here
        viewHolder.tvEmail.setText(person.getEmail());//modify here
        viewHolder.tvCountry.setText(person.getCountry());//modify here
        //viewHolder.imageView.setImageResource(person.getImage());
        return convertView;

    }
    static class ViewHolder {
        TextView tvUsername,tvEmail,tvCountry;
    }

}
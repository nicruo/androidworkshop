package com.nicruo.androidworkshop.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nicruo.androidworkshop.R;
import com.nicruo.androidworkshop.model.NamedAPIResource;

import java.util.List;

public class NamedAPIResourceAdapter extends ArrayAdapter<NamedAPIResource> {

    Activity activity;
    List<NamedAPIResource> objects;

    public NamedAPIResourceAdapter(@NonNull Context context, int resource, @NonNull List<NamedAPIResource> objects) {
        super(context, resource, objects);
        activity = (Activity)context;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = activity.getLayoutInflater().inflate( R.layout.poke_list_item, parent, false);

        TextView textView1 = view.findViewById(R.id.textView1);
        TextView textView2 = view.findViewById(R.id.textView2);

        textView1.setText(objects.get(position).getName());

        textView2.setText(objects.get(position).getUrl());


        return view;
    }
}
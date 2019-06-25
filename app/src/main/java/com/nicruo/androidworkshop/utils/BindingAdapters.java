package com.nicruo.androidworkshop.utils;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.ListView;

import com.nicruo.androidworkshop.R;
import com.nicruo.androidworkshop.model.NamedAPIResource;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BindingAdapters {

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {

        if(!TextUtils.isEmpty(imageUrl))
            Picasso.get().load(imageUrl).into(view);
    }

    @BindingAdapter({"listAdapter"})
    public static void loadListAdapter(ListView view, List<NamedAPIResource> items) {
        if(items != null) {

            NamedAPIResourceAdapter listAdapter = new NamedAPIResourceAdapter(view.getContext(), R.layout.poke_list_item, items);
            view.setAdapter(listAdapter);
        }
    }

}

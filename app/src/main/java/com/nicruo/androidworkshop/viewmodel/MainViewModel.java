package com.nicruo.androidworkshop.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.nicruo.androidworkshop.HomeActivity;

public class MainViewModel {

    public ObservableField<String> pokemonCount;
    public ObservableField<String> pokemonNumber;

    public ObservableBoolean isLoading;

    private Activity activity;

    public MainViewModel(Activity activity) {
        this.isLoading = new ObservableBoolean(false);
        this.pokemonCount = new ObservableField<>("");
        this.pokemonNumber = new ObservableField<>("");
        this.activity = activity;
    }

    public void loadData() {
        pokemonCount.set("Loading...");
    }

    public void goToHome(Context context)
    {
        Intent intent = new Intent(activity, HomeActivity.class);
        intent.putExtra("pokemonID", Integer.parseInt(pokemonNumber.get()));
        activity.startActivity(intent);
    }


}

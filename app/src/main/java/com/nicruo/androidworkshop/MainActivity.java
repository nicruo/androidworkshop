package com.nicruo.androidworkshop;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nicruo.androidworkshop.databinding.ActivityMainBinding;
import com.nicruo.androidworkshop.model.NamedList;
import com.nicruo.androidworkshop.model.Pokemon;
import com.nicruo.androidworkshop.services.PokeAPIService;
import com.nicruo.androidworkshop.viewmodel.MainViewModel;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit;
    PokeAPIService pokeAPIService;
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainViewModel = new MainViewModel(this);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(mainViewModel);
        mainViewModel.loadData();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        pokeAPIService = retrofit.create(PokeAPIService.class);


        mainViewModel.isLoading.set(true);

        Call<NamedList> pokemonNamedListCall = pokeAPIService.getPokemonNamedList();
        pokemonNamedListCall.enqueue(new Callback<NamedList>() {
            @Override
            public void onResponse(Call<NamedList> call, Response<NamedList> response) {

                mainViewModel.pokemonCount.set("There are " + response.body().getCount().toString() + " Pokemon");
                mainViewModel.isLoading.set(false);
            }

            @Override
            public void onFailure(Call<NamedList> call, Throwable t) {
                mainViewModel.isLoading.set(false);
            }
        });
    }
}

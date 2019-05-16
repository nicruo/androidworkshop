package com.nicruo.androidworkshop;

import android.content.Context;
import android.content.Intent;
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
import com.nicruo.androidworkshop.model.NamedList;
import com.nicruo.androidworkshop.model.Pokemon;
import com.nicruo.androidworkshop.services.PokeAPIService;

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

    Button loginButton;
    EditText nameEditText;
    TextView pokemonCountTextView;
    LinearLayout loadingView;

    Retrofit retrofit;
    PokeAPIService pokeAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = findViewById(R.id.loginButton);
        nameEditText = findViewById(R.id.nameEditText);
        pokemonCountTextView = findViewById(R.id.pokemonCountTextView);
        loadingView = findViewById(R.id.loadingView);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        pokeAPIService = retrofit.create(PokeAPIService.class);


        Call<NamedList> pokemonNamedListCall = pokeAPIService.getPokemonNamedList();
        pokemonNamedListCall.enqueue(new Callback<NamedList>() {
            @Override
            public void onResponse(Call<NamedList> call, Response<NamedList> response) {
                pokemonCountTextView.setText("There are " + response.body().getCount().toString() + " Pokemon");
                loadingView.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<NamedList> call, Throwable t) {

            }
        });




        final Context context = this;

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, HomeActivity.class);
                intent.putExtra("pokemonID", Integer.parseInt(nameEditText.getText().toString()));
                startActivity(intent);



                    //loginButton.setText(value);


            }
        });

    }
}

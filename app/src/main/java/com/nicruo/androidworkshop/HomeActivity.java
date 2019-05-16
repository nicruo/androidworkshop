package com.nicruo.androidworkshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.nicruo.androidworkshop.model.Pokemon;
import com.nicruo.androidworkshop.model.Post;
import com.nicruo.androidworkshop.services.PokeAPIService;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {

    TextView pokemonNameTextView;
    TextView pokemonBaseExperienceTextView;
    LinearLayout loadingView;
    ListView listView;

    Retrofit retrofit;
    Retrofit retrofitPost;
    PokeAPIService pokeAPIService;
    PokeAPIService pokeAPIServicePost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        pokemonNameTextView = findViewById(R.id.pokemonNameTextView);
        pokemonBaseExperienceTextView = findViewById(R.id.pokemonBaseExperienceTextView);
        loadingView = findViewById(R.id.loadingView);
        listView = findViewById(R.id.listView);


        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[] {
                "um",
                "um",
                "um",
                "sfgfdg",
                "dois",
                "um",
                "sfgfdg",
                "um",
                "sfgfdg",
                "dois",
                "um",
                "sfgfdg",
                "sdfdf"
        });

        listView.setAdapter(listAdapter);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitPost = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        pokeAPIService = retrofit.create(PokeAPIService.class);
        pokeAPIServicePost = retrofitPost.create(PokeAPIService.class);


        Call<Pokemon> getPokemonCall = pokeAPIService.getPokemon(getIntent().getExtras().getInt("pokemonID"));

        loadingView.setVisibility(View.VISIBLE);

        getPokemonCall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {

                pokemonNameTextView.setText(response.body().getName());
                pokemonBaseExperienceTextView.setText(response.body().getBaseExperience().toString());

                loadingView.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {

            }
        });


        Call<Post> postPostCall = pokeAPIServicePost.postPost("hello", "world", 1);
        postPostCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });

    }
}

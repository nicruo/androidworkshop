package com.nicruo.androidworkshop;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.nicruo.androidworkshop.model.NamedAPIResource;
import com.nicruo.androidworkshop.model.Pokemon;
import com.nicruo.androidworkshop.model.PokemonMove;
import com.nicruo.androidworkshop.model.Post;
import com.nicruo.androidworkshop.services.PokeAPIService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

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
    Activity activity;
    Retrofit retrofit;
    Retrofit retrofitPost;
    PokeAPIService pokeAPIService;
    PokeAPIService pokeAPIServicePost;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        pokemonNameTextView = findViewById(R.id.pokemonNameTextView);
        pokemonBaseExperienceTextView = findViewById(R.id.pokemonBaseExperienceTextView);
        loadingView = findViewById(R.id.loadingView);
        listView = findViewById(R.id.listView);
        imageView = findViewById(R.id.imageView);

        activity = this;

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


                ArrayList<NamedAPIResource> moveList = new ArrayList<>();

                for( PokemonMove move : response.body().getMoves()) {
                    moveList.add(move.getMove());
                }


                NamedAPIResourceAdapter listAdapter = new NamedAPIResourceAdapter(activity, R.layout.poke_list_item, moveList);

                listView.setAdapter(listAdapter);

                Picasso.get().load(response.body().getSprites().getFrontDefault()).into(imageView);

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

        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Select", "item: " + listView.getAdapter().getItem(position));
            }
        });

    }

    class NamedAPIResourceAdapter extends ArrayAdapter<NamedAPIResource> {

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

}

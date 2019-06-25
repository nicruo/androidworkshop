package com.nicruo.androidworkshop.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.nicruo.androidworkshop.model.NamedAPIResource;
import com.nicruo.androidworkshop.model.Pokemon;
import com.nicruo.androidworkshop.model.PokemonMove;
import com.nicruo.androidworkshop.services.PokeAPIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeViewModel {

    public ObservableField<String> pokemonName;

    public ObservableField<Integer> pokemonBaseExperience;

    public ObservableField<Boolean> isLoading;

    public ObservableField<String> imageUrl;

    public ObservableList<NamedAPIResource> moves;


    Retrofit retrofit;
    PokeAPIService pokeAPIService;

    public HomeViewModel(String pokemonName, Integer pokemonBaseExperience) {
        this.pokemonName = new ObservableField<>(pokemonName);
        this.pokemonBaseExperience = new ObservableField<>(pokemonBaseExperience);
        this.isLoading = new ObservableField<>(false);
        this.imageUrl = new ObservableField<>("");
        this.moves = new ObservableArrayList<>();
    }

    public void loadData(int pokemonId) {

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        pokeAPIService = retrofit.create(PokeAPIService.class);


        Call<Pokemon> getPokemonCall = pokeAPIService.getPokemon(pokemonId);

        isLoading.set(true);

        getPokemonCall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {

                pokemonName.set(response.body().getName());
                pokemonBaseExperience.set(response.body().getBaseExperience());

                moves.clear();
                for( PokemonMove move : response.body().getMoves()) {
                    moves.add(move.getMove());
                }

                imageUrl.set(response.body().getSprites().getFrontDefault());
                isLoading.set(false);
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {

            }
        });
    }
}
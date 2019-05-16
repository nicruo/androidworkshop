package com.nicruo.androidworkshop.services;

import com.nicruo.androidworkshop.model.NamedList;
import com.nicruo.androidworkshop.model.Pokemon;
import com.nicruo.androidworkshop.model.Post;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PokeAPIService {

    @GET("pokemon/{id}")
    Call<Pokemon> getPokemon(@Path("id") Integer id);

    @GET("pokemon")
    Call<NamedList> getPokemonNamedList();

    @POST("posts")
    @FormUrlEncoded
    Call<Post> postPost(@Field("title") String title, @Field("body") String body, @Field("userId") Integer userId);
}

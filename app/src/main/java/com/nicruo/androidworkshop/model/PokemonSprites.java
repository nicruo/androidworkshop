package com.nicruo.androidworkshop.model;

import com.google.gson.annotations.SerializedName;

public class PokemonSprites {

    @SerializedName("front_default")
    private String frontDefault;

    public PokemonSprites(String frontDefault) {
        this.frontDefault = frontDefault;
    }

    public String getFrontDefault() {
        return frontDefault;
    }

    public void setFrontDefault(String frontDefault) {
        this.frontDefault = frontDefault;
    }
}

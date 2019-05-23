package com.nicruo.androidworkshop.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pokemon {

    private Integer id;

    private String name;

    @SerializedName("base_experience")
    private Integer baseExperience;

    private List<PokemonMove> moves;

    private PokemonSprites sprites;

    public Pokemon(Integer id, String name, Integer baseExperiencem, List<PokemonMove> moves, PokemonSprites sprites) {
        this.id = id;
        this.name = name;
        this.baseExperience = baseExperience;
        this.moves = moves;
        this.sprites = sprites;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(Integer baseExperience) {
        this.baseExperience = baseExperience;
    }

    public List<PokemonMove> getMoves() {
        return moves;
    }

    public void setMoves(List<PokemonMove> moves) {
        this.moves = moves;
    }

    public PokemonSprites getSprites() {
        return sprites;
    }

    public void setSprites(PokemonSprites sprites) {
        this.sprites = sprites;
    }
}

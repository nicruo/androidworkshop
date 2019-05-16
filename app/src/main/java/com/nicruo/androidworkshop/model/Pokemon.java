package com.nicruo.androidworkshop.model;

import com.google.gson.annotations.SerializedName;

public class Pokemon {

    private Integer id;

    private String name;

    @SerializedName("base_experience")
    private Integer baseExperience;

    public Pokemon(Integer id, String name, Integer baseExperience) {
        this.id = id;
        this.name = name;
        this.baseExperience = baseExperience;
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
}

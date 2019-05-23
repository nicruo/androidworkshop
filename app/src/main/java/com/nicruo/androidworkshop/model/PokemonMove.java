package com.nicruo.androidworkshop.model;

public class PokemonMove {

    private NamedAPIResource move;

    public PokemonMove(NamedAPIResource move) {
        this.move = move;
    }

    public NamedAPIResource getMove() {
        return move;
    }

    public void setMove(NamedAPIResource move) {
        this.move = move;
    }
}

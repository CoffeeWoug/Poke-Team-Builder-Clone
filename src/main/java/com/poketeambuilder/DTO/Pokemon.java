package com.poketeambuilder.DTO;

public class Pokemon {
    
    private int pokedexID;
    private String pokemonName;
    private String pokemonType;
    private String pokedexEntry;
    private int hp;
    private int attack;
    private int defence;
    private int specialAttack;
    private int specialDefence;
    private int speed;

    public Pokemon() {
        
    }

    public Pokemon(int pokedexID, String pokemonName, String pokemonType, String pokedexEntry, int hp, int attack, int defence, int specialAttack, int specialDefence, int speed) {
        this.pokedexID = pokedexID;
        this.pokemonName = pokemonName;
        this.pokemonType = pokemonType;
        this.pokedexEntry = pokedexEntry;
        this.hp = hp;
        this.attack = attack;
        this.defence = defence;
        this.specialAttack = specialAttack;
        this.specialDefence = specialDefence;
        this.speed = speed;
    }

    public int getPokedexID() {
        return pokedexID;
    }

    public void setPokedexID(int pokedexID) {
        this.pokedexID = pokedexID;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public String getPokemonType() {
        return pokemonType;
    }

    public void setPokemonType(String pokemonType) {
        this.pokemonType = pokemonType;
    }

    public String getPokedexEntry() {
        return pokedexEntry;
    }

    public void setPokedexEntry(String pokedexEntry) {
        this.pokedexEntry = pokedexEntry;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(int specialAttack) {
        this.specialAttack = specialAttack;
    }

    public int getSpecialDefence() {
        return specialDefence;
    }

    public void setSpecialDefence(int specialDefence) {
        this.specialDefence = specialDefence;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    
}

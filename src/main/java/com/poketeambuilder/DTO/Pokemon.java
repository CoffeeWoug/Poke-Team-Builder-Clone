package com.poketeambuilder.DTO;

// import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/* 
@JsonPropertyOrder({
    "pokedexID",
    "pokemonName",
    "pokemonType",
    "hp",
    "attack",
    "defence",
    "specialAttack",
    "specialDefence",
    "speed",
    "total"
})
*/

public class Pokemon {
    
    private int pokedexID;
    private String pokemonName;
    private String pokemonType;
    private int hp;
    private int attack;
    private int defence;
    private int specialAttack;
    private int specialDefence;
    private int speed;
    private int total;
    private List<String> resistances;
    private List<String> weaknesses;

    public Pokemon() {

    }

    public Pokemon(int pokedexID, String pokemonName, String pokemonType, int hp, int attack, int defence, int specialAttack, int specialDefence, int speed, int total, List<String> resistances, List<String> weaknesses) {
        this.pokedexID = pokedexID;
        this.pokemonName = pokemonName;
        this.pokemonType = pokemonType;
        this.hp = hp;
        this.attack = attack;
        this.defence = defence;
        this.specialAttack = specialAttack;
        this.specialDefence = specialDefence;
        this.speed = speed;
        this.total = total;
        this.resistances = resistances;
        this.weaknesses = weaknesses;
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

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<String> getResistances() {
        return this.resistances;
    }

    public void setResistances(List<String> resistances) {
        this.resistances = resistances;
    }

    public List<String> getWeaknesses() {
        return this.weaknesses;
    }

    public void setWeaknesses(List<String> weaknesses) {
        this.weaknesses = weaknesses;
    }

    
}

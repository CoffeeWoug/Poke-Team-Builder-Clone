package com.poketeambuilder.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.poketeambuilder.DAO.PokedexDAO;
import com.poketeambuilder.DTO.Pokemon;

@Service 
public class PokedexService {

    @Autowired 
    private PokedexDAO pokedexDAO;
    
    public List<Pokemon> getAllPokemon() {
        return pokedexDAO.getAllPokemon();
    }

    public Pokemon getPokemon(String pokemonName) {
        return pokedexDAO.getPokemon(pokemonName);
    }

    public void createPokemon(Pokemon pokemon) {
        pokedexDAO.createPokemon(pokemon);
    }

    public void editPokemon(String pokemonName, Pokemon pokemon) {
        pokedexDAO.editPokemon(pokemonName, pokemon);
    }

    public void deletePokemon(String pokemonName) {
        pokedexDAO.deletePokemon(pokemonName);
    }
}

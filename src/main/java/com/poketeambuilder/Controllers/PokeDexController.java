package com.poketeambuilder.Controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.poketeambuilder.DTO.Pokemon;
import com.poketeambuilder.Service.PokedexService;

@RestController 
@RequestMapping("/pokemon")
public class PokeDexController {

    private PokedexService pokedexService;

    // Constructor bean injection, no autowired needed unlike setter injection
    public PokeDexController(PokedexService pokedexService) {
        this.pokedexService = pokedexService;
    }

    @RequestMapping
    public List<Pokemon> getAllPokemon() {
        return pokedexService.getAllPokemon();
    }

    @RequestMapping("/{pokemonName}")
    public Pokemon getPokemon(@PathVariable("pokemonName") String pokemonName) {
        return pokedexService.getPokemon(pokemonName);
    }

    @PostMapping
    public void createPokemon(@RequestBody Pokemon pokemon) {
        pokedexService.createPokemon(pokemon);
    }

    @PutMapping("/{pokemonName}")
    public void editPokemon(@PathVariable("pokemonName") String pokemonName, @RequestBody Pokemon pokemon) {
        pokedexService.editPokemon(pokemonName, pokemon);
    }

    @DeleteMapping("/{pokemonName}")
    public void deletePokemon(@PathVariable("pokemonName") String pokemonName) {
        pokedexService.deletePokemon(pokemonName);
    } 

    
    
}

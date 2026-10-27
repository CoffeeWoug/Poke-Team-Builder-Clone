package com.poketeambuilder.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.poketeambuilder.DTO.Pokemon;
import com.poketeambuilder.Service.PokedexService;

@RestController 
public class PokeDexController {

    @Autowired 
    private PokedexService pokedexService;

    @RequestMapping("/pokemon")
    public List<Pokemon> getAllPokemon() {
        return pokedexService.getAllPokemon();
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "Controller is working";
    }
    
}

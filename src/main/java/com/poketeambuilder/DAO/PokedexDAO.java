package com.poketeambuilder.DAO;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.poketeambuilder.DTO.Pokemon;
import com.poketeambuilder.Mappers.PokemonMapper;

@Repository 
public class PokedexDAO {
    
    private JdbcTemplate jdbcTemplate;
    private PokemonMapper pokemonMapper;

    public PokedexDAO(JdbcTemplate jdbcTemplate, PokemonMapper pokemonMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.pokemonMapper = pokemonMapper;
    }


    public List<Pokemon> getAllPokemon() {
        String sql = "SELECT * FROM gen3Pokedex";
        List<Pokemon> pokedex = jdbcTemplate.query(sql, pokemonMapper);
        return pokedex;
    }

}

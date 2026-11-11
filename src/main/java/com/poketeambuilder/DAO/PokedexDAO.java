package com.poketeambuilder.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.poketeambuilder.DTO.Pokemon;
import com.poketeambuilder.Mappers.PokemonMapper;

@Repository 
public class PokedexDAO {
    
    private JdbcTemplate jdbcTemplate;
    private PokemonMapper pokemonMapper;

    public PokedexDAO(PokemonMapper pokemonMapper) {
        this.pokemonMapper = pokemonMapper;
    }

    @Autowired 
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Pokemon> getAllPokemon() {
        String sql = "SELECT * FROM gen3Pokemon";
        List<Pokemon> pokedex = jdbcTemplate.query(sql, pokemonMapper);
        return pokedex;
    }

    public Pokemon getPokemon(String pokemonName) {
        String sql = "SELECT * FROM gen3Pokemon WHERE pokemon_name = ?";
        Pokemon pokemon = jdbcTemplate.queryForObject(sql, pokemonMapper, pokemonName);
        return pokemon;
    }

    public void createPokemon(Pokemon pokemon) {
        String sql = "INSERT INTO gen3Pokemon" +
        "(pokedex_id, pokemon_name, pokemon_type, hp, attack, defence" + 
        ", special_attack, special_defence, speed, total)" +  
        "VALUES(?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, pokemon.getPokedexID(), pokemon.getPokemonName(), 
        pokemon.getPokemonType(), pokemon.getHp(), 
        pokemon.getAttack(), pokemon.getDefence(), pokemon.getSpecialAttack(),
        pokemon.getSpecialDefence(), pokemon.getSpeed(), pokemon.getTotal());
    }

    public void editPokemon(String pokemonName, Pokemon pokemon) {
        String sql = "UPDATE gen3Pokemon" +
        " SET pokedex_id = ?, pokemon_name = ?, pokemon_type = ?, hp = ?, attack = ?," + 
        "defence = ?, special_attack = ?, special_defence = ?," 
        + " speed = ?, total = ? WHERE pokemonName = ?";
        jdbcTemplate.update(sql, pokemon.getPokedexID(), pokemon.getPokemonName(), 
        pokemon.getPokemonType(), pokemon.getHp(), pokemon.getAttack(), 
        pokemon.getDefence(), pokemon.getSpecialAttack(),
        pokemon.getSpecialDefence(), pokemon.getSpeed(), pokemon.getTotal(),
        pokemonName);   
    }

    public void deletePokemon(String pokemonName) {
        String sql = "DELETE FROM gen3Pokemon WHERE pokemon_name = ?";
        jdbcTemplate.update(sql, pokemonName);  

    }

}

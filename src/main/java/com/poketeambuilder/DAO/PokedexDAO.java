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

    // SETTER INJECTION 
    
    @Autowired
    public void setPokemonMapper(PokemonMapper pokemonMapper) {
        this.pokemonMapper = pokemonMapper;
    }

    @Autowired 
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Pokemon> getAllPokemon() {
        String sql = "SELECT pokemon.pokemon_id, pokemon.pokemon_name, " +
        "table_one.pokemon_type_name AS pokemon_primary_type, " +
        "table_two.pokemon_type_name AS pokemon_secondary_type," +
        "pokemon.pokemon_hp, pokemon.pokemon_attack, pokemon.pokemon_defence, " +
        "pokemon.pokemon_special_attack, pokemon.pokemon_special_defence, " +
        "pokemon.pokemon_speed, pokemon.pokemon_total " +
        "FROM pokemon " +
        "INNER JOIN pokemonTypes AS table_one " +
        "ON pokemon.pokemon_primary_type = table_one.type_id " +
        "LEFT JOIN pokemonTypes AS table_two " +
        "ON pokemon.pokemon_secondary_type = table_two.type_id " +
        "ORDER BY pokemon.pokemon_id";
        List<Pokemon> pokemon = jdbcTemplate.query(sql, pokemonMapper);
        return pokemon;
    }

    /*pokemon_type_name renamed due to mapper class, the mapper cannot find pokemon_type_name,
      it has nothing to do with the ON statement, that is different */
    public Pokemon getPokemon(String pokemonName) {
        String sql = "SELECT pokemon.pokemon_id, pokemon.pokemon_name, " +
        "type_one.pokemon_type_name AS pokemon_primary_type, " +
        "type_two.pokemon_type_name AS pokemon_secondary_type, " + 
        "pokemon.pokemon_hp, " +
        "pokemon.pokemon_attack, pokemon.pokemon_defence, pokemon.pokemon_special_attack, " +
        "pokemon.pokemon_special_defence, pokemon.pokemon_speed, pokemon.pokemon_total " +
        "FROM pokemon " + 
        "INNER JOIN pokemonTypes AS type_one " +
        "ON pokemon.pokemon_primary_type = type_one.type_id " +
        "LEFT JOIN pokemonTypes AS type_two " +
        "ON pokemon.pokemon_secondary_type = type_two.type_id " +
        "WHERE pokemon.pokemon_name = ?";
        Pokemon pokemon = jdbcTemplate.queryForObject(sql, pokemonMapper, pokemonName); 
        return pokemon;
    }

    public void createPokemon(Pokemon pokemon) {
        String getType = "SELECT type_id " +
        "FROM pokemonTypes " +
        "WHERE pokemon_type_name = ?";

        int primaryPokemonType = jdbcTemplate.queryForObject(getType, Integer.class,
        pokemon.getPokemonPrimaryType());

        Integer secondaryPokemonType = null;
        if(pokemon.getPokemonSecondaryType() != null) {
            secondaryPokemonType = jdbcTemplate.queryForObject(getType, Integer.class,
            pokemon.getPokemonSecondaryType());
        }

        String sql = "INSERT INTO pokemon (pokemon_id, pokemon_name, pokemon_primary_type, " +
        "pokemon_secondary_type, pokemon_hp, pokemon_attack, pokemon_defence, " + 
        "pokemon_special_attack, pokemon_special_defence, pokemon_speed, pokemon_total) " + 
        "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, pokemon.getPokedexID(), pokemon.getPokemonName(),
        primaryPokemonType, secondaryPokemonType, pokemon.getHp(),
        pokemon.getAttack(), pokemon.getDefence(), pokemon.getSpecialAttack(),
        pokemon.getSpecialDefence(), pokemon.getSpeed(), pokemon.getTotal());
    }

    public void editPokemon(String pokemonName, Pokemon pokemon) {
        String findTypeSql = "SELECT type_id FROM pokemonTypes WHERE pokemon_type_name = ?";
        
        int primaryPokemonType = jdbcTemplate.queryForObject(findTypeSql, 
        Integer.class, pokemon.getPokemonPrimaryType());
        Integer secondaryPokemonType = null;
        if(pokemon.getPokemonSecondaryType() != null) {
            secondaryPokemonType = jdbcTemplate.queryForObject(findTypeSql, 
            Integer.class, pokemon.getPokemonSecondaryType());
        }

        String sql = "UPDATE pokemon " +
        "SET pokemon_id = ?, pokemon_name = ?, pokemon_primary_type = ?, " +
        "pokemon_secondary_type = ?, pokemon_hp = ?, pokemon_attack = ?, " + 
        "pokemon_defence = ?, pokemon_special_attack = ?, pokemon_special_defence = ?, " +
        "pokemon_speed = ?, pokemon_total = ? " +
        "WHERE pokemon_name = ?";

        jdbcTemplate.update(sql, pokemon.getPokedexID(), pokemon.getPokemonName(),
        primaryPokemonType, secondaryPokemonType, pokemon.getHp(), pokemon.getAttack(),
        pokemon.getDefence(), pokemon.getSpecialAttack(), pokemon.getSpecialDefence(),
        pokemon.getSpeed(), pokemon.getTotal(), pokemonName);
    }

    public void deletePokemon(String pokemonName) {
        String sql = "DELETE FROM gen3Pokemon WHERE pokemon_name = ?";
        jdbcTemplate.update(sql, pokemonName);  

    }

}

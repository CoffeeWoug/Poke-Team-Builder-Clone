package com.poketeambuilder.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.poketeambuilder.DTO.Pokemon;
import com.poketeambuilder.Mappers.PokemonMapper;

@Repository 
public class PokedexDAO {
    
    private JdbcTemplate jdbcTemplate;
    private PokemonMapper pokemonMapper;
    private TypeChartDAO typeChartDAO;

    // CONSTRUCTOR INJECTION
    public PokedexDAO(TypeChartDAO typeChartDAO) {
        this.typeChartDAO = typeChartDAO;
        typeChartDAO.setTypeChart();
    }

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
        "primary_type.pokemon_type_name AS pokemon_primary_type, " +
        "secondary_type.pokemon_type_name AS pokemon_secondary_type," +
        "pokemon.pokemon_hp, pokemon.pokemon_attack, pokemon.pokemon_defence, " +
        "pokemon.pokemon_special_attack, pokemon.pokemon_special_defence, " +
        "pokemon.pokemon_speed, pokemon.pokemon_total " +
        "FROM pokemon " +
        "INNER JOIN pokemonTypes AS primary_type " +
        "ON pokemon.pokemon_primary_type = primary_type.type_id " +
        "LEFT JOIN pokemonTypes AS secondary_type " +
        "ON pokemon.pokemon_secondary_type = secondary_type.type_id " +
        "ORDER BY pokemon.pokemon_id";

        List<Pokemon> pokemon = new ArrayList<>();
        List<Map<String, Object>> pokemonQuery = jdbcTemplate.queryForList(sql);

        for(Map<String, Object> pokemonRow : pokemonQuery) {
            Pokemon currentPokemon = new Pokemon();
            currentPokemon.setPokedexID((int) pokemonRow.get("pokemon_id"));
            currentPokemon.setPokemonName((String) pokemonRow.get("pokemon_name"));
            currentPokemon.setPokemonPrimaryType((String) pokemonRow.get("pokemon_primary_type"));
            
        }

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
        if(pokemon.getPokemonSecondaryType() != null) {
            pokemon.setWeaknesses(typeChartDAO.getWeaknesses(pokemon.getPokemonPrimaryType(),
            pokemon.getPokemonSecondaryType()));
            pokemon.setResistances(typeChartDAO.getResistances(pokemon.getPokemonPrimaryType()
            , pokemon.getPokemonSecondaryType()));
        } else {
            pokemon.setWeaknesses(typeChartDAO.getWeaknesses(pokemon.getPokemonPrimaryType()));
            pokemon.setResistances(typeChartDAO.getResistances(pokemon.getPokemonPrimaryType()));
        }
        
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
        String sql = "DELETE FROM pokemon WHERE pokemon_name = ?";
        jdbcTemplate.update(sql, pokemonName);  

    }

}

package com.poketeambuilder.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.poketeambuilder.DTO.Pokemon;

@Component 
public class PokemonMapper implements RowMapper<Pokemon> {

    public Pokemon mapRow(ResultSet rs, int row) throws SQLException{
        Pokemon pokemon = new Pokemon();
        pokemon.setPokedexID(rs.getInt("pokemon_id"));
        pokemon.setPokemonName(rs.getString("pokemon_name"));
        pokemon.setPokemonPrimaryType(rs.getString("pokemon_primary_type"));
        pokemon.setPokemonSecondaryType(rs.getString("pokemon_secondary_type"));
        pokemon.setHp(rs.getInt("pokemon_hp"));
        pokemon.setAttack(rs.getInt("pokemon_attack"));
        pokemon.setDefence(rs.getInt("pokemon_defence"));
        pokemon.setSpecialAttack(rs.getInt("pokemon_special_attack"));
        pokemon.setSpecialDefence(rs.getInt("pokemon_special_defence"));
        pokemon.setSpeed(rs.getInt("pokemon_speed"));
        pokemon.setTotal(rs.getInt("pokemon_total"));
        return pokemon;
    }
    
}

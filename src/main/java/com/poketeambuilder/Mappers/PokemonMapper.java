package com.poketeambuilder.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.poketeambuilder.DTO.Pokemon;

@Component 
public class PokemonMapper implements RowMapper<Pokemon> {

    public Pokemon mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pokemon pokemon = new Pokemon();  
        pokemon.setPokedexID(rs.getInt("pokedex_id"));
        pokemon.setPokemonName(rs.getString("pokemon_name"));
        pokemon.setPokemonType(rs.getString("pokemon_type"));
        pokemon.setHp(rs.getInt("hp"));
        pokemon.setAttack(rs.getInt("attack"));
        pokemon.setDefence(rs.getInt("defence"));
        pokemon.setSpecialAttack(rs.getInt("special_attack"));
        pokemon.setSpecialDefence(rs.getInt("special_defence"));
        pokemon.setSpeed(rs.getInt("speed"));
        pokemon.setTotal(rs.getInt("total"));
        return pokemon;
    }
    
}

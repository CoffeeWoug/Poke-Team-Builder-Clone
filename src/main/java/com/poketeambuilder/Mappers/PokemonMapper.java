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
        pokemon.setPokedexID(rs.getInt("pokedexID"));
        pokemon.setPokemonName(rs.getString("pokemonName"));
        pokemon.setPokemonType(rs.getString("pokemonType"));
        pokemon.setPokedexEntry(rs.getString("pokedexEntry"));
        pokemon.setHp(rs.getInt("hp"));
        pokemon.setAttack(rs.getInt("attack"));
        pokemon.setDefence(rs.getInt("defence"));
        pokemon.setSpecialAttack(rs.getInt("specialAttack"));
        pokemon.setSpecialDefence(rs.getInt("specialDefence"));
        pokemon.setSpeed(rs.getInt("speed"));
        return pokemon;
    }
    
}

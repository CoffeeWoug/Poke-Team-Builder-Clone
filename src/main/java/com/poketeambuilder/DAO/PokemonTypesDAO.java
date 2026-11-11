package com.poketeambuilder.DAO;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Repository 
public class PokemonTypesDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJDBCTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> pokemonWeaknesses() {
        String sql = ""
    }

    
    
}

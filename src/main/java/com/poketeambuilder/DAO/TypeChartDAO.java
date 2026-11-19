package com.poketeambuilder.DAO;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.HashMap;

@Repository 
public class TypeChartDAO {

    private JdbcTemplate jdbcTemplate;
    private Map<String, Double> typeChart = new HashMap<>();

    // Constructor injection
    public TypeChartDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    } 

    public void getWeaknesses(String type1) {

    }

    public void getWeaknesses(String type1, String type2) {
        
    }




}

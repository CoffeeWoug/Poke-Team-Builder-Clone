package com.poketeambuilder.DAO;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.poketeambuilder.DTO.PokemonTypeMatchUp;

@Repository 
public class TypeChartDAO {

    private JdbcTemplate jdbcTemplate;
    private Map<String, List<PokemonTypeMatchUp>> typeChart;

    public TypeChartDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.typeChart = new HashMap<>();
    }

    public void setTypeChart() {
        String sql = "SELECT table_one.pokemon_type_name AS defending_type, " +
        "table_two.pokemon_type_name AS attacking_type, damage_percent " +
        "FROM pokemonTypeComparisons " +
        "INNER JOIN pokemonTypes AS table_one " +
        "ON defending_type = table_one.type_id " +
        "INNER JOIN pokemonTypes AS table_two " +
        "ON attacking_type = table_two.type_id " +
        "ORDER BY table_one.type_id";

        List<Map<String, Object>> pokemonTypesMap = jdbcTemplate.queryForList(sql);

        for(Map<String, Object> map : pokemonTypesMap) {
            PokemonTypeMatchUp typeMatchUp = new PokemonTypeMatchUp();
            typeMatchUp.setDefendingType((String) map.get("defending_type"));
            typeMatchUp.setAttackingType((String) map.get("attacking_type"));
            typeMatchUp.setDamageMultiplier((BigDecimal) map.get("damage_percent"));

            if(!typeChart.containsKey(typeMatchUp.getDefendingType())) {
                List<PokemonTypeMatchUp> matchupList = new ArrayList<>();
                matchupList.add(typeMatchUp);
                typeChart.put(typeMatchUp.getDefendingType(), matchupList);
            } else {
                List<PokemonTypeMatchUp> matchupList = typeChart.get(typeMatchUp.getDefendingType());
                matchupList.add(typeMatchUp);
                typeChart.put(typeMatchUp.getDefendingType(), matchupList);
            }
        }
    }

    public List<String> getWeaknesses(String type) {
        List<PokemonTypeMatchUp> allTypeComparisons = typeChart.get(type);
        List<String> weaknesses = new ArrayList<>();

        BigDecimal weaknessMultiplier = new BigDecimal("2.0");

        for(PokemonTypeMatchUp ptm : allTypeComparisons) {
            if(ptm.getDamageMultiplier().compareTo(weaknessMultiplier) == 0)  {
                weaknesses.add(ptm.getAttackingType());
            }
        }

        return weaknesses;
    }
 
    public void displayMap() {
        System.out.println(getWeaknesses("Rock"));
    }
}

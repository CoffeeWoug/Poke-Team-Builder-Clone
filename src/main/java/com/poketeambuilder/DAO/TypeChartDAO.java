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

    public List<String> getWeaknesses(String primaryType, String secondaryType) {
        List<PokemonTypeMatchUp> primaryWeaknesses = typeChart.get(primaryType);
        List<PokemonTypeMatchUp> secondaryWeaknesses = typeChart.get(secondaryType);
        List<String> weaknesses = new ArrayList<>();

        for(int i = 0; i < primaryWeaknesses.size(); i++) {
            BigDecimal primaryDamage = primaryWeaknesses.get(i).getDamageMultiplier();
            BigDecimal secondaryDamage = secondaryWeaknesses.get(i).getDamageMultiplier();
            BigDecimal totalDamage = primaryDamage.multiply(secondaryDamage);

            BigDecimal weak = new BigDecimal("2.0");
            BigDecimal doubleDamage = new BigDecimal("4.0");

            if(totalDamage.compareTo(weak) == 0 || totalDamage.compareTo(doubleDamage) == 0) {
                weaknesses.add(primaryWeaknesses.get(i).getAttackingType());
            }
        }
        return weaknesses;
    }

    public List<String> getResistances(String primaryType) {
        List<PokemonTypeMatchUp> typeMatchUps = typeChart.get(primaryType);
        List<String> resistances = new ArrayList<>();
        BigDecimal resistantAmount = new BigDecimal("0.5");

        for(PokemonTypeMatchUp ptm : typeMatchUps) {
            if(ptm.getDamageMultiplier().compareTo(resistantAmount) == 0) {
                resistances.add(ptm.getAttackingType());
            }
        }
        return resistances;
    }

    public List<String> getResistances(String primaryType, String secondaryType) {
        List<PokemonTypeMatchUp> primaryTypeResistances = typeChart.get(primaryType);
        List<PokemonTypeMatchUp> secondaryTypeResistances = typeChart.get(secondaryType);
        List<String> resistances = new ArrayList<>();

        for(int i = 0; i < primaryTypeResistances.size(); i++) {
            BigDecimal primaryDamage = primaryTypeResistances.get(i).getDamageMultiplier();
            BigDecimal secondaryDamage = secondaryTypeResistances.get(i).getDamageMultiplier();
            BigDecimal totalDamage = primaryDamage.multiply(secondaryDamage);
            BigDecimal notEffective = new BigDecimal("0.5");
            BigDecimal notVeryEffective = new BigDecimal("0.25");

            if(totalDamage.compareTo(notEffective) == 0 || totalDamage.compareTo(notVeryEffective)
            == 0) {
                resistances.add(primaryTypeResistances.get(i).getAttackingType());
            }
        }

        return resistances;
    }

    public List<String> getImmunities(String primaryType) {
        List<PokemonTypeMatchUp> typeMatchups = typeChart.get(primaryType);
        List<String> immunities = new ArrayList<>();

        for(PokemonTypeMatchUp ptm : typeMatchups) {
            BigDecimal totalDamage = ptm.getDamageMultiplier();
            BigDecimal zero = new BigDecimal("0");

            if(totalDamage.compareTo(zero) == 0) {
                immunities.add(ptm.getAttackingType());
            }
        }

        return immunities;
    }

    public List<String> getImmunities(String primaryType, String secondaryType) {
        List<PokemonTypeMatchUp> primaryTypeMatchups = typeChart.get(primaryType);
        List<PokemonTypeMatchUp> secondaryTypeMatchups = typeChart.get(secondaryType);
        List<String> immunities = new ArrayList<>();

        for(int i = 0; i < primaryTypeMatchups.size(); i++) {
            BigDecimal primaryTypeDamage = primaryTypeMatchups.get(i).getDamageMultiplier();
            BigDecimal secondaryTypeDamage = secondaryTypeMatchups.get(i).getDamageMultiplier();
            
            BigDecimal zero = new BigDecimal("0.0");

            if(primaryTypeDamage.compareTo(zero) == 0 || secondaryTypeDamage.compareTo(zero) == 0) {
                if(primaryTypeDamage.compareTo(zero) == 0) immunities.add(
                    primaryTypeMatchups.get(i).getAttackingType());
                if(secondaryTypeDamage.compareTo(zero) == 0) immunities.add(
                    secondaryTypeMatchups.get(i).getAttackingType());
            }
        }
        return immunities;
    }
 
}

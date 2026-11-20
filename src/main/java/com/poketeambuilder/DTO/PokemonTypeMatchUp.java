package com.poketeambuilder.DTO;

import java.math.BigDecimal;

public class PokemonTypeMatchUp {

    private String defendingType;
    private String attackingType;
    private BigDecimal damageMultiplier;

    public PokemonTypeMatchUp() {

    }

    public PokemonTypeMatchUp(String defendingType, String attackingType, BigDecimal damageMultiplier) {
        this.defendingType = defendingType;
        this.attackingType = attackingType;
        this.damageMultiplier = damageMultiplier;
    }

    public String getDefendingType() {
        return defendingType;
    }

    public void setDefendingType(String defendingType) {
        this.defendingType = defendingType;
    }

    public String getAttackingType() {
        return attackingType;
    }

    public void setAttackingType(String attackingType) {
        this.attackingType = attackingType;
    }

    public BigDecimal getDamageMultiplier() {
        return damageMultiplier;
    }

    public void setDamageMultiplier(BigDecimal damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }

    @Override 
    public String toString() {
        return " Defending type: " + this.defendingType + " Attacking type: " +
        this.attackingType + " Damage multiplier " + this.damageMultiplier;
    }

    




}

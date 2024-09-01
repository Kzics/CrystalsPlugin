package com.kzics.crystals.enums;

public enum CrystalType {
    ASTRAL,
    STRENGTH,
    LIFE,
    PUFF,
    WEALTH,
    SPEED,
    FIRE;

    CrystalType(){

    }

    public static CrystalType getRandom(){
        return values()[(int) (Math.random() * values().length)];
    }
}
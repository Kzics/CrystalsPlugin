package com.kzics.crystals.enums;

public enum CrystalType {
    ASTRAL(1),
    STRENGTH(2),
    LIFE(3),
    PUFF(4),
    WEALTH(5),
    SPEED(6),
    FIRE(7);

    private final int dataId;
    CrystalType(int dataId){
        this.dataId = dataId;

    }

    public int getDataId() {
        return dataId;
    }

    public static CrystalType getRandom(){
        return values()[(int) (Math.random() * values().length)];
    }
}
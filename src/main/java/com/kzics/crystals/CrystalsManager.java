package com.kzics.crystals;

import com.kzics.crystals.crystals.Ability;
import com.kzics.crystals.crystals.astral.AstralLeftClickAbility;
import com.kzics.crystals.crystals.astral.AstralRightClickAbility;
import com.kzics.crystals.enums.CrystalType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrystalsManager {

    private final Map<CrystalType, List<Ability>> abilities = new HashMap<>();

    public CrystalsManager(){
        this.loadAbilities();
    }

    public Ability getAbility(CrystalType type, int level){
        List<Ability> abilities = this.abilities.get(type);
        if (abilities == null) return null;

        if(level >= abilities.size()) return null;

        return abilities.get(level);
    }

    public List<Ability> getAbilities(CrystalType type){
        List<Ability> abilities = this.abilities.get(type);
        if (abilities == null) return new ArrayList<>();

        return abilities;

    }

    public void loadAbilities(){
        abilities.put(CrystalType.ASTRAL,List.of(new AstralLeftClickAbility(), new AstralRightClickAbility()));
    }

}

package com.kzics.crystals;

import com.kzics.crystals.crystals.Ability;
import com.kzics.crystals.crystals.astral.AstralAbility;
import com.kzics.crystals.crystals.fire.FireAbility;
import com.kzics.crystals.crystals.life.LifeAbility;
import com.kzics.crystals.crystals.puff.PuffAbility;
import com.kzics.crystals.crystals.speed.SpeedAbility;
import com.kzics.crystals.crystals.strength.StrengthAbility;
import com.kzics.crystals.crystals.wealth.WealthAbility;
import com.kzics.crystals.enums.CrystalType;
import org.bukkit.block.data.type.Fire;

import java.util.HashMap;
import java.util.Map;

public class CrystalsManager {

    private final Map<CrystalType, Ability> abilities = new HashMap<>();

    public CrystalsManager(){
        this.loadAbilities();
    }



    public Ability getAbilities(CrystalType type){

        return this.abilities.get(type);

    }

    public void loadAbilities(){
        abilities.put(CrystalType.ASTRAL,new AstralAbility());
        abilities.put(CrystalType.LIFE,new LifeAbility());
        abilities.put(CrystalType.STRENGTH,new StrengthAbility());
        abilities.put(CrystalType.PUFF,new PuffAbility());
        abilities.put(CrystalType.FIRE,new FireAbility());
        abilities.put(CrystalType.WEALTH,new WealthAbility());
        abilities.put(CrystalType.SPEED,new SpeedAbility());
    }

}

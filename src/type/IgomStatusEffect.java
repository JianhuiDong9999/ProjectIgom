package type;

import mindustry.Vars;
import mindustry.type.StatusEffect;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;
import mindustry.world.meta.StatValue;
import mindustry.world.meta.StatValues;

public class IgomStatusEffect extends StatusEffect {
    public float armor = 0f;
    public IgomStatusEffect(String name){
        super(name);
    }
    @Override
    public void setStats(){
        if(armor != 0) stats.add(Stat.armor, (StatValues.number(armor, StatUnit.none)));
        super.setStats();
    }
}

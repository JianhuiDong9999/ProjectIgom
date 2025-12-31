package world.blocks.liquid;

import arc.func.Boolf;
import mindustry.type.Item;
import mindustry.world.blocks.production.Pump;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;
import mindustry.world.meta.StatValues;

public class StatPump extends Pump {
    public StatPump(String name) {
        super(name);
    }
    @Override
    public void setStats(){
        super.setStats();
        if((hasItems && itemCapacity > 0) && consumeTime > 0){
            stats.add(Stat.input, 1 / consumeTime * 60f, StatUnit.perSecond);
        }
    }
}

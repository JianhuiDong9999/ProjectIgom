package world.blocks.turrets;

import mindustry.gen.Building;
import mindustry.type.Item;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;
import mindustry.world.meta.StatValues;

public class MultiItemTurret extends ItemTurret {
    // Not yet working. TODO: Implement MultiItemTurret.
    public MultiItemTurret(String name) {
        super(name);
    }
    @Override
    public void setStats(){
        super.setStats();
        stats.add(Stat.itemCapacity, itemCapacity, StatUnit.items);
    }
    public class MultiItemTurretBuild extends ItemTurretBuild {
        @Override
        public boolean acceptItem(Building source, Item item){
            return super.acceptItem(source, item) || (consumesItem(item) && items.length() < itemCapacity);
        }
    }
}

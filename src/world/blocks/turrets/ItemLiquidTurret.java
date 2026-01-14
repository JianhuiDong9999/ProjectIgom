package world.blocks.turrets;

import mindustry.Vars;
import mindustry.core.World;
import mindustry.entities.Fires;
import mindustry.entities.Units;
import mindustry.gen.Fire;
import mindustry.world.Tile;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.LiquidTurret;

import static mindustry.Vars.tilesize;

public class ItemLiquidTurret extends ItemTurret {
    public boolean extinguish = true;

    public ItemLiquidTurret(String name) {
        super(name);
        hasLiquids = true;
    }

    public class ItemLiquidTurretBuild extends ItemTurretBuild {
        @Override
        protected void findTarget() {
            if (extinguish) {
                int tx = World.toTile(x), ty = World.toTile(y);
                Fire result = null;
                float mindst = 0f;
                int tr = (int) (range / tilesize);
                for (int x = -tr; x <= tr; x++) {
                    for (int y = -tr; y <= tr; y++) {
                        Tile other = Vars.world.tile(x + tx, y + ty);
                        var fire = Fires.get(x + tx, y + ty);
                        float dst = fire == null ? 0 : dst2(fire);
                        //do not extinguish fires on other team blocks
                        if (other != null && fire != null && other.build != this && Fires.has(other.x, other.y) && dst <= range * range && (result == null || dst < mindst) && (other.build == null || other.team() == team)) {
                            result = fire;
                            mindst = dst;
                        }
                    }
                }

                if (result != null) {
                    target = result;
                    //don't run standard targeting
                    return;
                }
            }
            super.findTarget();
        }
    }
}

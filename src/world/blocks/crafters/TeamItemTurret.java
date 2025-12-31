package world.blocks.crafters;

import arc.graphics.g2d.TextureRegion;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.production.GenericCrafter;

public class TeamItemTurret extends ItemTurret {
    TextureRegion teamRegion;
    public TeamItemTurret(String name) {
        super(name);
    }
    @Override
    public void load(){
        super.load();
        //teamRegion = Core.atlas.find(name + "-team");
    }
    public class TeamItemTurretBuild extends ItemTurret.ItemTurretBuild {
        public TeamItemTurretBuild() {
            super();
        }
        @Override
        public void draw() {
            drawTeamTop();
            super.draw();
        }


    }
}

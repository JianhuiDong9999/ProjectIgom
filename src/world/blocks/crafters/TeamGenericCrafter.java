package world.blocks.crafters;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import mindustry.world.blocks.production.GenericCrafter;

public class TeamGenericCrafter extends GenericCrafter {
    TextureRegion teamRegion;
    public TeamGenericCrafter(String name) {
        super(name);
    }
    @Override
    public void load(){
        super.load();
        teamRegion = Core.atlas.find(name + "-team");
    }
    public class TeamGenericCrafterBuild extends GenericCrafterBuild {
        public TeamGenericCrafterBuild() {
            super();
        }
        @Override
        public void draw() {
            super.draw();
            drawTeamTop();
        }

    }
}

package world.blocks.extraction;

import mindustry.world.blocks.production.Fracker;

public class TeamFracker extends Fracker {
    public TeamFracker(String name){
        super(name);
    }

    public class TeamFrackerBuild extends FrackerBuild {
        @Override
        public void draw(){
            super.draw();
            drawTeamTop();
        }
    }
}

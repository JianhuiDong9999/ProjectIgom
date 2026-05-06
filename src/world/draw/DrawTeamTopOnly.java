package world.draw;

import mindustry.gen.Building;
import mindustry.world.draw.DrawBlock;

public class DrawTeamTopOnly extends DrawBlock{
    @Override
    public void draw(Building build){
        build.drawTeamTop();
    }
}

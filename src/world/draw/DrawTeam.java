package world.draw;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.util.Eachable;
import mindustry.Vars;
import mindustry.entities.units.BuildPlan;
import mindustry.gen.Building;
import mindustry.world.Block;
import mindustry.world.draw.DrawBlock;

import static mindustry.Vars.player;

public class DrawTeam extends DrawBlock{
    public DrawTeam(){}
    @Override
    public void draw(Building build){
        build.drawTeamTop();
    }

    @Override
    public void drawPlan(Block block, BuildPlan plan, Eachable<BuildPlan> list){
        if(plan.worldContext && player != null && block.teamRegion != null && block.teamRegion.found()){
            if(block.teamRegions[player.team().id] == block.teamRegion) Draw.color(player.team().color);
            Draw.rect(block.teamRegions[player.team().id], plan.drawx(), plan.drawy());
            Draw.color();
        }
    }

    @Override
    public TextureRegion[] icons(Block block){
        return new TextureRegion[]{block.teamRegion};
    }

}

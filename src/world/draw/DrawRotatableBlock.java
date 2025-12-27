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

public class DrawRotatableBlock extends DrawBlock{
    public TextureRegion main, side;


    public int rotOffset = 0;
    public boolean drawGlow = true;

    public DrawRotatableBlock(){}

    public DrawRotatableBlock(int rotOffset){
        this.rotOffset = rotOffset;
    }

    @Override
    public void draw(Building build){
        Draw.rect(Mathf.mod((build.rotation + rotOffset), 2) != 1 ? side : main, build.x, build.y, 0);
    }

    @Override
    public void drawPlan(Block block, BuildPlan plan, Eachable<BuildPlan> list){
        Draw.rect(Mathf.mod((plan.rotation + rotOffset), 2) != 1 ? side : main, plan.drawx(), plan.drawy(), 0);
        if(plan.worldContext && player != null && block.teamRegion != null && block.teamRegion.found()){
            if(block.teamRegions[player.team().id] == block.teamRegion) Draw.color(player.team().color);
            Draw.rect(block.teamRegions[player.team().id], plan.drawx(), plan.drawy());
            Draw.color();
        }
    }

    @Override
    public TextureRegion[] icons(Block block){
        return new TextureRegion[]{main, block.teamRegion};
    }
    @Override
    public void load(Block block){
        main = Core.atlas.find(block.name);
        side = Core.atlas.find(block.name + "-side");
    }

}

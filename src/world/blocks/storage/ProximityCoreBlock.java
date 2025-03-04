package world.blocks.storage;

import arc.Core;
import arc.graphics.Color;
import mindustry.Vars;
import mindustry.game.Team;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.world.Build;
import mindustry.world.Tile;
import mindustry.world.blocks.ConstructBlock;
import mindustry.world.blocks.storage.CoreBlock;

import static mindustry.Vars.player;
import static mindustry.Vars.tilesize;

public class ProximityCoreBlock extends CoreBlock {
    public int radius;
    public ProximityCoreBlock(String name) {
        super(name);
        this.radius = 0;
    }
    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        Tile tile = Vars.world.tile(x, y);
        // draw the core range box
        Drawf.dashSquare(Pal.placing,x * tilesize + offset, y * tilesize + offset, (float)((this.size + this.radius * 2) * 8));

        if(tile == null) return;

        if(!canPlaceOn(tile, player.team(), rotation)){

            drawPlaceText(!coreCheck(size % 2 == 0 ? x - 1 : x, size % 2 == 0 ? y - 1 : y) ? "Too Close To Another Core" : Core.bundle.get(
                    isFirstTier ?
                            //TODO better message
                            "bar.corefloor" :
                            (player.team().core() != null && player.team().core().items.has(requirements, Vars.state.rules.buildCostMultiplier)) || Vars.state.rules.infiniteResources ?
                                    "bar.corereq" :
                                    "bar.noresources"
            ), x, y, valid);
        }
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation){
        return super.canPlaceOn(tile, team, rotation) && coreCheck(size % 2 == 0 ? tile.x - 1 : tile.x, size % 2 == 0 ? tile.y - 1 : tile.y);
    }
    //TODO: Flawed design with CoreCheck. Requires algorithm compatible with all core sizes.
    public boolean coreCheck(int tx, int ty) {

        for(int ax = tx - radius; ax < tx + radius + size; ax++) {
            for(int ay = ty - radius; ay < ty + radius + size; ay++) {
                Tile t = Vars.world.tile(ax, ay);
                if(t != null && (t.block() instanceof CoreBlock || (t.build instanceof ConstructBlock.ConstructBuild &&
                        ((ConstructBlock.ConstructBuild) t.build).current instanceof CoreBlock))) {
                    return false;
                }
            }
        }
        return true;
    }
}

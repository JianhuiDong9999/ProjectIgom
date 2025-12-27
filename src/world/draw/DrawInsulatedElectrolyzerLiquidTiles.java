package world.draw;

import arc.math.Mathf;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.liquid.*;
import mindustry.world.draw.DrawBlock;

public class DrawInsulatedElectrolyzerLiquidTiles extends DrawBlock {
    public Liquid drawLiquid1;
    public Liquid drawLiquid2;
    public Liquid drawLiquid3;
    public float alpha = 1f;

    public DrawInsulatedElectrolyzerLiquidTiles(Liquid l1, Liquid l2, Liquid l3){
        drawLiquid1 = l1;
        drawLiquid2 = l2;
        drawLiquid3 = l3;
    }

    @Override
    public void draw(Building build){
        Liquid drawn1 = drawLiquid1 != null ? drawLiquid1 : build.liquids.current();
        int rota = build.rotation;
        LiquidBlock.drawTiledFrames(build.block.size, build.x, build.y,
                (rota < 2 ? (rota == 1 ? 0 : 8) : (rota == 3 ? 16 : 8)),
                (rota < 2 ? (rota == 1 ? 16 : 8) : (rota == 3 ? 0 : 8)),
                (rota < 2 ? (rota == 1 ? 8 : 0) : (rota == 3 ? 8 : 16)),
                (rota < 2 ? (rota == 1 ? 8 : 16) : (rota == 3 ? 8 : 0)),
                drawn1, build.liquids.get(drawn1) / build.block.liquidCapacity * alpha);
        Liquid drawn2 = drawLiquid2 != null ? drawLiquid2 : build.liquids.current();
        LiquidBlock.drawTiledFrames(build.block.size, build.x, build.y,
                (rota < 2 ? (rota == 1 ? 16 : 8) : (rota == 3 ? 0 : 8)),
                (rota < 2 ? (rota == 1 ? 0 : 8) : (rota == 3 ? 16 : 8)),
                (rota < 2 ? (rota == 1 ? 8 : 16) : (rota == 3 ? 8 : 0)),
                (rota < 2 ? (rota == 1 ? 8 : 0) : (rota == 3 ? 8 : 16)),
                drawn2, build.liquids.get(drawn2) / build.block.liquidCapacity * alpha);
        Liquid drawn3 = drawLiquid3 != null ? drawLiquid3 : build.liquids.current();
        LiquidBlock.drawTiledFrames(build.block.size, build.x, build.y,
                (Mathf.mod(rota, 2) != 0 ? 7 : 0),
                (Mathf.mod(rota, 2) != 0 ? 7 : 0),
                (Mathf.mod(rota, 2) != 0 ? 0 : 7),
                (Mathf.mod(rota, 2) != 0 ? 0 : 7),
                drawn3, build.liquids.get(drawn3) / build.block.liquidCapacity * alpha);
    }
}

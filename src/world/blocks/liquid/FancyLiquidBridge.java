package world.blocks.liquid;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.Texture;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.graphics.g2d.TextureRegion;
import arc.math.Angles;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.util.Nullable;
import arc.util.Tmp;
import mindustry.Vars;
import mindustry.core.Renderer;
import mindustry.entities.units.BuildPlan;
import mindustry.world.Tile;
import mindustry.world.blocks.distribution.DirectionBridge;
import mindustry.world.blocks.distribution.DirectionLiquidBridge;
import mindustry.world.blocks.distribution.ItemBridge;
import mindustry.world.blocks.liquid.LiquidBlock;
import mindustry.world.blocks.liquid.LiquidBridge;
import mindustry.world.meta.BlockGroup;

import static mindustry.logic.LogicOp.angle;
import static mindustry.logic.LogicOp.len;

public class FancyLiquidBridge extends LiquidBridge {
    public TextureRegion liquidRegion;
    public TextureRegion bridgeLiquidRegion;
    public TextureRegion bottomRegion;
    public TextureRegion bridgeBottomRegion;
    public float liquidPadding;
    public FancyLiquidBridge(String name) {
        super(name);
        this.liquidPadding = 1.0f;
    }
    @Override
    public void load() {
        super.load();
        liquidRegion = Core.atlas.find(name + "-liquid");
        bridgeLiquidRegion = Core.atlas.find(name + "-bridge-liquid");
        bottomRegion = Core.atlas.find(name + "-bottom");
        bridgeBottomRegion = Core.atlas.find(name + "-bridge-bottom");
    }
    @Override
    public TextureRegion[] icons() {
        return new TextureRegion[]{bottomRegion, region};
    }
    public void drawBridgeLiquid(float x1, float y1, float x2, float y2, @Nullable Color liquidColor) {
        float angle = Angles.angle(x1, y1, x2, y2);
        float cx = (x1 + x2) / 2.0F;
        float cy = (y1 + y2) / 2.0F;
        float len = Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)) - (float)(this.size * 8);
        //The liquid color wasn't very present with translucent bridge, so I took the opacity's square root.
        Draw.color(liquidColor, liquidColor.a * (float) Math.sqrt(Renderer.bridgeOpacity));
        Draw.rect(this.bridgeLiquidRegion, cx, cy, len, (float)this.bridgeLiquidRegion.height * this.bridgeLiquidRegion.scl(), angle);
        Draw.color();
        Draw.alpha(Renderer.bridgeOpacity);

        if (this.bridgeBottomRegion.found()) {
            Draw.color(0.4F, 0.4F, 0.4F, 0.4F * Renderer.bridgeOpacity);
            Draw.rect(this.bridgeBottomRegion, cx, cy, len, (float)this.bridgeBottomRegion.height * this.bridgeBottomRegion.scl(), angle);
            Draw.reset();
        }
        Draw.reset();
    }

    public class FancyLiquidBridgeBuild extends LiquidBridgeBuild {
        public FancyLiquidBridgeBuild() {
            super();
        }
        @Override
        public void draw() {
            Draw.rect(bottomRegion, x, y);
            if (liquids.currentAmount() > 0.001F) {
                LiquidBlock.drawTiledFrames(size, x, y, liquidPadding, liquids.current(), liquids.currentAmount() / liquidCapacity);
            }
            Tile other = Vars.world.tile(this.link);
            if (linkValid(this.tile, other) && liquids.current().color != null) {
                drawBridgeLiquid(x, y, other.getX(), other.getY(), Tmp.c1.set(liquids.current().color).a(liquids.currentAmount() / liquidCapacity * liquids.current().color.a));
            }
            super.draw();
        }

    }
}

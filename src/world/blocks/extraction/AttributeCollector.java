package world.blocks.extraction;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

// Credit to Saigo-no-nozomi for the code.

import arc.Core;
import arc.graphics.Color;
import arc.math.Mathf;
import arc.util.Time;
import mindustry.Vars;
import mindustry.game.Team;
import mindustry.graphics.Drawf;
import mindustry.type.LiquidStack;
import mindustry.world.Tile;
import mindustry.world.blocks.production.AttributeCrafter;
import mindustry.world.meta.Attribute;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatValues;
import world.blocks.IgomAttribute;

public class AttributeCollector extends AttributeCrafter {
    public Attribute attribute;
    public int radius;
    public boolean conf;
    public float eff;
    public float confCount;
    public Color radColor;

    public AttributeCollector(String name) {
        super(name);
        this.attribute = IgomAttribute.quartz;
        this.radius = 0;
        this.conf = true;
        this.radColor = Color.white;
    }

    public void drawOverlay(float x, float y, int rotation) {
        if (this.radius >= 1) {
            Drawf.dashSquare(this.radColor, x, y, (float)((this.size + this.radius * 2) * 8));
        }
    }

    public void setStats() {
        super.setStats();
        this.stats.remove(Stat.tiles);
        this.stats.add(Stat.tiles, StatValues.blocks(this.attribute, this.floating, this.boostScale, true, false));
    }

    public void drawPlace(int x, int y, int rotation, boolean valid) {
        this.displayEfficiency = false;
        super.drawPlace(x, y, rotation, valid);
        this.drawPlaceText(Core.bundle.format("bar.efficiency", new Object[]{(int)(this.baseEfficiency + Math.min(this.maxBoost, this.countEfficiency(x, y) * this.boostScale) * 100.0F)}), x, y, valid);
    }

    public float countEfficiency(int tx, int ty) {
        this.eff = this.baseEfficiency;
        this.confCount = 0.0F;

        for(int ax = tx - this.radius; ax < tx + this.radius + this.size; ++ax) {
            for(int ay = ty - this.radius; ay < ty + this.radius + this.size; ++ay) {
                Tile t = Vars.world.tile(ax, ay);
                if (t == null) {
                    return 0.0F;
                }

                if (t.block().attributes.get(this.attribute) > 0.0F) {
                    this.eff += t.block().attributes.get(this.attribute);
                } else {
                    this.eff += t.floor().attributes.get(this.attribute);
                }

                if ((ax < tx || ax >= tx + this.size || ay < ty || ay >= ty + this.size) && this.conf && t.block() instanceof AttributeCollector) {
                    ++this.confCount;
                }
            }
        }

        if (this.confCount < 1.0F) {
            return Math.min(this.eff, this.maxBoost / this.boostScale);
        } else {
            return 0.0F;
        }
    }

    public boolean canPlaceOn(Tile tile, Team team, int rotation) {
        return this.countEfficiency(tile.x, tile.y) > 0.0F;
    }

    public class AttributeCollectorBuild extends AttributeCrafter.AttributeCrafterBuild {
        public AttributeCollectorBuild() {
            super();
        }

        public void updateTile() {
            if (this.efficiency > 0.0F) {
                this.progress += this.getProgressIncrease(AttributeCollector.this.craftTime);
                this.warmup = Mathf.approachDelta(this.warmup, this.warmupTarget(), AttributeCollector.this.warmupSpeed);
                if (AttributeCollector.this.outputLiquids != null) {
                    float inc = this.getProgressIncrease(1.0F);
                    LiquidStack[] var2 = AttributeCollector.this.outputLiquids;
                    int var3 = var2.length;

                    for(int var4 = 0; var4 < var3; ++var4) {
                        LiquidStack output = var2[var4];
                        this.handleLiquid(this, output.liquid, Math.min(output.amount * inc, AttributeCollector.this.liquidCapacity - this.liquids.get(output.liquid)));
                    }
                }

                if (this.wasVisible && Mathf.chanceDelta((double)AttributeCollector.this.updateEffectChance)) {
                    AttributeCollector.this.updateEffect.at(this.x + Mathf.range((float)AttributeCollector.this.size * 4.0F), this.y + (float)Mathf.range(AttributeCollector.this.size * 4));
                }
            } else {
                this.warmup = Mathf.approachDelta(this.warmup, 0.0F, AttributeCollector.this.warmupSpeed);
            }

            this.totalProgress += this.warmup * Time.delta;
            if (this.progress >= 1.0F) {
                this.craft();
            }

            this.dumpOutputs();
        }

        public void onProximityUpdate() {
            this.attrsum = AttributeCollector.this.countEfficiency(this.tile.x, this.tile.y);
        }
    }
}


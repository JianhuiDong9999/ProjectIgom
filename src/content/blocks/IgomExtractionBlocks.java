package content.blocks;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import content.IgomItems;
import content.IgomLiquids;
import content.IgomUnitTypes;
import graphics.IgomCacheLayer;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.content.StatusEffects;
import mindustry.entities.Effect;
import mindustry.entities.effect.MultiEffect;
import mindustry.gen.Sounds;
import mindustry.graphics.CacheLayer;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.power.PowerNode;
import mindustry.world.blocks.power.ThermalGenerator;
import mindustry.world.blocks.production.AttributeCrafter;
import mindustry.world.blocks.production.Drill;
import mindustry.world.blocks.production.Fracker;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawGlowRegion;
import mindustry.world.draw.DrawLiquidRegion;
import mindustry.world.draw.DrawMulti;
import mindustry.world.meta.Attribute;
import type.MethaneFloor;
import type.MethaneMudFloor;
import world.blocks.IgomAttribute;
import world.blocks.distribution.JunctionReceptiveArmoredConveyor;
import world.blocks.extraction.AttributeCollector;

import static mindustry.type.ItemStack.with;

public class IgomExtractionBlocks {
    public static Block
    miniDrill, torqueDrill, iceDrill, thermalCondenser, magneticDrill, hydraulicDrill, methaneCultivator,
            crystalCollector, methaneExtractor, aluminaExtractor,
            thermalSeparator, precisionDrill, heliumCapturePlant, transporterBay, transporterReceiver;

    public static void load(){
        //Registers build IgomExtractionBlocks
        //no reference is needed here since they can be looked up by name later
        miniDrill = new Drill("mini-drill") {{
            // TODO: Implement functioning team region display.
            localizedName = "Mini Drill";
            buildCostMultiplier = 2f;
            // drawTeamOverlay = false;
            squareSprite = false;
            rotateSpeed = -1.25f;
            requirements(Category.production, with(IgomItems.nickel, 20));
            size = 2;
            health = 350;
            armor = 1;
            drillTime = 320;
            tier = 3;
            itemCapacity = 10;
            consumeLiquid(Liquids.water, 2.5f/60f).boost();
            liquidBoostIntensity = 1.28f;
            liquidCapacity = 20f;

            description = "Cheap drill that extracts trace resources from the ground. Can be boosted with water.";
        }};
        torqueDrill = new Drill("torque-drill") {{
            // TODO: Implement functioning team region display.
            localizedName = "Torque Drill";
            buildCostMultiplier = 2f;
            // drawTeamOverlay = false;
            squareSprite = false;
            rotateSpeed = -0.75f;
            requirements(Category.production, with(Items.graphite, 120, IgomItems.nickel, 120, Items.silicon, 40));
            size = 3;
            health = 750;
            armor = 2;
            drillTime = 240;
            tier = 3;
            itemCapacity = 25;
            consumeLiquid(Liquids.hydrogen, 1.0f / 62.5f).boost();
            liquidBoostIntensity = 1.62f;
            liquidCapacity = 30f;

            description = "Drill that extracts resources from the ground. Can be boosted with hydrogen.";
        }};
        iceDrill = new Fracker("ice-drill") {{
            localizedName = "Ice Drill";
            buildCostMultiplier = 2f;
            squareSprite = false;
            requirements(Category.production, with(Items.graphite, 160, IgomItems.nickel, 50, Items.silicon, 50));
            attribute = Attribute.water;
            result = Liquids.water;
            pumpAmount = 0.26f;
            size = 3;
            health = 825;
            armor = 2;
            liquidCapacity = 600f;
            itemCapacity = 30;
            consumeLiquid(IgomLiquids.methane, 0.1f);
            consumeItem(Items.graphite);
            itemUseTime = 300f;
            drawer = new DrawMulti(new DrawLiquidRegion(), new DrawDefault());
            // TODO: Set rotator region below the main region.
            rotateSpeed = 0.8f;
            envRequired |= 64;
            baseEfficiency = 0.5f;
            consumesPower = false;

            description = "Fracks underground water ice reservoirs with liquid methane. " +
                    "Requires graphite as filter material.";
        }};
        crystalCollector = new AttributeCollector("crystal-collector") {{
            localizedName = "Crystal Collector";
            buildCostMultiplier = 2f;
            size = 2;
            health = 360;
            armor = 2f;
            requirements(Category.production, with(IgomItems.nickel, 40, Items.graphite, 20, IgomItems.alumina, 115));
            attribute = IgomAttribute.quartz;
            radius = 2;
            radColor = Color.valueOf("f0a8b1");
            baseEfficiency = 0f;
            boostScale = 0.125f;
            minEfficiency = 1f;
            maxBoost = 4f;
            outputItem = new ItemStack(IgomItems.quartz, 2);
            craftEffect = new MultiEffect(Fx.mineImpact.wrap(Color.valueOf("f0a8b1")), new Effect(60.0F, (e) -> {
                Draw.color(Color.valueOf("f0a8b1"));
                Lines.stroke(e.fout() * 2f);
                Lines.square(e.x, e.y, e.finpow() * size / 2f * 8f);
                Lines.square(e.x, e.y, e.finpow() * (radius + size / 2.1f) * 8f);
            }));
            consumePower(1f);
            craftTime = 225f;
        }};
        aluminaExtractor = new AttributeCrafter("alumina-extractor") {{
            localizedName = "Alumina Extractor";
            buildCostMultiplier = 2f;
            size = 4;
            health = 2780;
            armor = 4f;
            requirements(Category.production, with(IgomItems.nickel, 450, Items.graphite, 825, Items.silicon, 245));
            attribute = IgomAttribute.alumina;
            baseEfficiency = 0;
            boostScale = 0.0613f;
            minEfficiency = 5f;
            maxBoost = 4f;
            outputItem = new ItemStack(IgomItems.alumina, 8);
            craftEffect = new MultiEffect(Fx.mineImpact, Fx.mineImpactWave).wrap(Color.valueOf("d66b6b"));
            ambientSound = Sounds.drill;
            loopSound = Sounds.drillImpact;
            consumePower(3f);
            consumeItem(Items.graphite, 4);
            itemCapacity = 50;
            consumeLiquid(Liquids.water, 0.25f);
            craftTime = 300f;
            squareSprite = false;
        }};
    }
}

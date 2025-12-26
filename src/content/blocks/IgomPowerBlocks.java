package content.blocks;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
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
import mindustry.graphics.Layer;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.power.Battery;
import mindustry.world.blocks.power.PowerNode;
import mindustry.world.blocks.power.ThermalGenerator;
import mindustry.world.blocks.production.AttributeCrafter;
import mindustry.world.blocks.production.Drill;
import mindustry.world.blocks.production.Fracker;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.draw.*;
import mindustry.world.meta.Attribute;
import mindustry.world.meta.BlockGroup;
import type.MethaneFloor;
import type.MethaneMudFloor;
import world.blocks.IgomAttribute;
import world.blocks.distribution.JunctionReceptiveArmoredConveyor;
import world.blocks.extraction.AttributeCollector;

import static arc.graphics.g2d.Draw.alpha;
import static arc.graphics.g2d.Draw.color;
import static mindustry.content.Fx.rand;
import static mindustry.content.Fx.v;
import static mindustry.type.ItemStack.with;

public class IgomPowerBlocks {
    public static Block
    // power parts
    pylon, connectorNode, nickelBattery, sulfurBattery, reinforcedDiode,
    // power generators
    insulatedThermalGenerator, insulatedTurbineCondenser, magneticGenerator, lithiumFuelCell, solarBattery, bioreactor, reinforcedRTGGenerator,
            thermoradiationGenerator, reinforcedThoriumReactor, hydrogenFusionReactor, heliumFusionReactor, quantumElectricGenerator,
            annihilationReactor;

    public static void load(){
        //Registers build IgomPowerBlocks
        //no reference is needed here since they can be looked up by name later

        // Power parts
        nickelBattery = new Battery("nickel-battery"){{
            localizedName = "Nickel Battery";
            size = 2;
            buildCostMultiplier = 1f;
            drawTeamOverlay = false;
            health = 200;
            armor = 2;
            requirements(Category.power, with(IgomItems.nickel, 40, Items.graphite, 80));
            consumePowerBuffered(20000f);
            emptyLightColor = Color.valueOf("e6e0da");
            fullLightColor = Color.valueOf("ff8266");
            baseExplosiveness = 0.5f;
        }};
        pylon = new PowerNode("pylon") {{
            localizedName = "Pylon";
            laserRange = 24.6f;
            size = 2;
            buildCostMultiplier = 2f;
            drawTeamOverlay = false;
            health = 850;
            armor = 5;
            requirements(Category.power, with(IgomItems.nickel, 30, Items.graphite, 30, Items.silicon, 15));
            laserColor1 = Color.valueOf("ffffff");
            laserColor2 = Color.valueOf("ffdfbf");
            laserScale = 0.5f;
            maxNodes = 4;
            absorbLasers = true;
            squareSprite = false;
        }};
        connectorNode = new PowerNode("connector-node") {{
            localizedName = "Connector Node";
            laserRange = 6f;
            size = 1;
            buildCostMultiplier = 2f;
            drawTeamOverlay = false;
            health = 400;
            armor = 3;
            requirements(Category.power, with(IgomItems.nickel, 10, Items.graphite, 10));
            laserColor1 = Color.valueOf("ffffff");
            laserColor2 = Color.valueOf("ffdfbf");
            laserScale = 0.5f;
            maxNodes = 8;
            squareSprite = false;
        }};
        // Generators
        insulatedTurbineCondenser = new ThermalGenerator("insulated-turbine-condenser"){{
            localizedName = "Insulated Turbine Condenser";
            description = "Generates power when placed on vents, condenses and collects a small amount of water.";
            details = "Technology originally designed for power generation on hot, volcanic planets such as Erekir. Modifications allow this iteration to generate substantial power from Igom's cryo-volcanic system.";
            buildCostMultiplier = 1.5f;
            drawTeamOverlay = false;
            health = 1250;
            armor = 4;
            squareSprite = false;
            requirements(Category.power, with(Items.graphite, 180, IgomItems.nickel, 80));
            attribute = Attribute.steam;
            group = BlockGroup.liquids;
            displayEfficiencyScale = 1f / 9f;
            minEfficiency = 9f - 0.0001f;
            powerProduction = 4f / 9f;
            displayEfficiency = false;
            generateEffect = new Effect(100, e -> {
                color(Color.valueOf("bfced4"));
                alpha(e.fslope() * 0.8f);

                rand.setSeed(e.id);
                for(int i = 0; i < 3; i++){
                    v.trns(rand.random(360f), rand.random(e.finpow() * 14f)).add(e.x, e.y);
                    Fill.circle(v.x, v.y, rand.random(1.4f, 3.4f));
                }
            }).layer(Layer.bullet - 1f);
            effectChance = 0.04f;
            size = 3;
            ambientSound = Sounds.loopHum;
            ambientSoundVolume = 0.06f;
            drawer = new DrawMulti(new DrawDefault(), new DrawBlurSpin("-rotator", 0.6f * 9f){{
                blurThresh = 0.01f;
            }});
            hasLiquids = true;
            outputLiquid = new LiquidStack(Liquids.water, 8.25f / 60f / 9f);
            liquidCapacity = 80f;
            fogRadius = 3;
        }};

        insulatedThermalGenerator = new ThermalGenerator("insulated-thermal-generator") {{
            localizedName = "Insulated Thermal Generator";
            buildCostMultiplier = 1.5f;
            drawTeamOverlay = false;
            squareSprite = false;
            requirements(Category.power, with(Items.graphite, 80, IgomItems.nickel, 60, Items.silicon, 100));
            health = 455;
            size = 2;
            armor = 2f;
            powerProduction = 1.5f;
            /*
            hasLiquids = true;
            consumeLiquid(IgomLiquids.methane, 0.05f).boost();
            liquidCapacity = 180f;
             */
            generateEffect = Fx.redgeneratespark;
            effectChance = 0.009f;
            drawer = new DrawMulti(new DrawDefault(), new DrawGlowRegion() {{
                alpha = 1f;
                glowScale = 11f;
                glowIntensity = 0.5f;
                color = Color.valueOf("b54b00");
            }});
        }};
    }
}

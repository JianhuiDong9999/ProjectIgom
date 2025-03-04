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
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.power.PowerNode;
import mindustry.world.blocks.power.ThermalGenerator;
import mindustry.world.blocks.production.AttributeCrafter;
import mindustry.world.blocks.production.Drill;
import mindustry.world.blocks.production.Fracker;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.draw.*;
import mindustry.world.meta.Attribute;
import type.MethaneFloor;
import type.MethaneMudFloor;
import world.blocks.IgomAttribute;
import world.blocks.crafters.TeamGenericCrafter;
import world.blocks.distribution.JunctionReceptiveArmoredConveyor;
import world.blocks.extraction.AttributeCollector;

import static mindustry.type.ItemStack.with;

public class IgomCrafterBlocks {
    public static Block
    reinforcedArcFurnace, grapheneSynthesizer, sulfidationChamber, insulatedElectrolyzer, ethyleneSynthesizer, polymerPress, nichromeCrucible,
            hydrogenRefprmer, miniCapacitorFactory, aluminumSmelter, crystalArcFurnace, lithiumKiln, nitrideSynthesizer, nickelCobaltFurnace,
            plasticAlloyWeaver, phaseKnitter, phasePlasticWeaver, vitrifier, cyanogenCatalyzer, nitrogenConcentrator, condenser,
            argonConcentrator, highEntropyAlloyFurnace, antimatterSolidifier, technetiumConverter, phaseKiln, subatomicCompressor;
    // Note: Hydrogen Reformer consumes water and methane to mass-produce hydrogen.
    // Input sulfide to massively increase process efficiency.
    public static void load() {
            //Registers build IgomCrafterBlocks
            //no reference is needed here since they can be looked up by name later
            reinforcedArcFurnace = new TeamGenericCrafter("reinforced-arc-furnace") {{
                    localizedName = "Reinforced Arc Furnace";
                    requirements(Category.crafting, with(Items.graphite, 640, IgomItems.nickel, 420));
                    squareSprite = false;
                    health = 6540;
                    armor = 4f;
                    craftEffect = Fx.none;
                    outputItem = new ItemStack(Items.silicon, 12);
                    outputLiquid = new LiquidStack(IgomLiquids.oxygen, 4f / 60);
                    ignoreLiquidFullness = true;
                    craftTime = 60f;
                    size = 4;
                    hasPower = true;
                    hasLiquids = false;
                    itemCapacity = 120;
                    liquidCapacity = 60;
                    drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawArcSmelt() {{
                            flameRad = 2f;
                            flameColor = Color.valueOf("f5b493");
                            midColor = Color.valueOf("f2dfaa");
                            circleSpace = 3f;
                            circleStroke = 2f;
                            particleRad = 12f;
                            particleStroke = 1.2f;
                            particleLife = 40f;
                    }}, new DrawDefault());
                    fogRadius = 3;
                    ambientSound = Sounds.smelter;
                    ambientSoundVolume = 0.2F;
                    consumeItems(ItemStack.with(Items.graphite, 4, Items.sand, 16));
                    consumePower(18.0F);
            }};
    }
}

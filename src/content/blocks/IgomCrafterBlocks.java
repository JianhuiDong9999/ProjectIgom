package content.blocks;

import arc.graphics.Color;
import content.IgomItems;
import content.IgomLiquids;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.draw.*;
import mindustry.world.meta.BlockGroup;
import world.blocks.crafters.TeamGenericCrafter;
import world.draw.DrawInsulatedElectrolyzerLiquidTiles;
import world.draw.DrawRotatableBlock;
import world.draw.DrawRotatableGlowRegion;

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
                    requirements(Category.crafting, with(Items.graphite, 480, IgomItems.nickel, 280));
                    squareSprite = false;
                    health = 6540;
                    armor = 4f;
                    craftEffect = Fx.none;
                    outputItem = new ItemStack(Items.silicon, 12);
                    outputLiquid = new LiquidStack(IgomLiquids.oxygen, 8f / 60);
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
                    ambientSound = Sounds.loopSmelter;
                    ambientSoundVolume = 0.2F;
                    consumeItems(ItemStack.with(Items.graphite, 4, Items.sand, 16));
                    consumePower(12.0F);
            }};

            insulatedElectrolyzer = new TeamGenericCrafter("insulated-electrolyzer"){{
                    requirements(Category.crafting, with(IgomItems.nickel, 80, Items.graphite, 140, Items.silicon, 180, IgomItems.lithium, 60));
                    size = 3;

                    craftTime = 10f;
                    rotate = true;
                    invertFlip = true;
                    group = BlockGroup.liquids;
                    hasLiquids = true;
                    itemCapacity = 0;

                    liquidCapacity = 40f;

                    consumeLiquid(Liquids.water, 12f / 60f);
                    consumePower(3f);
                    drawer = new DrawMulti(
                            new DrawRegion("-bottom"),
                            new DrawInsulatedElectrolyzerLiquidTiles(IgomLiquids.oxygen, Liquids.hydrogen, Liquids.water),
                            new DrawRotatableGlowRegion(){{
                                    alpha = 0.7f;
                                    color = Color.valueOf("c4bdf3");
                                    glowIntensity = 0.3f;
                                    glowScale = 6f;
                            }},
                            new DrawBubbles(Color.valueOf("7693e3")){{
                                    sides = 10;
                                    recurrence = 3f;
                                    spread = 6;
                                    radius = 1.5f;
                                    amount = 20;
                            }},
                            new DrawRotatableBlock(),
                            new DrawLiquidOutputs()
                    );

                    ambientSound = Sounds.loopElectricHum;
                    ambientSoundVolume = 0.08f;

                    regionRotated1 = 3;
                    outputLiquids = LiquidStack.with(IgomLiquids.oxygen, 6f / 60, Liquids.hydrogen, 6f / 60);
                    liquidOutputDirections = new int[]{1, 3};
            }};
    }
}

package content.blocks;

import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import content.IgomItems;
import content.IgomLiquids;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.entities.Effect;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.draw.*;
import mindustry.world.meta.BlockGroup;
import mindustry.world.meta.Env;
import world.blocks.crafters.TeamGenericCrafter;
import world.draw.DrawGlowWeave;
import world.draw.DrawInsulatedElectrolyzerLiquidTiles;
import world.draw.DrawRotatableBlock;
import world.draw.DrawRotatableGlowRegion;

import static arc.graphics.g2d.Draw.color;
import static arc.math.Angles.randLenVectors;
import static mindustry.type.ItemStack.with;

public class IgomCrafterBlocks {
    public static Block
    reinforcedArcFurnace, metafiberWeaver, cermetKiln, sulfidationChamber, insulatedElectrolyzer, ethyleneSynthesizer, polymerPress, nichromeCrucible,
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
                    requirements(Category.crafting, with(Items.graphite, 320, IgomItems.nickel, 180));
                    squareSprite = false;
                    health = 4540;
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
                    localizedName = "Insulated Electrolyzer";
                    requirements(Category.crafting, with(IgomItems.nickel, 80, Items.graphite, 140, Items.silicon, 80, IgomItems.lithium, 60));
                    squareSprite = false;
                    size = 3;
                    health = 1840;
                    armor = 2f;
                    craftTime = 10f;
                    rotate = true;
                    invertFlip = true;
                    group = BlockGroup.liquids;
                    hasLiquids = true;
                    hasItems = true;
                    itemCapacity = 30;

                    liquidCapacity = 40f;

                    consumeLiquid(Liquids.water, 12f / 60f);
                    consumeItem(IgomItems.nickel);
                    craftTime = 5f * 60f;
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
            metafiberWeaver = new TeamGenericCrafter("metafiber-weaver"){{
                    localizedName = "Metafiber Weaver";
                    requirements(Category.crafting, with(IgomItems.nickel, 160, Items.silicon, 320, IgomItems.lithium, 80));
                    squareSprite = false;
                    craftEffect = new Effect(30, e -> {
                            randLenVectors(e.id, 12, 9f + e.fin() * 8f, (x, y) -> {
                                    color(Pal.stoneGray);
                                    Fill.square(e.x + x, e.y + y, e.fout() + 0.5f, 45);
                            });
                    });
                    outputItem = new ItemStack(IgomItems.metafiber, 6);
                    craftTime = 2 * 60f;
                    size = 3;
                    health = 2260;
                    armor = 3f;
                    hasPower = true;
                    drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(IgomLiquids.methane) {{
                            padTop = padBottom = padLeft = padRight = 4f;
                            alpha = 0.3f;
                    }}, new DrawGlowWeave() {{
                            glowColor = Color.valueOf("35665e");
                            rotateSpeed = 1.25f;
                    }}, new DrawDefault());
                    envEnabled |= Env.space;

                    ambientSound = Sounds.loopGrind;
                    ambientSoundVolume = 0.05f;

                    consumeItems(with(Items.graphite, 6, IgomItems.nickel, 2));
                    consumeLiquid(IgomLiquids.methane, 12f / 60f);
                    consumePower(320f / 60f);
                    itemCapacity = 40;
                    liquidCapacity = 60f;
            }};
    }
}

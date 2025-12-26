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
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.RegenProjector;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.power.PowerNode;
import mindustry.world.blocks.power.ThermalGenerator;
import mindustry.world.blocks.production.AttributeCrafter;
import mindustry.world.blocks.production.Drill;
import mindustry.world.blocks.production.Fracker;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.draw.*;
import mindustry.world.meta.Attribute;
import type.MethaneFloor;
import type.MethaneMudFloor;
import world.blocks.IgomAttribute;
import world.blocks.distribution.JunctionReceptiveArmoredConveyor;
import world.blocks.extraction.AttributeCollector;

import static mindustry.type.ItemStack.with;

public class IgomEffectBlocks {
    public static Block
            mendActuator, bracedItemContainer, bracedItemVault, fortifiedItemContainer, fortifiedVault,
            podReceptionPlatform, LargePodReceptionPlatform;

    public static void load(){
        //Registers build IgomEffectBlocks
        //no reference is needed here since they can be looked up by name later
        mendActuator = new RegenProjector("mend-actuator") {{
            localizedName = "Mend Actuator";
            squareSprite = false;
            requirements(Category.effect, with(IgomItems.nickel, 160, Items.silicon, 80, IgomItems.lithium, 40));
            buildCostMultiplier = 4f;
            drawTeamOverlay = false;
            consumeLiquid(Liquids.hydrogen, 0.05f);
            consumePower(1f);
            size = 3;
            range = 32;
            baseColor = Pal.regen;
            health = 1220;
            armor = 2f;

            healPercent = 2f / 60f;

            Color col = Color.valueOf("8ca9e8");

            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.hydrogen, 9f / 4f), new DrawDefault(), new DrawGlowRegion(){{
                color = Color.sky;
            }}, new DrawPulseShape(false){{
                layer = Layer.effect;
                color = col;
            }});
        }};
    }
}

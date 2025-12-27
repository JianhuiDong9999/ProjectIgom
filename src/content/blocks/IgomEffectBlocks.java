package content.blocks;

import arc.graphics.Color;
import content.IgomItems;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.defense.RegenProjector;
import mindustry.world.draw.*;
import world.blocks.effect.CircleRegenProjector;

import static mindustry.type.ItemStack.with;

public class IgomEffectBlocks {
    public static Block
            mendActuator, bracedItemContainer, bracedItemVault, fortifiedItemContainer, fortifiedVault,
            podReceptionPlatform, LargePodReceptionPlatform;

    public static void load(){
        //Registers build IgomEffectBlocks
        //no reference is needed here since they can be looked up by name later
        mendActuator = new CircleRegenProjector("mend-actuator") {{
            localizedName = "Mend Actuator";
            squareSprite = false;
            requirements(Category.effect, with(IgomItems.nickel, 160, Items.silicon, 80, IgomItems.lithium, 40));
            buildCostMultiplier = 4f;
            drawTeamOverlay = false;
            consumeLiquid(Liquids.hydrogen, 0.05f);
            consumeItem(Items.silicon);
            optionalUseTime = 60f / 0.5f;
            consumePower(4f);
            size = 3;
            range = 19;
            baseColor = Pal.regen;
            health = 1220;
            armor = 2f;

            healPercent = 100f / 45f / 60f;

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

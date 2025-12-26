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

public class IgomCoreBlocks {
    public static Block
    // TODO: implement IgomCoreBlocks
    coreInitiate, coreInstigate, corePerpetuate, coreArsenal, coreErudite, coreDominator, coreOverlord;

    public static void load(){
        //Registers build IgomCoreBlocks
        //no reference is needed here since they can be looked up by name later
        coreInitiate = new CoreBlock("core-initiate") {{
            localizedName = "Core: Initiate";
            squareSprite = false;
            drawTeamOverlay = false;
            requirements(Category.effect, with(Items.graphite, 4000, Items.silicon, 4000, IgomItems.nickel, 4000));

            isFirstTier = true;
            unitType = IgomUnitTypes.detect;
            health = 16000;
            itemCapacity = 12800;
            size = 4;
            //radius = 10;
            thrusterLength = 34/4f;
            armor = 6f;
            alwaysUnlocked = true;
            incinerateNonBuildable = true;
            requiresCoreZone = true;

            buildCostMultiplier = 0.2f;
            unitCapModifier = 32;
            researchCostMultiplier = 0.07f;
        }};
    }
}

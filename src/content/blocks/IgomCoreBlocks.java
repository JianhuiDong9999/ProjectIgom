package content.blocks;

import content.IgomItems;
import content.IgomUnitTypes;
import mindustry.content.Items;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.storage.CoreBlock;

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
            health = 12000;
            itemCapacity = 12800;
            size = 4;
            //radius = 10;
            thrusterLength = 34/4f;
            armor = 6f;
            alwaysUnlocked = true;
            incinerateNonBuildable = true;
            requiresCoreZone = true;

            buildCostMultiplier = 0.2f;
            unitCapModifier = 16;
            researchCostMultiplier = 0.07f;
        }};
    }
}

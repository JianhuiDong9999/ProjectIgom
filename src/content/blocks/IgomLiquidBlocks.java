package content.blocks;

import content.IgomItems;
import mindustry.content.Items;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.liquid.ArmoredConduit;
import mindustry.world.blocks.liquid.LiquidRouter;
import world.blocks.liquid.FancyLiquidBridge;
import world.blocks.liquid.JunctionlessArmoredConduit;

import static mindustry.type.ItemStack.with;

public class IgomLiquidBlocks {
    public static Block
    // liquid blocks
    pressurePump, pistonPump, fluxPump, insulatedConduitJunction, insulatedConduitBridge,  insulatedConduit,
            insulatedConduitRouter, insulatedLiquidContainer, insulatedLiquidTank,
            fortifiedConduit, fortifiedLiquidContainer, fortifiedLiquidTank, fortifiedLiquidBridge;
    public static void load(){
        //Registers build IgomLiquidBlocks
        //no reference is needed here since they can be looked up by name later
        insulatedConduitRouter = new LiquidRouter("insulated-conduit-router") {{
            localizedName = "Insulated Conduit Router";
            requirements(Category.liquid, with(Items.graphite, 12, IgomItems.nickel, 12));
            buildCostMultiplier = 2f;
            squareSprite = false;
            solid = false;
            health = 240;
            armor = 4f;
            liquidCapacity = 10f;
            liquidPressure = 1.01f;
            liquidPadding = 0.75f;
        }};
        insulatedConduitBridge = new FancyLiquidBridge("insulated-conduit-bridge") {{
            localizedName = "Insulated Conduit Bridge";
            requirements(Category.liquid, with(Items.graphite, 18, IgomItems.nickel, 18));
            buildCostMultiplier = 2f;
            connectedPower = false;
            range = 5;
            health = 420;
            armor = 4f;
            liquidCapacity = 10f;
            liquidPressure = 1.01f;
        }};
        insulatedConduit = new JunctionlessArmoredConduit("insulated-conduit") {{
            localizedName = "Insulated Conduit";
            requirements(Category.liquid, with(Items.graphite, 3, IgomItems.nickel, 3));
            buildCostMultiplier = 3f;
            health = 240;
            armor = 4f;
            liquidCapacity = 5f;
            liquidPressure = 1.01f;
            leaks = true;
            bridgeReplacement = insulatedConduitBridge;
            junctionReplacement = null;
        }};
    }
}

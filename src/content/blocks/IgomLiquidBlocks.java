package content.blocks;

import content.IgomItems;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.liquid.ArmoredConduit;
import mindustry.world.blocks.liquid.LiquidJunction;
import mindustry.world.blocks.liquid.LiquidRouter;
import mindustry.world.blocks.production.Pump;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawLiquidRegion;
import mindustry.world.draw.DrawMulti;
import mindustry.world.draw.DrawPumpLiquid;
import world.blocks.liquid.ClearLiquidBridge;
import world.blocks.liquid.StatPump;
import world.draw.DrawTeam;

import static mindustry.type.ItemStack.with;

public class IgomLiquidBlocks {
    public static Block
    // liquid blocks
    insulatedPump, largeInsulatedPump, fluxPump, insulatedConduitJunction, insulatedConduitBridge,  insulatedConduit,
            insulatedConduitRouter, insulatedLiquidContainer, insulatedLiquidTank,
            fortifiedConduit, fortifiedLiquidContainer, fortifiedLiquidTank, fortifiedLiquidBridge;
    public static void load(){
        //Registers build IgomLiquidBlocks
        //no reference is needed here since they can be looked up by name later
        insulatedConduitJunction = new LiquidJunction("insulated-conduit-junction") {{
            localizedName = "Insulated Conduit Junction";
            requirements(Category.liquid, with(Items.graphite, 18, IgomItems.nickel, 18));
            buildCostMultiplier = 3f;
            health = 240;
            armor = 4f;
            //liquidCapacity = 10f;
            //liquidPressure = 1.01f;
        }};
        insulatedConduitRouter = new LiquidRouter("insulated-conduit-router") {{
            localizedName = "Insulated Conduit Router";
            requirements(Category.liquid, with(Items.graphite, 12, IgomItems.nickel, 12));
            buildCostMultiplier = 2f;
            squareSprite = false;
            solid = false;
            health = 240;
            armor = 4f;
            liquidCapacity = 32f;
            liquidPressure = 1f;
            liquidPadding = 0.75f;
        }};
        insulatedConduitBridge = new ClearLiquidBridge("insulated-conduit-bridge") {{
            localizedName = "Insulated Conduit Bridge";
            requirements(Category.liquid, with(Items.graphite, 18, IgomItems.nickel, 18));
            buildCostMultiplier = 2f;
            connectedPower = false;
            range = 5;
            health = 420;
            armor = 4f;
            liquidCapacity = 32f;
            liquidPressure = 1f;
            description = "Transports liquids across obstacles up to 4 tiles wide.";
        }};
        insulatedConduit = new ArmoredConduit("insulated-conduit") {{
            localizedName = "Insulated Conduit";
            requirements(Category.liquid, with(Items.graphite, 2, IgomItems.nickel, 2));
            buildCostMultiplier = 3f;
            health = 240;
            armor = 4f;
            liquidCapacity = 16f;
            liquidPressure = 1f;
            leaks = true;
            bridgeReplacement = insulatedConduitBridge;
            junctionReplacement = insulatedConduitJunction;
            description = "Transports liquids. Insulation system maintains pressure and temperature preventing liquids from changing state.";
        }};
        insulatedPump = new StatPump("insulated-pump") {{
            localizedName = "Insulated Pump";
            requirements(Category.liquid, with(IgomItems.nickel, 80, Items.graphite, 120, Items.silicon, 60));
            buildCostMultiplier = 2f;
            size = 3;
            squareSprite = false;
            health = 1920;
            armor = 4f;
            liquidCapacity = 120;
            liquidPressure = 1f;
            pumpAmount = 60f / 60f / 4f;
            consumeLiquid(Liquids.hydrogen, 2f / 60f);
            consumeItem(Items.graphite);
            drawer = new DrawMulti(new DrawDefault(), new DrawPumpLiquid(), new DrawTeam());
        }};
    }
}

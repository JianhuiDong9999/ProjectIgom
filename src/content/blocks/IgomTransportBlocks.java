package content.blocks;

import content.IgomItems;
import mindustry.content.Items;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.distribution.*;
import world.blocks.transport.JunctionReceptiveArmoredConveyor;
import world.blocks.transport.ToggleableArmoredConveyor;
import world.blocks.transport.ToggleableFlowGate;

import static mindustry.type.ItemStack.with;

public class IgomTransportBlocks {
    public static Block
    bracedConveyor, bracedJunction, bracedRouter, bracedFlowGate, reinforcedDistributor, bracedBridge,
            fortifiedConveyor, fortifiedRouter, fortifiedDistributor, fortifiedFlowGate, fortifiedBridge;

    public static void load(){
        //Registers build IgomTransportBlocks
        //no reference is needed here since they can be looked up by name later
        bracedBridge = new BufferedItemBridge("braced-bridge") {{
            localizedName = "Braced Bridge";
            buildCostMultiplier = 4f;
            drawTeamOverlay = false;
            requirements(Category.distribution, with(Items.graphite, 16, IgomItems.nickel, 8));
            health = 360;
            armor = 4f;
            speed = 116f;
            range = 5;
            itemCapacity = 8;
            bufferCapacity = 16;

            description = "Transports items over terrain and blocks. Outputs items evenly in three directions.";
        }};
        bracedJunction = new Junction("braced-junction") {{
            localizedName = "Braced Junction";
            requirements(Category.distribution, with(IgomItems.nickel, 8));
            buildCostMultiplier = 3f;
            drawTeamOverlay = false;
            health = 180;
            armor = 3f;
            itemCapacity = 3;
            speed = 43;

            description = "Allows the intersection of conveyors. Armored.";
        }};
        bracedConveyor = new ToggleableArmoredConveyor("braced-conveyor") {{
            // TODO: Implement ToggleableConveyor with functioning teamRegion.
            localizedName = "Braced Conveyor";
            requirements(Category.distribution, with(IgomItems.nickel, 2));
            buildCostMultiplier = 5f;
            drawTeamOverlay = false;
            health = 180;
            armor = 3f;
            speed = 0.0575f;
            displayedSpeed = 8f;
            bridgeReplacement = bracedBridge;
            junctionReplacement = bracedJunction;
            // noSideBlend = false;

            description = "Moves items forward. Armored. Can be toggled to receive non-conveyor and non-junction side inputs.";
        }};
        bracedRouter = new Router("braced-router") {{
            localizedName = "Braced Router";
            buildCostMultiplier = 4f;
            //drawTeamOverlay = false;
            requirements(Category.distribution, with(IgomItems.nickel, 8));
            health = 320;
            armor = 3f;
            solid = false;

            description = "Distributes items in three directions equally. Moderately armored.";
        }};
        bracedFlowGate = new ToggleableFlowGate("braced-flow-gate") {{
            // TODO: Implement ToggleableOverflowGate.
            localizedName = "Braced Flow Gate";
            buildCostMultiplier = 4f;
            drawTeamOverlay = false;
            requirements(Category.distribution, with(Items.graphite, 12, IgomItems.nickel, 8));
            health = 320;
            armor = 3f;
            speed = 8f;
            solid = false;
            regionRotated1 = 1;
        }};
        reinforcedDistributor = new Router("reinforced-distributor") {{
            localizedName = "Reinforced Distributor";
            buildCostMultiplier = 4f;
            drawTeamOverlay = false;
            requirements(Category.distribution, with(IgomItems.nickel, 16));
            size = 2;
            health = 240;
            armor = 2f;
            speed = 7.5f;
            solid = true;
            regionRotated1 = 1;
        }};
        /* TODO: Decide on the addition of receptive conveyors.
        receptiveconveyor = new Conveyor("receptive-conveyor") {{
            localizedName = "Receptive Conveyor";
            buildCostMultiplier = 4f;
            requirements(Category.distribution, with(Items.graphite, 4, IgomItems.nickel, 2));
            health = 80;
            armor = 1f;
            speed = 0.0575f;
            displayedSpeed = 8f;
            bridgeReplacement = bracedbridge;

            description = "Moves items forward. Lightly armored. Can receive side inputs. Slower than a braced conveyor.";
        }}; */
    }
}

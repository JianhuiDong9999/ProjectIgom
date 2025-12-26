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

public class IgomTransportBlocks {
    public static Block
    bracedConveyor, bracedJunction, bracedRouter, bracedFlowGate, reinforcedDistributor, receptiveConveyor, bracedBridge,
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
        bracedConveyor = new JunctionReceptiveArmoredConveyor("braced-conveyor") {{
            // TODO: Implement ToggleableConveyor with functioning teamRegion.
            localizedName = "Braced Conveyor";
            requirements(Category.distribution, with(IgomItems.nickel, 2));
            buildCostMultiplier = 6f;
            drawTeamOverlay = false;
            health = 180;
            armor = 3f;
            speed = 0.0575f;
            displayedSpeed = 8f;
            bridgeReplacement = bracedBridge;
            junctionReplacement = bracedJunction;

            description = "Moves items forward. Armored. Cannot receive side inputs.";
        }};
        bracedRouter = new Router("braced-router") {{
            localizedName = "Braced Router";
            buildCostMultiplier = 4f;
            //drawTeamOverlay = false;
            requirements(Category.distribution, with(IgomItems.nickel, 8));
            health = 320;
            armor = 3f;
            speed = 12f;
            solid = false;

            description = "Distributes items in three directions equally. Moderately armored.";
        }};
        bracedFlowGate = new OverflowDuct("braced-flow-gate") {{
            // TODO: Implement ToggleableOverflowGate.
            localizedName = "Braced Flow Gate";
            buildCostMultiplier = 4f;
            drawTeamOverlay = false;
            requirements(Category.distribution, with(Items.graphite, 12, IgomItems.nickel, 8));
            health = 320;
            armor = 3f;
            itemCapacity = 1;
            speed = 7.5f;
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

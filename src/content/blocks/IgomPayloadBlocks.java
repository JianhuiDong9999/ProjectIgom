package content.blocks;

import content.IgomItems;
import content.IgomLiquids;
import content.IgomUnitTypes;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.units.UnitFactory;
import world.blocks.payload.TeamUnitFactory;

import static mindustry.type.ItemStack.with;

public class IgomPayloadBlocks {
    public static Block
    // payload
    walkerConstructor, mechConstructor, battleTankConstructor, supportTankConstructor, fighterConstructor, cruiserConstructor,
            frigateConstructor, navalBattleshipAssembler, navalSupportShipAssembler, amphibiousConstructor, reaverConstructor,
            siegeConstructor, rollerConstructor, boosterConstructor, advancedGroundAssembler, advancedAirAssembler,
            groundReconstructor, airReconstructor, reinforcedPayloadMassDriver, bracedPayloadConveyor, bracedPayloadRouter,
            fortifiedPayloadConveyor, fortifiedPayloadRouter,
            payloadLaunchPad, payloadReceptionPlatform, reinforcedHangar, fortifiedHangar;

    public static void load(){
        //Registers build IgomPayloadBlocks
        //no reference is needed here since they can be looked up by name later
        walkerConstructor = new TeamUnitFactory("walker-constructor"){{
            localizedName = "Walker Constructor";
            requirements(Category.units, with(IgomItems.nickel, 320, Items.silicon, 240, Items.graphite, 240));
            size = 3;
            health = 2840;
            armor = 6f;
            configurable = false;
            plans.add(new UnitPlan(IgomUnitTypes.pike, 60f * 45f, with(IgomItems.nickel, 45, Items.silicon, 72)));
            consumeLiquid(IgomLiquids.oxygen, 4f / 60f);

            regionSuffix = "-chrome";
            fogRadius = 3;
            consumePower(320f / 60f);
            unitCapModifier = 4;
        }};
    }
}

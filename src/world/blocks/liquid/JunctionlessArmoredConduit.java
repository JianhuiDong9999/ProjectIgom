package world.blocks.liquid;

import arc.struct.Seq;
import mindustry.entities.units.BuildPlan;
import mindustry.input.Placement;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.liquid.ArmoredConduit;


public class JunctionlessArmoredConduit extends ArmoredConduit {

    public JunctionlessArmoredConduit(String name) {
        super(name);
    }
    @Override
    public void handlePlacementLine(Seq<BuildPlan> plans){
        if(bridgeReplacement == null) return;
        // TODO: Uncomment this code when V8 drops
        /*if(bridgeReplacement instanceof ItemBridge bridge) {
            Placement.calculateBridges(plans, bridge, false, b -> b instanceof Duct || b instanceof StackConveyor || b instanceof Conveyor);
        }*/
        if(bridgeReplacement instanceof DuctBridge bridge) {
            Placement.calculateBridges(plans, bridge, false, b -> b instanceof Duct || b instanceof StackConveyor || b instanceof Conveyor);
        }
    }
}

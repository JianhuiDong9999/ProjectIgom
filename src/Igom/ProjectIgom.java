package Igom;

import arc.Core;
import arc.Events;
import arc.util.*;
import content.*;
import content.blocks.*;
import game.IgomTeams;
import graphics.IgomCacheLayer;
import graphics.IgomEnvRenderers;
import graphics.IgomShaders;
import mindustry.Vars;
import mindustry.game.EventType;
import mindustry.gen.Building;
import mindustry.mod.*;
import mindustry.world.Block;
import mindustry.world.Tile;
import world.blocks.IgomAttribute;
import world.blocks.liquid.JunctionlessConduit;

public class ProjectIgom extends Mod {

    public ProjectIgom(){
        Log.info("Loaded ProjectIgom constructor.");
        /*
        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("frog");
                dialog.cont.add("behold").row();
                //mod sprites are prefixed with the mod name (this mod is called 'project-igom' in its config)
                dialog.cont.image(Core.atlas.find("project-igom-frog")).pad(20f).row();
                dialog.cont.button("Understood", dialog::hide).size(200f, 50f);
                dialog.show();
            });
        }); */
    }

    @Override
    public void loadContent() {
        Log.info("Loading ProjectIgom");
        IgomItems.load();
        IgomLiquids.load();
        IgomShaders.init();
        IgomCacheLayer.load();
        IgomUnitTypes.load();
        IgomTeams.load();
        IgomAttribute.load();
        //All Blocks TODO: Ensure correct block loading order.
        IgomEnvironmentBlocks.load();
        IgomCoreBlocks.load();
        IgomCrafterBlocks.load();
        IgomDefenseBlocks.load();
        IgomEffectBlocks.load();
        IgomExtractionBlocks.load();
        IgomLiquidBlocks.load();
        IgomLogicBlocks.load();
        IgomPayloadBlocks.load();
        IgomPowerBlocks.load();
        IgomTransportBlocks.load();
        IgomTurretBlocks.load();
        //Weathers
        IgomWeathers.load();
        IgomEnvRenderers.init();
    }
}

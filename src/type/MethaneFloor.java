package type;

import content.IgomLiquids;
import graphics.IgomCacheLayer;
import mindustry.graphics.CacheLayer;
import mindustry.world.blocks.environment.Floor;

public class MethaneFloor extends Floor {
    public MethaneFloor(String name){
        super(name);
        propertiesInit();
    }
    public MethaneFloor(String name, int variants){
        super(name, variants);
        propertiesInit();
    }
    public MethaneFloor(String name, float multiplier){
        super(name);
        propertiesInit(multiplier);
    }
    public MethaneFloor(String name, int variants, float multiplier){
        super(name, variants);
        propertiesInit(multiplier);
    }
    public void propertiesInit() {
        isLiquid = true;
        liquidDrop = IgomLiquids.methane;
        // TODO: status = IgomStatusEffects.methaneSoaked;
        statusDuration = 120f;
        cacheLayer = IgomCacheLayer.methane;
        albedo = 0.6f;
        supportsOverlay = true;
    }
    public void propertiesInit(float multiplier) {
        propertiesInit();
        liquidMultiplier = multiplier;
    }
}

package type;

import content.IgomLiquids;
import graphics.IgomCacheLayer;
import mindustry.content.StatusEffects;
import mindustry.gen.Sounds;

public class MethaneMudFloor extends MethaneFloor {
    public MethaneMudFloor(String name) {
        super(name);
    }
    public MethaneMudFloor(String name, float multiplier) {
        super(name, multiplier);
    }
    @Override
    public void propertiesInit() {
        isLiquid = true;
        liquidDrop = IgomLiquids.methane;
        status = StatusEffects.muddy;
        // TODO: naval units also get the slow effect
        statusDuration = 120f;
        walkSound = Sounds.mud;
        walkSoundVolume = 0.08f;
        walkSoundPitchMin = 0.4f;
        walkSoundPitchMax = 0.5f;
        cacheLayer = IgomCacheLayer.mud;
        albedo = 0.6f;
        supportsOverlay = true;
    }
}

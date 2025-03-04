package graphics;

import mindustry.graphics.CacheLayer;
import mindustry.graphics.Shaders;

public class IgomCacheLayer {
    public static CacheLayer
            methane, water, mud;
    public static void load(){
        CacheLayer.add(
            mud = new CacheLayer.ShaderLayer(IgomShaders.mud),
            water = new CacheLayer.ShaderLayer(IgomShaders.water),
            methane = new CacheLayer.ShaderLayer(IgomShaders.methane)
        );
    }
}

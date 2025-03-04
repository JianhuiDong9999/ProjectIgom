package graphics;

import arc.*;
import arc.graphics.*;
import arc.graphics.Texture.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.graphics.Layer;
import mindustry.type.*;
import mindustry.world.meta.*;
import world.meta.*;

import static mindustry.Vars.*;

public class IgomEnvRenderers {
    public static void init(){
        Core.assets.load("sprites/clouds.png", Texture.class);

        renderer.addEnvRenderer(IgomEnv.igomCloud, () -> {
            Texture tex = Core.assets.get("sprites/clouds.png", Texture.class);
            if(tex.getMagFilter() != Texture.TextureFilter.linear){
                tex.setFilter(Texture.TextureFilter.linear);
                tex.setWrap(Texture.TextureWrap.repeat);
            }

            //TODO layer looks better? should not be conditional
            Draw.z(state.rules.fog ? Layer.fogOfWar + 1 : Layer.weather - 1);
            Weather.drawNoiseLayers(tex, Color.magenta, 1000f, 0.24f, 0.4f, 1f, 1f, 0f, 4, -1.3f, 0.7f, 0.8f, 0.9f);
            Draw.reset();
        });
    }
}

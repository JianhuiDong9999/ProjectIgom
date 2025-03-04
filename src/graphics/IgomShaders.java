package graphics;

import arc.*;
import arc.files.*;
import arc.graphics.*;
import arc.graphics.gl.*;
import arc.util.*;
import mindustry.Vars;
import mindustry.graphics.Shaders;

import static mindustry.Vars.*;

public class IgomShaders {
    public static IgomSurfaceShader methane, water, mud;

    public static void init() {
        mud = new IgomSurfaceShader("mud");
        water = new IgomSurfaceShader("water");
        methane = new IgomSurfaceShader("methane");
    }

    public static class IgomSurfaceShader extends Shader {
        Texture noiseTex;

        public IgomSurfaceShader(String frag) {
            super(Shaders.getShaderFi("screenspace.vert"), Vars.tree.get("shaders/" + frag + ".frag"));
            loadNoise();
        }

        public String textureName() {
            return "noise";
        }

        public void loadNoise() {
            Core.assets.load("sprites/" + textureName() + ".png", Texture.class).loaded = t -> {
                t.setFilter(Texture.TextureFilter.linear);
                t.setWrap(Texture.TextureWrap.repeat);
            };
        }

        @Override
        public void apply() {
            setUniformf("u_campos", Core.camera.position.x - Core.camera.width / 2, Core.camera.position.y - Core.camera.height / 2);
            setUniformf("u_resolution", Core.camera.width, Core.camera.height);
            setUniformf("u_time", Time.time);

            if (hasUniform("u_noise")) {
                if (noiseTex == null) {
                    noiseTex = Core.assets.get("sprites/" + textureName() + ".png", Texture.class);
                }

                noiseTex.bind(1);
                renderer.effectBuffer.getTexture().bind(0);

                setUniformi("u_noise", 1);
            }
        }
    }
}
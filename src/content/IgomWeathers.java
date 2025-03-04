package content;
import arc.graphics.*;
import arc.util.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.type.weather.*;
import mindustry.world.meta.*;

public class IgomWeathers {
    public static Weather methaneFog, methaneRain, methaneSnow, carbonSnow;

    public static void load() {
        carbonSnow = new ParticleWeather("carbon-snow"){{
            localizedName = "Carbon Snow";
            particleRegion = "particle";
            sizeMax = 6f;
            sizeMin = 1.5f;
            density = 1200f;
            xspeed = 0.1f;
            yspeed = -0.8f;
            attrs.set(Attribute.light, -0.15f);
            color = Color.valueOf("dae1f1");
            sound = Sounds.windhowl;
            soundVol = 0.05f;
        }};
        methaneSnow = new ParticleWeather("methane-snow"){{
            localizedName = "Methane Snow";
            particleRegion = "particle";
            sizeMax = 10f;
            sizeMin = 1f;
            density = 6000f;
            xspeed = 0.1f;
            yspeed = -0.9f;
            attrs.set(Attribute.light, -0.15f);
            color = Color.valueOf("aed0c7");
            sound = Sounds.windhowl;
            soundVol = 0.05f;
        }};
        methaneRain = new RainWeather("methane-rain"){{
            localizedName = "Methane Rain";
            duration = 15f * Time.toMinutes;
            color = Color.valueOf("aed0c6");
            density = 2000f;
            yspeed = 1.1f;
            xspeed = 0.2f;
            sound = Sounds.rain;
            soundVol = 0.05f;
        }};
        methaneFog = new ParticleWeather("methane-fog"){{
            localizedName = "Methane Fog";
            duration = 45f * Time.toMinutes;
            noiseLayers = 3;
            noiseLayerAlphaM = 0.7f;
            noiseLayerSpeedM = 2f;
            noiseLayerSclM = 0.6f;
            baseSpeed = 0.05f;
            color = noiseColor = Color.valueOf("c8e6dc");
            noiseScale = 1100f;
            noisePath = "clouds";
            drawParticles = false;
            drawNoise = true;
            useWindVector = false;
            xspeed = 0.01f;
            yspeed = -0.01f;
            attrs.set(Attribute.light, -0.3f);
            opacityMultiplier = 0.1f;
        }};
    }
}

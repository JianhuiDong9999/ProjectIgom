package world.draw;

import arc.Core;
import arc.graphics.Blending;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import mindustry.Vars;
import mindustry.gen.Building;
import mindustry.world.Block;
import mindustry.world.draw.DrawWeave;

public class DrawGlowWeave extends DrawWeave {
    public TextureRegion glow;
    public float rotateSpeed = 1.0F;
    public Color glowColor = new Color(1.0F, 0.4F, 0.4F, 0.8F);
    public Color weaveColor;
    public float pulse;
    public float pulseScl;
    public DrawGlowWeave() {
        weaveColor = Color.white.cpy();
        pulse = 0.3F;
        pulseScl = 10.0F;
    }
    public void draw(Building build) {

        Draw.color(this.weaveColor);
        Draw.alpha(0.25f);
        Lines.lineAngleCenter(
                build.x + Mathf.sin(build.totalProgress() / 2, 6f, Vars.tilesize / 3f * build.block.size),
                build.y,
                90,
                build.block.size * Vars.tilesize / 2f);
        Draw.alpha(1f);
        Draw.rect(this.weave, build.x, build.y, build.totalProgress() * this.rotateSpeed);
        Draw.blend(Blending.additive);
        Draw.color(this.glowColor, build.warmup() * this.glowColor.a * (1.0F - this.pulse + Mathf.absin(this.pulseScl, this.pulse)));
        Draw.rect(this.glow, build.x, build.y, build.totalProgress() * this.rotateSpeed);
        Draw.blend();
        Draw.reset();
    }
    public void load(Block block) {
        this.weave = Core.atlas.find(block.name + "-weave");
        this.glow = Core.atlas.find(block.name + "-weave-glow");
    }
}

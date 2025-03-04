package entities.abilities;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.util.Time;
import arc.util.Tmp;
import mindustry.content.Fx;
import mindustry.entities.abilities.Ability;
import mindustry.entities.abilities.RepairFieldAbility;
import mindustry.gen.Unit;
import mindustry.graphics.Pal;

public class FancyRepairFieldAbility extends RepairFieldAbility {
    public float x = 0;
    public float y = 0;
    public float size = 2;
    public FancyRepairFieldAbility(float amount, float reload, float range) {
        super(amount, reload, range);
    }
    public void draw(Unit unit) {
        Tmp.v1.set(this.x, this.y).add(unit);
        float rx = Tmp.v1.x;
        float ry = Tmp.v1.y;
        Draw.z(110.0F);
        Draw.alpha(0.5f);
        Draw.color(Pal.regen);
        Fill.circle(rx, ry, size);
        Draw.color(Color.white);
        Fill.circle(rx, ry, size / 2f);

        Draw.reset();
    }
}

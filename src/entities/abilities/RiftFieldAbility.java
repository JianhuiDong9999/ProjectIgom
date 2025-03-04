package entities.abilities;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Angles;
import arc.math.Mathf;
import arc.scene.ui.layout.Table;
import arc.util.Interval;
import arc.util.Strings;
import arc.util.Tmp;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.Effect;
import mindustry.entities.Units;
import mindustry.entities.abilities.Ability;
import mindustry.gen.Unit;
import mindustry.graphics.Pal;
import mindustry.type.StatusEffect;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;

public class RiftFieldAbility extends Ability {
    public StatusEffect effect = StatusEffects.slow;
    public float duration = 60.0F;
    public float range = 20.0F;
    public Effect applyEffect;
    public Effect activeEffect;
    public float effectX;
    public float effectY;
    public boolean parentizeEffects;
    public boolean effectSizeParam;
    protected float timer;

    protected Interval checker;

    public float x = 0f;
    public float y = 0f;

    public float riftRadius = 24f;
    public Color riftColor = Pal.sap;

    public float riftStroke = 2f;

    // Constructors

    RiftFieldAbility() {
        this.applyEffect = Fx.none;
        this.activeEffect = Fx.none;
        this.effectSizeParam = true;
    }

    public RiftFieldAbility(StatusEffect effect, float duration, float range) {
        this.applyEffect = Fx.none;
        this.activeEffect = Fx.none;
        this.effectSizeParam = true;
        this.duration = duration;
        this.range = range;
        this.effect = effect;
    }
    // Methods
    public boolean timer(int index, float time, Interval timer) {
        return !Float.isInfinite(time) && timer.get(index, time);
    }

    public void addStats(Table t) {
        t.add("[lightgray]" + Stat.shootRange.localized() + ": [white]" + Strings.autoFixed(this.range / 8.0F, 2) + " " + StatUnit.blocks.localized());
        t.row();
        t.add(this.effect.emoji() + " " + this.effect.localizedName);
    }

    public void update(Unit unit) {
        checker = new Interval(1);
        if (timer(0, 12, checker)) {
            Units.nearby(null, unit.x, unit.y, this.range, (other) -> {
                if(other.team != unit.team) {
                    other.apply(this.effect, this.duration);
                    this.applyEffect.at(other, this.parentizeEffects);
                }
            });
            float x = unit.x + Angles.trnsx(unit.rotation, this.effectY, this.effectX);
            float y = unit.y + Angles.trnsy(unit.rotation, this.effectY, this.effectX);
            this.activeEffect.at(x, y, this.effectSizeParam ? this.range : unit.rotation, this.parentizeEffects ? unit : null);
            this.timer = 0.0F;
        }
    }
    public void draw(Unit unit) {
        float rad = riftRadius + Mathf.absin(12, 0.5f);
        Tmp.v1.set(this.x, this.y).rotate(unit.rotation - 90.0F).add(unit);
        float rx = Tmp.v1.x;
        float ry = Tmp.v1.y;
        Draw.z(110.00f);
        for(int i = 1; i < 5; i++) {
            Draw.color(riftColor, Color.white, 0.2f * i);
            Lines.stroke(riftStroke - (riftStroke / 5 * i));
            Lines.circle(rx, ry, riftStroke / 2f * rad);
        }
        Draw.z(79.99f);
        Draw.color(Color.black);
        Fill.circle(rx, ry, rad);
        Draw.color(Color.valueOf("00000011"));
        Fill.circle(rx, ry, range);
        Draw.color(Color.valueOf("00000015"));
        Fill.circle(rx, ry, Mathf.pow(range, 0.85f));
        Draw.color(Color.valueOf("0000001a"));
        Fill.circle(rx, ry, Mathf.pow(range, 0.7f));

        Draw.reset();
    }
}

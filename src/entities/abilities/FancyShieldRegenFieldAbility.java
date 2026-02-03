package entities.abilities;

import arc.Core;
import arc.audio.Sound;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import arc.scene.ui.layout.Table;
import arc.util.Strings;
import arc.util.Time;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.entities.Units;
import mindustry.entities.abilities.Ability;
import mindustry.gen.Sounds;
import mindustry.gen.Unit;

public class FancyShieldRegenFieldAbility extends Ability {
    public float amount = 1.0F;
    public float max = 100.0F;
    public float reload = 100.0F;
    public float range = 60.0F;
    public Effect applyEffect;
    public Effect activeEffect;
    public Sound sound;
    public float soundVolume;
    public boolean parentizeEffects;
    public boolean affectAir;
    public boolean affectGround;
    protected float timer;
    protected boolean applied;

    FancyShieldRegenFieldAbility() {
        applyEffect = Fx.shieldApply;
        activeEffect = new Effect(22.0F, (e) -> {
            Draw.color(e.color, 0.7F);
            Lines.stroke(e.fout() * 2.0F);
            Lines.circle(e.x, e.y, 4.0F + e.finpow() * range);
        });
        sound = Sounds.shieldWave;
        soundVolume = 0.7F;
        applied = false;
        affectAir = true;
        affectGround = true;
    }

    public FancyShieldRegenFieldAbility(float amount, float max, float reload, float range) {
        applyEffect = Fx.shieldApply;
        activeEffect = new Effect(22.0F, (e) -> {
            Draw.color(e.color, 0.7F);
            Lines.stroke(e.fout() * 2.0F);
            Lines.circle(e.x, e.y, 4.0F + e.finpow() * range);
        });
        sound = Sounds.shieldWave;
        soundVolume = 0.7F;
        applied = false;
        this.amount = amount;
        this.max = max;
        this.reload = reload;
        this.range = range;
        affectAir = true;
        affectGround = true;
    }

    public void addStats(Table t) {
        super.addStats(t);
        t.add(Core.bundle.format("bullet.range", new Object[]{Strings.autoFixed(this.range / 8.0F, 2)}));
        t.row();
        t.add(this.abilityStat("firingrate", new Object[]{Strings.autoFixed(60.0F / this.reload, 2)}));
        t.row();
        t.add(this.abilityStat("pulseregen", new Object[]{Strings.autoFixed(this.amount, 2)}));
        t.row();
        t.add(this.abilityStat("shield", new Object[]{Strings.autoFixed(this.max, 2)}));
    }

    public void update(Unit unit) {
        this.timer += Time.delta;
        if (this.timer >= this.reload) {
            this.applied = false;
            Units.nearby(unit.team, unit.x, unit.y, this.range, (other) -> {
                if (other.shield < this.max && ((affectGround && other.isGrounded()) || (affectAir && other.isFlying()))) {
                    other.shield = Math.min(other.shield + this.amount, this.max);
                    other.shieldAlpha += other.shield / this.max;
                    this.applyEffect.at(other.x, other.y, 0.0F, other.type.shieldColor(other), this.parentizeEffects ? other : null);
                    this.applied = true;
                }

            });
            if (this.applied) {
                this.activeEffect.at(unit.x, unit.y, unit.type.shieldColor(unit));
                this.sound.at(unit, 1.0F + Mathf.range(0.1F), this.soundVolume);
            }

            this.timer = 0.0F;
        }

    }
}

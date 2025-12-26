package entities.abilities;

import arc.Events;
import arc.audio.Sound;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Angles;
import arc.math.Mathf;
import arc.scene.ui.layout.*;
import arc.struct.Seq;
import arc.util.*;
import mindustry.Vars;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.abilities.Ability;
import mindustry.game.EventType;
import mindustry.gen.*;
import mindustry.graphics.Drawf;
import mindustry.world.blocks.defense.ShockwaveTower;
import mindustry.world.meta.*;

import static mindustry.Vars.tilesize;

public class ShockwaveAbility extends Ability {
    // Basic stats
    public float damage = 5, reload = 50, range = 80, shake = 2;
    // TODO: Implement Offset coordinates
    public float x = 0;
    public float y = 0;

    // Effects
    public Color shockwaveColor = Color.white;
    public Color shapeColor = shockwaveColor;
    public float shockwaveStroke = 2f;
    public Effect shockwaveEffect = new Effect(15.0F, (e) -> {
        Draw.color(shockwaveColor);
        Lines.stroke(e.fout() * shockwaveStroke);
        Lines.circle(e.x, e.y, e.finpow() * e.rotation);
        Lines.circle(e.x, e.y, e.finpow() * e.rotation / 10);
        Angles.randLenVectors((long)(e.id + 1), 8, 1.0F + 23.0F * e.finpow(), (x, y) -> {
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1.0F + e.fout() * 3.0F);
        });
    });
    public Effect hitEffect = Fx.hitSquaresColor;
    public int shapeSides = 6;
    public float shapeRadius = 6f;
    public float shapeRotateSpeed = 0.5f;
    public float heat = 0f;
    public Sound shockSound = Sounds.none;
    public float shockSoundVolume = 1f;
    protected Interval timer;

    // The # of targeted bullets before damage falloff
    public float falloffCount = 20.0F;

    // Data structure holding the targeted bullets
    public Seq<Bullet> targets = new Seq<>();
    protected float reloadCounter = Mathf.random(reload);

    // TODO: Implement interval check for performance improvement

    // TODO: Implement ground/air specializations
    public boolean ground = true;
    public boolean air = true;

    // No clue what this is
    protected boolean wasShocked = false;

    // Default constructor
    ShockwaveAbility(){}

    // Parameterized constructor
    public ShockwaveAbility(float damage, float reload, float range){
        this.damage = damage;
        this.reload = reload;
        this.range = range;
    }

    // Methods
    public boolean timer(int index, float time, Interval timer) {
        return !Float.isInfinite(time) && timer.get(index, time);
    }
    @Override
    public void addStats(Table t){
        t.add("[lightgray]" + Stat.damage.localized() + ": [white]" + Strings.autoFixed(damage * 60f / reload, 2) + StatUnit.perSecond.localized());
        t.row();
        t.add("[lightgray]" + Stat.shootRange.localized() + ": [white]" +  Strings.autoFixed(range / tilesize, 2) + " " + StatUnit.blocks.localized());
    }
    @Override
    public void update(Unit unit){

        float x = unit.x;
        float y = unit.y;
        timer = new Interval(1);
        if((reloadCounter += Time.delta) >= reload && timer(0, 12f, timer)){
            targets.clear();
            Groups.bullet.intersect(x - range, y - range, range * 2, range * 2, b -> {
                if(b.team != unit.team && b.type.hittable){
                    targets.add(b);
                }
            });

            if(targets.size > 0){
                Tmp.v1.set(this.x, this.y).rotate(unit.rotation - 90.0F).add(unit);
                float rx = Tmp.v1.x;
                float ry = Tmp.v1.y;
                heat = 1f;
                reloadCounter = 0f;
                shockwaveEffect.at(rx, ry, range);
                shockSound.at(rx, ry, 1, shockSoundVolume);
                Effect.shake(shake, shake, rx, ry);
                float waveDamage = Math.min(damage, damage * falloffCount / targets.size);

                for(var target : targets){
                    if(target.damage > waveDamage){
                        target.damage -= waveDamage;
                    }else{
                        target.remove();
                    }
                    hitEffect.at(target.x, target.y);
                }

                if(unit.team == Vars.state.rules.defaultTeam){
                    Events.fire(EventType.Trigger.shockwaveTowerUse);
                }
            }
        }
        heat = Mathf.clamp(heat - Time.delta / reload);
    }
    public void draw(Unit unit) {
        Tmp.v1.set(this.x, this.y).rotate(unit.rotation - 90.0F).add(unit);
        float rx = Tmp.v1.x;
        float ry = Tmp.v1.y;
        Draw.z(110.0F);
        Draw.color(shapeColor, shockwaveColor, 1f - heat);
        Fill.poly(rx, ry, shapeSides, shapeRadius, Time.time * shapeRotateSpeed);
        Lines.stroke(1.5f);
        Lines.poly(rx, ry, shapeSides, shapeRadius * 1.75f, Time.time * shapeRotateSpeed);
        Draw.color(shapeColor, Color.white, 1f - heat);
        Fill.poly(rx, ry, shapeSides, shapeRadius * 0.6f, Time.time * shapeRotateSpeed);

        Draw.reset();
    }
}

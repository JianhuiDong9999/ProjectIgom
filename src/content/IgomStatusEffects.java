package content;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import game.IgomTeams;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.Effect;
import mindustry.game.Team;
import mindustry.gen.Unit;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.StatusEffect;
import mindustry.type.UnitType;
import mindustry.world.meta.Stat;
import type.IgomStatusEffect;

import static arc.graphics.g2d.Draw.color;
import static arc.math.Angles.randLenVectors;

public class IgomStatusEffects {
    public static StatusEffect setBoss, drenched, breached, irradiated, hampered, sentinel;

    public static void load() {
        breached = new IgomStatusEffect("breached") {{
            localizedName = "Breached";
            effect = new Effect(40f, e -> {
                color(Pal.redLight);

                randLenVectors(e.id, 2, 1f + e.fin() * 2f, (x, y) -> {
                    Fill.square(e.x + x, e.y + y, e.fslope() * 1.1f, 45f);
                });
            });
            color = Pal.redLight;
            outline = true;
            transitionDamage = 12f;
            healthMultiplier = 0.9f;
            speedMultiplier = 0.7f;
            init(() -> {
                opposite(StatusEffects.shielded);

                affinity(StatusEffects.blasted, (unit, result, time) -> {
                    unit.damagePierce(transitionDamage);
                });
            });
        }};
        setBoss = new StatusEffect("set-boss") {{
            localizedName = "Inflict Preserver Boss";
            init(() -> {
                affinity(StatusEffects.boss, (unit, result, time) -> {
                    result.set(sentinel, 99999999);
                });
            });
        }};
        sentinel = new StatusEffect("sentinel"){{
            localizedName = "Sentinel";
            color = IgomTeams.preserver.color;
            permanent = true;
            damageMultiplier = 1.5f;
            healthMultiplier = 4f;
            effect = new Effect(30f, e -> {
                Draw.color(IgomTeams.preserver.color);
                Draw.z(Layer.bullet + 0.01f);
                Lines.stroke(e.fslope() * 2f);
                Lines.square(e.x, e.y, e.fout() * 3f, 45f);
                Draw.reset();
            });
        }};
    }
}

package content;

import arc.graphics.g2d.Fill;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.Effect;
import mindustry.graphics.Pal;
import mindustry.type.StatusEffect;
import mindustry.world.meta.Stat;
import type.IgomStatusEffect;

import static arc.graphics.g2d.Draw.color;
import static arc.math.Angles.randLenVectors;

public class IgomStatusEffects {
    public static StatusEffect drenched, breached, irradiated, hampered;

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
    }
}

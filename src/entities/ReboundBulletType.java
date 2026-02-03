package entities;

import arc.math.Mathf;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.gen.Bullet;

public class ReboundBulletType extends BasicBulletType {
    public boolean ricochet;
    public float reboundChance;
    public float lifetimeFactor;
    public ReboundBulletType(float speed, float damage, String sprite) {
        super(speed, damage, sprite);
        //Use pierce for bounce
        pierce = true;
        pierceBuilding = true;
        pierceArmor = false;
        pierceCap = 2;
        ricochet = true;
        reboundChance = 1f;
        lifetimeFactor = 1f;
    }
    public ReboundBulletType(float speed, float damage) {
        this(speed, damage, "bullet");
    }
    public ReboundBulletType() {
        this(1.0F, 1.0F, "bullet");
    }
    @Override
    public void handlePierce(Bullet b, float initialHealth, float x, float y) {
        b.fdata += 1f;
        float sub = Mathf.zero(this.pierceDamageFactor) ? 0.0F : Math.max(initialHealth * this.pierceDamageFactor, 0.0F);
        b.damage -= Float.isNaN(sub) ? b.damage : Math.min(b.damage, sub);
        if (this.removeAfterPierce && b.damage <= 0.0F || b.fdata == pierceCap || !Mathf.chance(reboundChance)) {
            b.hit = true;
            b.remove();
        }
        b.trns(-b.vel.x, -b.vel.y);

        float penX = Math.abs(x - b.x), penY = Math.abs(y - b.y);

        if (penX > penY) {
            b.vel.x *= -1;
        } else {
            b.vel.y *= -1;
        }
        b.lifetime *= lifetimeFactor;
        if(ricochet) {
            b.collided.clear();
        }
    }
}

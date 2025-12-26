package content.blocks;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import content.IgomItems;
import content.IgomLiquids;
import content.IgomUnitTypes;
import graphics.IgomCacheLayer;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.content.StatusEffects;
import mindustry.entities.Effect;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.effect.MultiEffect;
import mindustry.gen.Sounds;
import mindustry.graphics.CacheLayer;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.Turret;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.power.PowerNode;
import mindustry.world.blocks.power.ThermalGenerator;
import mindustry.world.blocks.production.AttributeCrafter;
import mindustry.world.blocks.production.Drill;
import mindustry.world.blocks.production.Fracker;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.consumers.ConsumeLiquidBase;
import mindustry.world.draw.*;
import mindustry.world.meta.Attribute;
import type.MethaneFloor;
import type.MethaneMudFloor;
import world.blocks.IgomAttribute;
import world.blocks.distribution.JunctionReceptiveArmoredConveyor;
import world.blocks.extraction.AttributeCollector;

import static mindustry.type.ItemStack.with;

public class IgomTurretBlocks {
    public static Block pelt, hammer, pummel, obfuscate, tide;

    public static void load(){
        //Registers build IgomTurretBlocks
        //no reference is needed here since they can be looked up by name later
        pelt = new ItemTurret("pelt") {{
            localizedName = "Pelt";
            description = "Fires volleys of bullets at enemy targets.";
            requirements(Category.turret, with(IgomItems.nickel, 120, Items.graphite, 60, Items.silicon, 80));
            buildCostMultiplier = 2f;
            size = 3;
            outlineColor = Color.valueOf("3a4752");
            //recoils = 3;
            recoil = 1f;
            shoot.shots = 3;
            shoot.shotDelay = 6f;
            squareSprite = false;
            itemCapacity = 30;
            shootY = 12f;
            reload = 80f;
            range = 240f;
            shootCone = 15f;
            ammoUseEffect = Fx.casing3;
            health = 1080;
            inaccuracy = 1f;
            rotateSpeed = 4f;
            coolant = consumeCoolant(0.05f);
            coolantMultiplier = 24f;
            limitRange(2.0F);
            ammo(IgomItems.nickel, new BasicBulletType(4.8f, 48){{
                width = 12f;
                height = 16f;
                lifetime = 48f;
                knockback = 3.1f;
                ammoMultiplier = 1f;
                hitColor = backColor = trailColor = Color.valueOf("549e60");
                frontColor = Color.white;
                despawnEffect = hitEffect = Fx.hitBulletSmall;
                trailWidth = 2.75f;
                trailLength = 3;
                smokeEffect = Fx.shootSmallSmoke;
            }}, IgomItems.chromium, new BasicBulletType(4.8f, 44){{
                width = 10f;
                height = 18f;
                lifetime = 64f;
                knockback = 0.8f;
                rangeChange = 96f;
                reloadMultiplier = 0.75f;
                ammoMultiplier = 2f;
                hitColor = backColor = trailColor = Color.valueOf("669995");
                frontColor = Color.white;
                despawnEffect = hitEffect = Fx.hitLancer;
                trailWidth = 2.25f;
                trailLength = 4;
                smokeEffect = Fx.shootSmallSmoke;
                fragOnHit = true;
                fragOnAbsorb = false;
                fragRandomSpread = 90f;
                fragBullets = 3;
                fragLifeMin = 0.5f;
                fragLifeMax = 4f;
                fragVelocityMin = 0.5f;
                fragVelocityMax = 2f;
                fragBullet = new BasicBulletType(){{
                    width = 5f;
                    height = 9f;
                    lifetime = 4f;
                    damage = 16;
                    hitColor = backColor = trailColor = Color.valueOf("669995");
                    frontColor = Color.white;
                    despawnEffect = hitEffect = Fx.none;
                    trailWidth = 1.125f;
                    trailLength = 2;
                }};
            }});
        }};
    }
}

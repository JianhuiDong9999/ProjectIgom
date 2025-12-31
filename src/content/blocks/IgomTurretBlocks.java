package content.blocks;

import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Interp;
import arc.math.Mathf;
import content.IgomItems;
import content.IgomLiquids;
import content.IgomStatusEffects;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.content.StatusEffects;
import mindustry.entities.Effect;
import mindustry.entities.UnitSorts;
import mindustry.entities.abilities.MoveEffectAbility;
import mindustry.entities.bullet.*;
import mindustry.entities.part.FlarePart;
import mindustry.entities.part.RegionPart;
import mindustry.gen.Sounds;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.Category;
import mindustry.type.Weapon;
import mindustry.type.unit.MissileUnitType;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.consumers.ConsumeLiquid;
import mindustry.world.consumers.ConsumeLiquidBase;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawHeatRegion;
import mindustry.world.draw.DrawMulti;
import mindustry.world.draw.DrawTurret;
import mindustry.world.meta.Env;
import world.blocks.crafters.TeamItemTurret;
import world.blocks.turrets.MultiItemTurret;

import static arc.graphics.g2d.Draw.alpha;
import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.lineAngle;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Angles.randLenVectors;
import static mindustry.content.Fx.rand;
import static mindustry.content.Fx.v;
import static mindustry.type.ItemStack.with;

public class IgomTurretBlocks {
    public static Block pelt, batter, erode, pummel, seethe, dismantle, punish, obfuscate, extricate, blight;

    public static void load(){
        //Registers build IgomTurretBlocks
        //no reference is needed here since they can be looked up by name later
        pelt = new ItemTurret("pelt") {{
            localizedName = "Pelt";
            description = "Fires volleys of bullets at enemy targets.";
            requirements(Category.turret, with(IgomItems.nickel, 120, Items.graphite, 120, Items.silicon, 80));
            buildCostMultiplier = 2f;
            size = 3;
            outlineColor = Color.valueOf("3a4752");
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
                width = 8f;
                height = 16f;
                lifetime = 48f;
                knockback = 3.1f;
                ammoMultiplier = 2f;
                hitColor = backColor = trailColor = Color.valueOf("549e60");
                frontColor = Color.white;
                despawnEffect = hitEffect = Fx.hitBulletSmall;
                trailWidth = 2.75f;
                trailLength = 3;
                smokeEffect = Fx.shootSmallSmoke;
            }}, IgomItems.chromium, new BasicBulletType(4.8f, 44){{
                width = 9f;
                height = 18f;
                lifetime = 64f;
                knockback = 0.8f;
                rangeChange = 96f;
                reloadMultiplier = 0.75f;
                ammoMultiplier = 3f;
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
        batter = new ItemTurret("batter"){{
            localizedName = "Batter";
            requirements(Category.turret, with(IgomItems.lithium, 120, Items.graphite, 220, Items.silicon, 180));
            buildCostMultiplier = 2f;

            predictTarget = false;
            ammo(
                    IgomItems.lithium, new BulletType(0f, 0f){{
                        shootEffect = Fx.shootSmokeSquareBig;
                        smokeEffect = new Effect(130f, 300f, e -> {
                            color(e.color);
                            alpha(0.5f);
                            rand.setSeed(e.id);
                            for(int i = 0; i < 15; i++){
                                v.trns(e.rotation + 180f + rand.range(21f), rand.random(e.finpow() * 90f)).add(rand.range(3f), rand.range(3f));
                                e.scaled(e.lifetime * rand.random(0.2f, 1f), b -> {
                                    Fill.circle(e.x + v.x, e.y + v.y, b.fout() * 3f + 0.3f);
                                });
                            }
                        });
                        hitColor = Pal.redLight;
                        ammoMultiplier = 1f;

                        spawnUnit = new MissileUnitType("batter-missile"){{
                            speed = 4.2f;
                            maxRange = 6f;
                            lifetime = 60f * 2.8f;
                            outlineColor = Color.valueOf("3a4752");
                            engineColor = trailColor = Pal.redLight;
                            engineLayer = Layer.effect;
                            engineSize = 1.5f;
                            engineOffset = 6f;
                            rotateSpeed = 0.6f;
                            targetInterval = 5f;
                            trailLength = 6;
                            missileAccelTime = 120f;
                            lowAltitude = true;
                            loopSound = Sounds.loopMissileTrail;
                            loopSoundVolume = 0.3f;
                            deathSound = Sounds.explosionMissile;
                            targetAir = false;
                            targetUnderBlocks = false;

                            fogRadius = 3f;

                            health = 140;

                            parts.add(new FlarePart(){{
                                color1 = Pal.redLight;
                                progress = PartProgress.life.slope().curve(Interp.pow2In);
                                radius = 5f;
                                radiusTo = 35f;
                                stroke = 3f;
                                rotation = 45f;
                                y = -5f;
                                followRotation = true;
                            }});

                            weapons.add(new Weapon(){{
                                shootCone = 360f;
                                mirror = false;
                                reload = 1f;
                                deathExplosionEffect = Fx.titanExplosion;
                                shootOnDeath = true;
                                shake = 5f;
                                bullet = new ExplosionBulletType(120f, 49f){{
                                    hitColor = Pal.redLight;
                                    shootEffect = new Effect(30, e -> {
                                        color(Pal.redLight);

                                        e.scaled(7, i -> {
                                            stroke(3f * i.fout());
                                            Lines.circle(e.x, e.y, 4f + i.fin() * 30f);
                                        });

                                        color(Color.gray);

                                        randLenVectors(e.id, 8, 2f + 30f * e.finpow(), (x, y) -> {
                                            Fill.circle(e.x + x, e.y + y, e.fout() * 4f + 0.5f);
                                        });

                                        color(Pal.redLight);
                                        stroke(e.fout());

                                        randLenVectors(e.id + 1, 6, 1f + 29f * e.finpow(), (x, y) -> {
                                            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + e.fout() * 4f);
                                        });

                                        Drawf.light(e.x, e.y, 50f, Pal.redLight, 0.8f * e.fout());
                                    });

                                    collidesAir = false;
                                    buildingDamageMultiplier = 0.1f;
                                    status = StatusEffects.blasted;

                                    ammoMultiplier = 1f;
                                    fragBullets = 4;
                                    fragSpread = 90f;
                                    fragRandomSpread = 0f;
                                    fragBullet = new ShrapnelBulletType(){{
                                        buildingDamageMultiplier = 0.1f;
                                        hitEffect = Fx.hitLaser;
                                        knockback = 4.5f;
                                        lifetime = 30f;
                                        width = 30f;
                                        length = 60f;
                                        serrationWidth = 5f;
                                        serrations = 5;
                                        serrationFadeOffset = 0.2f;
                                        collidesTiles = false;
                                        damage = 164f;
                                        fromColor = Color.white;
                                        toColor = Pal.redLight;
                                        smokeEffect = Fx.shootBigSmoke;
                                        status = IgomStatusEffects.breached;
                                        statusDuration = 3 * 60f;
                                        shake = 5f;
                                        lightRadius = 30f;
                                        lightColor = Pal.redLight;
                                        lightOpacity = 0.5f;
                                    }};
                                }};
                            }});

                            abilities.add(new MoveEffectAbility(){{
                                effect = new Effect(60f, 300f, b -> {
                                    float intensity = 1f;

                                    color(b.color, 0.7f);
                                    for(int i = 0; i < 2; i++){
                                        rand.setSeed(b.id*2 + i);
                                        float lenScl = rand.random(0.5f, 1f);
                                        int fi = i;
                                        b.scaled(b.lifetime * lenScl, e -> {
                                            randLenVectors(e.id + fi - 1, e.fin(Interp.pow10Out), (int)(2.9f * intensity), 13f * intensity, (x, y, in, out) -> {
                                                float fout = e.fout(Interp.pow5Out) * rand.random(0.5f, 1f);
                                                float rad = fout * ((1f + intensity) * 2.35f);

                                                Fill.circle(e.x + x, e.y + y, rad);
                                                Drawf.light(e.x + x, e.y + y, rad * 2.5f, b.color, 0.5f);
                                            });
                                        });
                                    }
                                }).layer(Layer.bullet - 1f);
                                rotation = 180f;
                                y = -9f;
                                color = Color.grays(0.6f).lerp(Pal.redLight, 0.5f).a(0.4f);
                                interval = 7f;
                            }});
                        }};
                    }}
            );

            drawer = new DrawMulti(new DrawTurret(){{
                parts.add(new RegionPart("-missile"){{
                            progress = PartProgress.reload.curve(Interp.pow2In);
                            y = 2f;

                            colorTo = new Color(1f, 1f, 1f, 0f);
                            color = Color.white;
                            mixColorTo = Pal.accent;
                            mixColor = new Color(1f, 1f, 1f, 0f);
                            outline = false;
                            under = true;

                            layerOffset = -0.01f;

                            moves.add(new PartMove(PartProgress.warmup.inv(), 0f, -6f, 0f));
                        }});
            }});

            recoil = 0.5f;

            coolantMultiplier = 8f;
            shootSound = Sounds.shootMissileLarge;
            consumeLiquid(IgomLiquids.oxygen, 3f / 60f);
            consumeLiquid(IgomLiquids.methane, 6f / 60f);
            coolant = consume(new ConsumeLiquid(IgomLiquids.liquidnitrogen, 6f / 60f));
            liquidCapacity = 20f;

            minWarmup = 0.94f;
            newTargetInterval = 20f;
            unitSort = UnitSorts.strongest;
            shootWarmupSpeed = 0.05f;
            targetAir = false;
            targetUnderBlocks = false;

            shake = 3f;
            ammoPerShot = 6;
            maxAmmo = 12;
            shootY = 0;
            outlineColor = Color.valueOf("3a4752");
            size = 3;
            envEnabled |= Env.space;
            reload = 360f;
            range = 384;
            shootCone = 60f;
            health = 2420;
            rotateSpeed = 0.6f;

            limitRange();
        }};
    }
}

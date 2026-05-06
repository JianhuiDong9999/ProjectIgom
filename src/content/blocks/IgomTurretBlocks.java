package content.blocks;

import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Interp;
import arc.math.Mathf;
import arc.struct.EnumSet;
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
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.part.DrawPart;
import mindustry.entities.part.FlarePart;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.ShootAlternate;
import mindustry.entities.pattern.ShootBarrel;
import mindustry.entities.pattern.ShootMulti;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.Sounds;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.Category;
import mindustry.type.Weapon;
import mindustry.type.unit.MissileUnitType;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ContinuousTurret;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.LaserTurret;
import mindustry.world.blocks.defense.turrets.PowerTurret;
import mindustry.world.consumers.ConsumeLiquid;
import mindustry.world.consumers.ConsumeLiquidBase;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawHeatRegion;
import mindustry.world.draw.DrawMulti;
import mindustry.world.draw.DrawTurret;
import mindustry.world.meta.BlockFlag;
import mindustry.world.meta.Env;
import world.blocks.crafters.TeamItemTurret;
import world.blocks.turrets.ItemLiquidTurret;
import world.blocks.turrets.MultiItemTurret;

import static arc.graphics.g2d.Draw.alpha;
import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.lineAngle;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Angles.randLenVectors;
import static mindustry.content.Fx.*;
import static mindustry.type.ItemStack.with;

public class IgomTurretBlocks {
    public static Block pelt, batter, quench, diverge, fixate, erode, pummel, seethe, dismantle, punish, obfuscate, extricate, blight;

    public static void load(){
        //Registers build IgomTurretBlocks
        //no reference is needed here since they can be looked up by name later
        pelt = new ItemTurret("pelt") {{
            localizedName = "Pelt";
            description = "Fires volleys of bullets at enemy targets.";
            requirements(Category.turret, with(IgomItems.nickel, 100, Items.graphite, 120, Items.silicon, 80));
            buildCostMultiplier = 1.2f;
            size = 3;
            squareSprite = false;
            outlineColor = Color.valueOf("3a4752");
            recoil = 1f;
            shoot.shots = 3;
            shoot.shotDelay = 6f;
            itemCapacity = 30;
            shootY = 12f;
            reload = 80f;
            range = 240f;
            shootCone = 15f;
            ammoUseEffect = Fx.casing3;
            health = 1680;
            inaccuracy = 1f;
            rotateSpeed = 4f;
            coolant = consumeCoolant(3f / 60f);
            coolantMultiplier = 24f;
            liquidCapacity = 60f;
            limitRange(2.0F);
            ammo(IgomItems.nickel, new BasicBulletType(4.8f, 24){{
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
            }}, IgomItems.chromium, new BasicBulletType(4.8f, 22){{
                width = 9f;
                height = 18f;
                lifetime = 64f;
                knockback = 0.8f;
                rangeChange = 96f;
                reloadMultiplier = 1.5f;
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
                    damage = 12;
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
            requirements(Category.turret, with(IgomItems.lithium, 160, Items.graphite, 320, Items.silicon, 240));
            buildCostMultiplier = 1.75f;

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
                            rotateSpeed = 0.8f;
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

                            health = 54;
                            armor = 1;

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
                                bullet = new ExplosionBulletType(78f, 49f){{
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
                                        width = 16f;
                                        length = 60f;
                                        serrationWidth = 5f;
                                        serrations = 5;
                                        serrationFadeOffset = 0.2f;
                                        collidesTiles = false;
                                        damage = 34f;
                                        fromColor = Color.white;
                                        toColor = Pal.redLight;
                                        smokeEffect = Fx.shootBigSmoke;
                                        status = IgomStatusEffects.breached;
                                        statusDuration = 5 * 60f;
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
                                        rand.setSeed(b.id* 2L + i);
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
                }}, new RegionPart() {{
                    drawRegion = false;
                    heatProgress = PartProgress.recoil;
                }});
            }});

            recoil = 0.5f;

            coolantMultiplier = 8f;
            shootSound = Sounds.shootMissileLarge;
            consumeLiquid(IgomLiquids.oxygen, 4f / 60f);
            //consumeLiquid(IgomLiquids.methane, 12f / 60f);
            coolant = consume(new ConsumeLiquid(IgomLiquids.liquidnitrogen, 6f / 60f));
            liquidCapacity = 120f;

            minWarmup = 0.94f;
            newTargetInterval = 20f;
            unitSort = UnitSorts.strongest;
            shootWarmupSpeed = 0.05f;
            targetAir = false;
            targetUnderBlocks = false;

            shake = 3f;
            ammoPerShot = 8;
            maxAmmo = 24;
            shootY = 12f;
            outlineColor = Color.valueOf("3a4752");
            size = 3;
            squareSprite = false;
            envEnabled |= Env.space;
            reload = 4.5f * 60f;
            range = 396;
            shootCone = 60f;
            health = 2820;
            rotateSpeed = 0.6f;

            limitRange();
        }};
        quench = new ItemLiquidTurret("quench"){{
            localizedName = "Quench";
            description = "Extinguishes fires with sand. Deals knockback to enemy units but no damage.";
            requirements(Category.turret, with(Items.graphite, 90, IgomItems.nickel, 20, Items.silicon, 40));
            buildCostMultiplier = 1.5f;
            hasLiquids = true;
            ammo(
                    Items.sand, new LiquidBulletType(IgomLiquids.sand){{
                        orbSize = 1.5f;
                        knockback = 0.5f;
                        lifetime = 50f;
                        speed = 5f;
                        drag = 0.025f;
                        layer = Layer.bullet - 2f;
                        ammoMultiplier = 12;
                    }}
            );
            size = 2;
            squareSprite = false;
            recoil = 1f;
            reload = 3f;
            maxAmmo = 60;
            shootCone = 60f;
            inaccuracy = 4f;
            shoot = new ShootBarrel(){{
                barrels = new float[]{
                        -3f, -1.5f, 0f,
                        -1f, -1f, 0f,
                        1f, -1f, 0f,
                        3f, -1.5f, 0f
                };
                shots = 4;
                shotDelay = 0f;
            }};
            loopSound = Sounds.loopSpray;
            shootSound = Sounds.none;
            shootEffect = Fx.shootLiquid;
            range = 148f;
            health = 1020;
            armor = 2f;
            flags = EnumSet.of(BlockFlag.turret, BlockFlag.extinguisher);
        }};
        diverge = new ItemTurret("diverge") {{
            localizedName = "Diverge";
            description = "Fires sprays of flak at airborne enemies.";
            requirements(Category.turret, with(IgomItems.nickel, 160, IgomItems.metafiber, 120, Items.silicon, 40));
            buildCostMultiplier = 1.2f;
            size = 3;
            squareSprite = false;
            outlineColor = Color.valueOf("3a4752");
            recoil = 0.5f;
            shoot = new ShootMulti(new ShootAlternate(8f), new ShootSpread(3, 7.5f));
            itemCapacity = 30;
            shootY = 10f;
            reload = 48f;
            range = 296f;
            velocityRnd = 0.2f;
            scaleLifetimeOffset = 1f / 2f;
            shootCone = 30f;
            ammoUseEffect = Fx.casing3;
            shootEffect = Fx.shootSmokeSquareBig;
            health = 1820;
            inaccuracy = 1f;
            rotateSpeed = 4.8f;
            consumeLiquid(Liquids.hydrogen, 0.75f / 60f);
            coolant = consume(new ConsumeLiquid(Liquids.water, 4f / 60f));
            coolantMultiplier = 16f;
            limitRange(2.0F);
            targetAir = true;
            targetGround = false;
            recoils = 2;
            drawer = new DrawTurret(){{
                for(int i = 0; i < 2; i ++){
                    int f = i;
                    parts.add(new RegionPart("-barrel-" + (i == 0 ? "l" : "r")){{
                        progress = PartProgress.recoil;
                        heatProgress = PartProgress.recoil;
                        recoilIndex = f;
                        //under = true;
                        moveY = -2f;
                    }});
                }
            }};
            ammo(Items.silicon, new FlakBulletType(9f, 21f){{
                splashDamage = 16f;
                splashDamageRadius = 28f;
                collidesGround = false;
                homingRange = 4f;
                homingPower = 0.2f;
                homingDelay = 18f;
                reloadMultiplier = 1.5f;
                width = 10f;
                height = 10f;
                lifetime = 64f;
                scaleLife = true;
                drag = 0.02f;
                knockback = 1.2f;
                ammoMultiplier = 2f;
                hitColor = backColor = trailColor = Color.valueOf("696a78");
                frontColor = Color.white;
                despawnEffect = hitEffect = Fx.flakExplosion;
                trailWidth = 2f;
                trailLength = 3;
                smokeEffect = Fx.shootSmokeSquare;
                shootSound = Sounds.shootCyclone;
                shootSoundVolume = 1.2f;
            }}, IgomItems.metafiber, new FlakBulletType(9f, 18f){{
                splashDamage = 28f;
                splashDamageRadius = 52f;
                scaledSplashDamage = true;
                collidesGround = false;
                width = 12f;
                height = 12f;
                lifetime = 72f;
                scaleLife = true;
                rangeChange = 60f;
                drag = 0.015f;
                knockback = 2.4f;
                ammoMultiplier = 2f;
                hitColor = backColor = trailColor = Color.valueOf("338e8c");
                frontColor = Color.white;
                despawnEffect = hitEffect = new MultiEffect(Fx.flakExplosion, Fx.shockwave);
                trailWidth = 1.5f;
                trailLength = 2;
                smokeEffect = Fx.shootSmokeSquare;
                shootSound = Sounds.shootCyclone;
                shootSoundVolume = 1.8f;
                fragOnHit = true;
                fragOnAbsorb = false;
                fragAngle = 45f;
                fragSpread = 90f;
                fragBullets = 4;
                fragLifeMin = 0.5f;
                fragLifeMax = 1f;
                fragVelocityMin = 0.5f;
                fragVelocityMax = 1f;
                fragBullet = new BasicBulletType(2f, 10f){{
                    width = 8f;
                    height = 5f;
                    collidesGround = false;
                    lifetime = 16f;
                    hitColor = backColor = trailColor = Color.valueOf("338e8c");
                    frontColor = Color.white;
                    despawnEffect = hitEffect = Fx.none;
                }};
            }});
        }};
        fixate = new ContinuousTurret("fixate") {{
            localizedName = "Fixate";
            requirements(Category.turret, with(IgomItems.nickel, 260, Items.silicon, 180, IgomItems.lithium, 80, IgomItems.metafiber, 120));
            buildCostMultiplier = 1.2f;
            shootEffect = Fx.none;
            shootCone = 360f;
            shootY = 4f;
            squareSprite = false;
            outlineColor = Color.valueOf("3a4752");
            recoil = 0f;
            size = 3;
            health = 2240;
            range = 195f;
            rotateSpeed = 0.6f;
            aimChangeSpeed = 0.9F;
            unitSort = UnitSorts.strongest;
            minWarmup = 0.1f;
            shootWarmupSpeed = 0.1f;
            shootSound = Sounds.none;
            loopSound = Sounds.beamLustre;
            loopSoundVolume = 1f;
            consumePower(272f / 60f);
            consumeLiquid(Liquids.hydrogen, 3f / 60f);
            shootType = new PointLaserBulletType(){
                {
                    sprite = "project-igom-blue-point-laser";
                    beamEffectSize = 2.8f;
                    damage = 18f;
                    scaleDamageEfficiency = true;
                    buildingDamageMultiplier = 0.1f;
                    status = StatusEffects.melting;
                    statusDuration = 30f;
                    drawSize = 195f;
                    ammoMultiplier = 1f;
                    lifetime = 60f;
                    hitColor = trailColor = lightColor = color = Color.valueOf("dbe8ff");
                    hitEffect = Fx.hitLancer;
                }};
            drawer = new DrawTurret(){{
                heatColor = Color.valueOf("000000");
                for(int i = 0; i < 2; i ++){
                    boolean f = i == 0;
                    parts.add(new RegionPart("-blade-small-" + (i == 0 ? "l" : "r")){{
                        final DrawPart.PartProgress heatp = PartProgress.warmup.blend((p) ->
                                Mathf.absin(2.0F, 1.0F) * p.warmup, 0.2F);
                        heatProgress = heatp;
                        progress = PartProgress.warmup;
                        heatColor = Color.valueOf("5a6fc9");
                        under = true;
                        moveRot = f ? 45 : -45;
                        moveY = 6f;
                    }});
                    parts.add(new RegionPart("-blade-large-" + (i == 0 ? "l" : "r")){{
                        final DrawPart.PartProgress heatp = PartProgress.warmup.blend((p) ->
                                Mathf.absin(2.0F, 1.0F) * p.warmup, 0.2F);
                        heatProgress = heatp;
                        heatColor = Color.valueOf("5a6fc9");
                        under = true;
                        moveRot = f ? 45 : -45;
                        moveX = f ? -6f : 6f;
                        moveY = 6f;
                    }});
                    parts.add(new RegionPart() {{
                        drawRegion = false;
                        heatColor = Color.valueOf("5a6fc9");
                        heatProgress = PartProgress.warmup;
                    }});
                }
            }};
        }};
    }
}

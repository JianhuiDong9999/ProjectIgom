package content;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Interp;
import arc.math.Mathf;
import entities.abilities.FancyRepairFieldAbility;
import entities.abilities.RiftFieldAbility;
import entities.abilities.ShockwaveAbility;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.Effect;
import mindustry.entities.abilities.ForceFieldAbility;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.*;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.type.weapons.PointDefenseWeapon;

import static arc.graphics.g2d.Draw.color;
import static arc.math.Angles.randLenVectors;
import static mindustry.Vars.tilesize;
import static mindustry.content.Fx.rand;

public class IgomUnitTypes {
    /*public static class IgomUnitEntity extends UnitEntity {
        public IgomUnitEntity() {
            super();
        }
    }*/
    public static UnitType
            // Core Units
            detect, survey, advent,
            // Ground Armored Units
            pike, halberd, bulwark, pavise, aegis,
            // Fighters
            neutrino, photon, gluon, hadron, nymph, shrike, condor, phoenix,
            // Air Frigates
            vector, azimuth, hyperplane, penumbra, umbra,
            // Air Cruisers
                // Reaver Cruisers
                    twilight, oblivion, nihility,
                // Surge Cruisers
                    nimbostratus, cumulonimbus, tempest,
            // Naval Cruisers
                // Battlecruisers
                    garfish, barracuda, cretoxyrhina;

    public static void load() {
        EntityMapping.nameMap.put("project-igom-pike", MechUnit::create);
        pike = new UnitType("pike") {{
            localizedName = "Pike";
            outlineColor = Color.valueOf("3a4752");

            speed = 0.6f;
            hitSize = 12f;
            rotateSpeed = 3f;
            health = 840;
            armor = 5f;
            weapons.add(new Weapon("project-igom-pike-cannon"){{
                top = false;
                reload = 36f;
                alternate = true;
                x = 6f;
                y = 0f;
                recoil = 1.5f;
                recoilPow = 1f;
                shootY = 6f;
                shoot.shots = 2;
                shoot.shotDelay = 5f;
                shootSound = Sounds.shoot;
                ejectEffect = Fx.casing1;
                bullet = new BasicBulletType(5f, 42){{
                    frontColor = Color.valueOf("ffffff");
                    backColor = Color.valueOf("657de2");
                    width = 12f;
                    height = 16f;
                    lifetime = 40f;
                    hitEffect = Fx.hitBulletBig;
                    knockback = 1.5f;
                }};
            }});
        }};
        EntityMapping.nameMap.put("project-igom-halberd", MechUnit::create);
        halberd = new UnitType("halberd") {{
            localizedName = "Halberd";
            outlineColor = Color.valueOf("3a4752");

            speed = 0.5f;
            hitSize = 18f;
            rotateSpeed = 2.5f;
            health = 3250;
            armor = 24f;
            weapons.add(new Weapon("project-igom-halberd-cannon"){{
                top = false;
                reload = 72f;
                shoot = new ShootSpread(5, 2f);
                recoil = 3f;
                recoilPow = 2f;
                inaccuracy = 2f;
                x = 8f;
                ejectEffect = Fx.casing2;
                shootSound = Sounds.shootDiffuse;
                bullet = new BasicBulletType(8f, 36){{
                    frontColor = Color.valueOf("ffffff");
                    backColor = Color.valueOf("657de2");
                    width = 20f;
                    height = 16f;
                    lifetime = 32f;
                    knockback = 1.2f;
                    hitEffect = Fx.hitBulletBig;
                    trailColor = Color.valueOf("657de2");
                    trailLength = 2;
                    trailWidth = 3f;
                }};
            }});
        }};
        EntityMapping.nameMap.put("project-igom-detect", PayloadUnit::create);
        detect = new UnitType("detect") {{
            outlineColor = Color.valueOf("3a4752");
            localizedName = "Detect";
            coreUnitDock = true;
            isEnemy = false;

            lowAltitude = false;
            flying = true;
            mineSpeed = 4f;
            mineTier = 2;
            buildSpeed = 1f;
            payloadCapacity = 2f * 2f * tilesize * tilesize;
            drag = 0.017f;
            speed = 2.2f;
            rotateSpeed = 12f;
            accel = 0.03f;
            fogRadius = 0f;
            itemCapacity = 75;
            health = 320f;
            armor = 3f;
            engineSize = 0;
            engines.set(new UnitEngine[]{new UnitEngine(5.5f,-5f,2f,315f),
                    new UnitEngine(-5.5f,-5f,2f,225f)});
            hitSize = 12f;
            faceTarget = false;
            circleTarget = true;
            range = 24f;
            alwaysUnlocked = true;
            abilities.add(new FancyRepairFieldAbility(25, 60f, 80f) {{
                healColor = Pal.regen;
                size = 2;
                activeEffect = new Effect(60.0F, (e) -> {
                    Draw.color(Pal.regen);
                    Lines.stroke(e.fout() * 2f);
                    Lines.circle(e.x, e.y, e.finpow() * range);
                });
                healEffect = new Effect(11.0F, (e) -> {
                    Draw.color(Pal.regen);
                    Lines.stroke(e.fout() * 2.0F);
                    Lines.circle(e.x, e.y, 2.0F + e.finpow() * 7.0F);
                });
            }});
            weapons.add(new Weapon[]{
                    new PointDefenseWeapon("project-igom-detect-mini-segment") {{
                        x = 5f;
                        y = 4f;
                        layerOffset = -0.01f;
                        reload = 5f;
                        targetInterval = 4f;
                        targetSwitchInterval = 4f;
                        bullet = new BulletType(){{
                            layerOffset = -1f;
                            shootSound = Sounds.shootSegment;
                            shootEffect = Fx.sparkShoot;
                            hitEffect = Fx.pointHit;
                            maxRange = 80f;
                            damage = 50f;
                        }};
                    }},
                    new Weapon() {{
                        x = 0f;
                        y = 0f;
                        layerOffset = -0.01f;
                        mirror = false;
                        shootY = 0f;
                        reload = 30f;
                        shootCone = 180f;
                        ejectEffect = Fx.none;
                        inaccuracy = 7.5f;
                        ignoreRotation = true;
                        shootSound = Sounds.shootRetusa;
                        bullet = new BombBulletType(35f, 12f * 8f){{
                            sprite = "project-igom-small-bomb";
                            backColor = Color.valueOf("99e2ff");
                            width = 8f;
                            height = 8f;
                            shrinkX = shrinkY = 0.3f;
                            spin = 3f;
                            lifetime = 32f;
                            keepVelocity = false;
                            ignoreRotation = true;
                            speed = 0f;
                            splashDamageRadius = 32f;
                            splashDamage = 24f;
                            splashDamagePierce = true;
                            healPercent = 7.5f;
                            healColor = Color.valueOf("99e2ff");
                            hitEffect = despawnEffect = new WrapEffect(Fx.dynamicSpikes, Color.valueOf("99e2ff"), 32f);
                            hitShake = 2f;
                            hitSound = Sounds.explosionPlasmaSmall;
                            shootEffect = Fx.none;
                            smokeEffect = Fx.none;
                        }};
                    }}
            });
        }};
        EntityMapping.nameMap.put("project-igom-cumulonimbus", UnitEntity::create);
        cumulonimbus = new UnitType("cumulonimbus") {{
            description = "Fires heavy bursts of charged energy bullets which pierce and explode on impact. " +
                    "Launches shock missiles at surrounding targets. " +
                    "Generates shockwaves that destroy nearby enemy projectiles. " +
                    "Low in agility. Immune to electric debuffs.";
            outlineColor = Color.valueOf("3a4752");
            localizedName = "Cumulonimbus";
            isEnemy = true;

            lowAltitude = true;
            flying = true;
            speed = 2f;
            drag = 0.017f;
            strafePenalty = 0.25f;
            rotateSpeed = 0.5f;
            accel = 0.01f;
            fogRadius = 32f;
            itemCapacity = 40;
            health = 15250f;
            armor = 18f;
            allowedInPayloads = false;
            immunities.addAll(StatusEffects.shocked, StatusEffects.electrified);
            engines.set(new UnitEngine[]{new UnitEngine(0f,-24f,8f,270f),
                    new UnitEngine(-24f,-8f,6f,225f),
                    new UnitEngine(24f, -8f, 6f, 315f)});
            hitSize = 40f;
            parts.add(
                new RegionPart("-plate") {{
                    mirror = true;
                    moveX = 7;
                    moveY = -7;
                    progress = PartProgress.warmup;
                }}
            );
            abilities.add(new ShockwaveAbility(175, 60, 120) {{
                shockwaveColor = Color.valueOf("b3ecff");
                shockwaveStroke = 3f;
                shockSound = Sounds.shockwaveTower;
                shapeRadius = 4.2f;
                shapeColor = Color.valueOf("ffffff");
                //y = 12f;
            }});
            // Standardized missile bullet for Cumulonimbus
            final BulletType electricMissile = new MissileBulletType(4.5f, 63, "project-igom-arrow-missile") {{
                width = 6f;
                height = 7f;
                frontColor = Color.valueOf("b3ecff");
                backColor = Color.valueOf("00bfff");
                trailColor = Color.valueOf("b3ecffdd");
                trailEffect = Fx.missileTrailShort;
                trailLength = 4;
                trailWidth = 2f;
                shootEffect = Fx.shootSmallSmoke;
                hitSound = Sounds.explosion;
                hitEffect = Fx.blastExplosion;
                hitShake = 1.5f;
                lifetime = 45f;
                splashDamage = 18;
                splashDamageRadius = 12;
                splashDamagePierce = true;
                weaveMag = 3f;
                weaveScale = 6f;
                lightning = 3;
                lightningColor = Color.valueOf("b3ecff");
                lightningLength = 6;
                lightningLengthRand = 2;
                lightningDamage = 15;
                lightningAngle = 120;
            }};
            weapons.add(
                new Weapon[]{
                    new Weapon("project-igom-cumulonimbus-cannon") {{
                        shootSound = Sounds.shootMalign;
                        x = 12;
                        y = 2;
                        rotate = true;
                        rotateSpeed = 0.5f;
                        rotationLimit = 3f;
                        alternate = false;

                        shootCone = 30f;
                        shoot.shots = 2;
                        shoot.shotDelay = 12f;
                        shootY = 24f;
                        shake = 3f;
                        recoil = 10f;
                        recoilPow = 2f;
                        reload = 40f;
                        shootWarmupSpeed = 0.08f;
                        minWarmup = 0.9f;
                        cooldownTime = 100f;
                        top = false;
                        layerOffset = -0.01f;
                        parts.add(
                            new RegionPart("-body") {{
                                mirror = false;
                                moveY = 18f;
                                progress = PartProgress.warmup;
                                shootWarmupSpeed = 0.05f;
                            }}
                        );
                        bullet = new BasicBulletType(15f, 165f){{
                            shootEffect = new MultiEffect(Fx.shootTitan, new WaveEffect(){{
                                colorTo = Color.valueOf("80dfff");
                                sizeTo = 26f;
                                lifetime = 15f;
                                strokeFrom = 4f;
                            }});
                            smokeEffect = Fx.shootSmokeTitan;
                            hitSound = Sounds.shootArc;
                            despawnSound = Sounds.explosionTitan;
                            hitSoundVolume = 1.25f;
                            hitColor = Color.valueOf("b3ecff");
                            hitShake = 7f;
                            hitEffect = despawnEffect = new MultiEffect(
                                    Fx.titanExplosion,
                                    new Effect(100f, 300f, b -> {
                                        float intensity = 3f;

                                        color(b.color, 0.7f);
                                            rand.setSeed(b.id * 2L);
                                            float lenScl = rand.random(0.5f, 1f);
                                            b.scaled(b.lifetime * lenScl, e -> {
                                                randLenVectors(e.id - 1, e.fin(Interp.pow10Out), (int)(1.2f * intensity), 22f * intensity, (x, y, in, out) -> {
                                                    float fout = e.fout(Interp.pow5Out) * rand.random(0.5f, 1f);
                                                    float rad = fout * ((2f + intensity) * 2.35f);

                                                    Fill.circle(e.x + x, e.y + y, rad);
                                                    Drawf.light(e.x + x, e.y + y, rad * 2.5f, b.color, 0.5f);
                                                });
                                            });
                                    })
                                    //, new WrapEffect(Fx.titanSmoke, Color.valueOf("b3ecff1"))
                                    /*,
                                    new ExplosionEffect(){{
                                        waveColor = Color.valueOf("b3ecff");
                                        smokeColor = Color.gray;
                                        sparkColor = Color.valueOf("00bfff");
                                        waveLife = 24f;
                                        waveStroke = 12f;
                                        waveRad = 72f;
                                    }}*/
                            );
                            sprite = "large-orb";
                            trailEffect = Fx.missileTrailShort;
                            trailInterval = 0.25f;
                            trailParam = 4f;
                            // recoil = 0.15f;
                            pierceBuilding = true;
                            pierceCap = 2;
                            splashDamage = 75;
                            splashDamagePierce = true;
                            splashDamageRadius = 64;
                            collidesAir = false;
                            status = StatusEffects.blasted;
                            lifetime = 18f;
                            width = 18f;
                            height = 18f;
                            backColor = Color.valueOf("80dfff");
                            frontColor = Color.valueOf("b3ecff");
                            shrinkX = shrinkY = 0f;
                            trailColor = Color.valueOf("80dfff");
                            trailLength = 3;
                            trailWidth = 8f;

                            intervalBullet = new LightningBulletType(){{
                                damage = 16;
                                collidesAir = false;
                                ammoMultiplier = 1f;
                                lightningColor = Color.valueOf("b3ecff");
                                lightningLength = 4;
                                lightningLengthRand = 6;

                                //for visual stats only.
                                buildingDamageMultiplier = 0.25f;

                                lightningType = new BulletType(0.0001f, 0f){{
                                    collidesAir = false;
                                    lifetime = Fx.lightning.lifetime;
                                    drawSize = 12f;
                                    hitEffect = Fx.hitLancer;
                                    despawnEffect = Fx.none;
                                    status = StatusEffects.shocked;
                                    statusDuration = 10f;
                                    hittable = false;
                                    lightColor = Color.white;
                                    buildingDamageMultiplier = 0.25f;
                                }};
                            }};

                            bulletInterval = 0.25f;

                            lightningColor = Color.valueOf("b3ecff");
                            lightningDamage = 45;
                            lightning = 8;
                            lightningLength = 6;
                            lightningLengthRand = 8;
                            fragBullets = 3;
                            fragAngle = 60f;
                            fragSpread = 120f;
                            fragRandomSpread = 3f;
                            fragOnAbsorb = true;
                            fragOnHit = true;
                            fragBullet = new BasicBulletType(){{
                                width = 16f;
                                height = 24f;
                                lifetime = 24f;
                                speed = 3f;
                                damage = 55f;
                                splashDamage = 25f;
                                splashDamageRadius = 12f;
                                collidesAir = false;
                                shootEffect = Fx.shootSmokeSquareBig;
                                hitEffect = new MultiEffect(Fx.hitSquaresColor,
                                        new ExplosionEffect(){{
                                            waveColor = Color.valueOf("b3ecff");
                                            smokeColor = Color.gray;
                                            sparkColor = Color.valueOf("b3ecff");
                                            waveLife = 18f;
                                            waveStroke = 6f;
                                            waveRad = 12f;
                                        }}
                                );
                                backColor = Color.valueOf("66d9ff");
                                frontColor = Color.valueOf("b3ecff");
                                lightningColor = Color.valueOf("b3ecff");
                                lightningDamage = 36;
                                lightning = 3;
                                lightningLength = 6;
                                lightningLengthRand = 8;
                            }};
                        }};
                    }},
                        new Weapon("project-igom-cumulonimbus-missile-pod") {{
                            shootSound = Sounds.shootMissile;
                            //controllable = false;
                            //autoTarget = true;
                            x = 12;
                            y = -8;
                            minWarmup = 1f;
                            shootWarmupSpeed = 1f;
                            //baseRotation = -45f;
                            baseRotateSpeed = 4f;
                            rotate = true;
                            rotateSpeed = 8f;
                            shootCone = 60f;
                            inaccuracy = 45f;
                            //shoot.shots = 3;
                            //shoot.shotDelay = 9f;
                            shootY = 4f;
                            shake = 1f;
                            recoil = 2f;
                            reload = 20f;
                            cooldownTime = 30f;
                            top = true;
                            layerOffset = 0.01f;
                            bullet = electricMissile;
                        }},
                        new Weapon("project-igom-cumulonimbus-missile-pod") {{
                            shootSound = Sounds.shootMissile;
                            //controllable = false;
                            //autoTarget = true;
                            x = 9;
                            y = 8;
                            minWarmup = 0f;
                            shootWarmupSpeed = 1f;
                            rotate = true;
                            rotateSpeed = 8f;
                            shootCone = 60f;
                            inaccuracy = 45f;
                            //shoot.shots = 3;
                            //shoot.shotDelay = 9f;
                            shootY = 4f;
                            shake = 1f;
                            recoil = 2f;
                            reload = 20f;
                            cooldownTime = 30f;
                            top = true;
                            layerOffset = 0.01f;
                            bullet = electricMissile;
                        }},
                });
        }};
        EntityMapping.nameMap.put("project-igom-oblivion", UnitEntity::create);
        oblivion = new UnitType("oblivion") {{
            outlineColor = Color.valueOf("3a4752");
            localizedName = "Oblivion";
            isEnemy = true;

            lowAltitude = false;
            flying = true;
            drag = 0.017f;
            speed = 2f;
            rotateSpeed = 1f;
            accel = 0.015f;
            fogRadius = 64f;
            itemCapacity = 60;
            health = 17500f;
            armor = 15f;
            allowedInPayloads = false;
            engines.add(new UnitEngine(0f,-18f,8f,270f),
                    new UnitEngine(-18f,-4f,6f,225f),
                    new UnitEngine(18f, -4f, 6f, 315f));
            hitSize = 64f;
            abilities.add(new RiftFieldAbility(StatusEffects.slow, 30f, 240f){{
                riftRadius = 6f;
                y = 8f;
            }}, new ForceFieldAbility(72.0F, 3F, 7500.0F, 1200.0F){{
                sides = 24;
                rotation = 30f;
            }});

            weapons.add(new Weapon[]{
                    new Weapon("project-igom-oblivion-launcher") {{
                        x = 18f;
                        y = 10f;
                        layerOffset = -0.01f;
                        faceTarget= false;
                        baseRotation = 0f;
                        rotate = true;
                        rotateSpeed = 12f;
                        shoot = new ShootSpread(2, 15f);
                        reload = 3.5f;
                        shootCone = 360f;
                        shootSound = Sounds.shootAvert;
                        bullet = new BasicBulletType(6.25f, 225f) {{
                            splashDamage = 125f;
                            splashDamageRadius = 20f;
                            splashDamagePierce = true;
                            knockback = -8f;
                            // TODO: Replace with status = IgomStatusEffects.warped;
                            status = StatusEffects.slow;
                            statusDuration = 60f;
                            homingDelay = 5f;
                            homingPower = 0.5f;
                            width = 6f;
                            height = 12f;
                            lifetime = 36.0F;
                            shootEffect = Fx.sparkShoot;
                            smokeEffect = Fx.shootBigSmoke;
                            hitColor = backColor = trailColor = Color.valueOf("9385db");
                            frontColor = Color.white;
                            trailWidth = 1.75f;
                            trailLength = 7;
                            hitEffect = despawnEffect = new MultiEffect(
                                //shockwave
                                new Effect(20.0F, (e) -> {
                                    Draw.color(e.color);
                                    Lines.stroke(e.fout() * 2.0F);
                                    Lines.circle(e.x, e.y, Mathf.pow(1 - e.fout(), 0.5f) * 20f);}),
                                //black hole
                                new Effect(25.0F, (e) -> {
                                    Draw.z(110.00f);
                                    Draw.color(e.color);
                                    Lines.stroke(1.5f);
                                    Lines.circle(e.x, e.y, 12f * Mathf.pow(e.fout(), 2));
                                    Draw.z(79.999f);
                                    Draw.color(Color.valueOf("000000"));
                                    Fill.circle(e.x, e.y, 11f * Mathf.pow(e.fout(), 2));
                            }));
                        }};
                    }}
            });
        }};
    }
}

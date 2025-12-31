package content.blocks;

import arc.graphics.*;
import content.IgomItems;
import graphics.IgomCacheLayer;
import mindustry.content.Items;
import mindustry.content.StatusEffects;
import mindustry.graphics.CacheLayer;
import mindustry.world.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.meta.*;
import type.MethaneFloor;
import type.MethaneMudFloor;
import world.blocks.IgomAttribute;

public class IgomEnvironmentBlocks {
    public static Block
    // environment - metal
            plating, platingLight, deck, deckLight,
            blueMetal, reinforcedBlueMetal, tarnishedPlating, tarnishedDeck, tarnishedMetal, reinforcedTarnishedMetal,
            catwalkI, catwalkII,
    // environment - solid natural
    abyss, abyssalBedrock, abyssalBasalt, abyssalClay, abyssalIce, bedrockWall, fineRegolith, fineRegolithWall,
            topsoil, denseTopsoil, topsoilWall,
            redClay, packedRedClay, redClayWall,
            quartzite, quartz, quartziteWall,
            andesite, andesiteSand, andesiteWall,
            basaltPlates, denseBasaltPlates, hotspot, fissures, hotPlates, basaltPlateWall,
            carbonSnow, carbonSnowIce, iceShards, greyIce, richIce, greyIceWall,
            bedrock,
    // environment - vents
    basaltPlateVent, topsoilVent, andesiteVent, greyIceVent, redClayVent, quartziteVent, blueMetalVent, tarnishedMetalVent,
    // environment - liquid natural
    fineRegolithMethane, redClayMethane, packedRedClayMethane, andesiteSandMethane,
            basaltPlateMethane, denseBasaltPlateMethane, methane, deepMethane,
            saturatedTopsoil, saturatedDenseTopsoil, topsoilMethane, denseTopsoilMethane,
    // overlay tiles - natural

    // boulders - natural
    fineRegolithBoulder, topsoilBoulder, redClayBoulder, quartziteBoulder,
            andesiteBoulder, basaltPlateBoulder, greyIceBoulder,
    // tallblocks - natural
    clayMound, topsoilMound, quartzCrystal, basaltMonolith, basaltPillars,
    // ores
    nickel, graphite, sulfur, corundum, chromium, lithium, thorium, cobalt, technetium;

    public static void load(){
        //Registers build IgomEnvironmentBlocks
        //no reference is needed here since they can be looked up by name later

        //  **  metallic environmental blocks  **
        plating = new Floor("plating"){{
            localizedName = "Plating";
            variants = 0;
            speedMultiplier = 1.2f;
            attributes.set(Attribute.water, -1.00f);
        }};
        platingLight = new Floor("plating-light"){{
            localizedName = "Plating (light)";
            variants = 0;
            speedMultiplier = 1.2f;
            attributes.set(Attribute.water, -1.00f);
        }};
        deck = new Floor("deck"){{
            localizedName = "Deck";
            variants = 0;
            speedMultiplier = 1.2f;
            attributes.set(Attribute.water, -1.00f);
        }};
        deckLight = new Floor("deck-light"){{
            localizedName = "Deck (light)";
            variants = 0;
            speedMultiplier = 1.2f;
            attributes.set(Attribute.water, -1.00f);
        }};
        blueMetal = new StaticWall("blue-metal"){{
            localizedName = "Blue Metal";
            variants = 3;
            attributes.set(Attribute.sand, 0.0f);
        }};
        reinforcedBlueMetal = new StaticWall("reinforced-blue-metal"){{
            localizedName = "Reinforced Blue Metal";
            variants = 3;
            attributes.set(Attribute.sand, 0.0f);
        }};
        tarnishedPlating = new Floor("tarnished-plating"){{
            localizedName = "Tarnished Plating";
            variants = 4;
            speedMultiplier = 1.5f;
            attributes.set(Attribute.water, -1.00f);
        }};
        tarnishedDeck = new Floor("tarnished-deck"){{
            localizedName = "Tarnished Deck";
            variants = 4;
            speedMultiplier = 1.5f;
            attributes.set(Attribute.water, -1.00f);
        }};
        tarnishedMetal = new StaticWall("tarnished-metal"){{
            localizedName = "Tarnished Metal";
            variants = 3;
            attributes.set(Attribute.sand, 0.0f);
        }};
        reinforcedTarnishedMetal = new StaticWall("reinforced-tarnished-metal"){{
            localizedName = "Reinforced Tarnished Metal";
            variants = 3;
            attributes.set(Attribute.sand, 0.0f);
        }};
        catwalkI = new Floor("catwalk-I") {{
            localizedName = "Catwalk I";
            variants = 2;
            speedMultiplier = 1.25f;
            attributes.set(Attribute.water, -1.00f);
            placeableOn = false;
        }};
        catwalkII = new Floor("catwalk-II") {{
            localizedName = "Catwalk II";
            variants = 2;
            speedMultiplier = 1.25f;
            attributes.set(Attribute.water, -1.00f);
            placeableOn = false;
        }};
        //  **  solid natural environmental blocks **
        abyss = new Floor("abyss"){{
            cacheLayer = CacheLayer.normal;
            variants = 3;
            localizedName = "Abyss";
            placeableOn = false;
            canShadow = false;
            solid = true;
        }};
        abyssalBedrock = new Floor("abyssal-bedrock"){{
            cacheLayer = CacheLayer.normal;
            variants = 3;
            localizedName = "Abyssal Bedrock";
            placeableOn = false;
            canShadow = false;
            solid = true;
        }};
        abyssalBasalt = new Floor("abyssal-basalt"){{
            cacheLayer = CacheLayer.normal;
            variants = 3;
            localizedName = "Abyssal Basalt";
            placeableOn = false;
            canShadow = false;
            solid = true;
        }};
        abyssalClay = new Floor("abyssal-clay"){{
            cacheLayer = CacheLayer.normal;
            variants = 3;
            localizedName = "Abyssal Clay";
            placeableOn = false;
            canShadow = false;
            solid = true;
        }};
        abyssalIce = new Floor("abyssal-ice"){{
            cacheLayer = CacheLayer.normal;
            variants = 3;
            localizedName = "Abyssal Ice";
            placeableOn = false;
            canShadow = false;
            solid = true;
        }};
        bedrockWall = new StaticWall("bedrock-wall"){{
            localizedName = "Bedrock Wall";
            variants = 3;
            attributes.set(Attribute.sand, 0.0f);
        }};
        topsoil = new Floor("topsoil"){{
            localizedName = "Topsoil";
            variants = 3;
            speedMultiplier = 0.85f;
        }};
        denseTopsoil = new Floor("dense-topsoil"){{
            localizedName = "Dense Topsoil";
            blendGroup = topsoil;
            variants = 3;
            speedMultiplier = 0.85f;
        }};
        topsoilWall = new StaticWall("topsoil-wall"){{
            localizedName = "Topsoil Wall";
            variants = 3;
            attributes.set(Attribute.sand, 1.3f);
        }};
        redClay = new Floor("red-clay"){{
            localizedName = "Red Clay";
            variants = 3;
            speedMultiplier = 0.85f;
            attributes.set(IgomAttribute.alumina, 0.500f);
        }};
        packedRedClay = new Floor("packed-red-clay"){{
            localizedName = "Packed Red Clay";
            variants = 3;
            speedMultiplier = 0.9f;
            attributes.set(IgomAttribute.alumina, 2.000f);
        }};
        redClayWall = new StaticWall("red-clay-wall"){{
            localizedName = "Red Clay Wall";
            variants = 3;
            attributes.set(Attribute.sand, 1.2f);
        }};
        quartzite = new Floor("quartzite"){{
            localizedName = "Quartzite";
            variants = 3;
            attributes.set(IgomAttribute.quartz, 0.25f);
            playerUnmineable = true;
        }};
        quartz = new Floor("quartz"){{
            localizedName = "Quartz";
            blendGroup = quartzite;
            variants = 3;
            attributes.set(IgomAttribute.quartz, 0.5f);
            playerUnmineable = true;
        }};
        quartziteWall = new StaticWall("quartzite-wall"){{
            localizedName = "Quartzite Wall";
            variants = 3;
            attributes.set(Attribute.sand, 1.5f);
            attributes.set(IgomAttribute.quartz, 0.25f);
        }};
        andesite = new Floor("andesite"){{
            localizedName = "Andesite";
            variants = 3;
        }};
        andesiteWall = new StaticWall("andesite-wall"){{
            localizedName = "Andesite Wall";
            variants = 3;
            attributes.set(Attribute.sand, 1.0f);
        }};
        basaltPlates = new Floor("basalt-plates"){{
            localizedName = "Basalt Plates";
            variants = 3;
            attributes.set(Attribute.water, -0.3f);
        }};
        denseBasaltPlates = new Floor("dense-basalt-plates"){{
            localizedName = "Dense Basalt Plates";
            blendGroup = basaltPlates;
            variants = 3;
            attributes.set(Attribute.water, -0.3f);
        }};
        hotspot = new Floor("hotspot"){{
            localizedName = "Hotspot";
            blendGroup = basaltPlates;
            speedMultiplier = 0.7f;
            status = StatusEffects.melting;
            statusDuration = 40f;
            variants = 3;
            attributes.set(Attribute.heat, 0.5f);
            attributes.set(Attribute.water, -0.5f);

            emitLight = true;
            lightRadius = 25f;
            lightColor = Color.orange.cpy().a(0.15f);
        }};
        hotPlates = new Floor("hot-plates"){{
            localizedName = "Hot Plates";
            blendGroup = denseBasaltPlates;
            speedMultiplier = 0.8f;
            status = StatusEffects.melting;
            statusDuration = 30f;
            variants = 3;
            attributes.set(Attribute.heat, 0.5f);
            attributes.set(Attribute.water, -0.5f);

            emitLight = true;
            lightRadius = 25f;
            lightColor = Color.orange.cpy().a(0.15f);
        }};
        fissures = new Floor("fissures"){{
            localizedName = "Fissures";
            blendGroup = basaltPlates;
            speedMultiplier = 0.5f;
            status = StatusEffects.melting;
            statusDuration = 80f;
            variants = 3;
            attributes.set(Attribute.heat, 0.75f);
            attributes.set(Attribute.water, -0.7f);

            emitLight = true;
            lightRadius = 50f;
            lightColor = Color.orange.cpy().a(0.3f);
        }};
        basaltPlateWall = new StaticWall("basalt-plate-wall"){{
            localizedName = "Basalt Plate Wall";
            variants = 3;
            attributes.set(Attribute.sand, 0.7f);
        }};
        carbonSnow = new Floor("carbon-snow"){{
            localizedName = "Carbon Snow";
            variants = 3;
            attributes.set(Attribute.water, 0.6f);
        }};
        carbonSnowIce = new Floor("carbon-snow-ice"){{
            localizedName = "Carbon Snow Ice";
            variants = 3;
            speedMultiplier = 1.1f;
            attributes.set(Attribute.water, 0.8f);
        }};
        greyIce = new Floor("grey-ice"){{
            localizedName = "Grey Ice";
            variants = 3;
            speedMultiplier = 1.2f;
            dragMultiplier = 0.2f;
            attributes.set(Attribute.water, 2.00f);
            // TODO: attributes.set(Attribute.helium, 0.200f);
        }};
        greyIceWall = new StaticWall("grey-ice-wall"){{
            localizedName = "Grey Ice Wall";
            variants = 3;
            attributes.set(Attribute.sand, 0.0f);
        }};
        richIce = new Floor("rich-ice"){{
            localizedName = "Rich Ice";
            variants = 3;
            speedMultiplier = 1.2f;
            dragMultiplier = 0.2f;
            attributes.set(Attribute.water, 3.00f);
            // TODO: attributes.set(Attribute.helium, 2.00f);
        }};
        // Structures of major importance have to be built on bedrock.
        bedrock = new Floor("bedrock"){{
            localizedName = "Bedrock";
            variants = 3;
            //allowCorePlacement = true;
            attributes.set(Attribute.water, -0.50f);
        }};
        //Sand covers over most tiles
        andesiteSand = new Floor("andesite-sand"){{
            localizedName = "Andesite Sand";
            variants = 3;
            speedMultiplier = 0.85f;
            itemDrop = Items.sand;
            playerUnmineable = true;
        }};
        fineRegolith = new Floor("fine-regolith"){{
            localizedName = "Fine Regolith";
            variants = 3;
            speedMultiplier = 0.85f;
            itemDrop = Items.sand;
            playerUnmineable = true;
        }};
        fineRegolithWall = new StaticWall("fine-regolith-wall"){{
            localizedName = "Fine Regolith Wall";
            variants = 3;
            attributes.set(Attribute.sand, 2.0f);
        }};

        //  **  vents  **
        basaltPlateVent = new SteamVent("basalt-plate-vent") {{
            localizedName = "Basalt Plate Vent";
            variants = 2;
            effectColor = Color.valueOf("bfced4");
            parent = blendGroup = basaltPlates;
            attributes.set(Attribute.steam, 1f);
        }};
        topsoilVent = new SteamVent("topsoil-vent") {{
            localizedName = "Topsoil Vent";
            variants = 2;
            effectColor = Color.valueOf("bfced4");
            parent = blendGroup = topsoil;
            attributes.set(Attribute.steam, 1f);
        }};
        andesiteVent = new SteamVent("andesite-vent") {{
            localizedName = "Andesite Vent";
            variants = 2;
            effectColor = Color.valueOf("bfced4");
            parent = blendGroup = andesite;
            attributes.set(Attribute.steam, 1f);
        }};
        greyIceVent = new SteamVent("grey-ice-vent") {{
            localizedName = "Grey Ice Vent";
            variants = 3;
            effectColor = Color.valueOf("bfced4");
            parent = blendGroup = greyIce;
            attributes.set(Attribute.steam, 1f);
        }};
        redClayVent = new SteamVent("red-clay-vent") {{
            localizedName = "Red Clay Vent";
            variants = 2;
            effectColor = Color.valueOf("bfced4");
            parent = blendGroup = redClay;
            attributes.set(Attribute.steam, 1f);
        }};
        quartziteVent = new SteamVent("quartzite-vent") {{
            localizedName = "Quartzite Vent";
            variants = 2;
            effectColor = Color.valueOf("bfced4");
            parent = blendGroup = quartzite;
            attributes.set(Attribute.steam, 1f);
            attributes.set(IgomAttribute.quartz, 0.25f);
        }};
        blueMetalVent = new SteamVent("blue-metal-vent") {{
            localizedName = "Blue Metal Vent";
            variants = 1;
            effectColor = Color.valueOf("bfced4");
            parent = blendGroup = deck;
            attributes.set(Attribute.steam, 1f);
        }};
        tarnishedMetalVent = new SteamVent("tarnished-metal-vent") {{
            localizedName = "Tarnished Metal Vent";
            variants = 3;
            effectColor = Color.valueOf("bfced4");
            parent = blendGroup = tarnishedDeck;
            attributes.set(Attribute.steam, 1f);
        }};
        //  **  liquid natural environmental blocks  **
        //Sand methane. Can extract either sand or methane depending on the extraction block used.
        fineRegolithMethane = new MethaneFloor("fine-regolith-methane", 0.1f){{
            localizedName = "Submerged Fine Regolith";
            speedMultiplier = 0.7f;
            variants = 3;
            itemDrop = Items.sand;
            playerUnmineable = true;
        }};
        redClayMethane = new MethaneFloor("red-clay-methane", 0.1f){{
            localizedName = "Submerged Red Clay";
            speedMultiplier = 0.6f;
            variants = 3;
            attributes.set(IgomAttribute.alumina, 0.500f);
        }};
        packedRedClayMethane = new MethaneFloor("packed-red-clay-methane", 0.1f){{
            localizedName = "Submerged Packed Red Clay";
            speedMultiplier = 0.7f;
            variants = 3;
            attributes.set(IgomAttribute.alumina, 2.000f);
        }};
        andesiteSandMethane = new MethaneFloor("andesite-sand-methane", 0.1f){{
            localizedName = "Submerged Andesite Sand";
            speedMultiplier = 0.7f;
            variants = 3;
            itemDrop = Items.sand;
            playerUnmineable = true;
        }};
        basaltPlateMethane = new MethaneFloor("basalt-plate-methane", 0.1f){{
            localizedName = "Submerged Basalt Plates";
            speedMultiplier = 0.65f;
            variants = 3;
        }};
        denseBasaltPlateMethane = new MethaneFloor("dense-basalt-plate-methane", 0.1f){{
            localizedName = "Submerged Dense Basalt Plates";
            speedMultiplier = 0.7f;
            variants = 3;
        }};
        methane = new MethaneFloor("shallow-methane", 0.2f){{
            localizedName = "Methane";
            speedMultiplier = 0.6f;
            variants = 2;
            albedo = 0.9f;
            supportsOverlay = true;
        }};
        deepMethane = new MethaneFloor("deep-methane", 0.4f){{
            localizedName = "Deep Methane";
            speedMultiplier = 0.3f;
            variants = 2;
            drownTime = 400f;
        }};
        saturatedTopsoil = new MethaneMudFloor("saturated-topsoil", 0.06f) {{
            localizedName = "Saturated Topsoil";
            speedMultiplier = 0.65f;
            variants = 3;
        }};
        saturatedDenseTopsoil = new MethaneMudFloor("saturated-dense-topsoil", 0.06f) {{
            localizedName = "Saturated Dense Topsoil";
            speedMultiplier = 0.7f;
            mapColor = Color.valueOf("473f39");
            variants = 3;
        }};
        topsoilMethane = new MethaneFloor("topsoil-methane", 0.1f){{
            localizedName = "Submerged Topsoil";
            cacheLayer = IgomCacheLayer.methane;
            speedMultiplier = 0.65f;
            variants = 3;
        }};
        denseTopsoilMethane = new MethaneFloor("dense-topsoil-methane", 0.1f){{
            localizedName = "Submerged Dense Topsoil";
            cacheLayer = IgomCacheLayer.methane;
            speedMultiplier = 0.7f;
            mapColor = Color.valueOf("50534b");
            variants = 3;
        }};

        //  **  natural overlay blocks  **
        iceShards = new OverlayFloor("ice-shards"){{
            localizedName = "Ice Shards";
            variants = 3;
        }};

        //  **  natural boulders  **
        fineRegolithBoulder = new Prop("fine-regolith-boulder"){{
            localizedName = "Fine Regolith Boulder";
            variants = 3;
            fineRegolith.asFloor().decoration = this;
        }};
        topsoilBoulder = new Prop("topsoil-boulder"){{
            localizedName = "Topsoil Boulder";
            variants = 3;
            topsoil.asFloor().decoration = this;
        }};
        redClayBoulder = new Prop("red-clay-boulder"){{
            localizedName = "Red Clay Boulder";
            variants = 3;
            redClay.asFloor().decoration = this;
        }};
        quartziteBoulder = new Prop("quartzite-boulder"){{
            localizedName = "Quartzite Boulder";
            variants = 3;
            quartzite.asFloor().decoration = this;
        }};
        andesiteBoulder = new Prop("andesite-boulder"){{
            localizedName = "Andesite Boulder";
            variants = 3;
            andesite.asFloor().decoration = this;
        }};
        basaltPlateBoulder = new Prop("basalt-plate-boulder"){{
            localizedName = "Basalt Plate Boulder";
            variants = 3;
            basaltPlates.asFloor().decoration = this;
        }};
        greyIceBoulder = new Prop("grey-ice-boulder"){{
            localizedName = "Grey Ice Boulder";
            variants = 3;
            greyIce.asFloor().decoration = this;
            carbonSnowIce.asFloor().decoration = this;
            carbonSnow.asFloor().decoration = this;
        }};
        //  **  natural tallblocks  **
        clayMound = new TallBlock("claymound"){{
            localizedName = "Claymound";
            variants = 6;
            clipSize = 64f;
            shadowAlpha = 0.5f;
            shadowOffset = -3.0f;
        }};
        topsoilMound = new TallBlock("topsoil-mound"){{
            localizedName = "Topsoil Mound";
            variants = 3;
            clipSize = 64f;
            shadowAlpha = 0.5f;
            shadowOffset = -1.5f;
        }};
        quartzCrystal = new TallBlock("quartz-crystal"){{
            localizedName = "Quartz Crystal";
            variants = 3;
            clipSize = 128f;
            shadowAlpha = 1.0f;
            shadowOffset = -3.0f;
            attributes.set(IgomAttribute.quartz, 1f);
        }};
        basaltMonolith = new TallBlock("basalt-monolith"){{
            localizedName = "Basalt Monolith";
            variants = 3;
            clipSize = 160f;
            shadowAlpha = 1f;
            shadowOffset = 0f;
            rotationRand = 0f;
        }};
        basaltPillars = new TallBlock("basalt-pillars"){{
            localizedName = "Basalt Pillars";
            variants = 3;
            clipSize = 1f;
            shadowAlpha = 1f;
            shadowOffset = -2.0f;
        }};
        //  **  ores  **
        nickel = new OreBlock("ore-nickel", IgomItems.nickel);
        graphite = new OreBlock("ore-graphite", Items.graphite);
        sulfur = new OreBlock("ore-sulfur", IgomItems.sulfur);
        corundum = new OreBlock("ore-corundum", IgomItems.alumina);
        chromium = new OreBlock("ore-chromium", IgomItems.chromium);
        lithium = new OreBlock("ore-lithium", IgomItems.lithium);
        thorium = new OreBlock("ore-vein-thorium", Items.thorium);
        cobalt = new OreBlock("ore-cobalt", IgomItems.cobalt);
        technetium = new OreBlock("ore-technetium", IgomItems.technetium);
    }
}

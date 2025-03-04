package content;

import arc.graphics.Color;
import mindustry.content.Items;
import mindustry.type.Item;

public class IgomItems {
    public static Item nickel, sulfur, quartz, alumina, chromium, aluminum, lithium, cobalt, technetium,
            graphene, sulfide, polyethylene, acrylic, polymerFabric, metaPlastic, niPolymer, cermet, niChrome,
            nitride, niCobalt, entropyAlloy, quantumFabric,
    phasePanel, strongium;
    // cermet is chromium + alumina
    // sulfide is nickel sulfide, used in batteries, capacitors, as a catalyst in electrolyzers
    public static void load() {

        Items.graphite.hardness = 1;
        Items.graphite.color = Color.valueOf("95abd9");
        nickel = new Item("item-nickel", Color.valueOf("8fc79d")) {{
            localizedName = "Nickel";
            healthScaling = 0.4f;
            hardness = 3;
            cost = 1.5f;
            description = "Used in various electric, industrial and construction processes. Can serve as ammunition.";
            details = "An abundant metal on Igom. Strong. Malleable. Viable to serve as the base of this planet's tech tree.";
        }};
        sulfur = new Item("item-sulfur", Color.valueOf("d0bd2f")) {{
            localizedName = "Sulfur";
            healthScaling = 0.2f;
            hardness = 2;
            cost = 1.0f;
        }};
        quartz = new Item("item-quartz", Color.valueOf("ffccd3")) {{
            localizedName = "Quartz";
            hardness = 3;
            healthScaling = 0f;
            cost = 1.2f;
            lowPriority = true;
        }};
        alumina = new Item("item-alumina", Color.valueOf("f2968c")) {{
            localizedName = "Alumina";
            healthScaling = 0.5f;
            hardness = 4;
            cost = 1.25f;
        }};
        chromium = new Item("item-chromium", Color.valueOf("afd5cb")) {{
            localizedName = "Chromium";
            healthScaling = 0.7f;
            hardness = 5;
            cost = 1.8f;
        }};
        aluminum = new Item("item-aluminum", Color.valueOf("d15454")) {{
            localizedName = "Aluminum";
            healthScaling = 0.4f;
            hardness = 2;
            cost = 1f;
        }};
        lithium = new Item("item-lithium", Color.valueOf("c5678a")) {{
            localizedName = "Lithium";
            healthScaling = 0.2f;
            hardness = 3;
            cost = 1.5f;
            explosiveness = 0.25f;
            flammability = 0.25f;
        }};
        cobalt = new Item("item-cobalt", Color.valueOf("1782e6")) {{
            localizedName = "Cobalt";
            healthScaling = 1f;
            hardness = 6;
            cost = 2f;
            radioactivity = 0.2f;
        }};
        technetium = new Item("item-technetium", Color.valueOf("5c4e99")) {{
            localizedName = "Technetium";
            healthScaling = 0.8f;
            hardness = 6;
            cost = 1.4f;
            radioactivity = 1.15f;
        }};
        graphene = new Item("item-graphene", Color.valueOf("7287b3")) {{
            localizedName = "Graphene";
            healthScaling = 0f;
            cost = 1.2f;
        }};
        sulfide = new Item("item-sulfide", Color.valueOf("677953")) {{
            localizedName = "Sulfide";
            healthScaling = 0f;
            cost = 1.6f;
            description = "Used as catalyst for material synthesis and in advanced power structures.";
        }};
        nitride = new Item("item-nitride", Color.valueOf("df7826")) {{
            localizedName = "Nitride";
            healthScaling = 0f;
            cost = 1.4f;
            description = "Insulator material with very high thermal conductivity. Used in advanced electronics and heat structures.";
        }};
    }
}

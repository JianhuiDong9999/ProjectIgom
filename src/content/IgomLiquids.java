package content;

import arc.graphics.Color;
import mindustry.content.StatusEffects;
import mindustry.type.CellLiquid;
import mindustry.type.Liquid;
public class IgomLiquids {
    public static Liquid methane, oxygen, helium, liquidnitrogen, argon, corium;
    public static void load() {
        methane = new Liquid("liquid-methane", Color.valueOf("7db5a388")){{
            localizedName = "Methane";
            flammability = 0.2f;
            heatCapacity = 0.2f;
            viscosity = 0.25f;
            temperature = 0.25f;
            description = "Used for chemical combustion and polymerization processes.";
            boilPoint = 0.35f;
            // TODO: effect = IgomStatusEffects.methaneSoaked;
            barColor = Color.valueOf("aed0c6");
        }};
        oxygen = new Liquid("liquid-oxygen", Color.valueOf("e3645b")) {{
            localizedName = "Oxygen";
            gas = true;
            explosiveness = 0.35f;
            flammability = 1f;
            //heatCapacity = 0.1f;
            //viscosity = 0.05f;
            //temperature = 0.25f;
            description = "Used as a propulsion medium and as reactant for oxidation reactions.";
            details = "A product of photosynthesis and a reactant for cellular respiration. " +
                    "Integral to the cultivation of carbon-based biomass.";
            barColor = Color.valueOf("ff8880");
        }};
        helium = new Liquid("liquid-helium", Color.valueOf("ccffff")) {{
            localizedName = "Helium";
            gas = true;
            description = "Used in cryogenics and in alloying processes. Chemically inert.";
            barColor = Color.valueOf("ccffff");
        }};
        liquidnitrogen = new Liquid("liquid-liquefied-nitrogen", Color.valueOf("e4d7f4")) {{
            localizedName = "Liquid Nitrogen";
            heatCapacity = 1.2f;
            viscosity = 0.1f;
            temperature = 0.15f;
            boilPoint = 0.25f;
            effect = StatusEffects.freezing;
            barColor = Color.valueOf("e4d7f4");
        }};
        argon = new Liquid("liquid-argon", Color.valueOf("ad7cbf")) {{
            localizedName = "Argon";
            gas = true;
            description = "Used for advanced alloying and unit fabrication processes. Chemically inert.";
            barColor = Color.valueOf("bf98cd");
        }};
        corium = new CellLiquid("liquid-corium", Color.valueOf("abd1bc")) {{
            localizedName = "Corium";
            heatCapacity = 0.5f;
            viscosity = 0.8f;
            // TODO: radioactivity = 2f;
            temperature = 2f;
            // TODO: effect = IgomStatusEffects.irradiatedMelting;
            effect = StatusEffects.melting;
            barColor = Color.valueOf("abd1bc");
            colorFrom = Color.valueOf("749081");
            colorTo = Color.valueOf("dbfeea");

            description = "A liquefied mixture of various highly-radioactive elements. " +
                    "Viscous. Can be refined into unstable materials and other radiation products.";
            details = "Highly unstable and dynamic in composition. Requires advanced instruments to analyze. " +
                    "Continuous internal fission capable of generating immense amounts of heat.";
        }};
    }
}

package content;

import arc.graphics.Color;
import mindustry.content.Liquids;
import mindustry.content.StatusEffects;
import mindustry.type.CellLiquid;
import mindustry.type.Liquid;
public class IgomLiquids {
    public static Liquid methane, acid, oxygen, helium, liquidnitrogen, argon, corium;
    public static void load() {
        methane = new Liquid("liquid-methane", Color.valueOf("7db5a388")){{
            localizedName = "Methane";
            flammability = 0f;
            heatCapacity = 0.2f;
            coolant = false;
            viscosity = 0.25f;
            temperature = 0.25f;
            description = "Used for chemical combustion and polymerization processes.";
            details = "Highly abundant in liquid and gas form on Igom. Released by geologic activity.";
            boilPoint = 0.35f;
            // TODO: effect = IgomStatusEffects.methaneSoaked;
            barColor = Color.valueOf("aed0c6");
        }};
        acid = new Liquid("liquid-acid", Color.valueOf("c9d976")){{
            localizedName = "Acid";
            flammability = 0f;
            heatCapacity = 0f;
            coolant = false;
            viscosity = 0.25f;
            temperature = 0.5f;
            description = "Used for batteries, ore refinement and alloying preparation. Highly corrosive.";
            details = "Kept warm by the insulation systems within insulated conduits. Would otherwise freeze on the surface of Igom.";
            boilPoint = 1.7f;
            effect = StatusEffects.corroded;
            barColor = Color.valueOf("c9d976");
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
            details = "Also a product of photosynthesis and a reactant for cellular respiration. " +
                    "Though Igom does not contain any such lifeforms yet.";
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
            description = "Used as coolant for turrets and production. Chemically inert.";
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
            coolant = false;
            viscosity = 0.8f;
            // TODO: radioactivity = 2f;
            temperature = 2f;
            // TODO: effect = IgomStatusEffects.irradiatedMelting;
            effect = StatusEffects.melting;
            barColor = Color.valueOf("abd1bc");
            colorFrom = Color.valueOf("749081");
            colorTo = Color.valueOf("dbfeea");

            description = "Liquefied mixture of various radioactive elements. " +
                    "Viscous. Can be used as heating or refined into powerful materials with advanced methods.";
            details = "A crude sludge, highly unstable and dynamic in composition. " +
                    "Continuous internal fission capable of generating immense amounts of heat.";
        }};
    }
}

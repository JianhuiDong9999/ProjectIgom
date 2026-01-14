package content.blocks;

import content.IgomItems;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;

import static mindustry.type.ItemStack.with;

public class IgomDefenseBlocks {
    public static Block
            nickelWallSmall, nickelWall, metafiberWallSmall, metaFiberwall;

    public static void load(){
        //Registers build IgomDefenseBlocks
        //no reference is needed here since they can be looked up by name later
        nickelWallSmall = new Wall("nickel-wall-small") {{
            localizedName = "Mini Nickel Wall";
            drawTeamOverlay = false;
            buildCostMultiplier = 3f;
            requirements(Category.defense, with(IgomItems.nickel, 10));
            size = 1;
            health = 800;
            armor = 6f;
            description = "Protects buildings from damage. Armored.";
        }};
        nickelWall = new Wall("nickel-wall") {{
            localizedName = "Nickel Wall";
            drawTeamOverlay = false;
            buildCostMultiplier = 3f;
            requirements(Category.defense, with(IgomItems.nickel, 30));
            size = 2;
            health = 2380;
            armor = 6f;
            description = "Protects buildings from damage. Armored.";
        }};
        metafiberWallSmall = new Wall("metafiber-wall-small") {{
            localizedName = "Mini Metafiber Wall";
            drawTeamOverlay = false;
            buildCostMultiplier = 3f;
            requirements(Category.defense, with(IgomItems.nickel, 4, IgomItems.metafiber, 10));
            size = 1;
            health = 1200;
            armor = 12f;
            absorbLasers = true;
            description = "Protects buildings from damage. Armored. Absorbs lasers.";
        }};
        metaFiberwall = new Wall("metafiber-wall") {{
            localizedName = "Metafiber Wall";
            drawTeamOverlay = false;
            buildCostMultiplier = 3f;
            requirements(Category.defense, with(IgomItems.nickel, 12, IgomItems.metafiber, 30));
            size = 2;
            health = 3520;
            armor = 12f;
            absorbLasers = true;
            description = "Protects buildings from damage. Armored. Absorbs lasers.";
        }};
    }
}

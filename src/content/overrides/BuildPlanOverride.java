package content.overrides;
/*
import arc.struct.Seq;
import mindustry.Vars;
import mindustry.game.Schematic.Stile;
import mindustry.world.Tile;
import mindustry.world.blocks.distribution.ItemBridge;
import mindustry.world.blocks.liquid.LiquidBridge;
import mindustry.world.blocks.distribution.DirectionBridge;
import mindustry.game.Schematic;
import mindustry.world.Block;
import arc.util.Log;
import world.blocks.liquid.JunctionlessConduit;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class CustomPlacement {

    public static void overrideBridgeCalculation() {
        try {
            // Get the Placement.calculateBridges method field
            Field field = mindustry.input.Placement.class.getDeclaredField("calculateBridges");
            field.setAccessible(true);

            // Remove final modifier using reflection
            Field modifiers = Field.class.getDeclaredField("modifiers");
            modifiers.setAccessible(true);
            modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);

            // Replace with our custom method using an anonymous class
            field.set(null, mindustry.input.Placement.calculateBridges()); {
                @Override
                public void calculateBridges(Seq<Schematic.Stile> plans, ItemBridge placer) {
                    Seq<Schematic.Stile> newPlans = new Seq<>();

                    for (Schematic.Stile plan : plans) {
                        Block block = plan.block;

                        // If it's a JunctionlessConduit, modify bridge placement logic
                        if (block instanceof JunctionlessConduit) {
                            Tile tile = Vars.world.tile(plan.x, plan.y);
                            if (tile == null) continue;

                            for (int i = 0; i < 4; i++) { // Check adjacent tiles (Up, Down, Left, Right)
                                Tile neighbor = tile.nearby(i);
                                if (neighbor != null && neighbor.block() instanceof JunctionlessConduit) {
                                    Log.info("Replacing DirectionBridge with custom LiquidBridge at ({}, {})", plan.x, plan.y);

                                    // ✅ Fixed Stile constructor usage with correct config type
                                    newPlans.add(new Schematic.Stile(
                                            Vars.content.block("your-mod-name-custom-liquid-bridge"), // Block
                                            plan.x, plan.y, // Position
                                            (byte) 0, // ✅ Rotation as byte
                                            (byte) 0 // ✅ Correct config type (no null required)
                                    ));
                                }
                            }
                        }
                    }

                    // Add modified bridge placements *before* the build executes
                    plans.addAll(newPlans);
                }
            }

            Log.info("Successfully overridden Placement.calculateBridges!");

        } catch (Exception e) {
            Log.err("Failed to override Placement.calculateBridges!", e);
        }
    }
}
*/
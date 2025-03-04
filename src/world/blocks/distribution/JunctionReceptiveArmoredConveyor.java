package world.blocks.distribution;

import arc.math.geom.Geometry;
import arc.math.geom.Point2;
import mindustry.gen.Building;
import mindustry.type.Item;
import mindustry.world.Block;
import mindustry.world.Edges;
import mindustry.world.Tile;
import mindustry.world.blocks.distribution.ArmoredConveyor;
import mindustry.world.blocks.distribution.Conveyor;
import mindustry.world.blocks.distribution.Junction;

public class JunctionReceptiveArmoredConveyor extends ArmoredConveyor {
    public JunctionReceptiveArmoredConveyor(String name) {
        super(name);
    }
    @Override
    public boolean blendsArmored(Tile tile, int rotation, int otherx, int othery, int otherrot, Block otherblock){
        return Point2.equals(tile.x + Geometry.d4(rotation).x, tile.y + Geometry.d4(rotation).y, otherx, othery)
                || ((!otherblock.rotatedOutput(otherx, othery) && Edges.getFacingEdge(otherblock, otherx, othery, tile) != null &&
                Edges.getFacingEdge(otherblock, otherx, othery, tile).relativeTo(tile) == rotation) || otherblock instanceof Junction ||
                (otherblock instanceof Conveyor && otherblock.rotatedOutput(otherx, othery) && Point2.equals(otherx + Geometry.d4(otherrot).x, othery + Geometry.d4(otherrot).y, tile.x, tile.y)));
    }
    public class JunctionReceptiveArmoredConveyorBuild extends ConveyorBuild{
        @Override
        public boolean acceptItem(Building source, Item item){
            return super.acceptItem(source, item) && (source.block instanceof Conveyor
                    || Edges.getFacingEdge(source.tile(), tile).relativeTo(tile) == rotation
                    || !source.proximity.contains(this)
                    || source.block instanceof Junction);
        }
    }
}

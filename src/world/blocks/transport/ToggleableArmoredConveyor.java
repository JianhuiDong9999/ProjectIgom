package world.blocks.transport;

import arc.Core;
import arc.audio.Sound;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.graphics.g2d.TextureRegion;
import arc.math.geom.Geometry;
import arc.math.geom.Point2;
import arc.util.Time;
import arc.util.Tmp;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.Vars;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.gen.Building;
import mindustry.gen.Sounds;
import mindustry.graphics.Layer;
import mindustry.type.Item;
import mindustry.world.Block;
import mindustry.world.Edges;
import mindustry.world.Tile;
import mindustry.world.blocks.distribution.ArmoredConveyor;
import mindustry.world.blocks.distribution.Conveyor;
import mindustry.world.blocks.distribution.Junction;

import static arc.graphics.g2d.Lines.stroke;
import static mindustry.Vars.itemSize;
import static mindustry.Vars.tilesize;

public class ToggleableArmoredConveyor extends ArmoredConveyor {
    public final int timerToggle = timers++;
    public Sound toggleSound = Sounds.door;
    public Effect toggleFx = new Effect(10, e -> {
        stroke(e.fout() * 1.6f);
        Lines.square(e.x, e.y, tilesize / 2f);
    });
    TextureRegion[][] openRegions = new TextureRegion[5][4];

    public ToggleableArmoredConveyor(String name) {
        super(name);
        consumesTap = true;
        noSideBlend = true;
        config(Boolean.class, (ToggleableArmoredConveyor.ToggleableArmoredConveyorBuild base, Boolean open) -> {
            toggleSound.at(base);
            toggleFx.at(base);
        });
    }
    @Override
    public void load() {
        super.load();
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 4; j++) {
                openRegions[i][j] = Core.atlas.find(this.name + "-" + i + "-" + j + "-open");
            }
        }
    }
    @Override
    public boolean blends(Tile tile, int rotation, int otherx, int othery, int otherrot, Block otherblock){
        return (!noSideBlend ? (otherblock.outputsItems() || (lookingAt(tile, rotation, otherx, othery, otherblock) && otherblock.hasItems))
                && lookingAtEither(tile, rotation, otherx, othery, otherrot, otherblock) : (otherblock.outputsItems() && blendsArmored(tile, rotation, otherx, othery, otherrot, otherblock)) ||
                (lookingAt(tile, rotation, otherx, othery, otherblock) && otherblock.hasItems));
    }
    @Override
    public boolean blendsArmored(Tile tile, int rotation, int otherx, int othery, int otherrot, Block otherblock){
        return Point2.equals(tile.x + Geometry.d4(rotation).x, tile.y + Geometry.d4(rotation).y, otherx, othery)
                || ((!otherblock.rotatedOutput(otherx, othery) && Edges.getFacingEdge(otherblock, otherx, othery, tile) != null &&
                Edges.getFacingEdge(otherblock, otherx, othery, tile).relativeTo(tile) == rotation) || otherblock instanceof Junction ||
                (otherblock instanceof Conveyor && otherblock.rotatedOutput(otherx, othery) && Point2.equals(otherx + Geometry.d4(otherrot).x, othery + Geometry.d4(otherrot).y, tile.x, tile.y)));
    }
    public class ToggleableArmoredConveyorBuild extends ConveyorBuild{
        public boolean open = false;
        @Override
        public void tapped(){
            if(!this.timer(timerToggle, 60f)){
                return;
            }
            configure(!open);
            open = !open;
            onProximityUpdate();
        }
        @Override
        public void onProximityUpdate(){
            noSideBlend = !open;
            super.onProximityUpdate();
        }
        @Override
        public boolean acceptItem(Building source, Item item){
            return (open ? super.acceptItem(source, item) : super.acceptItem(source, item) && (source.block instanceof Conveyor
                    || Edges.getFacingEdge(source.tile, tile).relativeTo(tile) == rotation
                    || !source.proximity.contains(this)
                    || source.block instanceof Junction));
        }
        @Override
        public void draw(){
            int frame = enabled && clogHeat <= 0.5f ? (int)(((Time.time * speed * 8f * timeScale * efficiency)) % 4) : 0;

            //draw extra conveyors facing this one for non-square tiling purposes
            Draw.z(Layer.blockUnder);
            for(int i = 0; i < 4; i++){
                if((blending & (1 << i)) != 0){
                    int dir = rotation - i;
                    float rot = i == 0 ? rotation * 90 : (dir)*90;

                    Draw.rect(sliced(regions[0][frame], i != 0 ? SliceMode.bottom : SliceMode.top), x + Geometry.d4x(dir) * tilesize*0.75f, y + Geometry.d4y(dir) * tilesize*0.75f, rot);
                }
            }

            Draw.z(Layer.block - 0.2f);

            Draw.rect(open ? openRegions[blendbits][frame] : regions[blendbits][frame], x, y, tilesize * blendsclx, tilesize * blendscly, rotation * 90);

            Draw.z(Layer.block - 0.1f);
            float layer = Layer.block - 0.1f, wwidth = Vars.world.unitWidth(), wheight = Vars.world.unitHeight(), scaling = 0.01f;

            for(int i = 0; i < len; i++){
                Item item = ids[i];
                Tmp.v1.trns(rotation * 90, tilesize, 0);
                Tmp.v2.trns(rotation * 90, -tilesize / 2f, xs[i] * tilesize / 2f);

                float
                        ix = (x + Tmp.v1.x * ys[i] + Tmp.v2.x),
                        iy = (y + Tmp.v1.y * ys[i] + Tmp.v2.y);

                //keep draw position deterministic.
                Draw.z(layer + (ix / wwidth + iy / wheight) * scaling);
                Draw.rect(item.fullIcon, ix, iy, itemSize, itemSize);
            }
        }
        @Override
        public void write(Writes write){
            super.write(write);
            write.bool(open);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            open = read.bool();
        }

    }
}

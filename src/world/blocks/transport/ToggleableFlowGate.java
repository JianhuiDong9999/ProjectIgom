package world.blocks.transport;

import arc.Core;
import arc.audio.Sound;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.entities.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.meta.*;

public class ToggleableFlowGate extends Block{
    public float speed = 5f;
    public final int timerToggle = timers++;
    public Sound toggleSound = Sounds.click;
    public TextureRegion topRegion;
    public TextureRegion topRegionInverted;

    public ToggleableFlowGate(String name){
        super(name);

        group = BlockGroup.transportation;
        update = true;
        solid = false;
        hasItems = true;
        conveyorPlacement = true;
        unloadable = false;
        itemCapacity = 1;
        noUpdateDisabled = true;
        rotate = true;
        underBullets = true;
        priority = TargetPriority.transport;
        envEnabled = Env.space | Env.terrestrial | Env.underwater;
        regionRotated1 = 1;
        consumesTap = true;
        config(Boolean.class, (ToggleableFlowGate.ToggleableFlowGateBuild base, Boolean open) -> toggleSound.at(base));
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.add(Stat.itemsMoved, 60f / speed, StatUnit.itemsSecond);
    }
    @Override
    public void load() {
        super.load();
        topRegion = Core.atlas.find(this.name + "-top");
        topRegionInverted = Core.atlas.find(this.name + "-top-inverted");
    }

    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{region, topRegion};
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list){
        Draw.rect(region, plan.drawx(), plan.drawy());
        Draw.rect(topRegion, plan.drawx(), plan.drawy(), plan.rotation * 90);
    }

    @Override
    public boolean rotatedOutput(int x, int y){
        return false;
    }

    public class ToggleableFlowGateBuild extends Building{
        public float progress;
        public boolean invert;

        public @Nullable Item current;

        @Override
        public void draw(){
            Draw.rect(region, x, y);
            Draw.rect(invert ? topRegionInverted : topRegion, x, y, rotdeg());
        }

        @Override
        public void updateTile(){
            progress += edelta() / speed * 2f;

            if(current != null){
                if(progress >= (1f - 1f/speed)){
                    var target = target();
                    if(target != null){
                        target.handleItem(this, current);
                        cdump = (byte)(cdump == 0 ? 2 : 0);
                        items.remove(current, 1);
                        current = null;
                        progress %= (1f - 1f/speed);
                    }
                }
            }else{
                progress = 0;
            }

            if(current == null && items.total() > 0){
                current = items.first();
            }
        }
        @Override
        public void tapped(){
            if(!this.timer(timerToggle, 60f)){
                return;
            }
            configure(!invert);
            invert = !invert;
        }

        @Nullable
        public Building target(){
            if(current == null) return null;

            if(invert){
                Building l = left(), r = right();
                boolean lc = l != null && l.team == team && l.acceptItem(this, current),
                        rc = r != null && r.team == team && r.acceptItem(this, current);

                if(lc && !rc){
                    return l;
                }else if(rc && !lc){
                    return r;
                }else if(lc && rc){
                    return cdump == 0 ? l : r;
                }
            }

            Building front = front();
            if(front != null && front.team == team && front.acceptItem(this, current)){
                return front;
            }

            if(invert) return null;

            for(int i = -1; i <= 1; i++){
                int dir = Mathf.mod(rotation + (((i + cdump + 1) % 3) - 1), 4);
                if(dir == rotation) continue;
                Building other = nearby(dir);
                if(other != null && other.team == team && other.acceptItem(this, current)){
                    return other;
                }
            }

            return null;
        }

        @Override
        public boolean acceptItem(Building source, Item item){
            return current == null && items.total() == 0 &&
                    (Edges.getFacingEdge(source.tile, tile).relativeTo(tile) == rotation);
        }

        @Override
        public int removeStack(Item item, int amount){
            int removed = super.removeStack(item, amount);
            if(item == current) current = null;
            return removed;
        }

        @Override
        public void handleStack(Item item, int amount, Teamc source){
            super.handleStack(item, amount, source);
            current = item;
        }

        @Override
        public void handleItem(Building source, Item item){
            current = item;
            progress = -1f;
            items.add(item, 1);
            noSleep();
        }
        @Override
        public void write(Writes write){
            super.write(write);
            write.bool(invert);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            invert = read.bool();
        }
    }
}


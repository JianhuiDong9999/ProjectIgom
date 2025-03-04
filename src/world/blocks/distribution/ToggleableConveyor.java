package world.blocks.distribution;

import arc.Core;
import arc.Graphics;
import arc.audio.Sound;
import arc.graphics.Texture;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.math.geom.Rect;
import arc.struct.Queue;
import arc.struct.Seq;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.Vars;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.gen.Building;
import mindustry.gen.Sounds;
import mindustry.logic.LAccess;
import mindustry.world.blocks.distribution.ArmoredConveyor;
import mindustry.world.blocks.distribution.Conveyor;

import static mindustry.Vars.pathfinder;
import static mindustry.Vars.player;
/**
 *  TODO: Implement class features.
 *  Conveyor class that can be toggled between receptive and unreceptive.
 *  Default mode is unreceptive and will not accept non-conveyor side inputs.
 *  Can be toggled by mouseclick to accept all side inputs.
 **/
public class ToggleableConveyor extends Conveyor {
    protected final static Rect rect = new Rect();
    protected final static Queue<Conveyor.ConveyorBuild> conveyorQueue = new Queue<>();

    public final int timerToggle = timers++;
    public Effect openfx = Fx.dooropen;
    public Effect closefx = Fx.doorclose;
    public Sound doorSound = Sounds.door;
    public TextureRegion openRegion;
    public ToggleableConveyor(String name) {
        super(name);
        consumesTap = true;
        config(Boolean.class, (ToggleableConveyorBuild base, Boolean open) -> {
            doorSound.at(base);
            base.effect();
        });

    }
    public void load() {
        super.load();
        openRegion = Core.atlas.find(this.name + "-open", "braced-conveyor-open");
    }
    public class ToggleableConveyorBuild extends ConveyorBuild {
        public boolean open = false;

        public ToggleableConveyorBuild(){
            super();
        }
        @Override
        public double sense(LAccess sensor){
            if(sensor == LAccess.enabled) return open ? 1 : 0;
            return super.sense(sensor);
        }

        @Override
        public void control(LAccess type, double p1, double p2, double p3, double p4){
            if(type == LAccess.enabled){
                boolean shouldOpen = !Mathf.zero(p1);

                if(Vars.net.client() || open == shouldOpen || !this.timer(timerToggle, 80f)){

                    return;
                }

                configureAny(shouldOpen);
            }
        }

        public void effect(){
            (open ? closefx : openfx).at(this, size);
        }
        @Override
        public void draw(){
            super.draw();
            Draw.rect(open ? openRegion : region, x, y);
            drawTeamTop();
        }

        @Override
        public Graphics.Cursor getCursor(){
            return interactable(player.team()) ? Graphics.Cursor.SystemCursor.hand : Graphics.Cursor.SystemCursor.arrow;
        }

        // Configures conveyor when tapped after the interaction cool-down interval.
        @Override
        public void tapped(){
            if(!this.timer(timerToggle, 60f)){
                return;
            }
            configure(!open);
        }

        @Override
        public Boolean config(){
            return open;
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
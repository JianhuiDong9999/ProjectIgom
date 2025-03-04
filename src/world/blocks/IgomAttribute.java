package world.blocks;

import mindustry.world.meta.Attribute;

public class IgomAttribute {
    public static Attribute quartz, alumina;

    public IgomAttribute(){}

    public static void load(){
        quartz = Attribute.add("quartz");
        alumina = Attribute.add("alumina");
    }
}

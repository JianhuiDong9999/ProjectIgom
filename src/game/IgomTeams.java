package game;

import arc.Core;
import arc.graphics.Color;
import mindustry.game.Team;
import mindustry.ui.Fonts;

import static mindustry.game.Team.*;

public class IgomTeams {
    public static Team allogene, preserver, ruin;

    //Loads Igom's enemy team, namely Allogene.
    public static void load() {
        preserver = newIgomTeam(4, "preserver", Color.valueOf("62cb72"));
        allogene = newIgomTeam(5, "allogene", Color.valueOf("5fabff"));
        ruin = newIgomTeam(127, "ruin", Color.valueOf("4f4f4f"));
    }
    //Changes the vanilla team's parameters at the designated id.
    protected static Team newIgomTeam(int id, String name, Color color) {
        //Gets the team that we are changing by id.
        Team team = Team.get(id);
        //Name is not final.
        team.name = name;
        //Must use set() since color is final.
        team.color.set(color);
        //Team palette construction algorithm.
        team.palette[0] = color;
        team.palette[1] = color.cpy().mul(0.75f);
        team.palette[2] = color.cpy().mul(0.5f);
        //Converts our team palette's hexadecimal to a rgba number.
        for(int i = 0; i < 3; i++){
            team.palettei[i] = team.palette[i].rgba();
        }

        // TODO: team.emoji = Fonts.getUnicodeStr(team.name);

        return team;
    }
}

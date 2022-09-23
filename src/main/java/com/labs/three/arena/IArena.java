package com.labs.three.arena;
import com.labs.three.droid.DroidTeam;

public interface IArena {
    DroidTeam fight();
    void setTeam1(DroidTeam team1);
    void setTeam2(DroidTeam team2);
}

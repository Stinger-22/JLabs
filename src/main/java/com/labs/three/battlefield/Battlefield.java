package com.labs.three.battlefield;

import com.labs.three.unit.droid.Droid;

public class Battlefield {
    private Droid droid1;
    private Droid droid2;

    public Battlefield(Droid droid1, Droid droid2) {
        this.droid1 = droid1;
        this.droid2 = droid2;
    }

    public void fight() {
        int i = 1;
        while(true) {
            System.out.println("ROUND:" + i++);
            droid1.attack(droid2);
            System.out.println(droid1 + " " + droid2);
            if (!droid2.isAlive()) {
                break;
            }
            droid2.attack(droid1);
            System.out.println(droid1 + " " + droid2);
            if (!droid1.isAlive()) {
                break;
            }
        }
    }
}

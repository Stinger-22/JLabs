package com.labs.three;

import com.labs.three.battlefield.Battlefield;
import com.labs.three.unit.droid.CommonDroid;
import com.labs.three.unit.droid.Droid;

public class DroidMain {
    public static void main(String[] args) {
        Droid droid1 = new CommonDroid("First", 100, 7, 11, 0);
        Droid droid2 = new CommonDroid("Second", 100, 7, 11, 0);

        Battlefield battlefield = new Battlefield(droid1, droid2);
        battlefield.fight();
    }

    public static void menu() {

        int choose;

    }
}

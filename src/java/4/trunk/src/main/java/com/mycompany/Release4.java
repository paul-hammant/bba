package com.mycompany;

/**
 * @author Paul Hammant DevOps, (c) 2018
 */
public class Release4 extends ReleaseToggles {

    static int colorCtr = 0;

    enum Color {

        Blonde(),
        Brown(),
        Black(),
        Red();

        Color() {
        }

        static Color rotatingChoice() {
            return Color.values()[colorCtr++ % Color.values().length];
        }

    }

    @Override
    public Object getChangingHairColor() {
        return Color.rotatingChoice();
    }
}

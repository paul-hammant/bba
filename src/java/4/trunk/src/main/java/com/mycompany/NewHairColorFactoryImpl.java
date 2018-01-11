package com.mycompany;

import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;

public class NewHairColorFactoryImpl extends HairColorFactory {

    int ctr = 0;

    enum Colors {

        BLONDE("Blonde"),
        BROWN("Brown"),
        BLACK("Black"),
        RED("Red");

        private final String colorName;

        Colors(String colorName) {

            this.colorName = colorName;
        }
    }

    @Override
    public String getHairColor() {
        return Colors.values()[ctr%Colors.values().length].colorName;
    }
}

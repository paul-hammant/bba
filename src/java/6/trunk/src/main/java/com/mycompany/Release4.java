package com.mycompany;

/**
 * @author Paul Hammant
 */
public class Release4 extends BranchByAbstractionFactory {

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

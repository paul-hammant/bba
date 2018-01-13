package com.mycompany;

import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;

/**
 * @author Paul Hammant DevOps, (c) 2018
 */
public class NewHairColorFactoryImpl extends HairColorFactory {
    @Override
    public String getHairColor() {
        List<String> colors = asList("Blonde", "Brown", "Black", "Red");
        return colors.get(new Random().nextInt(colors.size()));
    }
}

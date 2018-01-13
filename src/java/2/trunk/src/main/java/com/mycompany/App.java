package com.mycompany;

import org.jooby.Jooby;

import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;

/**
 * @author Paul Hammant DevOps, (c) 2018
 */
public class App extends Jooby {

  {
    get("/", () -> "Hello " + getRandomHairColor() + " World!");
  }

  private String getRandomHairColor() {
    List<String> colors = asList("Blonde", "Brown", "Black", "Red");
    return colors.get(new Random().nextInt(colors.size()));
  }

  public static void main(final String[] args) {
    run(App::new, args);
  }

}

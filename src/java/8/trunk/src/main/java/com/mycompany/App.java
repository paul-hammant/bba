package com.mycompany;

import com.typesafe.config.Config;
import org.jooby.Jooby;

/**
 * @author Paul Hammant
 */
public class App extends Jooby {

  BranchByAbstractionFactory bbaf;

  int colorCtr = 0;

  {
    get("/", () -> {
      return "Hello " + Colors.values()[colorCtr++ % Colors.values().length].getColorName() + " World!";
    });

    onStart(registry -> {
      bbaf = BranchByAbstractionFactory.make(registry.require(Config.class).getString("BranchByAbstractionFactory"));
    });
  }

  public static void main(final String[] args) {
    run(App::new, args);
  }

}

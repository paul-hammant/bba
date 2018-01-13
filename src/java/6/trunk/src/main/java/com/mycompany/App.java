package com.mycompany;

import com.typesafe.config.Config;
import org.jooby.Jooby;

/**
 * @author Paul Hammant DevOps, (c) 2018
 */
public class App extends Jooby {

  BranchByAbstractionFactory bbaf;

  {
    get("/", () -> {
      return "Hello " + bbaf.getHairColor() + " World!";
    });

    onStart(registry -> {
      bbaf = BranchByAbstractionFactory.make(registry.require(Config.class).getString("BranchByAbstractionFactory"));
    });
  }

  public static void main(final String[] args) {
    run(App::new, args);
  }

}

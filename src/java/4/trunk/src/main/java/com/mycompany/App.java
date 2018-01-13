package com.mycompany;

import com.typesafe.config.Config;
import javaslang.control.Try;
import org.jooby.Jooby;

/**
 * @author Paul Hammant DevOps, (c) 2018
 */
public class App extends Jooby {

  HairColorFactory hcf;

  {
    get("/", () -> {
      return "Hello " + hcf.getHairColor() + " World!";
    });

    onStart(registry -> {
      hcf = HairColorFactory.make(registry.require(Config.class).getString("HairColorClass"));
    });
  }

  public static void main(final String[] args) {
    run(App::new, args);
  }

}

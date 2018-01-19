package com.mycompany;

import com.typesafe.config.Config;
import org.jooby.Jooby;

/**
 * @author Paul Hammant DevOps, (c) 2018
 */
public class App extends Jooby {

  ReleaseToggles releaseToggles;

  {
    get("/color/hair.json", (req, rsp) -> {
      rsp.status(200)
              .type("application/json")
              .send("{\"color\":\"" + releaseToggles.getChangingHairColor() + "\"}");
    });

    onStart(registry -> {
      releaseToggles = ReleaseToggles.make(registry.require(Config.class).getString("ReleaseToggles"));
    });
  }

  public static void main(final String[] args) {
    run(App::new, args);
  }

}

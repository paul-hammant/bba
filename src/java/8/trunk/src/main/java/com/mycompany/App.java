package com.mycompany;

import com.typesafe.config.Config;
import org.jooby.Jooby;
import org.jooby.json.Jackson;

/**
 * @author Paul Hammant DevOps, (c) 2018
 */
public class App extends Jooby {

  private ReleaseToggles releaseToggles;

  {
    use(new Jackson());
    get("/color/hair.json", () -> Color.rotatingChoice());

    onStart(registry -> {
      releaseToggles = ReleaseToggles.make(registry.require(Config.class).getString("ReleaseToggles"));
    });
  }

  public App withTogglesFor(ReleaseToggles releaseToggles) {
    this.releaseToggles = releaseToggles;
    return this;
  }

  public static void main(final String[] args) {
    run(App::new, args);
  }

}

package com.mycompany;

import com.typesafe.config.Config;
import org.jooby.Jooby;

public class App extends Jooby {

  BranchByAbstractionFactory bbaf;

  int colorCtr = 0;

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


  {
    get("/", () -> {
      return "Hello " + Colors.values()[colorCtr % Colors.values().length].colorName + " World!";
    });

    onStart(registry -> {
      bbaf = BranchByAbstractionFactory.make(registry.require(Config.class).getString("BranchByAbstractionFactory"));
    });
  }

  public static void main(final String[] args) {
    run(App::new, args);
  }

}

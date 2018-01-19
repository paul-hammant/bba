package com.mycompany;

import static com.mycompany.Color.Black;
import static com.mycompany.Color.Blonde;
import static com.mycompany.Color.Brown;
import static com.mycompany.Color.Red;
import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.hamcrest.core.AnyOf;
import org.jooby.test.JoobyRule;
import org.jooby.test.MockRouter;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Paul Hammant DevOps, (c) 2018
 */
public class AppTest {

  /**
   * One app/server for all the tests in this class. If you want to start/stop a new server per test,
   * remove the static modifier and replace the {@link ClassRule} annotation with {@link Rule}.
   */
  @ClassRule
  public static JoobyRule app = new JoobyRule(new App());

  /**
   * An integration test that uses RestAssured to
   * check hair color functionality over HTTP (whichever is
   * configured in conf/application.conf (unless
   * overridden at launch)
   */
  @Test
  public void integrationTest() {
    get("/color/hair.json")
            .then()
            .assertThat()
            .body(startsWith("{\"color\":\""))
            .body(endsWith("\"}"))
            .body(specifiesAnyOfTheAllowedColors())
            .statusCode(200)
            .contentType("application/json;charset=UTF-8");
  }

  private AnyOf<String> specifiesAnyOfTheAllowedColors() {
    return anyOf(containsString(Blonde.toString()), containsString(Brown.toString()),
            containsString(Black.toString()), containsString(Red.toString()));
  }

  /**
   * A unit test that checks 'new' enum-based
   * implementation directly (without HTTP or TCP/IP)
   */
  @Test
  public void newHairColorTest() throws Throwable {
    App app = new App();
    app.releaseToggles = new Release4();

    Color color = new MockRouter(app)
            .get("/color/hair.json");

    assertThat(color, isIn(Color.values()));
  }

}

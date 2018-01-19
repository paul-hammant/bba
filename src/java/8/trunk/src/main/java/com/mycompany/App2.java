package com.mycompany;

import org.jooby.Jooby;
import org.jooby.json.Jackson;

import java.io.Serializable;
import java.util.HashMap;

public class App2 extends Jooby {
    {
        use(new Jackson());

        // sending
        get("/foo/bar.json", req -> new MyThing());

    }

    public static void main(final String[] args) {
        run(App2::new, args);
    }


    public static class MyThing implements Serializable {
        public int num = 3;

    }

}

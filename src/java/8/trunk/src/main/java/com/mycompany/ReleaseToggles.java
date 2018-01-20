package com.mycompany;

/**
 * @author Paul Hammant DevOps, (c) 2018
 */
public interface ReleaseToggles {
    static ReleaseToggles make(String name){
        try {
            return (ReleaseToggles) Class.forName("com.mycompany." + name).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.mycompany;

public abstract class  HairColorFactory {

    abstract String getHairColor();

    static HairColorFactory make(String name){
        try {
            return (HairColorFactory) Class.forName("com.mycompany." + name).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

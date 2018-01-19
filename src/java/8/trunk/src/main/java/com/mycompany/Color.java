package com.mycompany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Random;

/**
 * @author Paul Hammant DevOps, (c) 2018
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum Color {

  Blonde(),
  Brown(),
  Black(),
  Red();

  static int colorCtr = 0;

  Color() {
  }

  @JsonProperty()
  String getColor(){
    return name();
  }

  static Color rotatingChoice() {
    return Color.values()[colorCtr++ % Color.values().length];
  }

}

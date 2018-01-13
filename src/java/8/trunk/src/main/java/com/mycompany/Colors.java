package com.mycompany;

/**
 * @author Paul Hammant DevOps, (c) 2018
 */
enum Colors {

  BLONDE("Blonde"),
  BROWN("Brown"),
  BLACK("Black"),
  RED("Red");

  private final String colorName;

  Colors(String colorName) {
    this.colorName = colorName;
  }

  public String getColorName() {
    return colorName;
  }
}

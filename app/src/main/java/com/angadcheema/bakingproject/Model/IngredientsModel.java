package com.angadcheema.bakingproject.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class IngredientsModel implements Serializable {

  private String Quantity;
  private String Measure;
  private String Ingredient;

  public String getQuantity() {
    return Quantity;
  }

  public void setQuantity(String quantity) {
    Quantity = quantity;
  }

  public String getMeasure() {
    return Measure;
  }

  public void setMeasure(String measure) {
    Measure = measure;
  }

  public String getIngredient() {
    return Ingredient;
  }

  public void setIngredient(String ingredient) {
    Ingredient = ingredient;
  }

}

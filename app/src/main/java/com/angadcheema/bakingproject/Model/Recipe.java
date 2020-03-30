package com.angadcheema.bakingproject.Model;


import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable {

  private String recipeName;
  private ArrayList<IngredientsModel> ingredients;
  private ArrayList<Steps> steps;
  private String servings;
  private String image;

  public String getRecipeName() {
    return recipeName;
  }

  public void setRecipeName(String recipeName) {
    this.recipeName = recipeName;
  }

  public ArrayList<IngredientsModel> getIngredients() {
    return ingredients;
  }

  public void setIngredients(
      ArrayList<IngredientsModel> ingredients) {
    this.ingredients = ingredients;
  }

  public ArrayList<Steps> getSteps() {
    return steps;
  }

  public void setSteps(ArrayList<Steps> steps) {
    this.steps = steps;
  }

  public String getServings() {
    return servings;
  }

  public void setServings(String servings) {
    this.servings = servings;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Recipe() {
  }


}

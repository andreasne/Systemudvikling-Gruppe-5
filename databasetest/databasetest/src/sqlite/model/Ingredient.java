package sqlite.model;

import java.util.ArrayList;

public class Ingredient {
	
	private String category;
	private String name_ingredient;
	private ArrayList<IngredientRecipe> ingredientRecipeList;
	
	
	
	public ArrayList<IngredientRecipe> getIngredientRecipeList() {
		return ingredientRecipeList;
	}
	public void setIngredientRecipeList(
			ArrayList<IngredientRecipe> ingredientRecipeList) {
		this.ingredientRecipeList = ingredientRecipeList;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getIngredient() {
		return name_ingredient;
	}
	public void setIngredient(String name_ingredient) {
		this.name_ingredient = name_ingredient;
	}
}

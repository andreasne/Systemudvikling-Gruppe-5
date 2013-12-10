package sqlite.model;

import java.util.ArrayList;

public class Ingredient {
	
	private int id;
	private String category;
	private String ingredient;
	private ArrayList<IngredientRecipe> ingredientRecipeList;
	
	
	
	public ArrayList<IngredientRecipe> getIngredientRecipeList() {
		return ingredientRecipeList;
	}
	public void setIngredientRecipeList(
			ArrayList<IngredientRecipe> ingredientRecipeList) {
		this.ingredientRecipeList = ingredientRecipeList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getIngredient() {
		return ingredient;
	}
	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}
}

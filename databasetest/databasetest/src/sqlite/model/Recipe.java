package sqlite.model;

import java.util.ArrayList;

public class Recipe {
	
	private String name;
	private String description;
	private String preparation;
	private int isFavorite;
	private ArrayList<IngredientRecipe> ingredientRecipeList;
	
	public ArrayList<IngredientRecipe> getIngredientRecipeList() {
		return ingredientRecipeList;
	}

	public void setIngredientRecipeList(
			ArrayList<IngredientRecipe> ingredientRecipeList) {
		this.ingredientRecipeList = ingredientRecipeList;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean getIsFavorite() {
		boolean flag = false;
		
		if(isFavorite == 0){
			flag = false;
		}
		else if(isFavorite == 1){
			flag = true; 
		}
		else System.out.printf("isFavorite is set wrongly. %d is supposed to be either 0 or 1, check the database", isFavorite);
		
		return flag;	
	}
	
	public void setIsFavorite(int isFavorite) {
		this.isFavorite = isFavorite;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPreparation() {
		return preparation;
	}
	
	public void setPreparation(String preparation) {
		this.preparation = preparation;
	}
}

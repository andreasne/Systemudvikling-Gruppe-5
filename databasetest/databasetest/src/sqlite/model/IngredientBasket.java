package sqlite.model;

import java.util.ArrayList;

public class IngredientBasket {
	ArrayList<String> ingredientBasket = new ArrayList<String>();

	public ArrayList<String> getIngredientBasket() {
		return ingredientBasket;
	}

	public void setIngredientBasket(ArrayList<String> ingredientBasket) {
		this.ingredientBasket = ingredientBasket;
	}
}

package sqlite.model;

public class Shoppinglist {
	
	private int id;
	private Ingredients ingredient;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Ingredients getIngredient() {
		return ingredient;
	}
	public void setIngredient(Ingredients ingredient) {
		this.ingredient = ingredient;
	}
}

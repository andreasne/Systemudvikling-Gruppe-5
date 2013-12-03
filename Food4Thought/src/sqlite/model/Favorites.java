package sqlite.model;

public class Favorites {
	
	private int id;
	private Recipes recipe;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Recipes getRecipe() {
		return recipe;
	}
	public void setRecipe(Recipes recipe) {
		this.recipe = recipe;
	}
}

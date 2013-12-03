package sqlite.model;

public class Recipes {
	
	private int id;
	private Ingredients ingredient;
	private String description;
	private String preparation;
	
	
	
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

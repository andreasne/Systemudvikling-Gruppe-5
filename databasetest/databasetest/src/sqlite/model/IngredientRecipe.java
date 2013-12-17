package sqlite.model;

public class IngredientRecipe {
	
	private Ingredient Ingredient;
	private Recipe recipe;
	private int idIngredient;
	private int idRecipe;
	private String quantity;
	private int isList;
	
	
	public int getIdIngredient() {
		return idIngredient;
	}
	public void setIdIngredient(int idIngredient) {
		this.idIngredient = idIngredient;
	}
	public int getIdRecipe() {
		return idRecipe;
	}
	public void setIdRecipe(int idRecipe) {
		this.idRecipe = idRecipe;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public boolean isList() {
		boolean flag = false;
		
		if(isList == 0){
			flag = false;
		}
		else if(isList == 1){
			flag = true; 
		}
		else System.out.printf("isList is set wrongly. %d is supposed to be either 0 or 1, check the database", isList);
		
		return flag;
	}
	public void setList(int isList) {
		this.isList = isList;
	}
	
	public Ingredient getIngredient() {
		return Ingredient;
	}
	public void setIngredient(Ingredient ingredient) {
		Ingredient = ingredient;
	}
	
	public Recipe getRecipe() {
		return recipe;
	}
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
}

package sqlite.model;

public class IngredientRecipe {
	
	private int idIngredient;
	private int idRecipe;
	private int quantity;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
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

}

package sqlite.helper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import sqlite.model.*;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	
	// Path to the database
	private static final String DB_PATH = "/Food4Thought/com.UCN.food4thought/assets/";
	
	// database name
	private static final String DB_NAME = "foodMapDatabase";
	
	// database name
	private static final String DATABASE_NAME = "foodMapDatabase";
	
	private SQLiteDatabase myDataBase;
	
	private static Context myContext;
	
	private static final String ye = "/data/data/" + myContext.getApplicationContext().getPackageName() + "/";
	
	// myContext.getFilesDir().getPath()
		
	private static final String LOG = "DatabaseHelper";
	
	private static final int DATABASE_VERSION = 1;
	
	
	
	/**
	 * implementing methods from DatabaseHelper
	 */
	public DatabaseHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	

	/**
	 * Create empty database to the system and rewrite to our own database.
	 */
	public void createDataBase() throws IOException{
		
		boolean dbExist = checkDataBase();
		
		if(dbExist){
			
		}else
		{
			this.getReadableDatabase();
			
			try{
				copyDataBase();
			}catch(IOException e){
				
				throw new Error("Error copying database");
			}
		}
		
	}
	
	
	/**
	 * Check if database already exist to avoid re-copying the file each time
	 */
	private boolean checkDataBase(){
		
		SQLiteDatabase checkDB = null;
		
		try{
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
			
		}catch(SQLiteException e){
			
		}
		
		if(checkDB != null){
			
			checkDB.close();
		}
		
		return checkDB != null ? true : false;
	}
	
	
	
	/**
	 * Copies your database from your local assets-folder to the just created empty database in the
	 * system folder, from where it can be accessed and handled.
	 * This is done by transfering bytestream.
	 * */
	private void copyDataBase() throws IOException {
		
		InputStream myInput = myContext.getAssets().open(DB_NAME);
		
		String outFileName = DB_PATH + DB_NAME;
		
		OutputStream myOutput = new FileOutputStream(outFileName);
		
		byte[] buffer = new byte[1024];
		int length;
		while((length = myInput.read(buffer))>0){
			myOutput.write(buffer, 0, length);
		}
		
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}
	
	@Override
	public synchronized void close(){
		if(myDataBase != null){
			myDataBase.close();
		}
		
		super.close();
		
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	 
	}
	
	
	
	
	/**
	 * database tables
	 */
	private static final String TABLE_RECIPE = "recipe";
	private static final String TABLE_INGREDIENT = "ingredient";
	private static final String TABLE_INGREDIENTRECIPE = "ingredientrecipe";

	
	/**
	 * general column names
	 */
	private static final String KEY_ID = "id";
	private static final String KEY_CREATED_AT = "created_at";
	
	
	
	
	/**
	 * column names for recipe
	 */
	// description column name
	private static final String KEY_DESCRIPTION = "description";
			
	// preparation column name
	private static final String KEY_PREPARATION = "preparation";
	
	// name for recipe column name
	private static final String KEY_NAME = "name_recipe";
	
	// boolean for column is favorite
	private static final String KEY_ISFAVORITE = "is_favorite";

	
	
	/**
	 * column names for ingredient
	 */
	//ingredientname column name
	private static final String KEY_INGREDIENT_NAME = "name_ingredient";
	
	//category column name
	private static final String KEY_CATEGORY = "category";
	
	/**
	 *  column names for IngredientRecipe
	 */
	// idingredient column name
	private static final String KEY_ID_INGREDIENT = "idingredient";
	
	// idrecipe column name
	private static final String KEY_ID_RECIPE = "idrecipe";
	
	// quantity column name
	private static final String KEY_QUANTITY = "quantity";
	
	//
	private static final String KEY_ISLIST = "islist";
	
	/**
	 * Create table statements for: CREATE_TABLE_RECIPE,
	 * CREATE_TABLE_INGREDIENT, CREATE_TABLE_INGREDIENTRECIPE
	 */
	/*
	// recipe table create statement
	private static final String CREATE_TABLE_RECIPE = 
			"CREATE TABLE " + TABLE_RECIPE + 
			"(" 
			+ KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_NAME + " TEXT,"
			+ KEY_DESCRIPTION + " TEXT,"
			+ KEY_PREPARATION + " TEXT,"
			+ KEY_ISFAVORITE + " BOOLEAN" +
			");";
			
	
	// ingredient table create statement
	private static final String CREATE_TABLE_INGREDIENT =
			"CREATE TABLE " + TABLE_INGREDIENT +
			"("
			+ KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_CATEGORY + " TEXT,"
			+ KEY_INGREDIENT_NAME + " TEXT" +
			");";
	
	// ingredientrecipe table create statement
	private static final String CREATE_TABLE_INGREDIENTRECIPE =
			"CREAT TABLE " + TABLE_INGREDIENTRECIPE +
			"("
			+ KEY_ID_INGREDIENT + " INTEGER," 
			+ KEY_ID_RECIPE + " INTEGER," 
			+ KEY_QUANTITY + " INTEGER," 
			+ KEY_ISLIST + " BOOLEAN," 
			+ " FOREIGN KEY (" + KEY_ID_INGREDIENT + ") REFERENCES " 
			+ TABLE_INGREDIENT + "(" + KEY_ID + ")"
			+ " FOREIGN KEY (" + KEY_ID_RECIPE + ") REFERENCES " 
			+ TABLE_RECIPE + "(" + KEY_ID + ")" +
			");";
			
	

	
	
	/*
	 * implementing constructors from DatabaseHelper
	 
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		// creating required tables
		db.execSQL(CREATE_TABLE_INGREDIENT);
		db.execSQL(CREATE_TABLE_RECIPE);
		db.execSQL(CREATE_TABLE_INGREDIENTRECIPE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		// on upgrade drop tables
		db.execSQL(CREATE_TABLE_INGREDIENT);
		db.execSQL(CREATE_TABLE_RECIPE);
		db.execSQL(CREATE_TABLE_INGREDIENTRECIPE);
		onCreate(db);
	}
	*/
	
	/**
	 * get single ingredient
	 */
	public Ingredient getIngredient(long id_ingredient){
		SQLiteDatabase db = this.getReadableDatabase();
		
		String selectQuery = "SELECT * FROM " + TABLE_INGREDIENT + " WHERE "
				+ KEY_ID + " = " + id_ingredient;
		
		Log.e(LOG, selectQuery);
		
		Cursor  c = db.rawQuery(selectQuery, null);
		
		if (c != null){
			c.moveToFirst();
		}
		
		Ingredient ingredientObj = new Ingredient();
		ingredientObj.setId(c.getInt(c.getColumnIndex(KEY_ID)));
		ingredientObj.setCategory(c.getString(c.getColumnIndex(KEY_CATEGORY)));
		ingredientObj.setIngredient(c.getString(c.getColumnIndex(KEY_INGREDIENT_NAME)));
		
		return ingredientObj;
	}
	
	/**
	 * get single recipe by id
	 * @param id_recipe
	 * @return
	 */
	
	public Recipe getRecipe(long id_recipe){
		SQLiteDatabase db = this.getReadableDatabase();
		
		String selectQuery = "SELECT * FROM " + TABLE_RECIPE + " WHERE "
				+ KEY_ID + " = " + id_recipe;
		
		Log.e(LOG, selectQuery);
		
		Cursor  c = db.rawQuery(selectQuery, null);
		
		if (c != null){
			c.moveToFirst();
		}
		
		Recipe recipeObj = new Recipe();
		recipeObj.setId(c.getInt(c.getColumnIndex(KEY_ID)));
		recipeObj.setName(c.getString(c.getColumnIndex(KEY_NAME)));
		recipeObj.setPreparation(c.getString(c.getColumnIndex(KEY_PREPARATION)));
		recipeObj.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
		recipeObj.setIsFavorite(c.getInt(c.getColumnIndex(KEY_ISFAVORITE)));
		
		return recipeObj;
	}
	
	//getting all recipes
	public ArrayList<Recipe> getAllRecipes(){
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
		String selectQuery = "SELECT * FROM" + TABLE_RECIPE;
		
		Log.e(LOG, selectQuery);
		
		Cursor c = db.rawQuery(selectQuery, null);
		if(c.moveToFirst()){
			Recipe recipeObj = new Recipe();
			recipeObj.setId(c.getInt(c.getColumnIndex(KEY_ID)));
			recipeObj.setName(c.getString(c.getColumnIndex(KEY_NAME)));
			recipeObj.setPreparation(c.getString(c.getColumnIndex(KEY_PREPARATION)));
			recipeObj.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
			recipeObj.setIsFavorite(c.getInt(c.getColumnIndex(KEY_ISFAVORITE)));
			
			recipeList.add(recipeObj);
		}
		return recipeList;
	}
	
	//getting all ingredients
	public ArrayList<Ingredient> getAllIngredients(){
		
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
		String selectQuery = "SELECT * FROM" + TABLE_INGREDIENT;
		
		Log.e(LOG, selectQuery);
		
		Cursor c = db.rawQuery(selectQuery, null);
		
		// looping through all rows and adding to list
		if(c.moveToFirst()){
			do{
				Ingredient ingredientObj = new Ingredient();
				ingredientObj.setId(c.getInt(c.getColumnIndex(KEY_ID)));
				ingredientObj.setCategory(c.getString(c.getColumnIndex(KEY_CATEGORY)));
				ingredientObj.setIngredient(c.getString(c.getColumnIndex(KEY_INGREDIENT_NAME)));
				
				//adding  to ingredientlist
				ingredientList.add(ingredientObj);
			}while(c.moveToNext());
		}
		
		return ingredientList;
	}
	
	
	//get a recipe with all ingredients
	public Recipe getRecipeWithIngredients(long id_recipe){
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<IngredientRecipe> ingredientRecipeList = new ArrayList<IngredientRecipe>();
		
		//getting recipe by id
		String selectQuery = "SELECT * FROM " + TABLE_RECIPE + "WHERE "
				+ KEY_ID + " = " + id_recipe;
		
		Log.e(LOG, selectQuery);
		
		Cursor c = db.rawQuery(selectQuery, null);
		
		if (c != null){
			c.moveToFirst();
		}
		
		Recipe recipeObj = new Recipe();
		recipeObj.setId(c.getInt(c.getColumnIndex(KEY_ID)));
		recipeObj.setName(c.getString(c.getColumnIndex(KEY_NAME)));
		recipeObj.setPreparation(c.getString(c.getColumnIndex(KEY_PREPARATION)));
		recipeObj.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
		recipeObj.setIsFavorite(c.getInt(c.getColumnIndex(KEY_ISFAVORITE)));
		
			String selectQuery2 = "SELECT * FROM " + TABLE_INGREDIENTRECIPE + "WHERE "
					+ KEY_ID_RECIPE + " = " + id_recipe;
			
			Log.e(LOG, selectQuery2);
			
			Cursor c2 = db.rawQuery(selectQuery2, null);
			
			//looping through all ingredientrecipes and adding them to ingredientList
			if(c2.moveToFirst()){
				do{
					IngredientRecipe ingredientRecipeObj = new IngredientRecipe();
					ingredientRecipeObj.setIdIngredient(c2.getInt(c2.getColumnIndex(KEY_ID_INGREDIENT)));
					ingredientRecipeObj.setIdRecipe(c2.getInt(c2.getColumnIndex(KEY_ID_RECIPE)));
					ingredientRecipeObj.setList(c2.getInt(c2.getColumnIndex(KEY_ISLIST)));
					ingredientRecipeObj.setQuantity(c2.getString(c2.getColumnIndex(KEY_QUANTITY)));
						
						//connecting ingredients
						String selectQuery3 = "SELECT * FROM " + TABLE_INGREDIENT + "WHERE "
								+ c2.getInt(c2.getColumnIndex(KEY_ID_INGREDIENT)) + " = " + id_recipe;
						
						Log.e(LOG, selectQuery3);
						
						Cursor c3 = db.rawQuery(selectQuery3, null);
						
						if(c3 != null)
							c3.moveToFirst();
						
						Ingredient ingredientObj = new Ingredient();
						ingredientObj.setId(c3.getInt(c3.getColumnIndex(KEY_ID)));
						ingredientObj.setCategory(c3.getString(c3.getColumnIndex(KEY_CATEGORY)));
						ingredientObj.setIngredient(c3.getString(c3.getColumnIndex(KEY_INGREDIENT_NAME)));
					// adding ingredient object to ingredientrecipe	
					ingredientRecipeObj.setIngredient(ingredientObj);
					// adding ingredientrecipe to list
					ingredientRecipeList.add(ingredientRecipeObj);
				}while(c2.moveToNext());
			}
		//adding Ingridientrecipelist to recipe
		recipeObj.setIngredientRecipeList(ingredientRecipeList);
		
		return recipeObj;
	}
	
	public Ingredient getIngredientWithRecipe(String ingredientName){
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<IngredientRecipe> ingredientRecipeList = new ArrayList<IngredientRecipe>();
		
		//get ingredient by name
		String selectQuery = "SELECT * FROM " + TABLE_RECIPE + "WHERE "
				+ KEY_INGREDIENT_NAME + " = " + ingredientName;
		
		Log.e(LOG, selectQuery);
		
		Cursor c = db.rawQuery(selectQuery, null);
		
		if (c != null){
			c.moveToFirst();
		}
		Ingredient ingredientObj = new Ingredient();
		ingredientObj.setId(c.getInt(c.getColumnIndex(KEY_ID)));
		ingredientObj.setCategory(c.getString(c.getColumnIndex(KEY_CATEGORY)));
		ingredientObj.setIngredient(c.getString(c.getColumnIndex(KEY_INGREDIENT_NAME)));
		
		//get connected ingredientrecipe by ingredient id
			String selectQuery2 = "SELECT * FROM " + TABLE_INGREDIENTRECIPE + "WHERE "
					+ KEY_ID_INGREDIENT + " = " + c.getInt(c.getColumnIndex(KEY_ID));
			
			Log.e(LOG, selectQuery2);
			
			Cursor c2 = db.rawQuery(selectQuery2, null);
			
			//populate arraylist with ingredientrecipe objects
			if(c2.moveToFirst()){
				do{
					IngredientRecipe ingredientRecipeObj = new IngredientRecipe();
					ingredientRecipeObj.setIdIngredient(c2.getInt(c2.getColumnIndex(KEY_ID_INGREDIENT)));
					ingredientRecipeObj.setIdRecipe(c2.getInt(c2.getColumnIndex(KEY_ID_RECIPE)));
					ingredientRecipeObj.setList(c2.getInt(c2.getColumnIndex(KEY_ISLIST)));
					ingredientRecipeObj.setQuantity(c2.getString(c2.getColumnIndex(KEY_QUANTITY)));
					
					//get connected recipes
						String selectQuery3 = "SELECT * FROM " + TABLE_RECIPE + "WHERE "
								+ KEY_ID + " = " + c2.getInt(c2.getColumnIndex(KEY_ID_RECIPE));
						
						Log.e(LOG, selectQuery3);
						
						Cursor c3 = db.rawQuery(selectQuery3, null);
						if(c3 != null)
							c3.moveToFirst();
						
						Recipe recipeObj = new Recipe();
						recipeObj.setId(c3.getInt(c3.getColumnIndex(KEY_ID)));
						recipeObj.setName(c3.getString(c3.getColumnIndex(KEY_NAME)));
						recipeObj.setPreparation(c3.getString(c3.getColumnIndex(KEY_PREPARATION)));
						recipeObj.setDescription(c3.getString(c3.getColumnIndex(KEY_DESCRIPTION)));
						recipeObj.setIsFavorite(c3.getInt(c3.getColumnIndex(KEY_ISFAVORITE)));
					ingredientRecipeObj.setRecipe(recipeObj);
						
					ingredientRecipeList.add(ingredientRecipeObj);
				}while(c2.moveToNext());
			}
		ingredientObj.setIngredientRecipeList(ingredientRecipeList);
		
		return ingredientObj;
	}
	
}

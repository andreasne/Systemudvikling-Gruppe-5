package sqlite.helper;

import sqlite.model.*;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	private static final String LOG = "DatabaseHelper";
	
	private static final int DATABASE_VERSION = -1;
	
	// database name
	private static final String DATABASE_NAME = "foodMapDatabase";
	
	
	

	

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
	// recipe table create statement
	private static final String CREATE_TABLE_RECIPE = 
			"CREATE TABLE " + TABLE_RECIPE + 
			"(" 
			+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ KEY_NAME + " TEXT,"
			+ KEY_ID_INGREDIENT + " TEXT,"
			+ KEY_DESCRIPTION + " TEXT,"
			+ KEY_PREPARATION + " TEXT,"
			+ KEY_ISFAVORITE + " BOOLEAN" +
			");";
			
	
	// ingredient table create statement
	private static final String CREATE_TABLE_INGREDIENT =
			"CREATE TABLE " + TABLE_INGREDIENT +
			"("
			+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
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
			
	
	/**
	 * implementing methods from DatabaseHelper
	 */
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		// TODO Auto-generated constructor stub
	}

	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	
	/*
	 * implementing constructors from DatabaseHelper
	 */
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
		ingredientObj.setIngredient(c.getString(c.getColumnIndex(KEY_INGREDIENT_NAME)));
		
		return ingredientObj;
	}
	
	
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
	
}

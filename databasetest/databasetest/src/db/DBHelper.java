package db;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import sqlite.model.*;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author robert.hinds
 *
 */
public class DBHelper extends SQLiteOpenHelper{

	
	private static String DB_NAME = "finalFoodDbwData";
	private SQLiteDatabase myDataBase; 
	private Context myContext;

	//The Android's default system path of your application database.
	
	//private String DB_PATH = "/data/data/" + myContext.getPackageName() + "/databases/";
	private String DB_PATH = "/data/data/com.example.databasetest/databases/";
				
//"/data/data/com.example.databasetest/databases/";
	
	

	/**
	 * Constructor
	 * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
	 * @param context
	 */
	public DBHelper(Context context) {
		super(context, DB_NAME, null, 1);
		this.myContext = context;
	}	

	/**
	 * Creates a empty database on the system and rewrites it with your own database.
	 * */
	public void createDataBase() throws IOException{

		boolean dbExist = checkDataBase();
		if(!dbExist)
		{
			//By calling this method and empty database will be created into the default system path
			//of your application so we are gonna be able to overwrite that database with our database.
			this.getReadableDatabase();

			try {
				copyDataBase(); 
			} catch (IOException e) {
				throw new Error("Error copying database");
			}
		}
	}

	/**
	 * Check if the database already exist to avoid re-copying the file each time you open the application.
	 * @return true if it exists, false if it doesn't
	 */
	private boolean checkDataBase(){
		SQLiteDatabase checkDB = null;
		try{
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
		}catch(SQLiteException e){
			//database does't exist yet.
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
	private void copyDataBase() throws IOException{

		//Open your local db as the input stream
		InputStream myInput = myContext.getAssets().open(DB_NAME);

		// Path to the just created empty db
		String outFileName = DB_PATH + DB_NAME;

		//Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		//transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer))>0){
			myOutput.write(buffer, 0, length);
		}

		//Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public void openDataBase() throws SQLException{
		//Open the database
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
	}

	@Override
	public synchronized void close() {
		if(myDataBase != null)
			myDataBase.close();
		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	// Add your public helper methods to access and get content from the database.
	// You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
	// to you to create adapters for your views.

	
	
	
	/**
	 * get single ingredient
	 */
	public Ingredient getIngredient(long id_ingredient){
		//SQLiteDatabase db = this.getReadableDatabase();
		
		Ingredient ingredientObj = new Ingredient();
		
		Cursor c = myDataBase.rawQuery("SELECT * FROM Ingredient" + " WHERE _id = " + id_ingredient, null);
		
		while (c.moveToNext()){
			//ingredientObj.setId(c.getInt(c.getColumnIndex("_id")));
			ingredientObj.setCategory(c.getString(c.getColumnIndex("category")));
			ingredientObj.setIngredient(c.getString(c.getColumnIndex("name_ingredient")));
		}
		
		return ingredientObj;
	}
	
	public Recipe getRecipe(long id_recipe){
		Recipe recipeObj = new Recipe();
		
		Cursor c = myDataBase.rawQuery("SELECT * FROM Recipe" + " WHERE _id = " + id_recipe, null);
		
		while (c.moveToNext()){
			//ingredientObj.setId(c.getInt(c.getColumnIndex("_id")));
			recipeObj.setName(c.getString(c.getColumnIndex("name_recipe")));
			recipeObj.setDescription(c.getString(c.getColumnIndex("description")));
			recipeObj.setPreparation(c.getString(c.getColumnIndex("preparation")));
		}
		
		return recipeObj;	
		
	}
	
	

}
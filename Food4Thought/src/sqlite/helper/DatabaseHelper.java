package sqlite.helper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	private static final String LOG = "DatabaseHelper";
	
	private static final int DATABASE_VERSION = -1;
	
	// database name
	private static final String DATABASE_NAME = "foodMapDatabase";
	
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

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
	
	

	
	// tables
	private static final String TABLE_RECIPE = "recipe";
	private static final String TABLE_FAVORITE = "favorite";
	private static final String TABLE_INGREDIENT = "ingredient";
	private static final String TABLE_SHOPPING_LIST = "shoppinglist";

	// column names
	private static final String KEY_ID = "id";
	private static final String KEY_CREATED_AT = "created_at";
	
	
	// column names for recipe
	// ingredient column name
	private static final String KEY_ID_INGREDIENT = "idingredient";
	
	// description column name
	private static final String KEY_DESCRIPTION = "description";
			
	// preparation column name
	private static final String KEY_PREPARATION = "preparation";
	
	// recipe table create statement
	private static final String CREATE_TABLE_RECIPE = 
			"CREATE TABLE " + TABLE_RECIPE + 
			"(" 
			+ KEY_ID + " INTEGER PRIMARY KEY," 
			+ KEY_ID_INGREDIENT + " TEXT,"
			+ KEY_DESCRIPTION + " TEXT,"
			+ KEY_PREPARATION + " TEXT," + 
			");";
			
	
	
	
	// favorites table create statement
	
	// ingredients table create statement
	
	// shoppinglist table create statement
}

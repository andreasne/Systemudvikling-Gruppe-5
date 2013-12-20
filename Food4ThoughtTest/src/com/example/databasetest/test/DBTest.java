package com.example.databasetest.test;

import sqlite.model.Recipe;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.example.databasetest.*;

import db.DBHelper;

public class DBTest extends AndroidTestCase{
	private DBHelper db = new DBHelper(getContext());;
	//SQLiteDatabase _db;
	
	public void setUp(){
		RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
		db = new DBHelper(context);
	}
	
	
	public void testGetRecipe() throws Exception{
		String recipeName;
		String expectedRecipeName = "Grilled Jerk Chicken";
		
		db.openDataBase();
		recipeName = db.getRecipe(5).getName();
		assertTrue(expectedRecipeName.equals(recipeName));
		tearDown();
	}
	
	public void testGetIngredient() throws Exception{
		db.openDataBase();
		String ingredientName;
		String expectedIngredientName = "cumin seeds";
		
		ingredientName = db.getIngredient(1).getIngredient();
		assertTrue(expectedIngredientName.equals(ingredientName));
		tearDown();
	}
	public void testIfDatabaseExists() throws Exception{
		db.openDataBase();
		boolean test = db.checkDataBase();
		assertEquals(test, true);
		tearDown();
	}
	
	public void testGetAllIngredients() throws Exception{
		db.openDataBase();
		int ingredientCount = db.getAllIngredients().size();
		int expectedIngredientCount = 43;
		
		assertTrue(expectedIngredientCount == ingredientCount);
		tearDown();
	}
	
	public void testDb() throws Exception{
		db.openDataBase();
		int ingredientCount = db.getAllIngredients().size();
		int expectedIngredientCount = 43;
		
		assertTrue(expectedIngredientCount == ingredientCount);
		tearDown();
	}
	
	public void canGetWriteableDb() throws Exception{
		db.openDataBase();
		boolean test = db.checkDataBase();
		assertTrue(test == true);
		tearDown();
	}
	
	
	public void tearDown() throws Exception{
		db.close();
		super.tearDown();
	}
}

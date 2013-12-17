package com.example.databasetest;

import java.io.IOException;

import db.DBHelper;

import sqlite.model.Ingredient;
import sqlite.model.Recipe;
import android.os.Bundle;
import android.app.Activity;
import android.database.SQLException;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        
        
        Ingredient ingredientObj = getIngredientFromDbById(1);
        String ingredientName = ingredientObj.getIngredient();
        
        Recipe recipeObj = getRecipeFromDbById(1);
        String recipeName = recipeObj.getName();
        
        //RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);
        ListView listView1 = (ListView) findViewById(R.id.listView1);
        
        String[] items = { "Milk", "Butter", "Yogurt", "Toothpaste", "Ice Cream", ingredientName, recipeName };
        
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        
        listView1.setAdapter(adapter);
    }

    private Ingredient getIngredientFromDbById(int id) throws Error{
    	DBHelper dbObj = new DBHelper(this);
    	
    	try{
    		dbObj.createDataBase();
    		
    	}catch(IOException ioe){
    		throw new Error("Unable to create database");
    	}
    	
    	
    	try{
    		dbObj.openDataBase();
    	}catch(SQLException sqle){
			throw sqle;
		}
		
    	
    	Ingredient ingredientObj = new Ingredient();
        
    	ingredientObj = dbObj.getIngredient(id);
        
    	dbObj.close();
        return ingredientObj;
    }
  
    
    private Recipe getRecipeFromDbById(int id) throws Error{
    	DBHelper dbObj = new DBHelper(this);
    	
    	try{
    		dbObj.createDataBase();
    		
    	}catch(IOException ioe){
    		throw new Error("Unable to create database");
    	}
    	
    	
    	try{
    		dbObj.openDataBase();
    	}catch(SQLException sqle){
			throw sqle;
		}
		
    	
    	Recipe recipeObj = new Recipe();
        
    	recipeObj = dbObj.getRecipe(id);
        
    	dbObj.close();
        return recipeObj;
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}

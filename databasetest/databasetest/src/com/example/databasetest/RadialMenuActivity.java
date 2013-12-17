package com.example.databasetest;


import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import db.DBHelper;
import sqlite.model.*;
import com.example.databasetest.RadialMenuWidget.RadialMenuEntry;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.database.SQLException;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.widget.Toast;

public class RadialMenuActivity extends Activity {

	private RadialMenuWidget PieMenu;
	private LinearLayout ll;
	//private TextView tv;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DBHelper dbHelperObj = new DBHelper(this);
		
		
		
		/**
		 * Fill up listView1
		 */
		
		//Ingredient ingredientObj = getIngredientFromDbById(1);
        //String ingredientName = ingredientObj.getIngredient();
        
        //Recipe recipeObj = getRecipeFromDbById(1);
        //String recipeName = recipeObj.getName();
        
        
		
		
      //------------------------------------------------------------------------------------------
        // Generating Pie view
        //------------------------------------------------------------------------------------------
		setContentView(R.layout.main);
		
		
/*		There are 3 ways to add the view to also make it removeable.
 * 		1.)  Dialog box.  Use the .show() and .dismiss() commands
 * 		2.)  Just add to an existing layer using the addView() and removeView() commands
 * 		3.)  Add new layout using the addContentView; then use the addView() and removeView()
 */

		ll = new LinearLayout(this);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams( 
	             LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT); 
		addContentView(ll, params);

		
/*		final LinearLayout ll = new LinearLayout(this);
		LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT);
		final TextView tv = new TextView(this);
		tv.setText("Hello");
		tv.setTextColor(Color.WHITE); 
		ll.addView(tv);
		addContentView(ll, p);*/


		//Button testButton = (Button) this.findViewById(R.id.button1);
		//testButton.setOnClickListener(new OnClickListener() {
			//public void onClick(View v) {
				//Toast.makeText(RadialMenuActivity.this, "Button Clicked!",
					//	Toast.LENGTH_SHORT).show();
				/*
				 * Dialog dialog = new Dialog(RadialMenuActivity.this);
				 * dialog.setContentView(new
				 * RadialMenuWidget(RadialMenuActivity.this)); dialog.show();
				 */
				// Dialog dialog = new Dialog(RadialMenuActivity.this);
				// dialog.setContentView(new
				// RadialMenuWidget(RadialMenuActivity.this));
				// dialog.setCanceledOnTouchOutside(true);
				// dialog.show();

				PieMenu = new RadialMenuWidget(getBaseContext());

				PieMenu.setAnimationSpeed(0L);
				//PieMenu.setSourceLocation(100,100);
				//PieMenu.setCenterLocation(240,400);
				//PieMenu.setInnerRingRadius(50, 120);
				//PieMenu.setInnerRingColor(Color.LTGRAY, 255);
				//PieMenu.setHeader("Menu Header", 20);

				int xLayoutSize = ll.getWidth();
				int yLayoutSize = ll.getHeight();				
				PieMenu.setSourceLocation(xLayoutSize,yLayoutSize);
				PieMenu.setIconSize(15, 30);
				PieMenu.setTextSize(13);				
				
				PieMenu.setCenterCircle(new Close());
				PieMenu.addMenuEntry(new Menu1());
				PieMenu.addMenuEntry(new Menu2());
				PieMenu.addMenuEntry(new Menu3());
				PieMenu.addMenuEntry(new Menu4());
				PieMenu.addMenuEntry(new Menu5());
				PieMenu.addMenuEntry(new Menu6());
				PieMenu.addMenuEntry(new Menu7());
/*				try {
					Class<drawable> res = R.drawable.class;
					Field field = res.getField("icon");
					int drawableId = field.getInt(null);
				} catch (Exception e) {
					Log.e("MyTag", "Failure to get drawable id.", e);
				}*/
				//tv = new TextView(getBaseContext());
				//tv.setText("Hello");
				//tv.setTextColor(Color.WHITE); 
				//ll.addView(tv);
				
				ll.addView(PieMenu);

				
				// PieMenuImpl pieMenu = new PieMenuImpl();

				// pieMenu.addMenuEntry( new FileMenu() );

				// pieMenu.show();

			}
		
		//	});
 

		//}


		public boolean onTouchEvent(MotionEvent e) {
			int state = e.getAction();
			int eventX = (int) e.getX();
			int eventY = (int) e.getY();
			if (state == MotionEvent.ACTION_DOWN) {


				System.out.println( "Button Pressed");
				Toast.makeText(RadialMenuActivity.this, "Screen Touched!",
						Toast.LENGTH_SHORT).show();

				//Screen Sizes
				int xScreenSize = (getResources().getDisplayMetrics().widthPixels);
				int yScreenSize = (getResources().getDisplayMetrics().heightPixels);
				int xLayoutSize = ll.getWidth();
				int yLayoutSize = ll.getHeight();
				int xCenter = xScreenSize/2;
				int xSource = eventX;
				int yCenter = yScreenSize/2;
				int ySource = eventY;
				
				if (xScreenSize != xLayoutSize) {
					xCenter = xLayoutSize/2;
					xSource = eventX-(xScreenSize-xLayoutSize);
				}
				if (yScreenSize != yLayoutSize) {
					yCenter = yLayoutSize/2;
					ySource = eventY-(yScreenSize-yLayoutSize);
				}				
				
				PieMenu = new RadialMenuWidget(getBaseContext());

				// Koordinate header display
				PieMenu.setSourceLocation(xSource,ySource);
				PieMenu.setShowSourceLocation(true);
				PieMenu.setCenterLocation(xCenter,yCenter);

				// Show koordinate header display
				//PieMenu.setHeader("X:"+xSource+" Y:"+ySource, 20);
				
				PieMenu.setCenterCircle(new Close());
				
				PieMenu.addMenuEntry(new Menu1());
				PieMenu.addMenuEntry(new Menu2());
				PieMenu.addMenuEntry(new Menu3());
				PieMenu.addMenuEntry(new Menu4());
				PieMenu.addMenuEntry(new Menu5());
				PieMenu.addMenuEntry(new Menu6());
				PieMenu.addMenuEntry(new Menu7());
				
				
				
				
				
				ll.addView(PieMenu);
			}
			return true;
		}

		
		
		// Middle button
	   public class Close implements RadialMenuEntry
	   {

		  public String getName() { return "Close"; } 
		  public String getLabel() { return null; } 
	      public int getIcon() { return android.R.drawable.ic_menu_close_clear_cancel; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	  
	    	  System.out.println( "Close Menu Activated");
	    	  //Need to figure out how to to the layout.removeView(PieMenu)
	    	  //ll.removeView(PieMenu);
	    	  ((LinearLayout)PieMenu.getParent()).removeView(PieMenu); 

	    	  
	      }
	  	
	   }	
	
	   
	   /**
	    * FoodMap menuitems
	    */
	   
	   // Meats
	   public static class Menu1 implements RadialMenuEntry
	   {
	      public String getName() { return "Menu1"; } 
		  public String getLabel() { return "Meats"; } 
		  public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	    	  System.out.println( "Meats Activated");
	      }
	   }	
	   
	   // Vegetables
	   public class Menu2 extends Basket implements RadialMenuEntry
	   {
	      public String getName() { return "Menu2 - Children"; }
		  String ingre = ingredientObj.getIngredient();
	      public String getLabel() { return ingre; }
		  //"Vegetables"
	      public int getIcon() { return 0; }
	      private List<RadialMenuEntry> children = new ArrayList<RadialMenuEntry>( Arrays.asList( new StringOnly(), new IconOnly(), new StringAndIcon() ) );
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println( "Vegetables Activated");
	      }
	      
	   }	
	   
	   
	   // Fruits
	   public class Menu3 implements RadialMenuEntry
	   {
	      public String getName() { return "Menu3 - No Children"; }
		  public String getLabel() { return "Fruits"; } 
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println( "Fruits Activated");
	      }
	   }
	   
	   // Sweets
	   public class Menu4 implements RadialMenuEntry
	   {
	      public String getName() { return "Menu4 "; } 
		  public String getLabel() { return "Sweets"; }
	      public int getIcon() { return 0; }
	      
	      // list for children
	      private List<RadialMenuEntry> children = new ArrayList<RadialMenuEntry>( Arrays.asList( new ItemCircle(), new Item1Circle(), new Item2Circle(), new Item3Circle()));
	      public List<RadialMenuEntry> getChildren() { return children; }
	      public void menuActiviated()
	      {
	         System.out.println( "Sweets Activated");
	      }
	   }	

   
	   // Milk products
	   public class Menu5 implements RadialMenuEntry
	   {
	      public String getName() { return "Menu5"; } 
		  public String getLabel() { return "Milk"; }
	      public int getIcon() { return 0; }
	      private List<RadialMenuEntry> children = new ArrayList<RadialMenuEntry>( Arrays.asList( new YellowCircle(), new GreenCircle(), new BlueCircle() ) );
	      public List<RadialMenuEntry> getChildren() { return children; }
	      public void menuActiviated()
	      {
	         System.out.println( "Milk products Activated");
	      }
	   }	
	   
	   // Grains
	   public class Menu6 extends Basket implements RadialMenuEntry
	   {
	      public String getName() { return "Menu6"; } 
	      
	      //String ingr = ingredientObj.getIngredient();
		  public String getLabel() { return "Grains"; }
		  //"Grains"
		  
		  public int getIcon() { return 0; }
	      
	      // list for children
	      private List<RadialMenuEntry> children = new ArrayList<RadialMenuEntry>( Arrays.asList( new ItemCircle(), new Item1Circle(), new Item2Circle(), new Item3Circle()));
	      public List<RadialMenuEntry> getChildren() { return children; }
	      public void menuActiviated()
	      {
	         System.out.println( "Grains Activated");
	      }
	   }	
	   
	   // Alcohol
	   public class Menu7 implements RadialMenuEntry
	   {
		  public String getName() { return "Menu7"; } 
		  public String getLabel() { return "Alcohol"; }
	      public int getIcon() { return 0; }
	      
	      // list for children
	      private List<RadialMenuEntry> children = new ArrayList<RadialMenuEntry>( Arrays.asList( new ItemCircle(), new Item1Circle(), new Item2Circle(), new Item3Circle()));
	      public List<RadialMenuEntry> getChildren() { return children; }
	      public void menuActiviated()
	      {
	         System.out.println( "Alcohol Activated");
	      }
	   }	
	   
	   
	   
	   
	   /**
	    *	Foodmap subitems
	    */
	   
	   public class IconOnly implements RadialMenuEntry
	   {
	      public String getName() { return "IconOnly"; }
		  public String getLabel() { return null; } 
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println( "IconOnly Menu Activated");
	      }
	   }
	   
	   
	   public class StringAndIcon implements RadialMenuEntry
	   {
	      public String getName() { return "StringAndIcon"; }
		  public String getLabel() { return "String"; } 
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println( "StringAndIcon Menu Activated");
	      }
	   }
	   
	   public class StringOnly implements RadialMenuEntry
	   {
	      public String getName() { return "StringOnly"; } 
		  public String getLabel() { return "String\nOnly"; }
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println( "StringOnly Menu Activated");
	      }
	   }

	   public class NewTestMenu implements RadialMenuEntry
	   {
	      public String getName() { return "NewTestMenu"; } 
		  public String getLabel() { return "New\nTest\nMenu"; }
	      public int getIcon() { return 0; }
	      private List<RadialMenuEntry> children = new ArrayList<RadialMenuEntry>( Arrays.asList( new StringOnly(), new IconOnly(), new Item1Circle(), new Item2Circle(), new Item3Circle(), new ItemCircle() ) );
	      public List<RadialMenuEntry> getChildren() { return children; }
	      public void menuActiviated()
	      {
	         System.out.println( "New Test Menu Activated");
	      }
	   }


	   
	   
	  
	   
	   public class YellowCircle implements RadialMenuEntry
	   {
	      public String getName() { return "YellowCircle"; } 
		  public String getLabel() { return "Yellow"; }
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println( "Yellow Circle Activated");
	      }
	   }
	   public class GreenCircle implements RadialMenuEntry
	   {
	      public String getName() { return "GreenCircle"; } 
		  public String getLabel() { return "Green"; }
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println( "Green Circle Activated");
	      }
	   }
	   public class BlueCircle implements RadialMenuEntry
	   {
	      public String getName() { return "BlueCircle"; } 
		  public String getLabel() { return "Blue"; }
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println( "Blue Circle Activated");
	      }
	   }
	   
	   public class ItemCircle implements RadialMenuEntry
	   {
	      public String getName() { return "ItemCircle"; } 
		  public String getLabel() { return "Item"; }
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println( "Item Circle Activated");
	      }
	      
	   }
	   
	   public class Item1Circle extends Basket implements RadialMenuEntry
	   {
	      public String getName() { return "Item1Circle"; } 
		  public String getLabel() { return "Item1"; }
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println( "Item1 Circle Activated");
	         myBasket.add(getLabel());
	      }
	   }
	   
	   public class Item2Circle extends Basket implements RadialMenuEntry
	   {
	      public String getName() { return "Item2Circle"; } 
		  public String getLabel() { return "Item2"; }
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println( "Item2 Circle Activated");
	         myBasket.add("Item2");
	      }
	   }
	   
	   public class Item3Circle extends Basket implements RadialMenuEntry
	   {
		   Ingredient ingredientObj = new Ingredient();
		      String ingredientName = ingredientObj.getIngredient();
		       
		   
	      public String getName() { return ingredientName; } 
		  public String getLabel() { return "Item3"; }
	      public int getIcon() { return 0; }
	      
	      private List<RadialMenuEntry> children = new ArrayList<RadialMenuEntry>( Arrays.asList( new StringOnly(), new IconOnly(), new Item1Circle(), new Item2Circle(), new ItemCircle() ) );
	      
	      public List<RadialMenuEntry> getChildren() { return children; }
	      public void menuActiviated()
	      {
	         System.out.println( "Item3 Circle Activated");
	         myBasket.add("Item3");
	      }
	      
	      
	   }
	   
	   public class Basket 
	   {
	      IngredientBasket obj = new IngredientBasket();
	      public ArrayList<String> myBasket = obj.getIngredientBasket();
	      
	      Ingredient ingredientObj = getIngredientFromDbById(1);
	      
	   }
	   
	   
	   
	   
	   /*
	    * Database access methods
	    */
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


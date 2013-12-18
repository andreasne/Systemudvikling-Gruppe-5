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
		
		
		//RelativeLayout rl = (RelativeLayout) findViewById(R.id.relativeLayoutTest);
        //ListView listView1 = (ListView) findViewById(R.id.listView1);
        
        //String[] items = { "Milk", "Butter", "Yogurt", "Toothpaste", "Ice Cream" };
        
        
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        
        //listView1.setAdapter(adapter);
		
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
				PieMenu.setSourceLocation(100,100);
				PieMenu.setCenterLocation(340,300);
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
	   
	   // Spice
	   public class Menu1 extends Basket implements RadialMenuEntry
	   {
	      public String getName() { return "Menu1"; } 
		  public String getLabel() { return "Spice"; } 
		  public int getIcon() { return 0; }
		  private List<RadialMenuEntry> children = new ArrayList<RadialMenuEntry>( 
				  Arrays.asList( new ItemSpice1(), new ItemSpice2(), new ItemSpice3(), 
						  new ItemSpice4(), new ItemSpice5() ) );
		  
	      public List<RadialMenuEntry> getChildren() { return children; }
	      public void menuActiviated()
	      {
	    	  System.out.println( "Spice ingredients");
	      }
	   }	
	   
	   // Vegetables
	   public class Menu2 extends Basket implements RadialMenuEntry
	   {
	      public String getName() { return "Menu2 - Children"; }
		  public String getLabel() { return "Vegetables"; }
	      public int getIcon() { return 0; }
	      private List<RadialMenuEntry> children = new ArrayList<RadialMenuEntry>( 
	    		  Arrays.asList( new ItemVegetables1(), new ItemVegetables2(), 
	    				  new ItemVegetables3(), new ItemVegetables4(), new ItemVegetables5() ) );
	      
	      public List<RadialMenuEntry> getChildren() { return children; }
	      public void menuActiviated()
	      {
	         System.out.println( "Vegetables ingredients");
	      }
	      
	   }	
	   
	   
	   // Fruits
	   public class Menu3 implements RadialMenuEntry
	   {
	      public String getName() { return "Menu3 - No Children"; }
		  public String getLabel() { return "Fruits"; } 
	      public int getIcon() { return 0; }
	      private List<RadialMenuEntry> children = new ArrayList<RadialMenuEntry>( 
	    		  Arrays.asList( new ItemFruit1(), new ItemFruit2() ) );
	      
	      public List<RadialMenuEntry> getChildren() { return children; }
	      public void menuActiviated()
	      {
	         System.out.println( "Fruits ingredients");
	      }
	   }
	   
	   // Meat
	   public class Menu4 implements RadialMenuEntry
	   {
	      public String getName() { return "Menu4 "; } 
		  public String getLabel() { return "Meat"; }
	      public int getIcon() { return 0; }
	      
	      // list for children
	      private List<RadialMenuEntry> children = new ArrayList<RadialMenuEntry>( 
	    		  Arrays.asList( new ItemMeat1(), new ItemMeat2(), new ItemMeat3(),
	    				  new ItemMeat4(), new ItemMeat5()));
	      
	      
	      public List<RadialMenuEntry> getChildren() { return children; }
	      public void menuActiviated()
	      {
	         System.out.println( "Meat ingredients");
	      }
	   }	

   
	   // Milk products
	   public class Menu5 implements RadialMenuEntry
	   {
	      public String getName() { return "Menu5"; } 
		  public String getLabel() { return "Dairy"; }
	      public int getIcon() { return 0; }
	      
	      private List<RadialMenuEntry> children = new ArrayList<RadialMenuEntry>( 
	    		  Arrays.asList( new ItemDairy1(), new ItemDairy2()));
	      
	      
	      public List<RadialMenuEntry> getChildren() { return children; }
	      public void menuActiviated()
	      {
	         System.out.println( "Dairy ingredients");
	      }
	   }	
	   
	   // Grains
	   public class Menu6 extends Basket implements RadialMenuEntry
	   {
	      public String getName() { return "Menu6"; } 
	      
		  public String getLabel() { return "Starch"; }
		  public int getIcon() { return 0; }
	      
		  private List<RadialMenuEntry> children = new ArrayList<RadialMenuEntry>( 
	    		  Arrays.asList( new ItemStarch1(), new ItemStarch2(), new ItemStarch3(),
	    				  new ItemStarch4()));
		  
	      public List<RadialMenuEntry> getChildren() { return children; }
	      public void menuActiviated()
	      {
	         System.out.println( "Starch ingredients");
	      }
	   }	

	   
	   
	   /**
	    *	Foodmap subitems
	    */
	   
	   /**
	    * Spice items for foodmap categories
	    * @author Bjarke
	    *
	    */
	   
	   public class ItemSpice1 extends Basket implements RadialMenuEntry
	   {
		  Ingredient cuminSeeds = getIngredientFromDbById(1);
		  String cuminSeedsString = cuminSeeds.getIngredient(); 
		   
		   
	      public String getName() { return "cuminSeeds"; }
		  public String getLabel() { return cuminSeedsString; } 
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println( "Cumin seeds");
	      }
	   }
	   
	   public class ItemSpice2 extends Basket implements RadialMenuEntry
	   {
		  Ingredient corianderSeeds = getIngredientFromDbById(2);
		  String corianderSeedsString = corianderSeeds.getIngredient(); 
		   
		   
	      public String getName() { return "corianderSeeds"; }
		  public String getLabel() { return corianderSeedsString; } 
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println( "Coriander seeds");
	      }
	   }
	   
	   public class ItemSpice3 extends Basket implements RadialMenuEntry
	   {
		  Ingredient mayonnaise = getIngredientFromDbById(3);
		  String mayonnaiseString = mayonnaise.getIngredient(); 
		   
		   
	      public String getName() { return "mayonnaise"; }
		  public String getLabel() { return mayonnaiseString; } 
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println( "mayonnaise");
	      }
	   }
	   
	   public class ItemSpice4 extends Basket implements RadialMenuEntry
	   {
		  Ingredient oliveOil = getIngredientFromDbById(4);
		  String oliveOilString = oliveOil.getIngredient(); 
		   
		   
	      public String getName() { return "olive oil"; }
		  public String getLabel() { return oliveOilString; } 
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println( "olive oil");
	      }
	   }
	  
	   public class ItemSpice5 extends Basket implements RadialMenuEntry
	   {
		  Ingredient paprika = getIngredientFromDbById(6);
		  String paprikaString = paprika.getIngredient(); 
		   
		   
	      public String getName() { return "paprika"; }
		  public String getLabel() { return paprikaString; } 
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println( "paprika");
	      }
	   }
	  
	   
	   /**
	    * Fruit items for foodmap categories
	    * @author Bjarke
	    *
	    */
	   
	   public class ItemFruit1 extends Basket implements RadialMenuEntry
	   {
		  Ingredient lemon = getIngredientFromDbById(5);
		  String lemonString = lemon.getIngredient(); 
		   
		   
	      public String getName() { return "lemon"; }
		  public String getLabel() { return lemonString; } 
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println( "lemon");
	      }
	   }
	   
	   // pecans id 19
	   public class ItemFruit2 extends Basket implements RadialMenuEntry
	   {
		  Ingredient pecans = getIngredientFromDbById(19);
		  String pecansString = pecans.getIngredient(); 
		   
		   
	      public String getName() { return "pecans"; }
		  public String getLabel() { return pecansString; } 
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println( "pecans");
	      }
	   }
	   
	   
	   /**
	    * Vegetables items for foodmap categories
	    * @author Bjarke
	    *
	    */
	   // garlic clove id 7
	   public class ItemVegetables1 extends Basket implements RadialMenuEntry
	   {
		  Ingredient garlicClove = getIngredientFromDbById(7);
		  String garlicCloveString = garlicClove.getIngredient(); 
		   
		   
	      public String getName() { return "garlicClove"; }
		  public String getLabel() { return garlicCloveString; } 
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println( "garlic clove");
	      }
	   }
	   
	   // red onion id 8
	   public class ItemVegetables2 extends Basket implements RadialMenuEntry
	   {
		  Ingredient redOnion = getIngredientFromDbById(8);
		  String redOnionString = redOnion.getIngredient(); 
		   
		   
	      public String getName() { return "red onion"; }
		  public String getLabel() { return redOnionString; } 
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println("red onion");
	      }
	   }
	   
	   // bell pepper id 10
	   public class ItemVegetables3 extends Basket implements RadialMenuEntry
	   {
		  Ingredient bellPepper = getIngredientFromDbById(10);
		  String bellPepperString = bellPepper.getIngredient(); 
		   
		   
	      public String getName() { return "bell pepper"; }
		  public String getLabel() { return bellPepperString; } 
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println("bell pepper");
	      }
	   }
	   
	   // salad id 13
	   public class ItemVegetables4 extends Basket implements RadialMenuEntry
	   {
		  Ingredient salad = getIngredientFromDbById(13);
		  String saladString = salad.getIngredient(); 
		   
		   
	      public String getName() { return "salad"; }
		  public String getLabel() { return saladString; } 
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println("salad");
	      }
	   }
	   
	   // pickles id 14
	   public class ItemVegetables5 extends Basket implements RadialMenuEntry
	   {
		  Ingredient pickles = getIngredientFromDbById(14);
		  String picklesString = pickles.getIngredient(); 
		   
		   
	      public String getName() { return "pickles"; }
		  public String getLabel() { return picklesString; } 
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println("pickles");
	      }
	   }
	   
	   /**
	    * Meat items for foodmap categories
	    * @author Bjarke
	    *
	    */
	   // turkey id 9
	   public class ItemMeat1 extends Basket implements RadialMenuEntry
	   {
		  Ingredient turkey = getIngredientFromDbById(9);
		  String turkeyString = turkey.getIngredient(); 
		   
		   
	      public String getName() { return "turkey"; }
		  public String getLabel() { return turkeyString; } 
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println("turkey");
	      }
	   }
	   
	   
	   // sausage id 18
	   public class ItemMeat2 extends Basket implements RadialMenuEntry
	   {
		  Ingredient sausage = getIngredientFromDbById(18);
		  String sausageString = sausage.getIngredient(); 
		   
		   
	      public String getName() { return "sausage"; }
		  public String getLabel() { return sausageString; } 
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println("sausage");
	      }
	   }
	   
	   // beef id 22
	   public class ItemMeat3 extends Basket implements RadialMenuEntry
	   {
		  Ingredient beef = getIngredientFromDbById(22);
		  String beefString = beef.getIngredient(); 
		   
		   
	      public String getName() { return "beef"; }
		  public String getLabel() { return beefString; } 
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println("beef");
	      }
	   }
	   
	   
	   // bacon id 30
	   public class ItemMeat4 extends Basket implements RadialMenuEntry
	   {
		  Ingredient bacon = getIngredientFromDbById(30);
		  String baconString = bacon.getIngredient(); 
		   
		   
	      public String getName() { return "bacon"; }
		  public String getLabel() { return baconString; } 
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println("bacon");
	      }
	   }
	   
	   // ground chuck id 31
	   public class ItemMeat5 extends Basket implements RadialMenuEntry
	   {
		  Ingredient groundChuck = getIngredientFromDbById(31);
		  String groundChuckString = groundChuck.getIngredient(); 
		   
		   
	      public String getName() { return "ground chuck"; }
		  public String getLabel() { return groundChuckString; } 
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println("ground chuck");
	      }
	   }
	   
	   
	   
	   /**
	    * Dairy items for foodmap categories
	    * @author Bjarke
	    *
	    */
	   
	   // cheddar id 11
	   public class ItemDairy1 extends Basket implements RadialMenuEntry
	   {
		  Ingredient cheddar = getIngredientFromDbById(11);
		  String cheddarString = cheddar.getIngredient(); 
		   
		   
	      public String getName() { return "cheddar"; }
		  public String getLabel() { return cheddarString; } 
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println("cheddar");
	      }
	   }
	   
	   // blue cheese id 25
	   public class ItemDairy2 extends Basket implements RadialMenuEntry
	   {
		  Ingredient blueCheese = getIngredientFromDbById(25);
		  String blueCheeseString = blueCheese.getIngredient(); 
		   
		   
	      public String getName() { return "blue cheese"; }
		  public String getLabel() { return blueCheeseString; } 
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println("blue cheese");
	      }
	   }
	   
	   
	   
	   
	   /**
	    * Starch item for foodmap categories
	    */
	   
	   // burger buns id 12
	   public class ItemStarch1 extends Basket implements RadialMenuEntry
	   {
		  Ingredient burgerBuns = getIngredientFromDbById(12);
		  String burgerBunsString = burgerBuns.getIngredient(); 
		   
		   
	      public String getName() { return "burger buns"; }
		  public String getLabel() { return burgerBunsString; } 
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println("burger buns");
	         
	      }
	   }
	   
	   // corn chips id 15
	   public class ItemStarch2 extends Basket implements RadialMenuEntry
	   {
		  Ingredient cornChips = getIngredientFromDbById(15);
		  String cornChipsString = cornChips.getIngredient(); 
		   
		   
	      public String getName() { return "corn chips"; }
		  public String getLabel() { return cornChipsString; } 
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println("corn chips");
	         
	      }
	   }
	   
	   // flour id 33
	   public class ItemStarch3 extends Basket implements RadialMenuEntry
	   {
		  Ingredient flour = getIngredientFromDbById(33);
		  String flourString = flour.getIngredient(); 
		   
		   
	      public String getName() { return "flour"; }
		  public String getLabel() { return flourString; } 
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println("flour");
	         
	      }
	   }
	   
	   // panko id 35
	   public class ItemStarch4 extends Basket implements RadialMenuEntry
	   {
		  Ingredient panko = getIngredientFromDbById(35);
		  String pankoString = panko.getIngredient(); 
		   
		   
	      public String getName() { return "panko"; }
		  public String getLabel() { return pankoString; } 
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println("panko");
	         
	      }
	   }
	   
	   
	   
	   
	   /**
	    * Basket class for charging list 
	    * @author Bjarke
	    *
	    */
	   public class Basket 
	   {
		   
		   IngredientBasket obj = new IngredientBasket();
		   public ArrayList<String> myBasket = obj.getIngredientBasket();
	
		   ArrayList<Ingredient> allIngredients = getAllIngredientsFromDb();
	      
		   // Meat ingredients list
		   ArrayList<Ingredient> meatList = getMeatIngredients(allIngredients);
		   
		   // Spice ingredients list
		   ArrayList<Ingredient> spiceList = getSpiceIngredients(allIngredients);
		   
		   // Starch ingredients list
		   ArrayList<Ingredient> starchList = getStarchIngredients(allIngredients);
		   
		   // Vegetable ingredients list
		   ArrayList<Ingredient> vegetableList = getVegetableIngredients(allIngredients);
		   
		   // Fruit ingredients list
		   ArrayList<Ingredient> fruitList = getFruitIngredients(allIngredients);
		   
		   // Diary ingredients list
		   ArrayList<Ingredient> diaryList = getDiaryIngredients(allIngredients);
		   
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
	    
	    private ArrayList<Ingredient> getAllIngredientsFromDb() throws Error{
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
			
	    	ArrayList<Ingredient> allIngredients = dbObj.getAllIngredients();	
	        
	    	dbObj.close();
	        return allIngredients;
	    }
	    
	    
	    /**
		    * Get meat ingredients
		    * @param ingredientList
		    * @return
		    */
		   public ArrayList<Ingredient> getMeatIngredients(ArrayList<Ingredient> ingredientList){
			   
			   ArrayList<Ingredient> meatList = new ArrayList<Ingredient>();
			   
			   for(int i = 0; i < ingredientList.size() -1; i++){
			   
				    if(ingredientList.get(i).getCategory() == "meat"){
				    	meatList.add(ingredientList.get(i));
				    }
			   }
			   
			   return meatList;
		   }
		   
		  /**
		   * Get spice ingredients
		   * @param ingredientList
		   * @return
		   */
		   public ArrayList<Ingredient> getSpiceIngredients(ArrayList<Ingredient> ingredientList){
			   
			   ArrayList<Ingredient> spiceList = new ArrayList<Ingredient>();
			   
			   for(int i = 0; i < ingredientList.size() -1; i++){
			   
				    if(ingredientList.get(i).getCategory() == "spice"){
				    	spiceList.add(ingredientList.get(i));
				    }
			   }
			   
			   return spiceList;
		   }
			   
		   /**
		    * Get starch ingredients
		    * @param ingredientList
		    * @return
		    */
		   
		   public ArrayList<Ingredient> getStarchIngredients(ArrayList<Ingredient> ingredientList){
			   
			   ArrayList<Ingredient> starchList = new ArrayList<Ingredient>();
			   
			   for(int i = 0; i < ingredientList.size() -1; i++){
			   
				    if(ingredientList.get(i).getCategory() == "starch"){
				    	starchList.add(ingredientList.get(i));
				    }
			   }
			   
			   return starchList;
		   }
		   
		   /**
		    * Get vegetable ingredients
		    * @param ingredientList
		    * @return
		    */
		   
		   public ArrayList<Ingredient> getVegetableIngredients(ArrayList<Ingredient> ingredientList){
			   
			   ArrayList<Ingredient> vegetableList = new ArrayList<Ingredient>();
			   
			   for(int i = 0; i < ingredientList.size() -1; i++){
			   
				    if(ingredientList.get(i).getCategory() == "vegetable"){
				    	vegetableList.add(ingredientList.get(i));
				    }
			   }
			   
			   return vegetableList;
		   }
		   
		   /**
		    * Get fruit ingredients
		    * @param ingredientList
		    * @return
		    */
		   
		   public ArrayList<Ingredient> getFruitIngredients(ArrayList<Ingredient> ingredientList){
			   
			   ArrayList<Ingredient> fruitList = new ArrayList<Ingredient>();
			   
			   for(int i = 0; i < ingredientList.size() -1; i++){
			   
				    if(ingredientList.get(i).getCategory() == "fruit"){
				    	fruitList.add(ingredientList.get(i));
				    }
			   }
			   
			   return fruitList;
		   }
		  
		   
		   public ArrayList<Ingredient> getDiaryIngredients(ArrayList<Ingredient> ingredientList){
			   
			   ArrayList<Ingredient> diaryList = new ArrayList<Ingredient>();
			   
			   for(int i = 0; i < ingredientList.size() -1; i++){
			   
				    if(ingredientList.get(i).getCategory() == "diary"){
				    	diaryList.add(ingredientList.get(i));
				    }
			   }
			   
			   return diaryList;
		   }
	    
	    
	    
	    
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }  
}
	   


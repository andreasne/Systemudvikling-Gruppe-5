package com.example.databasetest.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;

import com.example.databasetest.*;

public class RadialMenuActivityTest extends ActivityInstrumentationTestCase2<RadialMenuActivity> {
	
	
	
	public RadialMenuActivityTest(Class<RadialMenuActivity> activityClass) {
		super(activityClass);
		
	}
	
	private RadialMenuActivity mActivity;
	private ListView mView;
	private String resourceString;
	
	protected void setUp() throws Exception{
		super.setUp();
		mActivity = this.getActivity();
		mView = (ListView) mActivity.findViewById(com.example.databasetest.R.id.listView1);
	}
	
	public void testPreconditions(){
		assertNotNull(mView);
	}
	
	
}

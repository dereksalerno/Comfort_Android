package com.AppliedArgonautics.comfort;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity implements OnGesturePerformedListener {
	
	private GestureLibrary gLibrary;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/clarendon.ttf");
/*		Button button1 = (Button) findViewById(R.id.button1);
		button1.setTypeface(myTypeface);*/
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setTypeface(myTypeface);
		
		gLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
		if (!gLibrary.load()){
			finish();
		}
		GestureOverlayView gOverlay = 
				(GestureOverlayView) findViewById(R.id.gOverlay);
		gOverlay.addOnGesturePerformedListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void goToFood(View v){
		Intent i = new Intent(this, FoodActivity.class);
		startActivity(i);
	}
	
	public void goToBar(View v){
		Intent i = new Intent(this, BarActivity.class);
		startActivity(i);
	}
	//Let user swipe to go to food or drink menu
	 public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
	        ArrayList<Prediction> predictions = gLibrary.recognize(gesture);
	        
	        if (predictions.size() > 0 && predictions.get(0).score > 1.0) {
	            
	        	String action = predictions.get(0).name;
	            if (action.equals("swipe_right")){
	            	Intent i = new Intent(this, FoodActivity.class);
	        		startActivity(i);
	            }
	            else if (action.equals("swipe_left")){
	            	Intent i = new Intent(this, BarActivity.class);
	        		startActivity(i);
	            }
	        }
	    }
}

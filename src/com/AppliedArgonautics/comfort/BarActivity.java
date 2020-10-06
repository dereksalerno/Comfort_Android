package com.AppliedArgonautics.comfort;

import java.util.ArrayList;
//import com.facebook.AccessToken;

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

public class BarActivity extends Activity implements OnGesturePerformedListener {

	private GestureLibrary gLibrary;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//Lock orientation to avoid clumsy switches
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_bar_linear);
		
		//set the custom typeface for all UI objects
		Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/clarendon.ttf");
		Button button1 = (Button) findViewById(R.id.button1);
			button1.setTypeface(myTypeface);
		Button button2 = (Button) findViewById(R.id.button2);
			button2.setTypeface(myTypeface);
		Button button3 = (Button) findViewById(R.id.button3);
			button3.setTypeface(myTypeface);
		Button button4= (Button) findViewById(R.id.button4);
			button4.setTypeface(myTypeface);
		Button button5 = (Button) findViewById(R.id.button5);
			button5.setTypeface(myTypeface);
		
		gLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
		if (!gLibrary.load()){
			finish();
		}
//		GestureOverlayView gOverlay = 
//				(GestureOverlayView) findViewById(R.id.gOverlay);
//		gOverlay.addOnGesturePerformedListener(this);
	}

	@Override
public boolean onCreateOptionsMenu(Menu menu) {
		 //Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_bar, menu);
		return true;
	}
	/**
	 * @param v is the view being clicked by user. It should be a 
	 * 		  Button to avoid java.ClassCastException when getting
	 * 		  the title of the button 
	 * @see   ShowListActivity.class to see how Intents are used
	 */
	//load info for the Intents on click of Buttons
	public void goToLiquorList(View v){
		Button b = (Button)v;
		Intent i = new Intent(this, ShowListActivity.class);
		i.putExtra("Value", b.getText().toString());
		i.putExtra("Title", b.getText().toString());
		i.putExtra("Category", "Liquor");
		startActivity(i);
	}

	public void goToBeerList(View v){
		Intent i = new Intent(this, BeerActivity.class);
		startActivity(i);
	}
	/**
	 * @param v is the view being clicked by user. It should be a 
	 * 		  Button to avoid java.ClassCastException when getting
	 * 		  the title of the button 
	 * @see   ShowListActivity.class
	 */
	public void goToCocktailList(View v){
		Button b = (Button)v;
		Intent i = new Intent(this, ShowListActivity.class);
		i.putExtra("Value", "cocktail");
		i.putExtra("Category", "Liquor");
		i.putExtra("Title", b.getText().toString());
		startActivity(i);
	}
	
	public void goToWineList(View v){
		Intent i = new Intent(this, WineActivity.class);
		startActivity(i);
	}

	public void goToWhiskey(View v){
		Intent i = new Intent(this, WhiskeyActivity.class);
		startActivity(i);
	}
	//Set up swipe to go back
	public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        ArrayList<Prediction> predictions = gLibrary.recognize(gesture);
        
        if (predictions.size() > 0 && predictions.get(0).score > 1.0) {
            
        	String action = predictions.get(0).name;
            //Swipe Right action takes user back to MainActivity.class
        	if (action.equals("swipe_right")){
            	Intent i = new Intent(this, MainActivity.class);
        		startActivity(i);
            }
        }
    }
}

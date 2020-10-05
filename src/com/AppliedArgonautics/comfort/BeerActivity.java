package com.AppliedArgonautics.comfort;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class BeerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Set the window title and lock screen rotation
		final String WINDOW_TITLE = ("Beer");
		setTitle(WINDOW_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_beer_linear);
		
		//Set the custom typeface for UI elements
		Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/clarendon.ttf");
		//ClarendonButton button1 = (ClarendonButton) findViewById(R.id.button1);
		Button button1 = (Button) findViewById(R.id.button1);
			button1.setTypeface(myTypeface);
		Button button2 = (Button) findViewById(R.id.button2);
			button2.setTypeface(myTypeface);
		Button button3 = (Button) findViewById(R.id.button3);
			button3.setTypeface(myTypeface);
		Button button5 = (Button) findViewById(R.id.button5);
			button5.setTypeface(myTypeface);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_beer, menu);
		return true;
	}
	public void goToList(View v){
		Button b = (Button)v;
		Intent i = new Intent(this, ShowListActivity.class);
		i.putExtra("Value", b.getText().toString());
		i.putExtra("Category", "Beer");
		i.putExtra("Title", b.getText().toString());
		startActivity(i);
	}

}

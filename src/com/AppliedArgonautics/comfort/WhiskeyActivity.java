package com.AppliedArgonautics.comfort;

import com.AppliedArgonautics.comfort.R.color;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class WhiskeyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		final String WINDOW_TITLE = ("Whiskey");
		setTitle(WINDOW_TITLE);
		setContentView(R.layout.activity_whiskey_linear);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/clarendon.ttf");
		Button button1 = (Button) findViewById(R.id.button1);
			button1.setTypeface(myTypeface);
		Button button2 = (Button) findViewById(R.id.button2);
			button2.setTypeface(myTypeface);
		Button button3= (Button) findViewById(R.id.button3);
			button3.setTypeface(myTypeface);
		Button button5 = (Button) findViewById(R.id.button5);
			button5.setTypeface(myTypeface);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_whiskey, menu);
		return true;
	}
	
	
	public void goToList(View v){
		Button b = (Button)v;
		Intent i = new Intent(this, ShowListActivity.class);
		i.putExtra("Value", b.getText().toString());
		i.putExtra("Category", "Liquor");
		i.putExtra("Title", b.getText().toString());
		startActivity(i);
	}
	public void goToOtherAmerican(View v){
		Intent i = new Intent(this, OtherAmericanWhiskeyActivity.class);
		startActivity(i);
	}
	public void goToForeign(View v){
		Intent i = new Intent(this, ForeignWhiskey.class);
		startActivity(i);
	}
}

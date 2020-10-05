package com.AppliedArgonautics.comfort;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class WineActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		final String WINDOW_TITLE = ("Wine");
		setTitle(WINDOW_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_wine_linear);
		Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/clarendon.ttf");
		Button button1 = (Button)findViewById(R.id.button1);
		button1.setTypeface(typeface);
		Button button2 = (Button)findViewById(R.id.button2);
		button2.setTypeface(typeface);
		Button button3 = (Button)findViewById(R.id.button3);
		button3.setTypeface(typeface);
		Button button5 = (Button)findViewById(R.id.button5);
		button5.setTypeface(typeface);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_wine, menu);
		return true;
	}
	public void goToRedGlass(View v){
		Intent i = new Intent(this, ShowListActivity.class);
		i.putExtra("Color", "red");
		i.putExtra("Category", "Wine");
		i.putExtra("Value", "btg");
		i.putExtra("Title", "Reds by the Glass");
		startActivity(i);
	}
	public void goToRedBottle(View v){
		Intent i = new Intent(this, ShowListActivity.class);
		i.putExtra("Color", "red");
		i.putExtra("Category", "Wine");
		i.putExtra("Value", "bottle");
		i.putExtra("Title", "Reds by the Bottle");
		startActivity(i);
	}
	public void goToWhiteGlass(View v){
		Intent i = new Intent(this, ShowListActivity.class);
		i.putExtra("Color", "white");
		i.putExtra("Category", "Wine");
		i.putExtra("Value", "btg");
		i.putExtra("Title", "Whites by the Glass");
		startActivity(i);
	}
	public void goToWhiteBottle(View v){
		Intent i = new Intent(this, ShowListActivity.class);
		i.putExtra("Color", "white");
		i.putExtra("Category", "Wine");
		i.putExtra("Value", "bottle");
		i.putExtra("Title", "Whites by the Bottle");
		startActivity(i);
	}
}

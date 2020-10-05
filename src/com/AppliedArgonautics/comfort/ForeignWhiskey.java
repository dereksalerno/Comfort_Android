package com.AppliedArgonautics.comfort;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class ForeignWhiskey extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		final String WINDOW_TITLE = ("Imported Whiskey");
		setTitle(WINDOW_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_foreign_whiskey_linear);
		Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/clarendon.ttf");
		Button button1 = (Button) findViewById(R.id.button1);
			button1.setTypeface(myTypeface);
		Button button2 = (Button) findViewById(R.id.button2);
			button2.setTypeface(myTypeface);
     	Button button3 = (Button) findViewById(R.id.button3);
			button3.setTypeface(myTypeface);
		Button button4= (Button) findViewById(R.id.button4);
			button4.setTypeface(myTypeface);
//		Button button5 = (Button) findViewById(R.id.button5);
//			button5.setTypeface(myTypeface);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_foreign_whiskey, menu);
		return true;
	}
	public void goToList(View v){
		Button b = (Button)v;
		Intent i = new Intent(this, ShowListActivity.class);
		i.putExtra("Value", b.getText().toString());
		System.out.println(b.getText().toString());
		i.putExtra("Category", "Liquor");
		i.putExtra("Title", b.getText().toString());
		startActivity(i);
	}

}

package com.AppliedArgonautics.comfort;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;


public class NotesActivity extends Activity implements OnRatingBarChangeListener {
	//final Intent goToBar = new Intent(NotesActivity.this, BarActivity.class);
	Float mRating;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_notes_linear);
		setupUI(findViewById(R.id.notesParent));
		Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/clarendon.ttf");
		TextView tv = (TextView)findViewById(R.id.textView1);
		tv.setTypeface(myTypeface);
		Intent i = getIntent();
		//get the sauce
		MenuItem itemRated = (MenuItem)i.getSerializableExtra("RatedItem");
		final String liqName = itemRated.toString();
//		final String liqName = i.getExtras().getString("Item");
		tv.setText(liqName);
		EditText et = (EditText)findViewById(R.id.editText1);
		RatingBar rateBar = (RatingBar)findViewById(R.id.ratingBar1);
		if (itemRated.hasRating){
			et.setText(itemRated.getNotes());
			rateBar.setRating(itemRated.getRating());
		}
		Button button1 = (Button) findViewById(R.id.button1);
			button1.setTypeface(myTypeface);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_red_bottle, menu);
		return true;
	}
	// Get the item info from the Intent and write the tasting notes to a SQLite DB
	public void addToDB(View v){
		Intent i = getIntent();
		MenuItem itemRated = (MenuItem)i.getSerializableExtra("RatedItem");
		final String liqName = itemRated.toString();
		// Let's make sure there isn't an existing entry!!
		boolean alreadyRated = existsAlreadyInDatabase(liqName);
		//Check to see if the item has already been rated and exists in the database
		if (!alreadyRated){
		TextView tv = (TextView)findViewById(R.id.textView1);
		//set the title
		tv.setText(liqName);
		
		EditText et = (EditText)findViewById(R.id.editText1);
		RatingBar rb = (RatingBar)findViewById(R.id.ratingBar1);
		final Float rating = rb.getRating();
		final String TastingNotes = et.getText().toString();
		SQLiteDatabase db = openOrCreateDatabase("TastingJournal", MODE_PRIVATE, null );
		db.execSQL("CREATE TABLE IF NOT EXISTS ItemsRated (ItemName VARCHAR, Notes VARCHAR, Rating REAL(2));");
		//SQLiteStatement stmt = db.compileStatement("CREATE TABLE IF NOT EXISTS ? (? VARCHAR, ? VARCHAR, ? REAL(2));");
		SQLiteStatement deleteItem = db.compileStatement("DELETE FROM ItemsRated WHERE ItemName=?;");
		deleteItem.bindString(1, liqName);
		deleteItem.execute();
		SQLiteStatement insertItem = db.compileStatement("INSERT INTO ItemsRated VALUES (?, ?, ?);");
		insertItem.bindString(1, liqName);
		insertItem.bindString(2,  TastingNotes);
		insertItem.bindDouble(3, rating.doubleValue());
		insertItem.execute();
		
		db.close();
		et.setText("");
		Log.d("RATINGCHANGE", rating.toString());
		Intent goToBar = new Intent(this, BarActivity.class);
		startActivity(goToBar);
		}else{
			showWarningDialog();
		}
	}
	public void printDBValues(View v){
		SQLiteDatabase db = openOrCreateDatabase("TastingJournal", MODE_PRIVATE, null );
		Cursor c = db.rawQuery("SELECT * FROM ItemsRated", null);
		ArrayList<String> arr1 = new ArrayList<String>();
		

		c.moveToFirst();
		while (c.isAfterLast() == false) 
		{
		    arr1.add(c.getString(0));
		    c.moveToNext();
		}
		for (int j=0;j<arr1.size(); j++){
			Log.d("WEINER", arr1.get(j));

		}

		db.close();
}
	
	public boolean existsAlreadyInDatabase(String newlyRatedItem){
		boolean tmpB = false;
		try{
			SQLiteDatabase db = openOrCreateDatabase("TastingJournal", MODE_PRIVATE, null );
			Cursor c = db.rawQuery("SELECT * FROM ItemsRated", null);
			ArrayList<String> arr1 = new ArrayList<String>();
			c.moveToFirst();
			
			
			while (c.isAfterLast() == false) 
			{
			    arr1.add(c.getString(0));
			    c.moveToNext();
			}
			for (String tmpString : arr1){
				if (tmpString.equals(newlyRatedItem)){
					tmpB = true;
				}
			}
			
	}catch (SQLiteException e){
			e.printStackTrace();
		}
			return tmpB;
	}
	
	//If the entry exists in the SQLite DB, let's go ahead and make sure the user wants to overwrite it
	public void showWarningDialog(){
		Builder builder = new AlertDialog.Builder(this);  
		builder.setTitle("Are you sure?");
		builder.setMessage("Would You like to overwrite the current entry?");
		builder.setCancelable(false);
		
		builder.setPositiveButton("Overwrite", new DialogInterface.OnClickListener() {
			
			
			@Override
	public void onClick(DialogInterface dialog, int which) {
				Intent i = getIntent();
				MenuItem itemRated = (MenuItem)i.getSerializableExtra("RatedItem");
				final String liqName = itemRated.toString();
				TextView tv = (TextView)findViewById(R.id.textView1);
				tv.setText(liqName);
				EditText et = (EditText)findViewById(R.id.editText1);
				RatingBar rb = (RatingBar)findViewById(R.id.ratingBar1);
				final Float rating = rb.getRating();
				final String TastingNotes = et.getText().toString();
				SQLiteDatabase db = openOrCreateDatabase("TastingJournal", MODE_PRIVATE, null );
				db.execSQL("CREATE TABLE IF NOT EXISTS ItemsRated (ItemName VARCHAR, Notes VARCHAR, Rating REAL(2));");
				SQLiteStatement deleteItem = db.compileStatement("DELETE FROM ItemsRated WHERE ItemName=?;");
				deleteItem.bindString(1, liqName);
				deleteItem.execute();
				
				
				//
				SQLiteStatement insertItem = db.compileStatement("INSERT INTO ItemsRated VALUES (?, ?, ?);");
				insertItem.bindString(1, liqName);
				insertItem.bindString(2,  TastingNotes);
				insertItem.bindDouble(3, rating.doubleValue());
				
				insertItem.execute();
				
				db.close();
				et.setText("");
				
				Intent goToBar = new Intent(NotesActivity.this, BarActivity.class);
				startActivity(goToBar);
			}
		});
		
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		builder.show();
	}

	// Put some stars next to it
	@Override
	public void onRatingChanged(RatingBar ratingBar, float rating,
			boolean fromUser) {
		ratingBar = (RatingBar)findViewById(R.id.ratingBar1);
		mRating = rating;
	}

	public static void hideSoftKeyboard(Activity activity){
		InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
	}
	
	public void setupUI(View view) {

	    //Set up touch listener for non-text box views to hide keyboard.
	    if(!(view instanceof EditText)) {

	        view.setOnTouchListener(new OnTouchListener() {

	            public boolean onTouch(View v, MotionEvent event) {
	                hideSoftKeyboard(NotesActivity.this);
	                return false;
	            }

	        });
	    }
	}
}
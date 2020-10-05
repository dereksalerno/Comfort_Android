package com.AppliedArgonautics.comfort;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class ShowListActivity extends ListActivity {

	final Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		Intent i = getIntent();
		String windowTitle = i.getExtras().getString("Title");
		setTitle(windowTitle);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_list);
		String ArrayCategory = i.getExtras().getString("Category");
		
		

		
		//Get the liquor view
		
		if(ArrayCategory.equals("Liquor")){
		
		try {
			InputStream is = this.getAssets().open("comfort_bar.xml");
			MenuParser p = new MenuParser(is, "liquor");
			//LiquorParser p = new LiquorParser(is);
			String ArrayType = i.getExtras().getString("Value");
			final ArrayList<MenuItem> items = p.getItems(ArrayType);
			
			
			ClarendonArrayAdapter<MenuItem> adapter = new ClarendonArrayAdapter<MenuItem>(
					this,
					R.id.listTextView,
					items);
			
			final ListView lv1 = (ListView) findViewById(android.R.id.list);
			lv1.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					final Liquor alertLiquor = (Liquor)lv1.getItemAtPosition(position);
					
					final Context mContext = ShowListActivity.this;
					Builder builder = new AlertDialog.Builder(mContext);  
					builder.setTitle(alertLiquor.toString());
					builder.setMessage(alertLiquor.getAlertString());
					builder.setCancelable(false);
					
					builder.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
						
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
						
						}
					});
					//changes the button from 'Rate' if already Rated
					String negButton = null;
					if (alertLiquor.hasRating){
						negButton = "View Notes";
					}
					else {
						negButton = "Rate";
					}
					
					builder.setNegativeButton(negButton, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent notesIntent = new Intent(mContext, NotesActivity.class);
							notesIntent.putExtra("RatedItem", alertLiquor);
							notesIntent.putExtra("Item", alertLiquor.toString());
							notesIntent.putExtra("Notes", alertLiquor.getNotes());
							mContext.startActivity(notesIntent);
						}
					});
					
					builder.setCancelable(true);
						builder.create();
						builder.show();
					
				}

					
			});
			
			lv1.setAdapter(adapter);
			
			
		} catch (IOException e) {
			e.printStackTrace();
	}
}
		
		
		
		//Get the beer view, if that is the case
		
		
		
		else if(ArrayCategory.equals("Beer")){
			////////////
			try {
				InputStream is = this.getAssets().open("comfort_bar.xml");
				MenuParser p = new MenuParser(is, "beer");
				String ArrayType = i.getExtras().getString("Value");
				final ArrayList<MenuItem> items = p.getItems(ArrayType);
				
				ClarendonArrayAdapter<MenuItem> adapter = new ClarendonArrayAdapter<MenuItem>(
						this,
						R.layout.simple_list_item_1,
						items);
				
				final ListView lv1 = (ListView) findViewById(android.R.id.list);
				//lv1.setTypeFace(myTypeFace);
				lv1.setOnItemClickListener(new OnItemClickListener(){

					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						final Beer alertBeer = (Beer)lv1.getItemAtPosition(position);
						
						final Context mContext = ShowListActivity.this;
						Builder builder = new AlertDialog.Builder(mContext);  
						

						builder.setTitle(alertBeer.toString());
						builder.setMessage(alertBeer.getAlertString());
						builder.setCancelable(false);
						
						builder.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
							
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
							
							}
						});

						String negButton = null;
						if (alertBeer.hasRating){
							negButton = "View Notes";
						}
						else {
							negButton = "Rate";
						}
						
						builder.setNegativeButton(negButton, new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								Intent notesIntent = new Intent(mContext, NotesActivity.class);
								notesIntent.putExtra("RatedItem", alertBeer);
								notesIntent.putExtra("Item", alertBeer.toString());
								notesIntent.putExtra("Notes", alertBeer.getNotes());
								mContext.startActivity(notesIntent);
							}
						});
							builder.create();
							builder.show();
						}
						
				
				});
		
				lv1.setAdapter(adapter);
				
				
			} catch (IOException e) {
				e.printStackTrace();
		}

	}
		
		//And here is the Wine view!!!!
		
		if(ArrayCategory.equals("Wine")){
			
			try {
				InputStream is = this.getAssets().open("comfort_bar.xml");
				MenuParser p = new MenuParser(is, "wine");
				String ArrayType = i.getExtras().getString("Value");
				String wineColor = i.getExtras().getString("Color");
				final ArrayList<MenuItem> items = p.getItems(wineColor,ArrayType);
				
				ClarendonArrayAdapter<MenuItem> adapter = new ClarendonArrayAdapter<MenuItem>(
						this,
						R.layout.simple_list_item_1,
						items);
				
				final ListView lv1 = (ListView) findViewById(android.R.id.list);
				lv1.setOnItemClickListener(new OnItemClickListener(){

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						final Wine alertWine = (Wine)lv1.getItemAtPosition(position);
						
						
						final Context mContext = ShowListActivity.this;
						Builder builder = new AlertDialog.Builder(mContext);  
					
						builder.setTitle(alertWine.toString());
						builder.setMessage(alertWine.getAlertString());
						
						builder.setCancelable(false);
						
						builder.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
							
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
							
							}
						});
						String negButton = null;
						if (alertWine.hasRating){
							negButton = "View Notes";
						}
						else {
							negButton = "Rate";
						}

						builder.setNegativeButton(negButton, new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								Intent notesIntent = new Intent(mContext, NotesActivity.class);
								notesIntent.putExtra("RatedItem", alertWine);
								notesIntent.putExtra("Item", alertWine.toString());
								notesIntent.putExtra("Notes", alertWine.getNotes());
								mContext.startActivity(notesIntent);
							}
						});
							builder.show();
						
					}

						
				});
				
				lv1.setAdapter(adapter);
				
				
			} catch (IOException e) {
				e.printStackTrace();
		}
	}
		
}
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_list, menu);
		return true;
	}
	public void showDialog(View v){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setTitle(this.getTitle());
	}
}
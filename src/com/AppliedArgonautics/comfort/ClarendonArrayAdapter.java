package com.AppliedArgonautics.comfort;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
//import com.AppliedArgonautics.comfort.MenuItem;


public class ClarendonArrayAdapter<MenuItem> extends ArrayAdapter<MenuItem> {
	
	private com.AppliedArgonautics.comfort.MenuItem menuItem;
	private ArrayList<MenuItem> items;
	private ItemViewHolder viewHolder;
	
	private class ItemViewHolder{
		TextView item;
		RatingBar rb;
	}
	
	public ClarendonArrayAdapter(Context context, int textViewResourceId, ArrayList<MenuItem> items) {
		super(context, textViewResourceId, items);
		this.items = items;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
	//This is all from so example
	//menuItem = (com.AppliedArgonautics.comfort.MenuItem)items.get(position);
	View view = convertView;
	com.AppliedArgonautics.comfort.MenuItem menuItem = 
			(com.AppliedArgonautics.comfort.MenuItem)items.get(position);
//	if (menuItem instanceof Liquor){
//		menuItem = (Liquor)menuItem;
//	}
//	if (menuItem instanceof Beer){
//		menuItem = (Beer)menuItem;
//	}
//	if (menuItem instanceof Wine){
//		menuItem = (Wine)menuItem;
//	}
	
	if (view == null){
		LayoutInflater vi = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = vi.inflate(R.layout.copy_of_simple_list_item_1, null);
		Context context = super.getContext();
		Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/clarendon.ttf");
		viewHolder = new ItemViewHolder();
		viewHolder.item = (TextView)view.findViewById(R.id.listTextView);
		viewHolder.item.setTypeface(tf);
		viewHolder.rb = (RatingBar)view.findViewById(R.id.listRatingBar);
		//just added these two
		String listName = menuItem.toString();
//		if (menuItem instanceof Liquor){
//			menuItem = (com.AppliedArgonautics.comfort.Liquor)menuItem;
//		}
//		if (menuItem instanceof Beer){
//			menuItem = (com.AppliedArgonautics.comfort.Beer)menuItem;
//		}
//		if (menuItem instanceof Wine){
//			menuItem = (com.AppliedArgonautics.comfort.Wine)menuItem;
//		}
		viewHolder.item.setText(listName);
//		String notes = com.AppliedArgonautics.comfort.LiquorParser.getNotes(menuItem.toString());
//		menuItem.setNotes(notes);
		if (menuItem.hasRating){
		viewHolder.rb.setRating(menuItem.getRating());
		}
		Float fl = (Float) menuItem.getRating();
		view.setTag(viewHolder);
	} else { viewHolder = (ItemViewHolder)view.getTag();
		
//	com.AppliedArgonautics.comfort.MenuItem menuItem = 
//			(com.AppliedArgonautics.comfort.MenuItem)items.get(position);
		
	if (menuItem != null)//* && (menuItem.hasRating))/*
			{
			Context context = super.getContext();
			Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/clarendon.ttf");
			if (menuItem instanceof Liquor){
				menuItem = (com.AppliedArgonautics.comfort.Liquor)menuItem;
			}
			viewHolder.item.setTypeface(tf);
			viewHolder.item.setText(menuItem.toString());
			viewHolder.rb.setRating(menuItem.getRating());
			//Let's see............
			//viewHolder.rb.setRating(menuItem.getRating());
		}
//this is the old code	
//	Context context = super.getContext();
//	Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/clarendon.ttf");
//	View v = super.getView(position, convertView, parent);
//	((TextView)v).setTypeface(tf);
//	return v;
		}
		return view;
	}
	
}
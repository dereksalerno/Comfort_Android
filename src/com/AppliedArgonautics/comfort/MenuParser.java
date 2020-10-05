package com.AppliedArgonautics.comfort;



import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

/**
 * @author Derek Salerno
 * @version 1.0
 */
public class MenuParser extends DefaultHandler {

    private ArrayList<MenuItem> ItemL;
    private StringBuilder sb;
    private String itemType;

    /**
     * @param ArrayType  a String representing the specific type of MenuItem
     * @return 			 a MenuItem array of the specific type 
     * @see				 MenuItem
     */
    public ArrayList<MenuItem> getItems(String ArrayType){
    	ArrayList<MenuItem> tmpItems = new ArrayList<MenuItem>();
    	Log.d("DEO", "Starting getItems");
    	ArrayList<String> rated = ratedItems();
    	for (MenuItem men : ItemL){
    		//loop through the rated Items, and setRating of current items from DB
    		for (int i = 0; i < rated.size(); i++)
    		{
    		if (men.toString().equals(rated.get(i))){
    		//int index = 
    		men.hasRating = true;
    		men.setRating(getRating(men.toString()));
    		//Log.d("FromDB", (getRating(men.toString())).toString());
    		men.setNotes(getNotes(men.toString()));
    		//Log.d("DEO", "getting rating for " +men.toString());
    			}
    		}
    		if (men.getType().equals(ArrayType)){
    		tmpItems.add(men);
    			}
    		}
    	Log.d("DEO", "getItems returned");
    	return tmpItems;
    }
    public ArrayList<MenuItem> getItems(String wineColor, String ArrayType){
    	ArrayList<MenuItem> tmpItems = new ArrayList<MenuItem>();
    	Log.d("DEO", "Starting getItems");
    	ArrayList<String> rated = ratedItems();
    	for (MenuItem men : ItemL){
    		if(men instanceof Wine){
    		//loop through the rated Items, and setRating of current items from DB
    		for (int i = 0; i < rated.size(); i++)
    		{
    		if (men.toString().equals(rated.get(i))){
    		//int index = 
    		men.hasRating = true;
    		men.setRating(getRating(men.toString()));
    		//Log.d("FromDB", (getRating(men.toString())).toString());
    		men.setNotes(getNotes(men.toString()));
    		//Log.d("DEO", "getting rating for " +men.toString());
    			}
    		}
    		Wine menWine = (Wine)men;
    		if ((menWine.getType().equals(ArrayType))&&(menWine.getColor().equals(wineColor))){
    		tmpItems.add(menWine);
    			}
    		}
    	}
    	Log.d("DEO", "getItems returned");
    	return tmpItems;
    }
    /**
     * @return 		an ArrayList of the names of previously rated items
     * @see			NotesActivity 
     */
    public ArrayList<String> ratedItems(){
    	ArrayList<String> ratedList = new ArrayList<String>();
    	File f = new File("/data/data/com.AppliedArgonautics.comfort/databases/TastingJournal");
		try{
    	SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(f, null);
		Cursor c = db.rawQuery("SELECT * FROM ItemsRated", null);
		c.moveToFirst();
		
		while (c.isAfterLast() == false) 
		{
		    ratedList.add(c.getString(0));
		    c.moveToNext();
		}
		db.close();
		}catch (SQLiteException e){
			e.printStackTrace();
		}
//		for (String str: ratedList)
//		{
//			Log.d("PEE", str);
//		}
		return ratedList;
		
    }
    
    /**
     * @param ratedItemString  The toString() value of the MenuItem 
     * 						   being parsed
     * @return 			   	   a Float Value from internal database created 
     * 						   by NotesActivity
     * @see NotesActivity
     */
    @SuppressLint("UseValueOf")
	public static Float getRating(String ratedItemString){
    	Float rating = new Float(0);
    	File f = new File("/data/data/com.AppliedArgonautics.comfort/databases/TastingJournal");
		try{
    	SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(f, null);
		Cursor c = db.rawQuery("SELECT * FROM ItemsRated", null);
		c.moveToFirst();
		
		while (c.isAfterLast() == false) 
		{
			if(c.getString(0).equals(ratedItemString)){
				rating = c.getFloat(2);		
			}
		    c.moveToNext();
		}
		db.close();
		}catch (SQLiteException e){
			e.printStackTrace();
		}
		return rating;
		
    }
    
    /**
     * @param ratedItemString the toString() value of a MenuItem that has 
     * 						  been rated, as stored in the database
     * @return				  notes from database, as entered by the user
     * @see 				  NotesActivity 					      
     */
    public static String getNotes(String ratedItemString){
    	String notes = new String();
    	File f = new File("/data/data/com.AppliedArgonautics.comfort/databases/TastingJournal");
		try{
    	SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(f, null);
		Cursor c = db.rawQuery("SELECT * FROM ItemsRated", null);
		c.moveToFirst();
		
		while (c.isAfterLast() == false) 
		{
			if(c.getString(0).equals(ratedItemString)){
				notes = c.getString(1);		
			}
		    c.moveToNext();
		}
		db.close();
		}catch (SQLiteException e){
			e.printStackTrace();
		}
		return notes;
		
    }
    

    
    InputStream barXmlInputStream;

    String tmpValue;

    Beer beerTmp;
    Liquor liquorTmp;
    Wine wineTmp;



    public MenuParser(InputStream barXmlInputStream, String itemType) {
    	this.barXmlInputStream = barXmlInputStream;
    	this.itemType = itemType;
        ItemL = new ArrayList<MenuItem>();

        parseDocument();

        //printDatas();

    }

    private void parseDocument() {

        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {

            SAXParser parser = factory.newSAXParser();

            parser.parse(barXmlInputStream, this);
            
        } catch (ParserConfigurationException e) {

            System.out.println("ParserConfig error");

        } catch (SAXException e) {

            System.out.println("SAXException : xml not well formed");

        } catch (IOException e) {

            System.out.println("IO error");

        }

    }

    @SuppressWarnings("unused")
	private void printDatas() {
        for (MenuItem tmpB : ItemL) {
            System.out.println(tmpB.toString());
        }
    }

    /* (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    @Override

    public void startElement(String s, String s1, String elementName, Attributes attributes) throws SAXException {

    	sb = new StringBuilder();

        if ((elementName.equalsIgnoreCase("liquor")) && (itemType.equals("liquor"))) {
            liquorTmp = new Liquor();
        }
        if ( (elementName.equalsIgnoreCase("beer") ) && (itemType.equals("beer") ) ){
        	beerTmp = new Beer();
        }
        if ((elementName.equalsIgnoreCase("wine")) && (itemType.equals("wine"))){
        	wineTmp = new Wine();
        }
    }

    /* (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override

    public void endElement(String s, String s1, String element) throws SAXException {

    	//parse liquor objects
        if (liquorTmp != null) {
        	
			if (element.equals("liquor")) {
				//Log.d("TYPE", liquorTmp.getType());
				ItemL.add(liquorTmp);
				liquorTmp=null;
			}
			if (element.equalsIgnoreCase("liquors")) {

				return;
			}
			if (element.equalsIgnoreCase("type")) {
				
				liquorTmp.setType(tmpValue);

			}
			if (element.equalsIgnoreCase("distiller")) {

				liquorTmp.setDistiller(tmpValue);

			}
			if (element.equalsIgnoreCase("bottling")) {

				liquorTmp.setBottling(tmpValue);
				liquorTmp.hasBottling = true;

			}
			if (element.equalsIgnoreCase("age")) {

				liquorTmp.setAge(tmpValue);

			}
			if (element.equalsIgnoreCase("price")) {

				liquorTmp.setPrice(tmpValue);

			}
			if (element.equalsIgnoreCase("proof")) {

				liquorTmp.setProof(tmpValue);

			}
			if (element.equalsIgnoreCase("place")) {

				liquorTmp.setPlace(tmpValue);
			}
			tmpValue=null;
		}
        
        
        //parse beer objects
        
        if (beerTmp != null){
        	
        	if (element.equals("beer")) {

				ItemL.add(beerTmp);
				beerTmp=null;

			}
        	if (element.equalsIgnoreCase("beers")) {

				return;

			}
   
			if ((element.equalsIgnoreCase("type")) && (beerTmp != null)) {

				beerTmp.setType(tmpValue);

			}
			if (element.equalsIgnoreCase("brewery")) {

				beerTmp.setBrewery(tmpValue);

			}
			if ((element.equalsIgnoreCase("bottling")) && (beerTmp != null)) {

				beerTmp.setBottling(tmpValue);
				beerTmp.hasBottling = true;

			}

			if ((element.equalsIgnoreCase("price")) && (beerTmp != null)) {

				beerTmp.setPrice(tmpValue);

			}

			if ((element.equalsIgnoreCase("place"))  && (beerTmp != null)){

				beerTmp.setPlace(tmpValue);

			}
        	tmpValue=null;
        	
        }
        	
        	//parse into wine objects
        	if (wineTmp != null){
        		
        		
        		if (element.equals("wine")) {

    				ItemL.add(wineTmp);
    				wineTmp=null;
    			}
        		if (element.equalsIgnoreCase("wines")){

    				return;

    			}
       
    			if ((element.equalsIgnoreCase("category")) && (wineTmp != null)) {

    				wineTmp.setType(tmpValue);

    			}
    			if ((element.equalsIgnoreCase("winery")) && (wineTmp != null)) {

    				wineTmp.setWinery(tmpValue);

    			}
    			if ((element.equalsIgnoreCase("bottling")) && (wineTmp != null)) {

    				wineTmp.setBottling(tmpValue);
    				wineTmp.hasBottling = true;

    			}

    			if ((element.equalsIgnoreCase("price")) && (wineTmp != null)) {

    				wineTmp.setPrice(tmpValue);

    			}
    			if ((element.equalsIgnoreCase("country"))  && (wineTmp != null)){

    				wineTmp.setCountry(tmpValue);

    			}
    			if ((element.equalsIgnoreCase("region"))  && (wineTmp != null)){

    				wineTmp.setRegion(tmpValue);
    				wineTmp.hasRegion = true;
    			}
    			if ((element.equalsIgnoreCase("varietals"))  && (wineTmp != null)){

    				wineTmp.setVarietals(tmpValue);

    			}
    			if ((element.equalsIgnoreCase("color"))  && (wineTmp != null)){

    				wineTmp.setColor(tmpValue);

    			}
    			if ((element.equalsIgnoreCase("vintage"))  && (wineTmp != null)){

    				wineTmp.setVintage(tmpValue);

    			}
    			if ((element.equalsIgnoreCase("world"))  && (wineTmp != null)){
    				if (tmpValue.equals("Old")){
    					wineTmp.isOldWorld = true;
    				}
    			}
        		
        	tmpValue=null;	
        	}
			
		}
        
    

    /* (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
     */
    @Override

    public void characters(char[] foundCharacters, int startIndex, int finishIndex) throws SAXException {
    	if (sb!=null) {
            for (int i=startIndex; i<startIndex+finishIndex; i++) {
                sb.append(foundCharacters[i]);
            }
        tmpValue = sb.toString();
        
        //tmpValue = new String(foundCharacters, startIndex, finishIndex);

    	}
    	sb = null;
    }
}
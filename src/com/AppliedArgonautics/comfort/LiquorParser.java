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

public class LiquorParser extends DefaultHandler {

    private ArrayList<Liquor> LiquorL;


    public ArrayList<Liquor> getItems(String ArrayType){
    	ArrayList<Liquor> tmpItems = new ArrayList<Liquor>();
    	Log.d("DEO", "Starting getItems");
    	ArrayList<String> rated = ratedItems();
    	for (Liquor men : LiquorL){
    		//loop through the rated Items, and setRating of current items from DB
    		for (int i = 0; i < rated.size(); i++)
    		{
    		if (men.toString().equals(rated.get(i))){
    		//int index = 
    		men.hasRating = true;
    		men.setRating(getRating(men.toString()));
    		Log.d("FromDB", (getRating(men.toString())).toString());
    		men.setNotes(getNotes(men.toString()));
    		Log.d("DEO", "getting rating for " +men.toString());
    			}
    		}
    		if (men.getType().equals(ArrayType)){
    		tmpItems.add(men);
    			}
    		}
    	Log.d("DEO", "getItems returned");
    	return tmpItems;
    }
    
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
		for (String str: ratedList)
		{
			Log.d("PEE", str);
		}
		return ratedList;
		
    }
    
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

    Liquor liquorTmp;




    public LiquorParser(InputStream barXmlInputStream) {

        
    	
    	this.barXmlInputStream = barXmlInputStream;

        LiquorL = new ArrayList<Liquor>();

        parseDocument();

        //printDatas();

    }

    private void parseDocument() {

        // parse

        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {

            SAXParser parser = factory.newSAXParser();

            parser.parse(barXmlInputStream, this);
            
            //parser.parse(response, parser.Encoding.UTF_8,rootElement.getContentHandler());

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


        for (Liquor tmpB : LiquorL) {

            //System.out.println(tmpB.toString());

        }
        

    }

    @Override

    public void startElement(String s, String s1, String elementName, Attributes attributes) throws SAXException {



        if (elementName.equalsIgnoreCase("liquor")) {

            liquorTmp = new Liquor();


        }


    }

    @Override

    public void endElement(String s, String s1, String element) throws SAXException {


        if (element.equals("liquor")) {

				LiquorL.add(liquorTmp);

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

			
		}
        
    

    @Override

    public void characters(char[] foundCharacters, int startIndex, int finishIndex) throws SAXException {

        tmpValue = new String(foundCharacters, startIndex, finishIndex);

    }

}

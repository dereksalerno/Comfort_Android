package com.AppliedArgonautics.comfort;



import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.AppliedArgonautics.comfort.Wine;

public class WineParser extends DefaultHandler {

    private ArrayList<Wine> WineL;

    public void printThemAll(){
    	for (Wine wine : WineL){
    		System.out.println("wine: " + wine.getWinery());
    		System.out.println("type: " + wine.getType());
    		System.out.println("color: " + wine.getColor());
    	}
    }
    public ArrayList<Wine> getItems(String color, String category){
    	ArrayList<Wine> tmpItem = new ArrayList<Wine>();
    	for (Wine wine : WineL){
    		if ((wine.getColor().equals(color))&&(wine.getType().equals(category))){
    		tmpItem.add(wine);
    			}
    		}
    	return tmpItem;
    }

    
    InputStream barXmlInputStream;

    String tmpValue;

   
    Wine wineTmp;
   

    //SimpleDateFormat sdf= new SimpleDateFormat("yy-MM-dd");

    public WineParser(InputStream barXmlInputStream) {

        
    	
    	this.barXmlInputStream = barXmlInputStream;

        WineL = new ArrayList<Wine>();
        
        parseDocument();

        printDatas();

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

        	e.printStackTrace();
            System.out.println("SAXException : xml not well formed");

        } catch (IOException e) {

            System.out.println("IO error");

        }

    }

    private void printDatas() {

        for (Wine tmpW : WineL) {

            //System.out.println(tmpW.toString());
            //System.out.println(tmpW.getType());
        }
        

    }

    @Override

    public void startElement(String s, String s1, String elementName, Attributes attributes) throws SAXException {



        if (elementName.equalsIgnoreCase("wine")) {

        	
        	wineTmp = new Wine();
            System.out.println("Instantiating a wine object!");

      

        }

    }

    @Override

    public void endElement(String s, String s1, String element) throws SAXException {

    	
        
    	    if (element.equals("wine")) {

				WineL.add(wineTmp);

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
		}	
			
	
        
    

    @Override

    public void characters(char[] ac, int i, int j) throws SAXException {

        tmpValue = new String(ac, i, j);

    }


}
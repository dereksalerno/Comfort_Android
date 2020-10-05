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

import com.AppliedArgonautics.comfort.Beer;


public class BeerParser extends DefaultHandler {

    private ArrayList<Beer> BeerL;

    public ArrayList<Beer> getItems(String ArrayType){
    	ArrayList<Beer> tmpItem = new ArrayList<Beer>();
    	for (Beer beer : BeerL){
    		if (beer.getType().equals(ArrayType)){
    		tmpItem.add(beer);
    			}
    		}
    	return tmpItem;
    }

    
    InputStream barXmlInputStream;

    String tmpValue;

   
    Beer beerTmp;
   

    //SimpleDateFormat sdf= new SimpleDateFormat("yy-MM-dd");

    public BeerParser(InputStream barXmlInputStream) {

        
    	
    	this.barXmlInputStream = barXmlInputStream;

        BeerL = new ArrayList<Beer>();

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

       // System.out.println(bookL.size());

        for (Beer tmpB : BeerL) {

            System.out.println(tmpB.toString());

        }
        

    }

    @Override

    public void startElement(String s, String s1, String elementName, Attributes attributes) throws SAXException {

        // if current element is book , create new book

        // clear tmpValue on start of element
/*    	if (elementName.equalsIgnoreCase("liquor")){
    		return;
    	}
 */

        if (elementName.equalsIgnoreCase("beer")) {

        	
        	beerTmp = new Beer();
            
            //pastTheLiquor = true;

      

        }

    }

    @Override

    public void endElement(String s, String s1, String element) throws SAXException {

    	
        
    	if (element.equals("beer")) {

				BeerL.add(beerTmp);

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
        }
			
	
        
    

    @Override

    public void characters(char[] ac, int i, int j) throws SAXException {

        tmpValue = new String(ac, i, j);

    }


}
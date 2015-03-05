package FBCrawl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.Date;
import java.util.ListIterator;

import com.sun.jmx.snmp.Timestamp;

import facebook4j.*;
import facebook4j.conf.ConfigurationBuilder;

public class facebook4J{
    private static final long serialVersionUID = -7453606094644144082L;

    public void IDretrieval(String query) throws FileNotFoundException, UnsupportedEncodingException, FacebookException {
      
       
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthAppId("657861844318963")
          .setOAuthAppSecret("52ff9596f18ed0ee9ae7992fee481246")
          .setOAuthAccessToken("CAACEdEose0cBAOJOI3TWoGXUwG2FKZCpYKOLCksO5Hoilf4ZCXrf1TEcB2ZB8w6mNT4ACSZCXkO6nP6vhdnTZCwL4zxIF4VnRhbXZBuePfK5fjn4ui8W9svoQhrvXPx3ymuzAEaFsFxlDXQBbMAhGtZBlFDTligKoXRf17pLIb1B5RH66yZBPLz5QqbI5ZCitGFTF6CVE52QjyOlLzyUWpsp7hqOLJZAXZAkCcZD")
          .setOAuthPermissions("email,publish_stream");
        FacebookFactory ff = new FacebookFactory(cb.build());
        Facebook facebook = ff.getInstance();
        
    	int lowerBoundary = 1325462400;
		int upperBoundary = lowerBoundary + 86400;
		ResponseList<Event> results;
		String lowerBoundaryString;
		String upperBoundaryString;
		int help = 0;
		int i=0;
		long tsEvent = 0;
		int EventCounter = 0;
		
		String file_name= query+".txt";
		PrintWriter writer = new PrintWriter(file_name, "UTF-8");
		
		while(lowerBoundary <= 1423008000 ){
			 lowerBoundaryString = Integer.toString(lowerBoundary);
			 //upperBoundaryString = Integer.toString(upperBoundary);
			 
	   System.out.println("timestamp: "+lowerBoundaryString);
        results = facebook.searchEvents(query, new Reading().since(lowerBoundaryString));
        boolean a = true;
        System.out.println(results.isEmpty());
        System.out.println(results.size());
        System.out.println(results.getCount());
        if( a == results.isEmpty()){
        	System.out.println("we're screwed");
        }
        else {;
        
        
        //My creation
        int i2=0;
        while(i2<results.size())
        {
        	String resultID = results.get(i2).getId();
        	System.out.println(resultID);
        	writer.println(resultID);
        	i2++;
        	EventCounter++;
        }
        
        java.util.Date lastEvent = results.get((i2-1)).getStartTime();
    
        tsEvent = new Long(lastEvent.getTime()/1000L);
        System.out.println("last timestamp:"+tsEvent);
        
        
        
        
        
        
        
        
       // int counter = 0;

      
//        while( counter < 4){ 
//      
//        writer.println(results.get(counter).getId());
//        System.out.println(results.get(counter).getId());
//        //ListIterator<Event> list = results.listIterator();
//        counter++;
//        }
   
        }
        i++;
    
        lowerBoundary = (int)tsEvent;
        //upperBoundary = lowerBoundary + 86400;
        help++;
        }
		writer.println("Number of ids:" +EventCounter);
		writer.close();
		
		
//        int i = results.getCount();
     
//int i = 0;

//     while (i < results.size()){
//    	 eventID = results.get(i).getId();
//    	 System.out.println(eventID);
//    	 i++;
//     }
     System.out.println("page1done");
     System.out.println(EventCounter);
     
//      Paging<Event> page1 = results.getPaging();
////      ResponseList<Event> page2 = facebook.fetchNext(page1);
//      ResponseList<Event> page0 = facebook.fetchPrevious(page1);
//      
////      i = 0;
////      while (i < page2.size()){
////     	 eventID = page2.get(i).getId();
////     	 System.out.println(eventID);
////     	 i++;
////      }
//      i = 0;
//      while (i < page0.size()){
//      	 eventID = page0.get(i).getId();
//      	 System.out.println(eventID);
//      	 i++;
//       }
//      System.out.println("page0done");
               
    }
}
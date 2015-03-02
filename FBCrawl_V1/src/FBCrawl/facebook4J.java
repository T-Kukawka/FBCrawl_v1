package FBCrawl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ListIterator;

import facebook4j.*;
import facebook4j.conf.ConfigurationBuilder;

public class facebook4J{
    private static final long serialVersionUID = -7453606094644144082L;

    public void IDretrieval() throws FileNotFoundException, UnsupportedEncodingException, FacebookException {
      
       
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthAppId("657861844318963")
          .setOAuthAppSecret("52ff9596f18ed0ee9ae7992fee481246")
          .setOAuthAccessToken("CAACEdEose0cBAN69kwZChFAYrBhtLzZAiz9OyuLfQkguTpF6CUeSqRadPmzIOilqZA7qa8NxjcOID3kIP9ZCSpXuuGOmH5JvBBuXkGtBA943ZCSbSZCuT5Qlw0JgS6qX1bKIrWCJk8TzPZAJHwxPZBO2uX0mtfxkHqfEhJEz4F1VHkUqpOUzYaRTATBTs1pALqfsZCRrV0dRrbZB8pRTd4IdaoMDKx00hiWO4ZD")
          .setOAuthPermissions("email,publish_stream");
        FacebookFactory ff = new FacebookFactory(cb.build());
        Facebook facebook = ff.getInstance();
        
    	int lowerBoundary = 1424908800;
		int upperBoundary = lowerBoundary + 604800;
		ResponseList<Event> results;
		String lowerBoundaryString;
		String upperBoundaryString;
		int help = 0;
		int i=0;
		
		PrintWriter writer = new PrintWriter("EventIDs.txt", "UTF-8");
		
		while(lowerBoundary < 1425440000 ){
			 lowerBoundaryString = Integer.toString(lowerBoundary);
			 upperBoundaryString = Integer.toString(upperBoundary);
        results = facebook.searchEvents("Amsterdam");
        System.out.println(results.get(i).getId());
        writer.println(results.get(i).getId());

        //ListIterator<Event> list = results.listIterator();
        String eventID;
        i++;
        lowerBoundary = upperBoundary;
        upperBoundary = lowerBoundary + 604800;
        help++;
        }
		
		writer.close();
		
		System.out.println(help);
//        int i = results.getCount();
     
//int i = 0;

//     while (i < results.size()){
//    	 eventID = results.get(i).getId();
//    	 System.out.println(eventID);
//    	 i++;
//     }
     System.out.println("page1done");
     
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
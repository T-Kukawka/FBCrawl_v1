package FBCrawl;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import facebook4j.Event;
import facebook4j.Place;
import facebook4j.Place.Location;
import facebook4j.Venue;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.conf.ConfigurationBuilder;
import facebook4j.internal.org.json.JSONObject;

public class facebook4J{
    private static final long serialVersionUID = -7453606094644144082L;
    Integer[] idList = new Integer[10000];
    
    public Integer[] returnList(){
    	return idList;
    }
    
    public void IDretrieval(String query, long timeFrom, long timeTo) throws FileNotFoundException, UnsupportedEncodingException, FacebookException {
      
       
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthAppId("657861844318963")
          .setOAuthAppSecret("52ff9596f18ed0ee9ae7992fee481246")
          .setOAuthAccessToken("CAACEdEose0cBAGWLkuPscZADTV4cr5bZBl4Jhx2jygtKaWZCA8iZAE4ZBwO38RFtZAquYEL7ZCc7hlcCrZCGdKBNHRZBR2HBO80o3qdslBS82LevOzxoz32nQ75PiqTmcZA1FaowWN2ZBZCDad00YgF5sZAZBWciw3gg6ZBugm3sslvvmHSF5EsZClGasWc8cUeY93E7udLZBYdM5dUqxFgDZBgv6OeRsm7VhmM2YXw98uXyeEZAswZCXAZDZD")
          .setOAuthPermissions("email,publish_stream");
        FacebookFactory ff = new FacebookFactory(cb.build());
        Facebook facebook = ff.getInstance();
        
        String location = null;
        Location Secondlocation = null;
        String PlaceLocation = null;
        
    	int lowerBoundary = (int) timeFrom;
    	int lastBoundary = 0;
		int endDate = (int) timeTo;
		ResponseList<Event> results;
		ResponseList<Place> results2;
		Double city;
		String country;
		String lowerBoundaryString;
		String ImplEndDate;
		int help = 0;
		int i=0;
		long tsEvent = 0;
		int EventCounter = 0;
		java.util.Date newlastEventDate = null;
		long newlastEvent = 0;
		long oldlastEvent = 0;
		String file_name= query+timeFrom+timeTo+".txt";
		PrintWriter writer = new PrintWriter(file_name, "UTF-8");
		
		while(endDate > lowerBoundary){
			if(lowerBoundary == lastBoundary){
				System.out.println("end");
				break;
			}else{
			System.out.println("end:" +endDate);
			System.out.println("lower:"+lowerBoundary);
			ImplEndDate = Integer.toString(endDate);
			// upperBoundaryString = Integer.toString(upperBoundary);
			 
			 System.out.println("timestamp: "+ImplEndDate);
		        results = facebook.searchEvents(query, new Reading().until(ImplEndDate));
		        
        boolean a = true;

        if( a == results.isEmpty()){
        	System.out.println("we're screwed");
        	break;
        }
        else {
        
        Boolean isLocation = true;
    	
       
        //My creation
        int i2=0;
        while(i2<results.size())
        {	
        	String CountryLocation = "";
        	String resultID = results.get(i2).getId();
        	idList[i2]=Integer.parseInt(resultID);
        	
        try{
        
        		location = results.get(i2).getLocation();
        		
        		if(location!=null){
        		results2 = facebook.searchPlaces(location);
            			
        		PlaceLocation = results2.get(0).getLocation().getCity();
    			CountryLocation = results2.get(0).getLocation().getCountry();
        			if(CountryLocation!=null){
		            	if(!CountryLocation.contains("")&&!CountryLocation.equals("Netherlands")){
		            		isLocation =false;
		            	}else{
		            		isLocation = true;	
		            	}
            		}else if(PlaceLocation!=null){
            			if(!PlaceLocation.contains("")&&!PlaceLocation.contains(query)){
		            		isLocation =false;
		            	}else{
		            		isLocation = true;
		            	}
            		}
            	}
            	else{
        			isLocation = false;
        		}
            	
            	
        		
        	}catch(IndexOutOfBoundsException e){
        		System.err.println();
        	}
        	if(isLocation){
        		
        		System.out.println(resultID+" "+location+" "+PlaceLocation);
            	writer.println(resultID);
            		
        	}
   
        	i2++;
        	EventCounter++;
        }
        
        
        Date lastEvent = results.get(i2-1).getStartTime();
        tsEvent = new Long(lastEvent.getTime()/1000);
        System.out.println("last timestamp:"+tsEvent);
        writer.println("last timestamp :"+tsEvent);
        endDate = (int) tsEvent;
        
        }
        System.out.println("done");
        writer.println("Number of ids:" +EventCounter);
		writer.close();
        
        i++;
        
        lastBoundary=lowerBoundary;
        //upperBoundary = lowerBoundary + 86400;
        help++;
        }
		
		}
               
    }
}
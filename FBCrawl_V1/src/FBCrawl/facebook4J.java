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
    Long[] idList = new Long[50000];
    
    public Long[] returnList(){
    	return idList;
    }
    
    public void EventsRetrieval(String[] search_term, String[] search_term2) throws FacebookException{
    	 ConfigurationBuilder cb = new ConfigurationBuilder();
         cb.setDebugEnabled(true)
           .setOAuthAppId("657861844318963")
           .setOAuthAppSecret("52ff9596f18ed0ee9ae7992fee481246")
           .setOAuthAccessToken("CAACEdEose0cBAPpr5PEKLJ573uEni8VGmhEIngZAO0BG8gFZADdVY1vI7IRve20UlWQZATWZAP5TCLM1s77HniT1rhuU2BzksXhIppabxIBbOci5hUD4vgDUn0Y8kZCDEtAEGEhgOI1tNcvOfSZCsa47SKGb0JbihHCfTKL8hBZBivEcfaUQjqlSb8wR0e3fZBLewAKezwPU2a6veyCDRWI3GIjmlMvNrvlfkQEyI8ul0wZDZD")
           .setOAuthPermissions("email,publish_stream");
         FacebookFactory ff = new FacebookFactory(cb.build());
         Facebook facebook = ff.getInstance();
         
         String query = search_term[0]+" "+search_term2[0];
    
    	
    	int counter = facebook.searchEvents(query).size();
    	
         System.out.println("Facebook event count: " +counter);
    	
    }
    
    public void IDretrieval(String query, long timeFrom, long timeTo) throws FileNotFoundException, UnsupportedEncodingException, FacebookException {
      
       
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthAppId("657861844318963")
          .setOAuthAppSecret("52ff9596f18ed0ee9ae7992fee481246")
          .setOAuthAccessToken("CAACEdEose0cBAPpr5PEKLJ573uEni8VGmhEIngZAO0BG8gFZADdVY1vI7IRve20UlWQZATWZAP5TCLM1s77HniT1rhuU2BzksXhIppabxIBbOci5hUD4vgDUn0Y8kZCDEtAEGEhgOI1tNcvOfSZCsa47SKGb0JbihHCfTKL8hBZBivEcfaUQjqlSb8wR0e3fZBLewAKezwPU2a6veyCDRWI3GIjmlMvNrvlfkQEyI8ul0wZDZD")
          .setOAuthPermissions("email,publish_stream");
        FacebookFactory ff = new FacebookFactory(cb.build());
        Facebook facebook = ff.getInstance();
        
        String query2 = query.toUpperCase();
        
        String location = null;
        Location Secondlocation = null;
        String PlaceLocation = null;
        
        int indexFB=0;
        int indexEvEx=0;
    	int lowerBoundary = (int) timeFrom;
    	int lastBoundary = 0;
		int endDate = (int) timeTo;
		ResponseList<Event> results;
		ResponseList<Place> results2;
		ResponseList<Event> results3;
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
		writer.println(query);
		
		while(endDate > lowerBoundary){
			if(lowerBoundary == lastBoundary){
				//System.out.println("end");
				break;
			}else{
			//System.out.println("end:" +endDate);
			//System.out.println("lower:"+lowerBoundary);
			ImplEndDate = Integer.toString(endDate);
			// upperBoundaryString = Integer.toString(upperBoundary);
			 
			 //System.out.println("timestamp: "+ImplEndDate);
		        results = facebook.searchEvents(query, new Reading().until(ImplEndDate));
		        results3 = facebook.searchEvents(query);
		        
		        
        boolean a = true;

        if( a == results.isEmpty() ){
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
        	idList[i2]=Long.parseLong(resultID);
        	
        try{
        
        		String location2 = results.get(i2).getLocation();
        		
        		if(location2!=null){
        		location = location2.toUpperCase();
        		}else{
        			location=location2;
        		}
        		if(location2!=null && !location.contains(query2)){
        		 
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
            			if(!PlaceLocation.contains("")&&!PlaceLocation.contains(query2)){
		            		isLocation =false;
		            	}else{
		            		isLocation = true;
		            	}
            		}
            	}
            	else if(location!=null && location.contains(query2)  ){
        			isLocation = true;
        			
        		}else{
        			isLocation = false;
        		}
            	
            	
        		
        	}catch(IndexOutOfBoundsException e){
        		//System.err.println();
        	}
        	if(isLocation){
        		
        		//System.out.println(resultID+" "+location+" "+location.contains(query2));
        		indexEvEx++;
            	writer.println(resultID);		
        	}
        	
        	i2++;
        	
        }
        
        
        
        Date lastEvent = results.get(i2-1).getStartTime();
        tsEvent = new Long(lastEvent.getTime()/1000);
        
        writer.println("last timestamp :"+tsEvent);
        endDate = (int) tsEvent;
        
        }
        
        System.out.println("---------------------KPI 1.--------------------------------------");
        System.out.println("Number of events with location filter: "+indexEvEx);
        EventCounter=results3.size();
        System.out.println("Number events returned by facebook api:" +EventCounter);
        
        float kpi1 = ((float)indexEvEx/EventCounter);
        String str = String.format("%2.04f", kpi1);
        System.out.println("KPI value:" + str);
        
        
        
        
        
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
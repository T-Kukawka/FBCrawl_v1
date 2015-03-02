package FBCrawl;

import facebook4j.Event;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Paging;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.conf.ConfigurationBuilder;

public class FacebookJAPI {

	public static void main(String[] args) throws FacebookException {		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthAppId("657861844318963")
          .setOAuthAppSecret("52ff9596f18ed0ee9ae7992fee481246")
          .setOAuthAccessToken("CAACEdEose0cBAN69kwZChFAYrBhtLzZAiz9OyuLfQkguTpF6CUeSqRadPmzIOilqZA7qa8NxjcOID3kIP9ZCSpXuuGOmH5JvBBuXkGtBA943ZCSbSZCuT5Qlw0JgS6qX1bKIrWCJk8TzPZAJHwxPZBO2uX0mtfxkHqfEhJEz4F1VHkUqpOUzYaRTATBTs1pALqfsZCRrV0dRrbZB8pRTd4IdaoMDKx00hiWO4ZD")
          .setOAuthPermissions("email,publish_stream");
		FacebookFactory ff = new FacebookFactory(cb.build());
		Facebook facebook = ff.getInstance();

		ResponseList<Event> results = facebook.searchEvents("amsterdam",new Reading().since("1422748800").until("1422835200"));
		
		int i = 0,i2 = 0;
		String event,event2;
		while(i < results.size()){
			event = results.get(i).getId();
			System.out.println(event);
			
			i++;
		}
		System.out.println(results.size());
		

		// Getting Previous page
		Paging<Event> paging2 = results.getPaging();
		ResponseList<Event> page1 = facebook.fetchPrevious(paging2);
		System.out.println(page1);
		while(i2 < page1.size()){
			event2 = page1.get(i2).getId();
		
			System.out.println(event2);
			
			i2++;
		}

		

	}

}

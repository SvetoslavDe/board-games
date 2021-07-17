package home.crow.boardgames.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraper {

	public static void scrape() {
		for(int i=1; i<1288; i++) {
			String page = i + "";
			final String url = "https://boardgamegeek.com/browse/boardgame/page/"+ page;
			
			try {
				Document document = Jsoup.connect(url).get();
				
				for (Element row : document.select("table.collection_table tr")) {
					for (Element td : row.select("td.collection_objectname.browse")) {
							String ahref = td.select("div").select("a.primary").attr("href");
							String id = ahref.substring(11,ahref.lastIndexOf("/"));
							System.out.println(td.select("div").text()+ " " + id);
							
						}
				
					
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		
		
		}
	}
}

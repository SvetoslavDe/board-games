package home.crow.boardgames.scraper;

import java.util.*;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import home.crow.boardgames.model.Game;

public class Scraper {
	
	private final List<Game> games = new ArrayList<>();
	
	public List<Game> scrape() {
		
		
	for(int i=1; i<1288; i++) {
		String page = i + "";
		final String url = "https://boardgamegeek.com/browse/boardgame/page/"+ page;
			
			try {
				Document document = Jsoup.connect(url).get();
				
				for (Element row : document.select("table.collection_table tr")) {
					for (Element td : row.select("td.collection_objectname.browse")) {
							String ahref = td.select("div").select("a.primary").attr("href");
							String idString = ahref.substring(11,ahref.lastIndexOf("/"));
							long id = Long.parseLong(idString);
							String title = td.select("div").text();
							Game newGame = new Game(id, title);
							games.add(newGame);											
						}	
									}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
			return games;
	}
}

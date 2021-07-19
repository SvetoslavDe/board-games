package home.crow.boardgames.scraper;

import java.util.*;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import home.crow.boardgames.database.GameRepository;
import home.crow.boardgames.model.Game;

@Service
public class Scraper {
	
	@Autowired
	private GameRepository gameRepository;
	
	public Game scrapeById(Long id) {
		final String url = "https://boardgamegeek.com/boardgame/" + id;
		WebClient client = new WebClient();
		client.getOptions().setJavaScriptEnabled(true);
		Document document=null;
		try {
			HtmlPage myPage = client.getPage(url);
			document = Jsoup.parse(myPage.asXml());	
		} catch(Exception e) {
			e.printStackTrace();
		} finally {client.close();}
		String title = document.select("h1 > a.ng-binding").text();
		int year = Integer.parseInt(document.select("span.game-year").text().substring(1,5));
		Game game = new Game(id, title, year);
		gameRepository.save(game);
		return game;
		
		}
	
	public static List<Game> scrape() {
		List<Game> games = new ArrayList<>();
		
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
								String title = td.select("div").text().substring(0,td.select("div").text().lastIndexOf("(")-1);
								int year =Integer.parseInt(td.select("div").text().substring(td.select("div").text().lastIndexOf("(")+1,
										td.select("div").text().lastIndexOf(")")));
								Game newGame = new Game(id, title, year); 
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

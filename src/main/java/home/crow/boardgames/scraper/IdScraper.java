package home.crow.boardgames.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import home.crow.boardgames.model.Game;

public class IdScraper {
	private long id;
	private WebClient client = new WebClient();
	private Document document;
	private String url = "https://boardgamegeek.com/boardgame/";
	
	public IdScraper (Long id) {
		this.id = id;
	}
	public void setDocument() {
		try {
			HtmlPage myPage = client.getPage(url+id);
			document = Jsoup.parse(myPage.asXml());	
		} catch(Exception e) {
			e.printStackTrace();
		} finally {client.close();}
	}
	public String scrapeTitle() {
		return document.select("h1 > a.ng-binding").text();
	}
	public int scrapeYear() {
		return Integer.parseInt(document.select("span.game-year").text().substring(1,5));
	}
	public Game scrape() {
		setDocument();
		return new Game(id, scrapeTitle(), scrapeYear());	
		}
}

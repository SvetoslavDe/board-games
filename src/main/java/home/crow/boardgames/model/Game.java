package home.crow.boardgames.model;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;



@Entity
public class Game {
	
	@Id
	private long id;
	private String title;
	@Transient
	private List<Link> links = new ArrayList<>();
	
	public Game() {}
	
	public Game(long id, String title) {
		super();
		this.id = id;
		this.title = title;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public void addLinks(String url, String rel) {
		Link link = new Link();
		link.setRel(rel);
		link.setLink(url);
		links.add(link);
		
	} 
	
}

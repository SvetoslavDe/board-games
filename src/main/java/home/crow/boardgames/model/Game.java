package home.crow.boardgames.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;



@Entity
public class Game {
	
	@Id
	private long id;
	private String title;
	private int year;
	private Timestamp timestamp;
	@Transient
	private List<Link> links = new ArrayList<>();
	
	public Game() {}
	
	public Game(long id, String title, int year) {
		super();
		this.id = id;
		this.title = title;
		this.year = year;
		this.timestamp = Timestamp.valueOf(LocalDateTime.now());
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
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	
	
	public Timestamp getTimestamp() {
		return timestamp;
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

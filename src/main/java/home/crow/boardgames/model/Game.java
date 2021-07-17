package home.crow.boardgames.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Game {
	
	@Id
	private long id;
	private String title;
	
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
	
	
}

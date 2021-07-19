package home.crow.boardgames.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import home.crow.boardgames.model.*;
import home.crow.boardgames.scraper.Scraper;
import home.crow.boardgames.database.GameRepository;

@Service
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;
	@Autowired
	Scraper scraper;
	@Value("${days}")
	long days;
	
	public List<Game> getGames() {
		List<Game> games = new ArrayList<>();
		gameRepository.findAll().forEach(games::add);
		return games;
	}
	
	public List<Game> updateGames() {
		List<Game> addedGames = new ArrayList<>();
		gameRepository.saveAll(Scraper.scrape()).forEach(addedGames::add);		
		return addedGames;
	}
	
	public void deleteGames() {
		gameRepository.deleteAll();		
	}
	public List<Game> getGameByTitle(String title){
		List<Game> games = new ArrayList<>();
		gameRepository.findByTitle(title).forEach(games::add);
		return games;
	}	
	public Game getGameById(long id) {
		Optional<Game> result = gameRepository.findById(id);
		if(result.isPresent() && 
		LocalDate.now().isBefore(result.get().getDateAdded().plusDays(days))){
			return result.get();	
		} else {
			return scraper.scrapeById(id);
		}
	}
	
}

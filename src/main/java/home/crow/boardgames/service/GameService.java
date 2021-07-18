package home.crow.boardgames.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import home.crow.boardgames.model.*;
import home.crow.boardgames.scraper.Scraper;
import home.crow.boardgames.database.GameRepository;

@Service
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	public List<Game> getGames() {
		List<Game> games = new ArrayList<>();
		gameRepository.findAll().forEach(games::add);
		return games;
	}
	
	public List<Game> updateGames() {
		Scraper scraper = new Scraper();
		List<Game> addedGames = new ArrayList<>();
		gameRepository.saveAll(scraper.scrape()).forEach(addedGames::add);		
		return addedGames;
	}
	
	public void deleteGames() {
		gameRepository.deleteAll();		
	}
	
	public Game getGameById(long id) {
		return gameRepository.findById(id).get();		
	}
//	public Game getGameByTitle(String title) {
//		return gameRepository.findAllByTitle
//	}

}

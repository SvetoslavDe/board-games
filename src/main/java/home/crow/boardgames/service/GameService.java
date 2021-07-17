package home.crow.boardgames.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import home.crow.boardgames.model.*;
import home.crow.boardgames.database.GameRepository;

@Service
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	public Iterable<Game> getGames() {
		return gameRepository.findAll();
		
	}

}

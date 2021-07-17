package home.crow.boardgames.controller;
import home.crow.boardgames.model.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import home.crow.boardgames.database.GameRepository;

@RestController
@RequestMapping("/games")
public class GameController {
	
	@Autowired
	GameRepository gameRepository;
	
	@GetMapping
	public Iterable<Game> getGames(){
		return gameRepository.findAll();
	}
	
	

}

package home.crow.boardgames.controller;
import home.crow.boardgames.model.*;
import home.crow.boardgames.service.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/games")
public class GameController {
	
	@Autowired
	GameService gameService;
	
	@GetMapping
	public Iterable<Game> getAllGames(){
		return gameService.getGames();
	}
	
	

}

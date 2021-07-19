package home.crow.boardgames.controller;
import home.crow.boardgames.model.*;
import home.crow.boardgames.service.GameService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/games")
public class GameController {
	
	@Autowired
	GameService gameService;
	
	@GetMapping
	public List<Game> getAllGames(HttpServletRequest hsr, @RequestParam(required = false) String title){
		if (title ==null) {
			List<Game> allGames = gameService.getGames();
			for(Game game: allGames) {
			game.addLinks(hsr.getRequestURL().toString() + "/" + game.getId(), "self");
			}
			return allGames;
		} else {
			List<Game> allGames = gameService.getGameByTitle(title);
			for(Game game: allGames) {
			game.addLinks(hsr.getRequestURL().toString() + "/" + game.getId(), "self");
			}
			return allGames;
		}
		
	}
	
	@GetMapping("/{gameId}")
	public Game getGameById(@PathVariable long gameId, HttpServletRequest hsr) {
		Game game = gameService.getGameById(gameId);
		game.addLinks(hsr.getRequestURL().toString(), "self");
		return game;
	}
	
	@GetMapping("/update")
	public List<Game> updateGameDB() {
		gameService.deleteGames();
		return gameService.updateGames();
	}
	

	
//	@GetMapping("/{gameTitle}")
//	public Game getGameByTitle(@PathVariable String gameTitle) {
//		
//		
//	}
	
//	@GetMapping("/test")
//	public String test(HttpServletRequest hsr) {
//		return hsr.getRequestURL().toString();
//	}

}

package com.example.game;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("games")
public class GameController {
	private static Logger logger = LoggerFactory.getLogger(GameController.class);
	private List<Game> gamesDb = new ArrayList<>();

	@Autowired
	GameRaitingService gameRaitingService;

	@GetMapping("")
	public List<Game> showAllGames() {
		logger.info("----------------------> Entering /all");
		return gamesDb;
	}

	@GetMapping("/rating")
	public int getRating(@RequestParam String gameName) {
		return gameRaitingService.getRating(gameName);
	}

	@PostMapping("")
	public Game addGame(@RequestBody Game game) {

		Long id = (long) gamesDb.size() + 1;
		Game newGame = new Game(id, game.getName(), game.getCategory());

		boolean success = gamesDb.add(newGame);
		return success ? newGame : null;

	}

	@DeleteMapping("/{id}")
	public Game deleteGame(@PathVariable String id) {
		Game game = null;
		try {
			game = findByIdInternal(Long.parseLong(id));
		} catch (GameNotFoundException e) {
			e.printStackTrace();
		}

		boolean success = gamesDb.remove(game);
		return success ? game : null;
	}

	@PutMapping("")
	public Game updateUser(@RequestBody Game game) {
		Game updatedUser = new Game(game.getId(), game.getName(), game.getCategory());
		Game toUpdate = findByIdInternal(game.getId());

		return gamesDb.set(gamesDb.indexOf(toUpdate), updatedUser);
	}

	@GetMapping("/{id}")
	public Game findUser(@PathVariable String id) {

		return findByIdInternal(Long.parseLong(id));
	}

	public void populateDb() {
		Game game1 = new Game(1L, "Leauge of Legends", "MOBA");
		Game game2 = new Game(2L, "Hades", "Roguelike");
		Game game3 = new Game(3L, "Warcraft 3", "RTS");
		gamesDb.add(game1);
		gamesDb.add(game2);
		gamesDb.add(game3);
		System.out.println(gamesDb.size());
	}

	private Game findByIdInternal(Long id) {
		Game found = null;
		for (Game game : gamesDb) {
			if (game.getId() == id) {
				found = game;
			}
		}

		if (found == null) {
			throw new GameNotFoundException();
		}
		return found;
	}
}

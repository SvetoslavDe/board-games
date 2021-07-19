package home.crow.boardgames.database;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import home.crow.boardgames.model.Game;

@Repository
public interface GameRepository extends CrudRepository<Game, Long>{
	public List<Game> findByTitle(String title);
}

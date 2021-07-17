package home.crow.boardgames.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import home.crow.boardgames.model.Game;

@Repository
public interface GameRepository extends CrudRepository<Game, Long>{

}

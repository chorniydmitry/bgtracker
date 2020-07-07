package chernyj.hsbgtracker.service;

import java.util.Date;
import java.util.List;

import chernyj.hsbgtracker.dao.GameDao;
import chernyj.hsbgtracker.dao.ResultDao;
import chernyj.hsbgtracker.dao.impl.GameDatabaseDao;
import chernyj.hsbgtracker.dao.impl.ResultDatabaseDao;
import chernyj.hsbgtracker.entity.Result;
import chernyj.hsbgtracker.entity.Game;
import chernyj.hsbgtracker.entity.Hero;
import chernyj.hsbgtracker.entity.User;

public class GameService {
	private GameDao dao = new GameDatabaseDao();

	public void saveResults(User user, Hero hero, Date date, int place) {
		// TODO Auto-generated method stub
		
	}

	public void save(Game game) {
		dao.add(game);
	}
	
	public Game getById(long id) {
		return dao.getById(id);
	}
	
	public boolean exists(Game game) {
		return dao.exists(game);
	}
	
	public List<Game> getAll() {
		return dao.getAll();
	}

}

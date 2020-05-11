package chernyj.hsbgtracker.service;

import java.util.Date;

import chernyj.hsbgtracker.dao.GameDao;
import chernyj.hsbgtracker.dao.impl.GameDatabaseDao;
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
	
	public Game getByTimes(Date timeStart, Date timeFinish) {
		return dao.getByTimes(timeStart, timeFinish);
	}

	public Game getById(long id) {
		return dao.getById(id);
	}
	
	public boolean exists(Game game) {
		return dao.exists(game);
	}

}

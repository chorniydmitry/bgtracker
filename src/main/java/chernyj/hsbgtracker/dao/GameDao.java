package chernyj.hsbgtracker.dao;

import java.util.Date;

import chernyj.hsbgtracker.entity.Game;
import chernyj.hsbgtracker.entity.Hero;
import chernyj.hsbgtracker.entity.User;

public interface GameDao extends ItemDao<Game> {
	
	public void saveResults(User user, Hero hero, Date date, int place);

	public Game getByTimes(Date timeStart, Date timeFinish);
	
	public boolean exists(Game game);

}

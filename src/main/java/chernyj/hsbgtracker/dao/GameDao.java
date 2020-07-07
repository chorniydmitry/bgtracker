package chernyj.hsbgtracker.dao;

import java.util.Date;

import chernyj.hsbgtracker.entity.Game;

public interface GameDao  extends ItemDao<Game> {
	
	public Game getByTimes(Date timeStart, Date timeFinish);

	public boolean exists(Game game);

}

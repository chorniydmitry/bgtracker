package chernyj.hsbgtracker.dao;

import java.util.Date;

import chernyj.hsbgtracker.entity.Result;
import chernyj.hsbgtracker.entity.Hero;
import chernyj.hsbgtracker.entity.User;

public interface ResultDao extends ItemDao<Result> {
	
	public void saveResults(User user, Hero hero, Date date, int place);

	public boolean exists(Result game);

}

package chernyj.hsbgtracker.service;

import chernyj.hsbgtracker.dao.UserDao;
import chernyj.hsbgtracker.dao.impl.UserDatabaseDao;
import chernyj.hsbgtracker.entity.User;

public class UserService {
	UserDao dao = new UserDatabaseDao();
	
	public User getByNameAndBTag(String name, int bTag) {
		return dao.getByNameAndBTag(name, bTag);
		
	}

}

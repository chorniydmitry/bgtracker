package chernyj.hsbgtracker.dao;

import chernyj.hsbgtracker.entity.User;

public interface UserDao  extends ItemDao<User> {

	public User getByNameAndBTag(String name, int bTag);

}

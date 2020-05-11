package chernyj.hsbgtracker.dao;

import java.util.List;

import chernyj.hsbgtracker.entity.Hero;

public interface HeroDao extends ItemDao<Hero> {
	public List<Hero> getByHsId(int startPos, int endPos, String hsId);
	public Hero getByHsId(String hsId);

}

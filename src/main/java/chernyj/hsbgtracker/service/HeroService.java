package chernyj.hsbgtracker.service;

import java.util.List;

import chernyj.hsbgtracker.dao.HeroDao;
import chernyj.hsbgtracker.dao.impl.HeroDatabaseDao;
import chernyj.hsbgtracker.entity.Hero;

public class HeroService {
	
	HeroDao dao = new HeroDatabaseDao();
	
	public void add(Hero hero) {
		dao.add(hero);
	}
	
	public void addAll(List<Hero> heroes) {
		heroes.forEach(h->add(h));
	}
	
	public Hero getByHsId(String hsId) {
		return dao.getByHsId(hsId);
	}
	
	public List<Hero> getByHsId(int startPos, int endPos, String hsId) {
		return dao.getByHsId(startPos, endPos, hsId);
	}

}

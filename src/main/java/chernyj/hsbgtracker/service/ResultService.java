package chernyj.hsbgtracker.service;

import chernyj.hsbgtracker.dao.ResultDao;
import chernyj.hsbgtracker.dao.impl.ResultDatabaseDao;
import chernyj.hsbgtracker.entity.Result;

public class ResultService {
	private ResultDao dao = new ResultDatabaseDao();
	
	public boolean exists(Result result) {
		return dao.exists(result);
	}
	
	public void save(Result result) {
		dao.add(result);
	}
}

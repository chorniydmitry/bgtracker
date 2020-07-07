package chernyj.hsbgtracker.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import chernyj.hsbgtracker.dao.GameDao;
import chernyj.hsbgtracker.entity.Game;
import chernyj.hsbgtracker.entity.Result;
import chernyj.hsbgtracker.utils.HibernateUtil;

public class GameDatabaseDao extends AbstractHibernateDao<Game> implements GameDao {
	
	@Override
	public Game getByTimes(Date timeStart, Date timeFinish) {
		Game game = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Game> criteriaQuery = builder.createQuery(Game.class);

			Root<Game> root = criteriaQuery.from(Game.class);
			
			List<Predicate> predicates = new ArrayList<Predicate>();
			
			predicates.add(builder.equal(root.get("timeStarted"), timeStart));
			predicates.add(builder.equal(root.get("timeFinished"), timeFinish));
			
			criteriaQuery.select(root).where(predicates.toArray(new Predicate[] {}));

			Query<Game> query = session.createQuery(criteriaQuery);
			
			try {
				game = query.getSingleResult();
			} catch(NoResultException e) {
				//its ok!
			}
			session.close();

		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return game;
	}
	
	@Override
	public boolean exists(Game game) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Game> criteriaQuery = builder.createQuery(Game.class);

			Root<Game> root = criteriaQuery.from(Game.class);
			
			List<Predicate> predicates = new ArrayList<Predicate>();
			
			predicates.add(builder.equal(root.get("timeFinished"), game.getTimeFinished()));
			predicates.add(builder.equal(root.get("timeStarted"), game.getTimeStarted()));
			
			criteriaQuery.select(root).where(predicates.toArray(new Predicate[] {}));

			Query<Game> query = session.createQuery(criteriaQuery);
			
			
			return query.getResultList().size() > 0;
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}

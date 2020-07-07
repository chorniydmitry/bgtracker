package chernyj.hsbgtracker.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import chernyj.hsbgtracker.dao.ResultDao;
import chernyj.hsbgtracker.entity.Hero;
import chernyj.hsbgtracker.entity.Result;
import chernyj.hsbgtracker.entity.User;
import chernyj.hsbgtracker.utils.HibernateUtil;

public class ResultDatabaseDao extends AbstractHibernateDao<Result> implements ResultDao {

	@Override
	public void saveResults(User user, Hero hero, Date date, int place) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists(Result result) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Result> criteriaQuery = builder.createQuery(Result.class);

			Root<Result> root = criteriaQuery.from(Result.class);
			
			List<Predicate> predicates = new ArrayList<Predicate>();
			
			predicates.add(builder.equal(root.get("game"), result.getGame()));
			predicates.add(builder.equal(root.get("hero"), result.getHero()));
			predicates.add(builder.equal(root.get("place"), result.getPlace()));
			predicates.add(builder.equal(root.get("user"), result.getUser()));
			
			criteriaQuery.select(root).where(predicates.toArray(new Predicate[] {}));

			Query<Result> query = session.createQuery(criteriaQuery);
			
			
			return query.getResultList().size() > 0;
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}

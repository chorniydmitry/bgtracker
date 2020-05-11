package chernyj.hsbgtracker.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import chernyj.hsbgtracker.dao.UserDao;
import chernyj.hsbgtracker.entity.User;
import chernyj.hsbgtracker.utils.HibernateUtil;

public class UserDatabaseDao extends AbstractHibernateDao<User> implements UserDao {

	@Override
	public User getByNameAndBTag(String name, int bTag) {
		User user = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);

			Root<User> root = criteriaQuery.from(User.class);
			
			List<Predicate> predicates = new ArrayList<Predicate>();
			
			predicates.add(builder.equal(root.get("name"), name));
			predicates.add(builder.equal(root.get("bTag"), bTag));
			
			
			criteriaQuery.select(root).where(predicates.toArray(new Predicate[] {}));

			Query<User> query = session.createQuery(criteriaQuery);
			
			try {
				user = query.getSingleResult();
			} catch(NoResultException e) {
				//its ok!
			}
			session.close();

		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return user;
	}

}

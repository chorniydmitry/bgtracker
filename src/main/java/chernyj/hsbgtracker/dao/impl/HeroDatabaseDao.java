package chernyj.hsbgtracker.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import chernyj.hsbgtracker.dao.HeroDao;
import chernyj.hsbgtracker.entity.Hero;
import chernyj.hsbgtracker.utils.HibernateUtil;

public class HeroDatabaseDao extends AbstractHibernateDao<Hero> implements HeroDao {

	@Override
	public List<Hero> getByHsId(int startPos, int endPos, String hsId) {
		List<Hero> heroList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Hero> criteriaQuery = builder.createQuery(Hero.class);

			Root<Hero> root = criteriaQuery.from(Hero.class);
			criteriaQuery.select(root).where(builder.equal(root.get("hsId"), "%" + hsId + "%"));

			Query<Hero> query = session.createQuery(criteriaQuery);

			if (!(endPos == -1 || startPos == -1)) {
				query.setFirstResult(startPos);
				query.setMaxResults(endPos);
			}

			heroList = query.getResultList();

			session.close();

		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return heroList;
	}

	@Override
	public Hero getByHsId(String hsId) {
		Hero hero = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Hero> criteriaQuery = builder.createQuery(Hero.class);

			Root<Hero> root = criteriaQuery.from(Hero.class);
			criteriaQuery.select(root).where(builder.like((root.get("hsId")), hsId));

			Query<Hero> query = session.createQuery(criteriaQuery);

			hero = query.getSingleResult();

			session.close();

		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return hero;
	}
	

}

package cloud.migration.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cloud.migration.dao.TimeIntervalDao;
import cloud.migration.model.TimeInterval;

@Repository
public class TimeIntervalDaoImp implements TimeIntervalDao{

	private SessionFactory session;	
	
	public SessionFactory getSession() {
		return session;
	}

	public void setSession(SessionFactory session) {
		this.session = session;
	}

	
	@Override
	public void add(TimeInterval timeInterval) {
		// TODO Auto-generated method stub
		session.getCurrentSession().save(timeInterval);
	}

	@Override
	public void edit(TimeInterval timeInterval) {
		// TODO Auto-generated method stub		
		TimeInterval newTimeInterval=(TimeInterval)session.getCurrentSession().get(TimeInterval.class, timeInterval.getId());
		newTimeInterval.setDate(timeInterval.getDate());
		newTimeInterval.setStartTime(timeInterval.getStartTime());
		session.getCurrentSession().save(newTimeInterval);
		
	}

	@Override
	public void delete(int timeIntervalId) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(getTimeInterval(timeIntervalId));
	}

	@Override
	public TimeInterval getTimeInterval(int timeIntervalId) {
		// TODO Auto-generated method stub
		
		return (TimeInterval)session.getCurrentSession().get(TimeInterval.class, timeIntervalId);
	}

	
	@Override
	public List getAllTimeInterval() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from TimeInterval").list();
	}

	@Override
	public List getTimeIntervalByDate(Date date) {
		// TODO Auto-generated method stub
		
		String s_query = "FROM TimeInterval as p WHERE p.date = :date";
		Query query;
		query=session.getCurrentSession().createQuery(s_query).setDate("date", date);
		
		return query.list();

		
	}

	@Override
	public void addTimeSet(Set<TimeInterval> tals) {
		// TODO Auto-generated method stub
		
		for(TimeInterval tal:tals)
			session.getCurrentSession().save(tal);
		
	}

}

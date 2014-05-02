package cloud.migration.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cloud.migration.dao.HallDao;
import cloud.migration.model.Court;
import cloud.migration.model.Hall;

@Repository
public class HallDaoImp  implements HallDao{

	private SessionFactory session;
	
	
	public SessionFactory getSession() {
		return session;
	}

	public void setSession(SessionFactory session) {
		this.session = session;
	}

	@Override
	public void add(Hall hall) {
		// TODO Auto-generated method stub
		session.getCurrentSession().save(hall);
	}

	@Override
	public void edit(Hall hall) {
		// TODO Auto-generated method stub		
		
		Hall newHall=(Hall)session.getCurrentSession().get(Hall.class, hall.getId());
		newHall.setCloseTime(hall.getCloseTime());
		newHall.setOpenTime(hall.getOpenTime());
		newHall.setIntroduction(hall.getIntroduction());
		newHall.setName(hall.getName());
		session.getCurrentSession().save(newHall);
		
		
	}

	@Override
	public void delete(int hallId) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(getHall(hallId));
	}

	@Override
	public Hall getHall(int hallId) {
		// TODO Auto-generated method stub
		
		return (Hall)session.getCurrentSession().get(Hall.class, hallId);
	}

	@Override
	public List getAllHall() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from Hall").list();
	}

	@Override
	public Hall getHallByName(String name) {
		// TODO Auto-generated method stub
		List<Hall> ha=getAllHall();
		for(Hall h:ha)
			if(h.getName().equalsIgnoreCase(name))
				return h;
		return null;
	}
}

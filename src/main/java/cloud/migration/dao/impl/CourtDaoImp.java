package cloud.migration.dao.impl;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cloud.migration.dao.CourtDao;
import cloud.migration.model.Court;

@Repository
public class CourtDaoImp implements CourtDao {


	private SessionFactory session;
	

	public SessionFactory getSession() {
		return session;
	}

	public void setSession(SessionFactory session) {
		this.session = session;
	}


	@Override
	public void add(Court court) {
		// TODO Auto-generated method stub
		session.getCurrentSession().save(court);
	}

	@Override
	public void edit(Court court) {
		// TODO Auto-generated method stub
		
		Court newCourt=(Court)session.getCurrentSession().get(Court.class, court.getId());
		newCourt.setIntroduction(court.getIntroduction());
		newCourt.setName(court.getName());
		//newCourt.setHall(court.getHall());
		session.getCurrentSession().save(newCourt);
		
	}

	@Override
	public void delete(int courtId) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(getCourt(courtId));
	}

	@Override
	public Court getCourt(int courtId) {
		// TODO Auto-generated method stub
		
		return (Court)session.getCurrentSession().get(Court.class, courtId);
	}

	@Override
	public List getAllCourt() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from Court").list();
	}


	@Override
	public Court getCourtByName(String name)
	{
		List<Court> ls=getAllCourt();
		for(Court ct:ls)
			if(ct.getName().equalsIgnoreCase(name))
				return ct;
		return null;	
	}
	


}

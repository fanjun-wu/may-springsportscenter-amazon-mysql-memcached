package cloud.migration.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cloud.migration.dao.CapabilityDao;
import cloud.migration.model.Capability;

@Repository
public class CapabilityDaoImp  implements CapabilityDao{
	
	private SessionFactory session;
	

	public SessionFactory getSession() {
		return session;
	}

	public void setSession(SessionFactory session) {
		this.session = session;
	}


	@Override
	public void add(Capability capability) {
		// TODO Auto-generated method stub
		session.getCurrentSession().save(capability);
	}

	@Override
	public void edit(Capability capability) {
		// TODO Auto-generated method stub
		//session.getCurrentSession().update(capability);
		
		Capability newCapability=(Capability)session.getCurrentSession().get(Capability.class, capability.getId());
		newCapability.setConditionCap(capability.getConditionCap());
		newCapability.setDiscriptionCap(capability.getDiscriptionCap());
		newCapability.setResource(capability.getResource());
		session.getCurrentSession().save(newCapability);
		
	}

	@Override
	public void delete(int capabilityId) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(getCapability(capabilityId));
	}

	@Override
	public Capability getCapability(int capabilityId) {
		// TODO Auto-generated method stub
		
		return (Capability)session.getCurrentSession().get(Capability.class, capabilityId);
	}

	@Override
	public List getAllCapability() {
		// TODO Auto-generated method stub		
		return session.getCurrentSession().createQuery("from Capability").list();
	}
}

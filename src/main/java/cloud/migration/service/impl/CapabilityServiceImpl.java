package cloud.migration.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cloud.migration.dao.AdminDao;
import cloud.migration.dao.CapabilityDao;
import cloud.migration.model.Capability;
import cloud.migration.service.CapabilityService;
@Service
@Transactional
public class CapabilityServiceImpl  implements CapabilityService{


	private CapabilityDao capabilityDao;
	

	@Override
	public void saveCapability(Capability capability) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		capabilityDao.add(capability);
	}
	

	@Override
	public void editCapability(Capability capability) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		capabilityDao.edit(capability);
	}


	@Override
	public void deleteCapability(int capabilityId) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		capabilityDao.delete(capabilityId);
	}


	@Override
	public Capability getCapability(int capabilityId) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		return capabilityDao.getCapability(capabilityId);
	}
	
	@Cacheable(value = "capabilityCache",key="#root.method.name")
	@Override
	public List getCapabilities() {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		return capabilityDao.getAllCapability();
	}


	public CapabilityDao getCapabilityDao() {
		return capabilityDao;
	}


	public void setCapabilityDao(CapabilityDao capabilityDao) {
		this.capabilityDao = capabilityDao;
	}
}

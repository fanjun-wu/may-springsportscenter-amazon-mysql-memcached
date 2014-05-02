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

import cloud.migration.dao.CapabilityDao;
import cloud.migration.dao.HallDao;
import cloud.migration.model.Hall;
import cloud.migration.service.HallService;

@Service
@Transactional
public class HallServiceImpl implements HallService {

	private HallDao hallDao;
	

	public HallDao getHallDao() {
		return hallDao;
	}

	public void setHallDao(HallDao hallDao) {
		this.hallDao = hallDao;
	}

	@Override
	public void saveHall(Hall hall) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		hallDao.add(hall);
	}

	@Override
	public void editHall(Hall hall) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		hallDao.edit(hall);
	}


	@Override
	public void deleteHall(int hallId) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		hallDao.delete(hallId);
	}


	@Override
	public Hall getHall(int hallId) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		return hallDao.getHall(hallId);
	}

	@Cacheable(value = "hallCache",key="#root.method.name")
	@Override
	public List getHalls() {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		return hallDao.getAllHall();
	}

	@Override
	public Hall getHallByName(String name) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		return hallDao.getHallByName(name);
	}

}

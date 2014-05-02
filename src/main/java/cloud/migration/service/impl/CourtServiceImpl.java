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
import cloud.migration.dao.CourtDao;
import cloud.migration.model.Court;
import cloud.migration.service.CourtService;

@Service
@Transactional
public class CourtServiceImpl implements CourtService{


	private CourtDao courtDao;
	

	public CourtDao getCourtDao() {
		return courtDao;
	}

	public void setCourtDao(CourtDao courtDao) {
		this.courtDao = courtDao;
	}

	@Override
	public void saveCourt(Court court) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		courtDao.add(court);
	}

	@Override
	public void editCourt(Court court) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		courtDao.edit(court);
	}


	@Override
	public void deleteCourt(int courtId) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		courtDao.delete(courtId);
	}


	@Override
	public Court getCourt(int courtId) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		return courtDao.getCourt(courtId);
	}

	@Cacheable(value = "courtCache", key="#root.method.name")
	@Override
	public List getCourts() {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		return courtDao.getAllCourt();
	}
	

	@Override
	public Court getCourtByName(String name) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		return courtDao.getCourtByName(name);
	}

	
}

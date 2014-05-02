package cloud.migration.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cloud.migration.dao.SubscriberDao;
import cloud.migration.dao.TimeIntervalDao;
import cloud.migration.model.TimeInterval;
import cloud.migration.service.TimeIntervalService;
@Service
@Transactional
public class TimeIntervalServiceImpl implements TimeIntervalService {

	private TimeIntervalDao timeIntervalDao;
	


public TimeIntervalDao getTimeIntervalDao() {
		return timeIntervalDao;
	}

	public void setTimeIntervalDao(TimeIntervalDao timeIntervalDao) {
		this.timeIntervalDao = timeIntervalDao;
	}

	@Override
	public void saveTimeInterval(TimeInterval timeInterval) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		timeIntervalDao.add(timeInterval);
	}
	
	@Override
	public void editTimeInterval(TimeInterval timeInterval) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		timeIntervalDao.edit(timeInterval);
	}

	@Override
	public void deleteTimeInterval(int timeIntervalId) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		timeIntervalDao.delete(timeIntervalId);
	}

	@Override
	public TimeInterval getTimeInterval(int timeIntervalId) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		return timeIntervalDao.getTimeInterval(timeIntervalId);
	}
	
	@Cacheable(value = "timeIntervalCache", key="#root.method.name")
	@Override
	public List getTimeIntervals() {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		return timeIntervalDao.getAllTimeInterval();
	}
	

	@Override
	public List getTimeIntervalByDate(Date date) {
		// TODO Auto-generated method stub

		return timeIntervalDao.getTimeIntervalByDate(date);		
	}

	@Override
	public void saveTimeSet(Set<TimeInterval> tals) {
		// TODO Auto-generated method stub
		timeIntervalDao.addTimeSet(tals);
	}
}

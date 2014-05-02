package cloud.migration.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cloud.migration.dao.ReservationDao;
import cloud.migration.dao.SubscriberDao;
import cloud.migration.model.Subscriber;
import cloud.migration.service.SubscriberService;
@Service
@Transactional
public class SubscriberServiceImpl implements SubscriberService {

	private SubscriberDao subscriberDao;
	

public SubscriberDao getSubscriberDao() {
		return subscriberDao;
	}

	public void setSubscriberDao(SubscriberDao subscriberDao) {
		this.subscriberDao = subscriberDao;
	}


	@Override
	public void saveSubscriber(Subscriber subscriber) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		subscriberDao.add(subscriber);
	}

	@Override
	public void editSubscriber(Subscriber subscriber) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		subscriberDao.edit(subscriber);
	}


	@Override
	public void deleteSubscriber(int subscriberId) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		subscriberDao.delete(subscriberId);
	}


	@Override
	public Subscriber getSubscriber(int subscriberId) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		return subscriberDao.getSubscriber(subscriberId);
	}

	@Cacheable(value = "subscriberCache", key="#root.method.name")
	@Override
	public List getSubscribers() {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		return subscriberDao.getAllSubscriber();
	}

	@Override
	public Set getReservationsBySubscriber(int id) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		return subscriberDao.getReservationsBySubscriber(id);
	}

}

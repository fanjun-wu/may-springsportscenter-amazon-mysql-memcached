package cloud.migration.dao;

import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import cloud.migration.model.Subscriber;


public interface SubscriberDao {
	public void add(Subscriber subscriber);
	public void edit(Subscriber subscriber);
	public void delete(int subscriberId);
	public Subscriber getSubscriber(int subscriberId);
	public List getAllSubscriber();
	
	
	public Set getReservationsBySubscriber(int id);
}

package cloud.migration.service;

import java.util.List;
import java.util.Set;

import cloud.migration.model.Subscriber;


public interface SubscriberService {

	public void saveSubscriber(Subscriber subscriber);
	public void editSubscriber(Subscriber subscriber);
	public void deleteSubscriber(int subscriberId);
	public Subscriber getSubscriber(int subscriberId);
	public List getSubscribers();
	
	public Set getReservationsBySubscriber(int id);
	
	
}

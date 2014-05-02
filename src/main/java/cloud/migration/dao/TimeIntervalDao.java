package cloud.migration.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import cloud.migration.model.TimeInterval;


public interface TimeIntervalDao {
	public void add(TimeInterval timeInterval);
	public void edit(TimeInterval timeInterval);
	public void delete(int timeIntervalId);
	public TimeInterval getTimeInterval(int timeIntervalId);
	public List getAllTimeInterval();
	public List getTimeIntervalByDate(Date date);
	public void addTimeSet(Set<TimeInterval> tals);
	
	
}

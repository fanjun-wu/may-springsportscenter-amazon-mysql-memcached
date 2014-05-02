package cloud.migration.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import cloud.migration.model.TimeInterval;


public interface TimeIntervalService {

	public void saveTimeInterval(TimeInterval timeInterval);
	public void editTimeInterval(TimeInterval timeInterval);
	public void deleteTimeInterval(int timeIntervalId);
	public TimeInterval getTimeInterval(int timeIntervalId);	
	public List getTimeIntervals();	
	public List getTimeIntervalByDate(Date date);
	public void saveTimeSet(Set<TimeInterval> tals);
	
	
	
}

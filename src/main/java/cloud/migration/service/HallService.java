package cloud.migration.service;

import java.util.List;

import cloud.migration.model.Hall;


public interface HallService {
	public void saveHall(Hall hall);
	public void editHall(Hall hall);
	public void deleteHall(int hallId);
	public Hall getHall(int hallId);
	public List getHalls();
	public Hall getHallByName(String name);
	
}

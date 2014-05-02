package cloud.migration.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cloud.migration.model.Hall;

public interface HallDao {
	public void add(Hall hall);
	public void edit(Hall hall);
	public void delete(int hallId);
	public Hall getHall(int hallId);
	public List getAllHall();
	public Hall getHallByName(String name);
}

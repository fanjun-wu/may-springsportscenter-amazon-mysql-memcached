package cloud.migration.service;

import java.util.List;

import cloud.migration.model.Court;


public interface CourtService{
	public void saveCourt(Court court);
	public void editCourt(Court court);
	public void deleteCourt(int courtId);
	public Court getCourt(int courtId);
	public List getCourts();
	public Court getCourtByName(String name);
}

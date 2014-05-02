package cloud.migration.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cloud.migration.model.Admin;

public interface AdminDao {
	public void add(Admin admin);
	public void edit(Admin admin);
	public void delete(int adminId);
	public Admin getAdmin(int adminId);
	public List getAllAdmin();
	
}

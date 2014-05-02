package cloud.migration.service;

import java.util.List;

import cloud.migration.model.Admin;

public interface AdminService {
	public void saveAdmin(Admin admin);
	public void editAdmin(Admin admin);
	public void deleteAdmin(int adminId);
	public Admin getAdmin(int adminId);
	public List getAdmins();
}

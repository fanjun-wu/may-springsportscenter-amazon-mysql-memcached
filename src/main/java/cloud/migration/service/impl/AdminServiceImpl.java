package cloud.migration.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cloud.migration.dao.AdminDao;
import cloud.migration.model.Admin;
import cloud.migration.service.AdminService;
import cloud.others.MailMail;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	private AdminDao adminDao;
	
	public AdminDao getAdminDao() {
		return adminDao;
	}


	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}


	@Override
	public void saveAdmin(Admin admin) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		adminDao.add(admin);
	}
	

	@Override
	public void editAdmin(Admin admin) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		adminDao.edit(admin);
	}


	@Override
	public void deleteAdmin(int adminId) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		adminDao.delete(adminId);
	}


	@Override
	public Admin getAdmin(int adminId) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		return adminDao.getAdmin(adminId);
	}
	

	@Cacheable(value = "adminCache", key="#root.method.name")
	@Override
	public List getAdmins() {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));				
		
		return adminDao.getAllAdmin();
	}

	
}

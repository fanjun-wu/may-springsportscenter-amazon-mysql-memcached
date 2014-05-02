package cloud.migration.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cloud.migration.dao.AdminDao;
import cloud.migration.model.Admin;
import cloud.migration.model.Court;
import cloud.migration.service.AdminService;

@Repository
public class AdminDaoImp implements AdminDao {

	private SessionFactory session;

	public SessionFactory getSession() {
		return session;
	}

	public void setSession(SessionFactory session) {
		this.session = session;
	}

	@Override
	public void add(Admin admin) {
		// TODO Auto-generated method stub
		session.getCurrentSession().save(admin);
	}

	@Override
	public void edit(Admin admin) {
		// TODO Auto-generated method stub
		
		System.out.println("update admin: "+admin.getId()+" "+admin.toString());
		
		//session.getCurrentSession().update(admin);
//		Admin newAdmin=new Admin(admin.getName(),admin.getEmail(),admin.getTelephone(),admin.getGender(),admin.getAge(),admin.getPrivilege()); 
//		newAdmin.setId(admin.getId());		
//		session.getCurrentSession().save(newAdmin);
//		
		Admin newAdmin=(Admin)session.getCurrentSession().get(Admin.class, admin.getId());
		newAdmin.setAge(admin.getAge());
		newAdmin.setEmail(admin.getEmail());
		newAdmin.setGender(admin.getGender());
		newAdmin.setName(admin.getName());
		newAdmin.setPrivilege(admin.getPrivilege());
		newAdmin.setTelephone(admin.getTelephone());
		session.getCurrentSession().save(newAdmin);
		
	}

	@Override
	public void delete(int adminId) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(getAdmin(adminId));
	}

	@Override
	public Admin getAdmin(int adminId) {
		// TODO Auto-generated method stub
		
		return (Admin)session.getCurrentSession().get(Admin.class, adminId);
	}

	@Override
	public List getAllAdmin() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from Admin").list();
	}

	

}

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

import cloud.migration.dao.CapabilityDao;
import cloud.migration.dao.ReservationDao;
import cloud.migration.model.Reservation;
import cloud.migration.service.ReservationService;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService{


	private ReservationDao reservationDao;
	


	public ReservationDao getReservationDao() {
		return reservationDao;
	}


	public void setReservationDao(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}


	@Override
	public void saveReservation(Reservation reservation) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		reservationDao.add(reservation);
	}
	

	@Override
	public void editReservation(Reservation reservation) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		reservationDao.edit(reservation);
	}


	@Override
	public void deleteReservation(int reservationId) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		reservationDao.delete(reservationId);
	}


	@Override
	public Reservation getReservation(int reservationId) {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		return reservationDao.getReservation(reservationId);
	}

	@Cacheable(value = "reservationCache", key="#root.method.name")
	@Override
	public List getReservations() {
		// TODO Auto-generated method stub
		System.out.println(new Timestamp(new java.util.Date().getTime()));
		return reservationDao.getAllReservation();
	}
}

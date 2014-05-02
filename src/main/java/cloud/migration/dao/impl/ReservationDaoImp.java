package cloud.migration.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cloud.migration.dao.ReservationDao;
import cloud.migration.model.Reservation;

@Repository
public class ReservationDaoImp implements ReservationDao{


	private SessionFactory session;


	public SessionFactory getSession() {
		return session;
	}

	public void setSession(SessionFactory session) {
		this.session = session;
	}

	@Override
	public void add(Reservation reservation) {
		// TODO Auto-generated method stub
		session.getCurrentSession().save(reservation);
	}

	@Override
	public void edit(Reservation reservation) {
		// TODO Auto-generated method stub		
		Reservation newReservation=(Reservation)session.getCurrentSession().get(Reservation.class, reservation.getId());
		newReservation.setDiscription(reservation.getDiscription());
		newReservation.setTips(reservation.getTips());
		session.getCurrentSession().save(newReservation);
	}

	@Override
	public void delete(int reservationId) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(getReservation(reservationId));
	}

	@Override
	public Reservation getReservation(int reservationId) {
		// TODO Auto-generated method stub
		
		return (Reservation)session.getCurrentSession().get(Reservation.class, reservationId);
	}

	@Override
	public List getAllReservation() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from Reservation").list();
	}

	
}

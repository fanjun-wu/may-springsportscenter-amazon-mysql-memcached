package cloud.migration.service;

import java.util.List;

import cloud.migration.model.Reservation;


public interface ReservationService {

	public void saveReservation(Reservation reservation);
	public void editReservation(Reservation reservation);
	public void deleteReservation(int reservationId);
	public Reservation getReservation(int reservationId);
	public List getReservations();
}

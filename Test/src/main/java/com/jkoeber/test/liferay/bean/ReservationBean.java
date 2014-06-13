package com.jkoeber.test.liferay.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.jkoeber.test.jpa.Reservation;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.faces.util.portal.WebKeys;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;

@ManagedBean
@ViewScoped
public class ReservationBean {
	private Log log = LogFactoryUtil.getLog(ReservationBean.class.getName());
	
	@PersistenceContext ( name="TestJPA" )
	private EntityManager em;
	
	private List<Reservation> myReservations;
	private Reservation reservation;
	private User myUser;
	
	@PostConstruct
	public void init() {
		ThemeDisplay themeDisplay = (ThemeDisplay)FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(WebKeys.THEME_DISPLAY);
		myUser = themeDisplay.getUser();
		log.info("User found: " + myUser.toString());
		
		// load all my reservations
		myReservations = myReservations();
		reservation = new Reservation();
	}
	
	/**
	 * get my reservations
	 * @return
	 */
	private List<Reservation> myReservations() {
		List<Reservation> results = new ArrayList<Reservation>();
		
		Query query = em.createQuery("SELECT r FROM Reservation r WHERE r.userid = :id");
		query.setParameter("id", myUser.getUserId());
		results = query.getResultList();
		
		return results;
	}

	public void createReservation() {
		log.info("Store Reservation!");
		long count;
		try {
			count = CounterLocalServiceUtil.increment(Reservation.class.getName());
			reservation.setUserid(myUser.getUserId());
			reservation.setId(count);
		} catch (SystemException e) {
			log.error(e.getMessage());
		}
		
		em.persist(reservation);
		em.flush();
		
		log.info("Reservation is: " + reservation.toString());
	}
	
	public void deleteReservation() {
		em.remove(reservation);
	}
	
	public List<Reservation> getMyReservations() {
		return myReservations;
	}
	public void setMyReservations(List<Reservation> myReservations) {
		this.myReservations = myReservations;
	}
	public Reservation getReservation() {
		return reservation;
	}
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
}

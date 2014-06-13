package com.jkoeber.test.jpa;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.sql.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Reservation
 *
 */
@Entity
@Table ( name="test_reservation" )
public class Reservation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	private String bezng;
	private String beschr;
	private Date crdate;
	private Long userid;

	public Reservation() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getBezng() {
		return this.bezng;
	}

	public void setBezng(String bezng) {
		this.bezng = bezng;
	}   
	public String getBeschr() {
		return this.beschr;
	}

	public void setBeschr(String beschr) {
		this.beschr = beschr;
	}   
	public Date getCrdate() {
		return this.crdate;
	}

	public void setCrdate(Date crdate) {
		this.crdate = crdate;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
}

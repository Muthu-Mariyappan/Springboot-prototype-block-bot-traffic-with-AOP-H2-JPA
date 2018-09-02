package com.gmm.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="IPTable")
public class IPEntry implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id //dont add generatedValue to prevent any auto generation of primary key value
	@Column(name="IP",unique = true,nullable = false)//JPA has no primary key support for String values only for ints now
	private String IP;
	
	@Column(name="hitCount",nullable = false)
	private Long hitCount;
	
	@Column(name="lastHitTime",nullable = false)
	@Temporal(value = TemporalType.TIMESTAMP) //to store date and time to keep track of hits
	private Date lastHitTime;
	
	public IPEntry() {}
	
	public IPEntry(String iP, Long hitCount, Date lastHitTime) {
		super();
		IP = iP;
		this.hitCount = hitCount;
		this.lastHitTime = lastHitTime;
	}

	public String getIP() {
		return IP;
	}
	
	public void setIP(String iP) {
		IP = iP;
	}
	
	public Long getHitCount() {
		return hitCount;
	}
	
	public void setHitCount(Long hitCount) {
		this.hitCount = hitCount;
	}
	
	public Date getLastHitTime() {
		return lastHitTime;
	}

	public void setLastHitTime(Date lastHitTime) {
		this.lastHitTime = lastHitTime;
	}
}

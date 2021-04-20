package com.twiiter.base.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mysql.cj.jdbc.Blob;

@Entity
@Table(name = "TWIIT")
public class TwiiterTwiitEntity {

	@Id
	@Column(name = "TWIIT_ID")
	private int twiiterTwiitId;
	
	@Column(name = "TWIIT_MESSAGE")
	private String twiiterTwiitMessage;
	
	@Column(name = "TWIIT_PHOTO")
	private Blob twiiterTwiitPhoto;
	
	@Column(name = "TWIIT_TIME")
	private String twiiterTwiitTime;

	public int getTwiiterTwiitId() {
		return twiiterTwiitId;
	}

	public void setTwiiterTwiitId(int twiiterTwiitId) {
		this.twiiterTwiitId = twiiterTwiitId;
	}

	public String getTwiiterTwiitMessage() {
		return twiiterTwiitMessage;
	}

	public void setTwiiterTwiitMessage(String twiiterTwiitMessage) {
		this.twiiterTwiitMessage = twiiterTwiitMessage;
	}

	public Blob getTwiiterTwiitPhoto() {
		return twiiterTwiitPhoto;
	}

	public void setTwiiterTwiitPhoto(Blob twiiterTwiitPhoto) {
		this.twiiterTwiitPhoto = twiiterTwiitPhoto;
	}

	public String getTwiiterTwiitTime() {
		return twiiterTwiitTime;
	}

	public void setTwiiterTwiitTime(String twiiterTwiitTime) {
		this.twiiterTwiitTime = twiiterTwiitTime;
	}
	
	
	
}

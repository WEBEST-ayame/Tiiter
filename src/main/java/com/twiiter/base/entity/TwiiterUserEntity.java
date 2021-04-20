package com.twiiter.base.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mysql.cj.jdbc.Blob;

@Entity
@Table(name = "USER")
public class TwiiterUserEntity {
	
	@Id
	@Column(name = "USER_ID")
	private int TwiiterUserId;
	
	@Column(name = "USER_NAME")
	private String TwiiterUserName;
	
	@Column(name = "PASSWORD")
	private String TwiiterPassword;
	
	@Column(name = "USER_DETAIL")
	private String TwiiterUserDtail;
	
	@Column(name = "USER_PHOTO")
	private Blob TwiiterUserPhoto;

	public int getTwiiterUserId() {
		return TwiiterUserId;
	}

	public void setTwiiterUserId(int twiiterUserId) {
		TwiiterUserId = twiiterUserId;
	}

	public String getTwiiterUserName() {
		return TwiiterUserName;
	}

	public void setTwiiterUserName(String twiiterUserName) {
		TwiiterUserName = twiiterUserName;
	}

	public String getTwiiterPassword() {
		return TwiiterPassword;
	}

	public void setTwiiterPassword(String twiiterPassword) {
		TwiiterPassword = twiiterPassword;
	}

	public String getTwiiterUserDtail() {
		return TwiiterUserDtail;
	}

	public void setTwiiterUserDtail(String twiiterUserDtail) {
		TwiiterUserDtail = twiiterUserDtail;
	}

	public Blob getTwiiterUserPhoto() {
		return TwiiterUserPhoto;
	}

	public void setTwiiterUserPhoto(Blob twiiterUserPhoto) {
		TwiiterUserPhoto = twiiterUserPhoto;
	}

	
	
	

}

package com.twiiter.base.form;

import org.springframework.stereotype.Component;

import com.mysql.cj.jdbc.Blob;
import com.sun.istack.NotNull;

@Component
public class TwiiterForm {
	
	@NotNull
	private int twiitUserId;
	
	@NotNull
	private String twiiterPassword;
	
	@NotNull
	private String twiiterUserName;

	@NotNull
	private String twiiterUserDetail;

	@NotNull
	private Blob twiiterProfilePhoto;
	
	@NotNull
	private int twiiterId;
	
	@NotNull
	private String twiiterTwiitMessage;

	@NotNull
	private Blob twiiterTwiitPhoto;

	public int getTwiitUserId() {
		return twiitUserId;
	}

	public void setTwiitUserId(int twiitUserId) {
		this.twiitUserId = twiitUserId;
	}

	public String getTwiiterPassword() {
		return twiiterPassword;
	}

	public void setTwiiterPassword(String twiiterPassword) {
		this.twiiterPassword = twiiterPassword;
	}

	public String getTwiiterUserName() {
		return twiiterUserName;
	}

	public void setTwiiterUserName(String twiiterUserName) {
		this.twiiterUserName = twiiterUserName;
	}

	public String getTwiiterUserDetail() {
		return twiiterUserDetail;
	}

	public void setTwiiterUserDetail(String twiiterUserDetail) {
		this.twiiterUserDetail = twiiterUserDetail;
	}

	public Blob getTwiiterProfilePhoto() {
		return twiiterProfilePhoto;
	}

	public void setTwiiterProfilePhoto(Blob twiiterProfilePhoto) {
		this.twiiterProfilePhoto = twiiterProfilePhoto;
	}

	public int getTwiiterId() {
		return twiiterId;
	}

	public void setTwiiterId(int twiiterId) {
		this.twiiterId = twiiterId;
	}

	public String getTwiiterTwiitMessage() {
		return twiiterTwiitMessage;
	}

	public void setTwiiterMessage(String twiiterTwiitMessage) {
		this.twiiterTwiitMessage = twiiterTwiitMessage;
	}

	public Blob getTwiiterTwiitPhoto() {
		return twiiterTwiitPhoto;
	}

	public void setTwiiterTwiitPhoto(Blob twiiterTwiitPhoto) {
		this.twiiterTwiitPhoto = twiiterTwiitPhoto;
	}


}

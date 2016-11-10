package com.loveofworld.mvc.model.abstracts;

import com.loveofworld.mvc.model.data.Params;

public abstract class MessageService {

	private String SERVICE;
	private String STATUS;
	private String MSG;
	private Params params;
	
	public String getSERVICE() {
		return SERVICE;
	}

	public void setSERVICE(String sERVICE) {
		SERVICE = sERVICE;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	public String getMSG() {
		return MSG;
	}

	public void setMSG(String mSG) {
		MSG = mSG;
	}
	
	public Params getParams() {
		return params;
	}

	public void setParams(Params params) {
		this.params = params;
	}

	public abstract Object convertResponseMessage();

}

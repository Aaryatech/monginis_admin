package com.ats.adminpanel.model.supplierMaster;

import java.io.Serializable;
import java.util.List;

import com.ats.adminpanel.model.modules.ErrorMessage;


public class TransporterList implements Serializable{

	List<Transporter> transporterList;
	
	ErrorMessage errorMessage;

	public List<Transporter> getTransporterList() {
		return transporterList;
	}

	public void setTransporterList(List<Transporter> transporterList) {
		this.transporterList = transporterList;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "TransporterList [transporterList=" + transporterList + ", errorMessage=" + errorMessage + "]";
	}
	
	
	
}

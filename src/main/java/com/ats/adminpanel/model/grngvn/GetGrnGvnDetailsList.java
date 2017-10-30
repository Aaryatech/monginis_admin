package com.ats.adminpanel.model.grngvn;

import java.util.List;

import com.ats.adminpanel.model.Info;

public class GetGrnGvnDetailsList {
	
	List<GetGrnGvnDetails> grnGvnDetails;
	
	Info info;

	public List<GetGrnGvnDetails> getGrnGvnDetails() {
		return grnGvnDetails;
	}

	public void setGrnGvnDetails(List<GetGrnGvnDetails> grnGvnDetails) {
		this.grnGvnDetails = grnGvnDetails;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "GetGrnGvnDetailsList [grnGvnDetails=" + grnGvnDetails + ", info=" + info + "]";
	}
	
	
}

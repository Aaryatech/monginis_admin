package com.ats.adminpanel.model.creditnote;

public class CrnSrNoDateBean {
	
	
	String srNo;
	
	String grnGvnDate;

	public String getSrNo() {
		return srNo;
	}

	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}

	public String getGrnGvnDate() {
		return grnGvnDate;
	}

	public void setGrnGvnDate(String grnGvnDate) {
		this.grnGvnDate = grnGvnDate;
	}

	@Override
	public String toString() {
		return "CrnSrNoDateBean [srNo=" + srNo + ", grnGvnDate=" + grnGvnDate + "]";
	}

}

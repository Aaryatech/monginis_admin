package com.ats.adminpanel.model.grngvn;

import java.util.List;

import com.ats.adminpanel.model.Info;

public class GrnGvnHeaderList {
	
	List<GrnGvnHeader> grnGvnHeader;
	
	Info info;

	public List<GrnGvnHeader> getGrnGvnHeader() {
		return grnGvnHeader;
	}

	public void setGrnGvnHeader(List<GrnGvnHeader> grnGvnHeader) {
		this.grnGvnHeader = grnGvnHeader;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "GrnGvnHeaderList [grnGvnHeader=" + grnGvnHeader + ", info=" + info + "]";
	}
	
	

}

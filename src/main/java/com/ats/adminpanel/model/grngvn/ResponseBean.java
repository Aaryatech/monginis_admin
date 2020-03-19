package com.ats.adminpanel.model.grngvn;

import java.util.List;


public class ResponseBean {
	
	List<GHeader> ggHeaderList;
	List<GetGrnGvnDetails> ggDetailList;
	
	public List<GHeader> getGgHeaderList() {
		return ggHeaderList;
	}
	public void setGgHeaderList(List<GHeader> ggHeaderList) {
		this.ggHeaderList = ggHeaderList;
	}
	public List<GetGrnGvnDetails> getGgDetailList() {
		return ggDetailList;
	}
	public void setGgDetailList(List<GetGrnGvnDetails> ggDetailList) {
		this.ggDetailList = ggDetailList;
	}
	
	@Override
	public String toString() {
		return "ResponseBean [ggHeaderList=" + ggHeaderList + ", ggDetailList=" + ggDetailList + "]";
	}
	
	
}

package com.ats.adminpanel.model;

import java.util.List;


public class RegCakeAsSpDispatchReportList {
	
	List<RegCakeAsSpDispatchReport> regCakeAsSp;
	
	Info info;

	public List<RegCakeAsSpDispatchReport> getRegCakeAsSp() {
		return regCakeAsSp;
	}

	public void setRegCakeAsSp(List<RegCakeAsSpDispatchReport> regCakeAsSp) {
		this.regCakeAsSp = regCakeAsSp;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	
	@Override
	public String toString() {
		return "RegCakeAsSpDispatchReportList [regCakeAsSp=" + regCakeAsSp + ", info=" + info + "]";
	}
	

}

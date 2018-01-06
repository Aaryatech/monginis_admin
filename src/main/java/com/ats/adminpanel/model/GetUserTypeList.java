package com.ats.adminpanel.model;

import java.util.List;

public class GetUserTypeList {

	
	List<GetUserType> getUserTypeList;
	Info info;
	public List<GetUserType> getGetUserTypeList() {
		return getUserTypeList;
	}
	public void setGetUserTypeList(List<GetUserType> getUserTypeList) {
		this.getUserTypeList = getUserTypeList;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return "GetUserTypeList [getUserTypeList=" + getUserTypeList + ", info=" + info + "]";
	}
	
	
}

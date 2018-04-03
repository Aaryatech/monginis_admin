package com.ats.adminpanel.model;

import java.util.List;

public class GetUserDetailList {
	
	List<GetUserDetail> userDetail;
	
	Info info;

	public List<GetUserDetail> getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(List<GetUserDetail> userDetail) {
		this.userDetail = userDetail;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "GetUserDetailList [userDetail=" + userDetail + ", info=" + info + "]";
	}

}

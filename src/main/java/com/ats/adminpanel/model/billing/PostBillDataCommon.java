package com.ats.adminpanel.model.billing;

import java.util.Date;
import java.util.List;

public class PostBillDataCommon {
	
	
	List<PostBillHeader> postBillHeadersList;
	
	

	public List<PostBillHeader> getPostBillHeadersList() {
		return postBillHeadersList;
	}

	public void setPostBillHeadersList(List<PostBillHeader> postBillHeadersList) {
		this.postBillHeadersList = postBillHeadersList;
	}

	@Override
	public String toString() {
		return "PostBillDataCommon [postBillHeadersList=" + postBillHeadersList + "]";
	}

	

	
	
	

}

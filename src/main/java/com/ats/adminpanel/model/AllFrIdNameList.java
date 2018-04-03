package com.ats.adminpanel.model;

import java.util.List;

public class AllFrIdNameList {
	
	List<AllFrIdName> frIdNamesList;
	
	Info info;

	public List<AllFrIdName> getFrIdNamesList() {
		return frIdNamesList;
	}

	public void setFrIdNamesList(List<AllFrIdName> frIdNamesList) {
		this.frIdNamesList = frIdNamesList;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "AllFrIdNameList [frIdNamesList=" + frIdNamesList + ", info=" + info + "]";
	}
	
	

}

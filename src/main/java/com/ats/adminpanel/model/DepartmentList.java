package com.ats.adminpanel.model;

import java.util.List;

public class DepartmentList {

	List<Department> departmentList;
	
	Info info;

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "DepartMentList [departmentList=" + departmentList + ", info=" + info + "]";
	}
	
	
}

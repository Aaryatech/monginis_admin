package com.ats.adminpanel.model;

public class EventNameId {
	
	String id;
	String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "EventNameId [id=" + id + ", name=" + name + "]";
	}
	
	

}

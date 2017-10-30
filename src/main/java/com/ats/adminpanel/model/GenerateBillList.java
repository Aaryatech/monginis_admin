package com.ats.adminpanel.model;

import java.util.List;

public class GenerateBillList {
	
	List<GenerateBill> generateBills;
	
	Info info;

	public List<GenerateBill> getGenerateBills() {
		return generateBills;
	}

	public void setGenerateBills(List<GenerateBill> generateBills) {
		this.generateBills = generateBills;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "GenerateBillList [generateBills=" + generateBills + ", info=" + info + "]";
	}
	
	

}

package com.ats.adminpanel.model.billing;

import java.io.Serializable;
import java.util.List;


public class SlabwiseBillList{

	private List<SlabwiseBill> slabwiseBill;
	
	private int billNo;

	
	public List<SlabwiseBill> getSlabwiseBill() {
		return slabwiseBill;
	}

	public void setSlabwiseBill(List<SlabwiseBill> slabwiseBill) {
		this.slabwiseBill = slabwiseBill;
	}

	public int getBillNo() {
		return billNo;
	}

	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}

	@Override
	public String toString() {
		return "SlabwiseBillList [slabwiseBill=" + slabwiseBill + ", billNo=" + billNo + "]";
	}
    
}

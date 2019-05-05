package com.ats.adminpanel.model;

public class SpCakeWtCount {

	private float spSelectedWt;
	private int count;

	public float getSpSelectedWt() {
		return spSelectedWt;
	}

	public void setSpSelectedWt(float spSelectedWt) {
		this.spSelectedWt = spSelectedWt;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "SpCakeWtCount [spSelectedWt=" + spSelectedWt + ", count=" + count + "]";
	}

}

package com.ats.adminpanel.model;


public class SpCakeSupplement {

	private boolean error;

	private String message;
	
	private int id;
	
	private int spId;
	
	private String spHsncd;
	
	private String spUom;
	
	private int uomId;
	
	private float spCess;
	
	private int cutSection;
	
	private int isTallySync;
	
	private int delStatus;

	
	public int getUomId() {
		return uomId;
	}

	public void setUomId(int uomId) {
		this.uomId = uomId;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSpId() {
		return spId;
	}

	public void setSpId(int spId) {
		this.spId = spId;
	}

	public String getSpHsncd() {
		return spHsncd;
	}

	public void setSpHsncd(String spHsncd) {
		this.spHsncd = spHsncd;
	}

	public String getSpUom() {
		return spUom;
	}

	public void setSpUom(String spUom) {
		this.spUom = spUom;
	}

	public float getSpCess() {
		return spCess;
	}

	public void setSpCess(float spCess) {
		this.spCess = spCess;
	}

	public int getCutSection() {
		return cutSection;
	}

	public void setCutSection(int cutSection) {
		this.cutSection = cutSection;
	}

	public int getIsTallySync() {
		return isTallySync;
	}

	public void setIsTallySync(int isTallySync) {
		this.isTallySync = isTallySync;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "SpCakeSupplement [error=" + error + ", message=" + message + ", id=" + id + ", spId=" + spId
				+ ", spHsncd=" + spHsncd + ", spUom=" + spUom + ", uomId=" + uomId + ", spCess=" + spCess
				+ ", cutSection=" + cutSection + ", isTallySync=" + isTallySync + ", delStatus=" + delStatus + "]";
	}
    
}

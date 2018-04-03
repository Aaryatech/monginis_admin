package com.ats.adminpanel.model.spprod;

public class Instrument {

	private boolean isError;

	private String message;
	
	private int instrumentId;
	
	private String instrumentName;

	private int instrumentOpqty;
	
	private int instrumentClqty;
	
	private int instrumentIsUsed;
	
	private String instrumentStatus;

	private int stockQty;

	private int instType;
	
	private int delStatus;
	
	public int getInstType() {
		return instType;
	}

	public void setInstType(int instType) {
		this.instType = instType;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getInstrumentId() {
		return instrumentId;
	}

	public void setInstrumentId(int instrumentId) {
		this.instrumentId = instrumentId;
	}

	public String getInstrumentName() {
		return instrumentName;
	}

	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}

	public int getInstrumentOpqty() {
		return instrumentOpqty;
	}

	public void setInstrumentOpqty(int instrumentOpqty) {
		this.instrumentOpqty = instrumentOpqty;
	}

	public int getInstrumentClqty() {
		return instrumentClqty;
	}

	public void setInstrumentClqty(int instrumentClqty) {
		this.instrumentClqty = instrumentClqty;
	}

	public int getInstrumentIsUsed() {
		return instrumentIsUsed;
	}

	public void setInstrumentIsUsed(int instrumentIsUsed) {
		this.instrumentIsUsed = instrumentIsUsed;
	}

	public String getInstrumentStatus() {
		return instrumentStatus;
	}

	public void setInstrumentStatus(String instrumentStatus) {
		this.instrumentStatus = instrumentStatus;
	}

	public int getStockQty() {
		return stockQty;
	}

	public void setStockQty(int stockQty) {
		this.stockQty = stockQty;
	}

	@Override
	public String toString() {
		return "Instrument [isError=" + isError + ", message=" + message + ", instrumentId=" + instrumentId
				+ ", instrumentName=" + instrumentName + ", instrumentOpqty=" + instrumentOpqty + ", instrumentClqty="
				+ instrumentClqty + ", instrumentIsUsed=" + instrumentIsUsed + ", instrumentStatus=" + instrumentStatus
				+ ", stockQty=" + stockQty + "]";
	}

}

package com.ats.adminpanel.model.logistics;
 
public class Document {
	
	 
	private int docId;  
	private String docName;  
	private String docAlertdays;  
	private String docSeq;  
	private int delStatus;
	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getDocAlertdays() {
		return docAlertdays;
	}
	public void setDocAlertdays(String docAlertdays) {
		this.docAlertdays = docAlertdays;
	}
	public String getDocSeq() {
		return docSeq;
	}
	public void setDocSeq(String docSeq) {
		this.docSeq = docSeq;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	@Override
	public String toString() {
		return "Document [docId=" + docId + ", docName=" + docName + ", docAlertdays=" + docAlertdays + ", docSeq="
				+ docSeq + ", delStatus=" + delStatus + "]";
	}
	
	

}

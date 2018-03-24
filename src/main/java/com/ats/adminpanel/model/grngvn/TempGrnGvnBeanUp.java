package com.ats.adminpanel.model.grngvn;

//24 FEB new Bean to send data to rest for updateGateGrn,updateStoreGvn,updateAccGrn and GVN s
public class TempGrnGvnBeanUp {
	
	
	
	int grnGvnId;
	
	private int grnGvnStatus;

	private int approvedLoginGate;

	private String approveimedDateTimeGate;

	private String approvedRemarkGate;

	private int approvedLoginStore;

	private String approvedDateTimeStore;

	private String approvedRemarkStore;

	private int approvedLoginAcc;

	private String approvedDateTimeAcc;

	private String approvedRemarkAcc;
	
	//23 FEB new Fields to handle qty variation between entry(insert) and dispatch
		int aprQtyGate;
		
		int aprQtyStore;
		
		int aprQtyAcc;
		
		float aprTaxableAmt;
		
		float aprTotalTax;
		
		float aprSgstRs;
		
		float aprCgstRs;
		
		float aprIgstRs;
		
		float aprGrandTotal;
		
		float aprROff;

		int isSameState;
		//23 FEB new Fields to handle qty variation between entry(insert) and dispatch

		public int getGrnGvnId() {
			return grnGvnId;
		}

		public void setGrnGvnId(int grnGvnId) {
			this.grnGvnId = grnGvnId;
		}

		public int getGrnGvnStatus() {
			return grnGvnStatus;
		}

		public void setGrnGvnStatus(int grnGvnStatus) {
			this.grnGvnStatus = grnGvnStatus;
		}

		public int getApprovedLoginGate() {
			return approvedLoginGate;
		}

		public void setApprovedLoginGate(int approvedLoginGate) {
			this.approvedLoginGate = approvedLoginGate;
		}

		public String getApproveimedDateTimeGate() {
			return approveimedDateTimeGate;
		}

		public void setApproveimedDateTimeGate(String approveimedDateTimeGate) {
			this.approveimedDateTimeGate = approveimedDateTimeGate;
		}

		public String getApprovedRemarkGate() {
			return approvedRemarkGate;
		}

		public void setApprovedRemarkGate(String approvedRemarkGate) {
			this.approvedRemarkGate = approvedRemarkGate;
		}

		public int getApprovedLoginStore() {
			return approvedLoginStore;
		}

		public void setApprovedLoginStore(int approvedLoginStore) {
			this.approvedLoginStore = approvedLoginStore;
		}

		public String getApprovedDateTimeStore() {
			return approvedDateTimeStore;
		}

		public void setApprovedDateTimeStore(String approvedDateTimeStore) {
			this.approvedDateTimeStore = approvedDateTimeStore;
		}

		public String getApprovedRemarkStore() {
			return approvedRemarkStore;
		}

		public void setApprovedRemarkStore(String approvedRemarkStore) {
			this.approvedRemarkStore = approvedRemarkStore;
		}

		public int getApprovedLoginAcc() {
			return approvedLoginAcc;
		}

		public void setApprovedLoginAcc(int approvedLoginAcc) {
			this.approvedLoginAcc = approvedLoginAcc;
		}

		public String getApprovedDateTimeAcc() {
			return approvedDateTimeAcc;
		}

		public void setApprovedDateTimeAcc(String approvedDateTimeAcc) {
			this.approvedDateTimeAcc = approvedDateTimeAcc;
		}

		public String getApprovedRemarkAcc() {
			return approvedRemarkAcc;
		}

		public void setApprovedRemarkAcc(String approvedRemarkAcc) {
			this.approvedRemarkAcc = approvedRemarkAcc;
		}

		public int getAprQtyGate() {
			return aprQtyGate;
		}

		public void setAprQtyGate(int aprQtyGate) {
			this.aprQtyGate = aprQtyGate;
		}

		public int getAprQtyStore() {
			return aprQtyStore;
		}

		public void setAprQtyStore(int aprQtyStore) {
			this.aprQtyStore = aprQtyStore;
		}

		public int getAprQtyAcc() {
			return aprQtyAcc;
		}

		public void setAprQtyAcc(int aprQtyAcc) {
			this.aprQtyAcc = aprQtyAcc;
		}

		public float getAprTaxableAmt() {
			return aprTaxableAmt;
		}

		public void setAprTaxableAmt(float aprTaxableAmt) {
			this.aprTaxableAmt = aprTaxableAmt;
		}

		public float getAprTotalTax() {
			return aprTotalTax;
		}

		public void setAprTotalTax(float aprTotalTax) {
			this.aprTotalTax = aprTotalTax;
		}

		public float getAprSgstRs() {
			return aprSgstRs;
		}

		public void setAprSgstRs(float aprSgstRs) {
			this.aprSgstRs = aprSgstRs;
		}

		public float getAprCgstRs() {
			return aprCgstRs;
		}

		public void setAprCgstRs(float aprCgstRs) {
			this.aprCgstRs = aprCgstRs;
		}

		public float getAprIgstRs() {
			return aprIgstRs;
		}

		public void setAprIgstRs(float aprIgstRs) {
			this.aprIgstRs = aprIgstRs;
		}

		public float getAprGrandTotal() {
			return aprGrandTotal;
		}

		public void setAprGrandTotal(float aprGrandTotal) {
			this.aprGrandTotal = aprGrandTotal;
		}

		public float getAprROff() {
			return aprROff;
		}

		public void setAprROff(float aprROff) {
			this.aprROff = aprROff;
		}

		public int getIsSameState() {
			return isSameState;
		}

		public void setIsSameState(int isSameState) {
			this.isSameState = isSameState;
		}

		@Override
		public String toString() {
			return "TempGrnGvnBeanUp [grnGvnId=" + grnGvnId + ", grnGvnStatus=" + grnGvnStatus + ", approvedLoginGate="
					+ approvedLoginGate + ", approveimedDateTimeGate=" + approveimedDateTimeGate
					+ ", approvedRemarkGate=" + approvedRemarkGate + ", approvedLoginStore=" + approvedLoginStore
					+ ", approvedDateTimeStore=" + approvedDateTimeStore + ", approvedRemarkStore="
					+ approvedRemarkStore + ", approvedLoginAcc=" + approvedLoginAcc + ", approvedDateTimeAcc="
					+ approvedDateTimeAcc + ", approvedRemarkAcc=" + approvedRemarkAcc + ", aprQtyGate=" + aprQtyGate
					+ ", aprQtyStore=" + aprQtyStore + ", aprQtyAcc=" + aprQtyAcc + ", aprTaxableAmt=" + aprTaxableAmt
					+ ", aprTotalTax=" + aprTotalTax + ", aprSgstRs=" + aprSgstRs + ", aprCgstRs=" + aprCgstRs
					+ ", aprIgstRs=" + aprIgstRs + ", aprGrandTotal=" + aprGrandTotal + ", aprROff=" + aprROff
					+ ", isSameState=" + isSameState + "]";
		}
		

}

package com.ats.adminpanel.model.materialreceipt;


import java.util.Date;
import java.util.List; 
public class MaterialRecNote {


		private int mrnId;
		private String mrnNo;		
		private String gateEntryDate;
		private String gateEntryTime;
		private int transportId;
		private String vehicleNo;
		private String lrNo;
		private Date lrDate;
		private int supplierId;
		private int noOfItem;
		private String photo1;
		private String photo2;
		private int gateUserId;
		private String gateRemark;
		
		//--------------------------------
		
		private Date mrnStoreDate;
		private int mrnType;
		private int apainstPo;
		private int poId;
		private String poNo;
		private String poDate;
		private int useridStores;
		private String storesRemark;
		private int approvedUserId;
		private String approvalRemark;
		//-------------------------------
		
		private String invoiceNumber;
		private Date invDate;
		private Date invBookDate;
		private float basicValue;
		private float discPer;
		private float discAmt;
		private float discAmt2;
		private float freightAmt;
		private float insuranceAmt;
		private float cgst;
		private float sgst;
		private float igst;
		private float cess;
		private float roundOff;
		private float billAmount;
		private int useridAcc;
		private String accRemark;
		
		//--------------------------
		
		private int isTallySync;
		private int delStatus;
		private int status; 
		private float other1; 
		private float other2; 
		private float other3; 
		private float other4;
		 
		List<MaterialRecNoteDetails> materialRecNoteDetails;

		public int getMrnId() {
			return mrnId;
		}

		public void setMrnId(int mrnId) {
			this.mrnId = mrnId;
		}

		public String getMrnNo() {
			return mrnNo;
		}

		public void setMrnNo(String mrnNo) {
			this.mrnNo = mrnNo;
		}

		/*public Date getGateEntryDate() {
			return gateEntryDate;
		}

		public void setGateEntryDate(Date gateEntryDate) {
			this.gateEntryDate = gateEntryDate;
		}*/

		public String getGateEntryTime() {
			return gateEntryTime;
		}

		public String getGateEntryDate() {
			return gateEntryDate;
		}

		public void setGateEntryDate(String gateEntryDate) {
			this.gateEntryDate = gateEntryDate;
		}

		public void setGateEntryTime(String gateEntryTime) {
			this.gateEntryTime = gateEntryTime;
		}

		public int getTransportId() {
			return transportId;
		}

		public void setTransportId(int transportId) {
			this.transportId = transportId;
		}

		public String getVehicleNo() {
			return vehicleNo;
		}

		public void setVehicleNo(String vehicleNo) {
			this.vehicleNo = vehicleNo;
		}

		public String getLrNo() {
			return lrNo;
		}

		public void setLrNo(String lrNo) {
			this.lrNo = lrNo;
		}

		public Date getLrDate() {
			return lrDate;
		}

		public void setLrDate(Date lrDate) {
			this.lrDate = lrDate;
		}

		public int getSupplierId() {
			return supplierId;
		}

		public void setSupplierId(int supplierId) {
			this.supplierId = supplierId;
		}

		public int getNoOfItem() {
			return noOfItem;
		}

		public void setNoOfItem(int noOfItem) {
			this.noOfItem = noOfItem;
		}

		public String getPhoto1() {
			return photo1;
		}

		public void setPhoto1(String photo1) {
			this.photo1 = photo1;
		}

		public String getPhoto2() {
			return photo2;
		}

		public void setPhoto2(String photo2) {
			this.photo2 = photo2;
		}

		public int getGateUserId() {
			return gateUserId;
		}

		public void setGateUserId(int gateUserId) {
			this.gateUserId = gateUserId;
		}

		public String getGateRemark() {
			return gateRemark;
		}

		public void setGateRemark(String gateRemark) {
			this.gateRemark = gateRemark;
		}

		public Date getMrnStoreDate() {
			return mrnStoreDate;
		}

		public void setMrnStoreDate(Date mrnStoreDate) {
			this.mrnStoreDate = mrnStoreDate;
		}

		public int getMrnType() {
			return mrnType;
		}

		public void setMrnType(int mrnType) {
			this.mrnType = mrnType;
		}

		public int getApainstPo() {
			return apainstPo;
		}

		public void setApainstPo(int apainstPo) {
			this.apainstPo = apainstPo;
		}

		public int getPoId() {
			return poId;
		}

		public void setPoId(int poId) {
			this.poId = poId;
		}

		public String getPoNo() {
			return poNo;
		}

		public void setPoNo(String poNo) {
			this.poNo = poNo;
		}

		/*public Date getPoDate() {
			return poDate;
		}

		public void setPoDate(Date poDate) {
			this.poDate = poDate;
		}*/

		public int getUseridStores() {
			return useridStores;
		}

		public String getPoDate() {
			return poDate;
		}

		public void setPoDate(String poDate) {
			this.poDate = poDate;
		}

		public void setUseridStores(int useridStores) {
			this.useridStores = useridStores;
		}

		public String getStoresRemark() {
			return storesRemark;
		}

		public void setStoresRemark(String storesRemark) {
			this.storesRemark = storesRemark;
		}

		public int getApprovedUserId() {
			return approvedUserId;
		}

		public void setApprovedUserId(int approvedUserId) {
			this.approvedUserId = approvedUserId;
		}

		public String getApprovalRemark() {
			return approvalRemark;
		}

		public void setApprovalRemark(String approvalRemark) {
			this.approvalRemark = approvalRemark;
		}

		public String getInvoiceNumber() {
			return invoiceNumber;
		}

		public void setInvoiceNumber(String invoiceNumber) {
			this.invoiceNumber = invoiceNumber;
		}

		public Date getInvDate() {
			return invDate;
		}

		public void setInvDate(Date invDate) {
			this.invDate = invDate;
		}

		public Date getInvBookDate() {
			return invBookDate;
		}

		public void setInvBookDate(Date invBookDate) {
			this.invBookDate = invBookDate;
		}

		public float getBasicValue() {
			return basicValue;
		}

		public void setBasicValue(float basicValue) {
			this.basicValue = basicValue;
		}

		public float getDiscPer() {
			return discPer;
		}

		public void setDiscPer(float discPer) {
			this.discPer = discPer;
		}

		public float getDiscAmt() {
			return discAmt;
		}

		public void setDiscAmt(float discAmt) {
			this.discAmt = discAmt;
		}

		public float getDiscAmt2() {
			return discAmt2;
		}

		public void setDiscAmt2(float discAmt2) {
			this.discAmt2 = discAmt2;
		}

		public float getFreightAmt() {
			return freightAmt;
		}

		public void setFreightAmt(float freightAmt) {
			this.freightAmt = freightAmt;
		}

		public float getInsuranceAmt() {
			return insuranceAmt;
		}

		public void setInsuranceAmt(float insuranceAmt) {
			this.insuranceAmt = insuranceAmt;
		}

		public float getCgst() {
			return cgst;
		}

		public void setCgst(float cgst) {
			this.cgst = cgst;
		}

		public float getSgst() {
			return sgst;
		}

		public void setSgst(float sgst) {
			this.sgst = sgst;
		}

		public float getIgst() {
			return igst;
		}

		public void setIgst(float igst) {
			this.igst = igst;
		}

		public float getCess() {
			return cess;
		}

		public void setCess(float cess) {
			this.cess = cess;
		}

		public float getRoundOff() {
			return roundOff;
		}

		public void setRoundOff(float roundOff) {
			this.roundOff = roundOff;
		}

		public float getBillAmount() {
			return billAmount;
		}

		public void setBillAmount(float billAmount) {
			this.billAmount = billAmount;
		}

		public int getUseridAcc() {
			return useridAcc;
		}

		public void setUseridAcc(int useridAcc) {
			this.useridAcc = useridAcc;
		}

		public String getAccRemark() {
			return accRemark;
		}

		public void setAccRemark(String accRemark) {
			this.accRemark = accRemark;
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

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public List<MaterialRecNoteDetails> getMaterialRecNoteDetails() {
			return materialRecNoteDetails;
		}

		public void setMaterialRecNoteDetails(List<MaterialRecNoteDetails> materialRecNoteDetails) {
			this.materialRecNoteDetails = materialRecNoteDetails;
		}
		
		

		public float getOther1() {
			return other1;
		}

		public void setOther1(float other1) {
			this.other1 = other1;
		}

		public float getOther2() {
			return other2;
		}

		public void setOther2(float other2) {
			this.other2 = other2;
		}

		public float getOther3() {
			return other3;
		}

		public void setOther3(float other3) {
			this.other3 = other3;
		}

		public float getOther4() {
			return other4;
		}

		public void setOther4(float other4) {
			this.other4 = other4;
		}
		
		 

		@Override
		public String toString() {
			return "MaterialRecNote [mrnId=" + mrnId + ", mrnNo=" + mrnNo + ", gateEntryDate=" + gateEntryDate
					+ ", gateEntryTime=" + gateEntryTime + ", transportId=" + transportId + ", vehicleNo=" + vehicleNo
					+ ", lrNo=" + lrNo + ", lrDate=" + lrDate + ", supplierId=" + supplierId + ", noOfItem=" + noOfItem
					+ ", photo1=" + photo1 + ", photo2=" + photo2 + ", gateUserId=" + gateUserId + ", gateRemark="
					+ gateRemark + ", mrnStoreDate=" + mrnStoreDate + ", mrnType=" + mrnType + ", apainstPo="
					+ apainstPo + ", poId=" + poId + ", poNo=" + poNo + ", poDate=" + poDate + ", useridStores="
					+ useridStores + ", storesRemark=" + storesRemark + ", approvedUserId=" + approvedUserId
					+ ", approvalRemark=" + approvalRemark + ", invoiceNumber=" + invoiceNumber + ", invDate=" + invDate
					+ ", invBookDate=" + invBookDate + ", basicValue=" + basicValue + ", discPer=" + discPer
					+ ", discAmt=" + discAmt + ", discAmt2=" + discAmt2 + ", freightAmt=" + freightAmt
					+ ", insuranceAmt=" + insuranceAmt + ", cgst=" + cgst + ", sgst=" + sgst + ", igst=" + igst
					+ ", cess=" + cess + ", roundOff=" + roundOff + ", billAmount=" + billAmount + ", useridAcc="
					+ useridAcc + ", accRemark=" + accRemark + ", isTallySync=" + isTallySync + ", delStatus="
					+ delStatus + ", status=" + status + ", other1=" + other1 + ", other2=" + other2 + ", other3="
					+ other3 + ", other4=" + other4 + ", materialRecNoteDetails=" + materialRecNoteDetails + "]";
		}

		 
		
		
		
	}



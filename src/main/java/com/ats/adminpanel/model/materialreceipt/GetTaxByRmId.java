package com.ats.adminpanel.model.materialreceipt;



public class GetTaxByRmId {
	
	
		private int rmId;
		private float cgstPer;
		private float sgstPer;
		private float igstPer;
	    private int taxId;
		public int getRmId() {
			return rmId;
		}
		public void setRmId(int rmId) {
			this.rmId = rmId;
		}
		public float getCgstPer() {
			return cgstPer;
		}
		public void setCgstPer(float cgstPer) {
			this.cgstPer = cgstPer;
		}
		public float getSgstPer() {
			return sgstPer;
		}
		public void setSgstPer(float sgstPer) {
			this.sgstPer = sgstPer;
		}
		public float getIgstPer() {
			return igstPer;
		}
		public void setIgstPer(float igstPer) {
			this.igstPer = igstPer;
		}
		public int getTaxId() {
			return taxId;
		}
		public void setTaxId(int taxId) {
			this.taxId = taxId;
		}
		@Override
		public String toString() {
			return "GetTaxByRmId [rmId=" + rmId + ", cgstPer=" + cgstPer + ", sgstPer=" + sgstPer + ", igstPer="
					+ igstPer + ", taxId=" + taxId + "]";
		}
		
	    
	    
}

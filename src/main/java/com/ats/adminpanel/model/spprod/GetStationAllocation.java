package com.ats.adminpanel.model.spprod;



public class GetStationAllocation{

		private int allocationId;
	
		private int stationId;
		
	
		private String stName;
		
	
		private int shiftId;
		
	
		private String shiftName;
		
	
		private int empMistryId;
		
		private String empMistryName;
		
	
		private int empHelperId;
		
		private String empHelperName;
		
		private int delStatus;
        
        
		public String getStName() {
			return stName;
		}

		public void setStName(String stName) {
			this.stName = stName;
		}

		public void setEmpHelperName(String empHelperName) {
			this.empHelperName = empHelperName;
		}

		public String getShiftName() {
			return shiftName;
		}

		public void setShiftName(String shiftName) {
			this.shiftName = shiftName;
		}

		public String getEmpMistryName() {
			return empMistryName;
		}

		public void setEmpMistryName(String empMistryName) {
			this.empMistryName = empMistryName;
		}
        
		public String getEmpHelperName() {
			return empHelperName;
		}

		public int getAllocationId() {
			return allocationId;
		}

		public void setAllocationId(int allocationId) {
			this.allocationId = allocationId;
		}

		public int getStationId() {
			return stationId;
		}

		public void setStationId(int stationId) {
			this.stationId = stationId;
		}

		public int getShiftId() {
			return shiftId;
		}

		public void setShiftId(int shiftId) {
			this.shiftId = shiftId;
		}

		public int getEmpMistryId() {
			return empMistryId;
		}

		public void setEmpMistryId(int empMistryId) {
			this.empMistryId = empMistryId;
		}

		public int getEmpHelperId() {
			return empHelperId;
		}

		public void setEmpHelperId(int empHelperId) {
			this.empHelperId = empHelperId;
		}

		public int getDelStatus() {
			return delStatus;
		}

		public void setDelStatus(int delStatus) {
			this.delStatus = delStatus;
		}

		@Override
		public String toString() {
			return "StationAllocation [ allocationId=" + allocationId
					+ ", stationId=" + stationId + ", shiftId=" + shiftId + ", empMistryId=" + empMistryId
					+ ", empHelperId=" + empHelperId + ", delStatus=" + delStatus + "]";
		}
		
	}

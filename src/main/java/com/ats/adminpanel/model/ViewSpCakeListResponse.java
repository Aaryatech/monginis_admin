package com.ats.adminpanel.model;

import java.util.List;

public class ViewSpCakeListResponse {
	private int srno;
	String image;
		String code;
		String name;
		String type;
		String minwt,maxwt,bookb4;
		int sprid;
		double tx1,tx2,tx3;
		String eventList, erpcode;
		int isused,allowpupload,type2appl;
		int delstatus;
		int basecode;
		public int getSrno() {
			return srno;
		}
		public void setSrno(int srno) {
			this.srno = srno;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getMinwt() {
			return minwt;
		}
		public void setMinwt(String minwt) {
			this.minwt = minwt;
		}
		public String getMaxwt() {
			return maxwt;
		}
		public void setMaxwt(String maxwt) {
			this.maxwt = maxwt;
		}
		public String getBookb4() {
			return bookb4;
		}
		public void setBookb4(String bookb4) {
			this.bookb4 = bookb4;
		}
		public int getSprid() {
			return sprid;
		}
		public void setSprid(int sprid) {
			this.sprid = sprid;
		}
		public double getTx1() {
			return tx1;
		}
		public void setTx1(double tx1) {
			this.tx1 = tx1;
		}
		public double getTx2() {
			return tx2;
		}
		public void setTx2(double tx2) {
			this.tx2 = tx2;
		}
		public double getTx3() {
			return tx3;
		}
		public void setTx3(double tx3) {
			this.tx3 = tx3;
		}
		public String getEventList() {
			return eventList;
		}
		public void setEventList(String eventList) {
			this.eventList = eventList;
		}
		public String getErpcode() {
			return erpcode;
		}
		public void setErpcode(String erpcode) {
			this.erpcode = erpcode;
		}
		public int getIsused() {
			return isused;
		}
		public void setIsused(int isused) {
			this.isused = isused;
		}
		public int getAllowpupload() {
			return allowpupload;
		}
		public void setAllowpupload(int allowpupload) {
			this.allowpupload = allowpupload;
		}
		public int getType2appl() {
			return type2appl;
		}
		public void setType2appl(int type2appl) {
			this.type2appl = type2appl;
		}
		public int getDelstatus() {
			return delstatus;
		}
		public void setDelstatus(int delstatus) {
			this.delstatus = delstatus;
		}
		public int getBasecode() {
			return basecode;
		}
		public void setBasecode(int basecode) {
			this.basecode = basecode;
		}
		@Override
		public String toString() {
			return "ViewSpecialCakeList [srno=" + srno + ", image=" + image + ", code=" + code + ", name=" + name
					+ ", type=" + type + ", minwt=" + minwt + ", maxwt=" + maxwt + ", bookb4=" + bookb4 + ", sprid="
					+ sprid + ", tx1=" + tx1 + ", tx2=" + tx2 + ", tx3=" + tx3 + ", eventList=" + eventList
					+ ", erpcode=" + erpcode + ", isused=" + isused + ", allowpupload=" + allowpupload + ", type2appl="
					+ type2appl + ", delstatus=" + delstatus + ", basecode=" + basecode + "]";
		}
		
		
		
		
		
		
		
}

package com.ats.adminpanel.model;


import java.util.List;

public class GetDumpOrderList {
	 List<GetDumpOrder> getDumpOrder;
	 Info info;
	 
	 public List<GetDumpOrder> getGetDumpOrder() {
			return getDumpOrder;
		}
		public void setGetDumpOrder(List<GetDumpOrder> getDumpOrder) {
			this.getDumpOrder = getDumpOrder;
		}
		public Info getInfo() {
			return info;
		}
		public void setInfo(Info info) {
			this.info = info;
		}
		@Override
		public String toString() {
			return "GetDumpOrderList [getDumpOrder=" + getDumpOrder + ", info=" + info + "]";
		}
		
}

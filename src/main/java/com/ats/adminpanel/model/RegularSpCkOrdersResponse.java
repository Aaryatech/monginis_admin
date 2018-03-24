package com.ats.adminpanel.model;

import java.util.List;

public class RegularSpCkOrdersResponse {
	 private List<RegularSpCkOrder> regularSpCkOrdersList = null;;
      private ErrorMessage errorMessage;
	public List<RegularSpCkOrder> getRegularSpCkOrdersList() {
		return regularSpCkOrdersList;
	}
	public void setRegularSpCkOrdersList(List<RegularSpCkOrder> regularSpCkOrdersList) {
		this.regularSpCkOrdersList = regularSpCkOrdersList;
	}
	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

   }

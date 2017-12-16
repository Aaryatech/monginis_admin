package com.ats.adminpanel.model.masters;

import java.util.List;

import com.ats.adminpanel.model.modules.ErrorMessage;

public class AllRatesResponse {


private List<Rate> rates = null;

private ErrorMessage errorMessage;

public List<Rate> getRates() {
return rates;
}

public void setRates(List<Rate> rates) {
this.rates = rates;
}

public ErrorMessage getErrorMessage() {
return errorMessage;
}

public void setErrorMessage(ErrorMessage errorMessage) {
this.errorMessage = errorMessage;
}

@Override
public String toString() {
	return "AllRatesResponse [rates=" + rates + ", errorMessage=" + errorMessage + "]";
}

}
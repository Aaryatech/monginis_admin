package com.ats.adminpanel.model.masters;

import java.util.List;

import com.ats.adminpanel.model.modules.ErrorMessage;

public class AllspMessageResponse {

private ErrorMessage errorMessage;

private List<SpMessage> spMessage = null;

public ErrorMessage getErrorMessage() {
return errorMessage;
}

public void setErrorMessage(ErrorMessage errorMessage) {
this.errorMessage = errorMessage;
}

public List<SpMessage> getSpMessage() {
return spMessage;
}

public void setSpMessage(List<SpMessage> spMessage) {
this.spMessage = spMessage;
}

@Override
public String toString() {
	return "AllspMessageResponse [errorMessage=" + errorMessage + ", spMessage=" + spMessage + "]";
}

}

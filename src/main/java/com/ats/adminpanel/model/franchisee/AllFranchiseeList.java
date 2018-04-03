package com.ats.adminpanel.model.franchisee;

import java.util.List;

import com.ats.adminpanel.model.modules.ErrorMessage;

public class AllFranchiseeList {


private List<FranchiseeList> franchiseeList = null;

private ErrorMessage errorMessage;

public List<FranchiseeList> getFranchiseeList() {
return franchiseeList;
}

public void setFranchiseeList(List<FranchiseeList> franchiseeList) {
this.franchiseeList = franchiseeList;
}

public ErrorMessage getErrorMessage() {
return errorMessage;
}

public void setErrorMessage(ErrorMessage errorMessage) {
this.errorMessage = errorMessage;
}

@Override
public String toString() {
	return "AllFranchiseeList [franchiseeList=" + franchiseeList + ", errorMessage=" + errorMessage + "]";
}

}
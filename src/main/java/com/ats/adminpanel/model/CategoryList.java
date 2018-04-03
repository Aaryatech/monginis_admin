package com.ats.adminpanel.model;

import java.util.List;

public class CategoryList {
List<MCategory> mCategoryList;
ErrorMessage errorMessage;
public List<MCategory> getmCategoryList() {
	return mCategoryList;
}
public void setmCategoryList(List<MCategory> mCategoryList) {
	this.mCategoryList = mCategoryList;
}
public ErrorMessage getErrorMessage() {
	return errorMessage;
}
public void setErrorMessage(ErrorMessage errorMessage) {
	this.errorMessage = errorMessage;
}
@Override
public String toString() {
	return "CategoryList [mCategoryList=" + mCategoryList + ", errorMessage=" + errorMessage + "]";
}

}

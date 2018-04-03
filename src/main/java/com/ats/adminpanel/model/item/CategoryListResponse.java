
package com.ats.adminpanel.model.item;

import java.util.List;

public class CategoryListResponse {

    private List<MCategoryList> mCategoryList;
    private ErrorMessage errorMessage;

    

   
	public List<MCategoryList> getmCategoryList() {
		return mCategoryList;
	}

	public void setmCategoryList(List<MCategoryList> mCategoryList) {
		this.mCategoryList = mCategoryList;
	}

	public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    
    

}

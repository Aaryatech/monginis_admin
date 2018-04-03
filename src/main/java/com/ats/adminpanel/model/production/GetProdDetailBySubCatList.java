
package com.ats.adminpanel.model.production;

import java.util.List;

import com.ats.adminpanel.model.Info;

public class GetProdDetailBySubCatList {

	
	List<GetProdDetailBySubCat> prodDetailBySubCat;
	
	Info info;

	public List<GetProdDetailBySubCat> getProdDetailBySubCat() {
		return prodDetailBySubCat;
	}

	public void setProdDetailBySubCat(List<GetProdDetailBySubCat> prodDetailBySubCat) {
		this.prodDetailBySubCat = prodDetailBySubCat;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "GetProdDetailBySubCatList [prodDetailBySubCat=" + prodDetailBySubCat + ", info=" + info + "]";
	}
	
}

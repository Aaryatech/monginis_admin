
package com.ats.adminpanel.model;

import java.util.List;

public class SpCakeResponse {

   
    private List<SpecialCake> specialCake ;
   
    private Info info;

 
    

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

	public List<SpecialCake> getSpecialCake() {
		return specialCake;
	}

	public void setSpecialCake(List<SpecialCake> specialCake) {
		this.specialCake = specialCake;
	}

	@Override
	public String toString() {
		return "SpCakeResponse [specialCake=" + specialCake + ", info=" + info + "]";
	}


    
    
    
}

package com.ats.adminpanel.model;

import java.util.List;

public class SpCakeCatResponse {

	 private List<SpecialCake> specialCakeCat ;
	   
	    private Info info;

		public List<SpecialCake> getSpecialCakeCat() {
			return specialCakeCat;
		}

		public void setSpecialCakeCat(List<SpecialCake> specialCakeCat) {
			this.specialCakeCat = specialCakeCat;
		}

		public Info getInfo() {
			return info;
		}

		public void setInfo(Info info) {
			this.info = info;
		}

		@Override
		public String toString() {
			return "SpCakeCatResponse [specialCakeCat=" + specialCakeCat + ", info=" + info + "]";
		}
	    
	    

	    
}

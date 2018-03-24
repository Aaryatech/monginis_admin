package com.ats.adminpanel.model.flavours;

import java.util.List;

import com.ats.adminpanel.model.message.Info;

public class AllFlavoursListResponse {


private List<Flavour> flavour ;

private Info info;

public List<Flavour> getFlavour() {
return flavour;
}

public void setFlavour(List<Flavour> flavour) {
this.flavour = flavour;
}

public Info getInfo() {
return info;
}

public void setInfo(Info info) {
this.info = info;
}

@Override
public String toString() {
	return "AllFlavoursListResponse [flavour=" + flavour + ", info=" + info + "]";
}

}
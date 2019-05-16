package com.ats.adminpanel.model;

public class GetRegSpCakeOrderForProdApp {

	private int supId;
	private int tRegSupOrderId;
	private int srNo;
	private String photo1;
	private String photo2;
	private String prodDate;

	private int status;

	private float inputKgProd;
	private String isCharUsed;

	private String routeName;
	private int noInRoute;

	private String frName;
	private String frCode;
	private int frId;
	private int routeId;

	private int id;
	private String itemId;
	private String itemName;
	private String itemImage;

	private String rspPlace;
	private String rspDeliveryDt;

	private Long startTime;
	private Long endTime;

	private int subCatId;
	private String subCatName;
	private String catName;
	private int catId;

	public int getSupId() {
		return supId;
	}

	public void setSupId(int supId) {
		this.supId = supId;
	}

	public int gettRegSupOrderId() {
		return tRegSupOrderId;
	}

	public void settRegSupOrderId(int tRegSupOrderId) {
		this.tRegSupOrderId = tRegSupOrderId;
	}

	public int getSrNo() {
		return srNo;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}

	public String getPhoto1() {
		return photo1;
	}

	public void setPhoto1(String photo1) {
		this.photo1 = photo1;
	}

	public String getPhoto2() {
		return photo2;
	}

	public void setPhoto2(String photo2) {
		this.photo2 = photo2;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public float getInputKgProd() {
		return inputKgProd;
	}

	public void setInputKgProd(float inputKgProd) {
		this.inputKgProd = inputKgProd;
	}

	public String getIsCharUsed() {
		return isCharUsed;
	}

	public void setIsCharUsed(String isCharUsed) {
		this.isCharUsed = isCharUsed;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public int getNoInRoute() {
		return noInRoute;
	}

	public void setNoInRoute(int noInRoute) {
		this.noInRoute = noInRoute;
	}

	public String getFrName() {
		return frName;
	}

	public void setFrName(String frName) {
		this.frName = frName;
	}

	public String getFrCode() {
		return frCode;
	}

	public void setFrCode(String frCode) {
		this.frCode = frCode;
	}

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemImage() {
		return itemImage;
	}

	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}

	public String getRspPlace() {
		return rspPlace;
	}

	public void setRspPlace(String rspPlace) {
		this.rspPlace = rspPlace;
	}

	public int getSubCatId() {
		return subCatId;
	}

	public void setSubCatId(int subCatId) {
		this.subCatId = subCatId;
	}

	public String getSubCatName() {
		return subCatName;
	}

	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public String getProdDate() {
		return prodDate;
	}

	public void setProdDate(String prodDate) {
		this.prodDate = prodDate;
	}

	public String getRspDeliveryDt() {
		return rspDeliveryDt;
	}

	public void setRspDeliveryDt(String rspDeliveryDt) {
		this.rspDeliveryDt = rspDeliveryDt;
	}

	@Override
	public String toString() {
		return "GetRegSpCakeOrderForProdApp [supId=" + supId + ", tRegSupOrderId=" + tRegSupOrderId + ", srNo=" + srNo
				+ ", photo1=" + photo1 + ", photo2=" + photo2 + ", prodDate=" + prodDate + ", status=" + status
				+ ", inputKgProd=" + inputKgProd + ", isCharUsed=" + isCharUsed + ", routeName=" + routeName
				+ ", noInRoute=" + noInRoute + ", frName=" + frName + ", frCode=" + frCode + ", frId=" + frId
				+ ", routeId=" + routeId + ", id=" + id + ", itemId=" + itemId + ", itemName=" + itemName
				+ ", itemImage=" + itemImage + ", rspPlace=" + rspPlace + ", rspDeliveryDt=" + rspDeliveryDt
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", subCatId=" + subCatId + ", subCatName="
				+ subCatName + ", catName=" + catName + ", catId=" + catId + "]";
	}

}

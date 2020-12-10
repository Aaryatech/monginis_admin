package com.ats.adminpanel.model.album;

import java.util.List;

public class AlbumNew {

	private int albumId;

	private String albumCode;

	private String albumName;

	private String photo1;
	private String photo2;

	private float minWt;
	private float maxWt;

	private int delStatus;

	private int spId;

	private String flavourId;
	private int isActive;
	private String albumDesc;
	private int isVisibleToAlbum;

	private int enqNo;
	private int status;
	private List<Integer> menuList;
	List<Integer> frIds;

	private String token;

	private String category;
	private float mrp;

	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public String getAlbumCode() {
		return albumCode;
	}

	public void setAlbumCode(String albumCode) {
		this.albumCode = albumCode;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
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

	public float getMinWt() {
		return minWt;
	}

	public void setMinWt(float minWt) {
		this.minWt = minWt;
	}

	public float getMaxWt() {
		return maxWt;
	}

	public void setMaxWt(float maxWt) {
		this.maxWt = maxWt;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getSpId() {
		return spId;
	}

	public void setSpId(int spId) {
		this.spId = spId;
	}

	public String getFlavourId() {
		return flavourId;
	}

	public void setFlavourId(String flavourId) {
		this.flavourId = flavourId;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getAlbumDesc() {
		return albumDesc;
	}

	public void setAlbumDesc(String albumDesc) {
		this.albumDesc = albumDesc;
	}

	public int getIsVisibleToAlbum() {
		return isVisibleToAlbum;
	}

	public void setIsVisibleToAlbum(int isVisibleToAlbum) {
		this.isVisibleToAlbum = isVisibleToAlbum;
	}

	public int getEnqNo() {
		return enqNo;
	}

	public void setEnqNo(int enqNo) {
		this.enqNo = enqNo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Integer> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Integer> menuList) {
		this.menuList = menuList;
	}

	public List<Integer> getFrIds() {
		return frIds;
	}

	public void setFrIds(List<Integer> frIds) {
		this.frIds = frIds;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getMrp() {
		return mrp;
	}

	public void setMrp(float mrp) {
		this.mrp = mrp;
	}

	@Override
	public String toString() {
		return "AlbumNew [albumId=" + albumId + ", albumCode=" + albumCode + ", albumName=" + albumName + ", photo1="
				+ photo1 + ", photo2=" + photo2 + ", minWt=" + minWt + ", maxWt=" + maxWt + ", delStatus=" + delStatus
				+ ", spId=" + spId + ", flavourId=" + flavourId + ", isActive=" + isActive + ", albumDesc=" + albumDesc
				+ ", isVisibleToAlbum=" + isVisibleToAlbum + ", enqNo=" + enqNo + ", status=" + status + ", menuList="
				+ menuList + ", frIds=" + frIds + ", token=" + token + ", category=" + category + ", mrp=" + mrp + "]";
	}

}

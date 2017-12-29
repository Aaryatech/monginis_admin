package com.ats.adminpanel.model.franchisee;

import java.sql.Date;
import com.sun.istack.internal.NotNull;

public class FranchiseeList {

private int frId;

private String frName;

private String frCode;

private String frOpeningDate;

public String getFrOpeningDate() {
	return frOpeningDate;
}

public void setFrOpeningDate(String frOpeningDate) {
	this.frOpeningDate = frOpeningDate;
}

private int frRate;

private String frImage;

private int frRouteId;

private String frCity;

private int frKg1;

private int frKg2;

private int frKg3;

private int frKg4;

private String frEmail;


private String frPassword;

private String frMob;

private String frOwner;

private int frRateCat;

private int grnTwo;

private int delStatus;

private String frRmn1;

private String showItems;

private String notShowItems;

private int frOpening;

private String frPasswordKey;

private int isSameDayApplicable;

private String ownerBirthDate;

private String fbaLicenseDate;

private String frAgreementDate;

private String frGstType;

private String frGstNo;

private Integer stockType;

private String frAddress;

private String frTarget;

private int isSameState;


public int getIsSameState() {
	return isSameState;
}

public void setIsSameState(int isSameState) {
	this.isSameState = isSameState;
}

public String getOwnerBirthDate() {
	return ownerBirthDate;
}

public void setOwnerBirthDate(String ownerBirthDate) {
	this.ownerBirthDate = ownerBirthDate;
}


public String getFbaLicenseDate() {
	return fbaLicenseDate;
}

public void setFbaLicenseDate(String fbaLicenseDate) {
	this.fbaLicenseDate = fbaLicenseDate;
}

public String getFrAgreementDate() {
	return frAgreementDate;
}

public void setFrAgreementDate(String frAgreementDate) {
	this.frAgreementDate = frAgreementDate;
}

public String getFrGstType() {
	return frGstType;
}

public void setFrGstType(String frGstType) {
	this.frGstType = frGstType;
}

public String getFrGstNo() {
	return frGstNo;
}

public void setFrGstNo(String frGstNo) {
	this.frGstNo = frGstNo;
}

public Integer getStockType() {
	return stockType;
}

public void setStockType(Integer stockType) {
	this.stockType = stockType;
}

public String getFrAddress() {
	return frAddress;
}

public void setFrAddress(String frAddress) {
	this.frAddress = frAddress;
}

public String getFrTarget() {
	return frTarget;
}

public void setFrTarget(String frTarget) {
	this.frTarget = frTarget;
}

public int getFrId() {
	return frId;
}

public void setFrId(int frId) {
	this.frId = frId;
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


public int getFrRate() {
	return frRate;
}

public void setFrRate(int frRate) {
	this.frRate = frRate;
}

public String getFrImage() {
	return frImage;
}

public void setFrImage(String frImage) {
	this.frImage = frImage;
}

public int getFrRouteId() {
	return frRouteId;
}

public void setFrRouteId(int frRouteId) {
	this.frRouteId = frRouteId;
}

public String getFrCity() {
	return frCity;
}

public void setFrCity(String frCity) {
	this.frCity = frCity;
}

public int getFrKg1() {
	return frKg1;
}

public void setFrKg1(int frKg1) {
	this.frKg1 = frKg1;
}

public int getFrKg2() {
	return frKg2;
}

public void setFrKg2(int frKg2) {
	this.frKg2 = frKg2;
}

public int getFrKg3() {
	return frKg3;
}

public void setFrKg3(int frKg3) {
	this.frKg3 = frKg3;
}

public int getFrKg4() {
	return frKg4;
}

public void setFrKg4(int frKg4) {
	this.frKg4 = frKg4;
}

public String getFrEmail() {
	return frEmail;
}

public void setFrEmail(String frEmail) {
	this.frEmail = frEmail;
}

public String getFrPassword() {
	return frPassword;
}

public void setFrPassword(String frPassword) {
	this.frPassword = frPassword;
}

public String getFrMob() {
	return frMob;
}

public void setFrMob(String frMob) {
	this.frMob = frMob;
}

public String getFrOwner() {
	return frOwner;
}

public void setFrOwner(String frOwner) {
	this.frOwner = frOwner;
}

public int getFrRateCat() {
	return frRateCat;
}

public void setFrRateCat(int frRateCat) {
	this.frRateCat = frRateCat;
}

public int getGrnTwo() {
	return grnTwo;
}

public void setGrnTwo(int grnTwo) {
	this.grnTwo = grnTwo;
}

public int getDelStatus() {
	return delStatus;
}

public void setDelStatus(int delStatus) {
	this.delStatus = delStatus;
}

public String getFrRmn1() {
	return frRmn1;
}

public void setFrRmn1(String frRmn1) {
	this.frRmn1 = frRmn1;
}

public String getShowItems() {
	return showItems;
}

public void setShowItems(String showItems) {
	this.showItems = showItems;
}

public String getNotShowItems() {
	return notShowItems;
}

public void setNotShowItems(String notShowItems) {
	this.notShowItems = notShowItems;
}

public int getFrOpening() {
	return frOpening;
}

public void setFrOpening(int frOpening) {
	this.frOpening = frOpening;
}

public String getFrPasswordKey() {
	return frPasswordKey;
}

public void setFrPasswordKey(String frPasswordKey) {
	this.frPasswordKey = frPasswordKey;
}

public int getIsSameDayApplicable() {
	return isSameDayApplicable;
}

public void setIsSameDayApplicable(int isSameDayApplicable) {
	this.isSameDayApplicable = isSameDayApplicable;
}

@Override
public String toString() {
	return "FranchiseeList [frId=" + frId + ", frName=" + frName + ", frCode=" + frCode + ", frOpeningDate="
			+ frOpeningDate + ", frRate=" + frRate + ", frImage=" + frImage + ", frRouteId=" + frRouteId + ", frCity="
			+ frCity + ", frKg1=" + frKg1 + ", frKg2=" + frKg2 + ", frKg3=" + frKg3 + ", frKg4=" + frKg4 + ", frEmail="
			+ frEmail + ", frPassword=" + frPassword + ", frMob=" + frMob + ", frOwner=" + frOwner + ", frRateCat="
			+ frRateCat + ", grnTwo=" + grnTwo + ", delStatus=" + delStatus + ", frRmn1=" + frRmn1 + ", showItems="
			+ showItems + ", notShowItems=" + notShowItems + ", frOpening=" + frOpening + ", frPasswordKey="
			+ frPasswordKey + ", isSameDayApplicable=" + isSameDayApplicable + "]";
}


}



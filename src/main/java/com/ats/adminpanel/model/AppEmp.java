package com.ats.adminpanel.model;

public class AppEmp {

	private int appEmpId;

	private int empId;
	private String empDsc;

	private String token1;
	private String token2;
	private String token3;
	private String token4;
	private String token5;
	private String token6;
	private String token7;
	private String token8;

	private Integer exInt1;
	private Integer exInt2;
	private String exVar1;
	private String exVar2;
	
	

	public AppEmp() {
		
	}

	public AppEmp(int appEmpId, int empId, String empDsc, String token1, String token2, String token3, String token4,
			String token5, String token6, String token7, String token8, Integer exInt1, Integer exInt2, String exVar1,
			String exVar2) {
		super();
		this.appEmpId = appEmpId;
		this.empId = empId;
		this.empDsc = empDsc;
		this.token1 = token1;
		this.token2 = token2;
		this.token3 = token3;
		this.token4 = token4;
		this.token5 = token5;
		this.token6 = token6;
		this.token7 = token7;
		this.token8 = token8;
		this.exInt1 = exInt1;
		this.exInt2 = exInt2;
		this.exVar1 = exVar1;
		this.exVar2 = exVar2;
	}

	public int getAppEmpId() {
		return appEmpId;
	}

	public void setAppEmpId(int appEmpId) {
		this.appEmpId = appEmpId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpDsc() {
		return empDsc;
	}

	public void setEmpDsc(String empDsc) {
		this.empDsc = empDsc;
	}

	public String getToken1() {
		return token1;
	}

	public void setToken1(String token1) {
		this.token1 = token1;
	}

	public String getToken2() {
		return token2;
	}

	public void setToken2(String token2) {
		this.token2 = token2;
	}

	public String getToken3() {
		return token3;
	}

	public void setToken3(String token3) {
		this.token3 = token3;
	}

	public String getToken4() {
		return token4;
	}

	public void setToken4(String token4) {
		this.token4 = token4;
	}

	public String getToken5() {
		return token5;
	}

	public void setToken5(String token5) {
		this.token5 = token5;
	}

	public String getToken6() {
		return token6;
	}

	public void setToken6(String token6) {
		this.token6 = token6;
	}

	public String getToken7() {
		return token7;
	}

	public void setToken7(String token7) {
		this.token7 = token7;
	}

	public String getToken8() {
		return token8;
	}

	public void setToken8(String token8) {
		this.token8 = token8;
	}

	public Integer getExInt1() {
		return exInt1;
	}

	public void setExInt1(Integer exInt1) {
		this.exInt1 = exInt1;
	}

	public Integer getExInt2() {
		return exInt2;
	}

	public void setExInt2(Integer exInt2) {
		this.exInt2 = exInt2;
	}

	public String getExVar1() {
		return exVar1;
	}

	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}

	public String getExVar2() {
		return exVar2;
	}

	public void setExVar2(String exVar2) {
		this.exVar2 = exVar2;
	}

	@Override
	public String toString() {
		return "AppEmp [appEmpId=" + appEmpId + ", empId=" + empId + ", empDsc=" + empDsc + ", token1=" + token1
				+ ", token2=" + token2 + ", token3=" + token3 + ", token4=" + token4 + ", token5=" + token5
				+ ", token6=" + token6 + ", token7=" + token7 + ", token8=" + token8 + ", exInt1=" + exInt1
				+ ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}
	
}

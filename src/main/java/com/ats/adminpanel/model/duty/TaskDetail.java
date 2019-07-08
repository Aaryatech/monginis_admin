package com.ats.adminpanel.model.duty;

public class TaskDetail {

	private int taskId;
	private int dutyId;
	private String taskNameEng;
	private String taskNameMar;
	private String taskNameHin;
	private String taskDescEng;
	private String taskDescMar;
	private String taskDescHin;
	private int photoReq;
	private int remarkReq;
	private int taskWeight;
	private int createdBy;
	private String createdDate;
	private int delStatus;
	private Integer exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1;
	private String exVar2;
	private String exVar3;
	
	

	public TaskDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	public TaskDetail(int taskId, int dutyId, String taskNameEng, String taskNameMar, String taskNameHin,
			String taskDescEng, String taskDescMar, String taskDescHin, int photoReq, int remarkReq, int taskWeight,
			int createdBy, String createdDate, int delStatus) {
		super();
		this.taskId = taskId;
		this.dutyId = dutyId;
		this.taskNameEng = taskNameEng;
		this.taskNameMar = taskNameMar;
		this.taskNameHin = taskNameHin;
		this.taskDescEng = taskDescEng;
		this.taskDescMar = taskDescMar;
		this.taskDescHin = taskDescHin;
		this.photoReq = photoReq;
		this.remarkReq = remarkReq;
		this.taskWeight = taskWeight;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.delStatus = delStatus;
	}




	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getDutyId() {
		return dutyId;
	}

	public void setDutyId(int dutyId) {
		this.dutyId = dutyId;
	}

	public String getTaskNameEng() {
		return taskNameEng;
	}

	public void setTaskNameEng(String taskNameEng) {
		this.taskNameEng = taskNameEng;
	}

	public String getTaskNameMar() {
		return taskNameMar;
	}

	public void setTaskNameMar(String taskNameMar) {
		this.taskNameMar = taskNameMar;
	}

	public String getTaskNameHin() {
		return taskNameHin;
	}

	public void setTaskNameHin(String taskNameHin) {
		this.taskNameHin = taskNameHin;
	}

	public String getTaskDescEng() {
		return taskDescEng;
	}

	public void setTaskDescEng(String taskDescEng) {
		this.taskDescEng = taskDescEng;
	}

	public String getTaskDescMar() {
		return taskDescMar;
	}

	public void setTaskDescMar(String taskDescMar) {
		this.taskDescMar = taskDescMar;
	}

	public String getTaskDescHin() {
		return taskDescHin;
	}

	public void setTaskDescHin(String taskDescHin) {
		this.taskDescHin = taskDescHin;
	}

	public int getPhotoReq() {
		return photoReq;
	}

	public void setPhotoReq(int photoReq) {
		this.photoReq = photoReq;
	}

	public int getRemarkReq() {
		return remarkReq;
	}

	public void setRemarkReq(int remarkReq) {
		this.remarkReq = remarkReq;
	}

	public int getTaskWeight() {
		return taskWeight;
	}

	public void setTaskWeight(int taskWeight) {
		this.taskWeight = taskWeight;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
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

	public Integer getExInt3() {
		return exInt3;
	}

	public void setExInt3(Integer exInt3) {
		this.exInt3 = exInt3;
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

	public String getExVar3() {
		return exVar3;
	}

	public void setExVar3(String exVar3) {
		this.exVar3 = exVar3;
	}

	@Override
	public String toString() {
		return "TaskDetail [taskId=" + taskId + ", dutyId=" + dutyId + ", taskNameEng=" + taskNameEng + ", taskNameMar="
				+ taskNameMar + ", taskNameHin=" + taskNameHin + ", taskDescEng=" + taskDescEng + ", taskDescMar="
				+ taskDescMar + ", taskDescHin=" + taskDescHin + ", photoReq=" + photoReq + ", remarkReq=" + remarkReq
				+ ", taskWeight=" + taskWeight + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3
				+ ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + "]";
	}

}

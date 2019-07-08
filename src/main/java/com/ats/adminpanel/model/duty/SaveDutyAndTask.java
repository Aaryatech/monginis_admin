package com.ats.adminpanel.model.duty;

import java.util.List;

public class SaveDutyAndTask {

	private DutyHeader dutyHeader;
	private List<TaskDetail> taskDetailList;

	public DutyHeader getDutyHeader() {
		return dutyHeader;
	}

	public void setDutyHeader(DutyHeader dutyHeader) {
		this.dutyHeader = dutyHeader;
	}

	public List<TaskDetail> getTaskDetailList() {
		return taskDetailList;
	}

	public void setTaskDetailList(List<TaskDetail> taskDetailList) {
		this.taskDetailList = taskDetailList;
	}

	@Override
	public String toString() {
		return "SaveDutyAndTask [dutyHeader=" + dutyHeader + ", taskDetailList=" + taskDetailList + "]";
	}

}

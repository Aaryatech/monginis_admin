package com.ats.adminpanel.model;

import java.util.List;

public class ExportToExcel {

	
	List<String> rowData;

	public List<String> getRowData() {
		return rowData;
	}

	public void setRowData(List<String> rowData) {
		this.rowData = rowData;
	}

	@Override
	public String toString() {
		return "ExportToExcel [rowData=" + rowData + "]";
	}
	
}

package com.ats.adminpanel.model.hr;

public class Settings {
	
	private int settingId;
	private String settingKey;
	private String settingValue;
	public int getSettingId() {
		return settingId;
	}
	public void setSettingId(int settingId) {
		this.settingId = settingId;
	}
	public String getSettingKey() {
		return settingKey;
	}
	public void setSettingKey(String settingKey) {
		this.settingKey = settingKey;
	}
	public String getSettingValue() {
		return settingValue;
	}
	public void setSettingValue(String settingValue) {
		this.settingValue = settingValue;
	}
	@Override
	public String toString() {
		return "Settings [settingId=" + settingId + ", settingKey=" + settingKey + ", settingValue=" + settingValue
				+ "]";
	}
	
	
	

}

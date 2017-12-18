
package com.ats.adminpanel.model.item;


public class FrItemStockConfigure {

    private Integer settingId;
    private String settingKey;
    private Integer settingValue;

    public Integer getSettingId() {
        return settingId;
    }

    public void setSettingId(Integer settingId) {
        this.settingId = settingId;
    }

    public String getSettingKey() {
        return settingKey;
    }

    public void setSettingKey(String settingKey) {
        this.settingKey = settingKey;
    }

    public Integer getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(Integer settingValue) {
        this.settingValue = settingValue;
    }

	@Override
	public String toString() {
		return "FrItemStockConfigure [settingId=" + settingId + ", settingKey=" + settingKey + ", settingValue="
				+ settingValue + "]";
	}

}

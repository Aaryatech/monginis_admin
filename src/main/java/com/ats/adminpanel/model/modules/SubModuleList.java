
package com.ats.adminpanel.model.modules;


public class SubModuleList {

  
    private Integer subModuleId;
  
    private Integer moduleId;
  
    private String subModuleName;
  
    private String subModuleDesc;
  
    private Integer isDelete;

    public Integer getSubModuleId() {
        return subModuleId;
    }

    public void setSubModuleId(Integer subModuleId) {
        this.subModuleId = subModuleId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getSubModuleName() {
        return subModuleName;
    }

    public void setSubModuleName(String subModuleName) {
        this.subModuleName = subModuleName;
    }

    public String getSubModuleDesc() {
        return subModuleDesc;
    }

    public void setSubModuleDesc(String subModuleDesc) {
        this.subModuleDesc = subModuleDesc;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

	@Override
	public String toString() {
		return "SubModuleList [subModuleId=" + subModuleId + ", moduleId=" + moduleId + ", subModuleName="
				+ subModuleName + ", subModuleDesc=" + subModuleDesc + ", isDelete=" + isDelete + "]";
	}

}

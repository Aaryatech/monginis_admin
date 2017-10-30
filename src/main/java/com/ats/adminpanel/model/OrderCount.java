
package com.ats.adminpanel.model;


public class OrderCount {

    private Integer menuId;
    private String menuTitle;
    private Integer total;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

	@Override
	public String toString() {
		return "OrderCount [menuId=" + menuId + ", menuTitle=" + menuTitle + ", total=" + total + "]";
	}
    
    

}

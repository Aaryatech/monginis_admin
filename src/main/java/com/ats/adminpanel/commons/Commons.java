package com.ats.adminpanel.commons;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.model.accessright.AccessRightModule;
import com.ats.adminpanel.model.accessright.AccessRightSubModule;
import com.ats.adminpanel.model.accessright.ModuleJson;
import com.ats.adminpanel.model.accessright.SubModuleJson;

public class Commons {

	
	//public static List<ModuleJson> newModuleList=null;
	  
	static HttpServletRequest request;
 
	public static SubModuleJson getAccess(int moduleId, int subModuleId)
	{
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList=(List)session.getAttribute("newModuleList");
		SubModuleJson subModuleJson=new SubModuleJson();
		for(int i=0;i<newModuleList.size();i++)
			if(newModuleList.get(i).getModuleId()==moduleId)
			for(int j=0;j<newModuleList.get(i).getSubModuleJsonList().size();j++)
				if(newModuleList.get(i).getSubModuleJsonList().get(j).getSubModuleId()==subModuleId)
					{
					subModuleJson=newModuleList.get(i).getSubModuleJsonList().get(j);
						break;
						
					}
		return subModuleJson;
	}
}

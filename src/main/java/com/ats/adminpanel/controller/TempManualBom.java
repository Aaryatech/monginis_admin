package com.ats.adminpanel.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.Info;
import com.ats.adminpanel.model.RawMaterial.ItemSfHeader;
import com.ats.adminpanel.model.RawMaterial.ItemSfHeaderList;
import com.ats.adminpanel.model.RawMaterial.RawMaterialDetails;
import com.ats.adminpanel.model.RawMaterial.RawMaterialDetailsList;
import com.ats.adminpanel.model.RawMaterial.RawMaterialUom;
import com.ats.adminpanel.model.RawMaterial.RawMaterialUomList;
import com.ats.adminpanel.model.franchisee.CommonConf;
import com.ats.adminpanel.model.login.UserResponse;
import com.ats.adminpanel.model.productionplan.BillOfMaterialDetailed;
import com.ats.adminpanel.model.productionplan.BillOfMaterialHeader;
import com.ats.adminpanel.model.spprod.MDeptList;

@Controller
@Scope("session")
public class TempManualBom {

	public  List<CommonConf> commonConfs = new ArrayList<CommonConf>();
	List<BillOfMaterialDetailed> bomDetailList = new ArrayList<BillOfMaterialDetailed>();
	boolean isMix=false;
	
	@RequestMapping(value = "/goToManualBom", method = RequestMethod.POST)
	public ModelAndView manualBom(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		bomDetailList = new ArrayList<BillOfMaterialDetailed>();
		try
		{
			int isProd=0;
			int headerId = 0;
			String prodDate = null; 
			String mixOrProd=request.getParameter("isMixing");//isMixing is hidden field on showmixindetaile jsp: to determine which req
			
			if(mixOrProd==null) {
			
			 headerId = Integer.parseInt(request.getParameter("production_id"));

			 prodDate = request.getParameter("prod_date");
			 isProd=1;
			
			}
			
			else {
				isMix=true;
				 headerId = Integer.parseInt(request.getParameter("mixId"));

				 prodDate = request.getParameter("mix_date");
				
			}
			
			modelAndView = new ModelAndView("production/manualBom");
			System.out.println("inside manual BoM");
			RestTemplate restTemplate = new RestTemplate();

			MDeptList mDeptList = restTemplate.getForObject(Constants.url + "/spProduction/mDeptList", MDeptList.class);
			System.out.println("Response: " + mDeptList.toString());

			modelAndView.addObject("deptList", mDeptList.getList());

			modelAndView.addObject("prodHeaderId", headerId);

			modelAndView.addObject("prodDate", prodDate);
			modelAndView.addObject("isProd",isProd);
		}catch(Exception e)
		{
			e.printStackTrace();
		}


		return modelAndView;

	}

	@RequestMapping(value = "/getMaterial", method = RequestMethod.GET)
	public @ResponseBody List<CommonConf> getRawMaterialList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = new ModelAndView("production/manualBom");

		int rmType = Integer.parseInt(request.getParameter("material_type"));
		System.out.println("rmType:" + rmType);

		RestTemplate rest = new RestTemplate();

		List<CommonConf> commonConfList = new ArrayList<CommonConf>();

		if (rmType == 1) {
			System.out.println("inside if");
			try {
				RawMaterialDetailsList rawMaterialDetailsList = rest
						.getForObject(Constants.url + "rawMaterial/getAllRawMaterial", RawMaterialDetailsList.class);

				System.out.println("RM Details : " + rawMaterialDetailsList.toString());

				for (RawMaterialDetails rawMaterialDetails : rawMaterialDetailsList.getRawMaterialDetailsList()) {
					CommonConf commonConf = new CommonConf();

					commonConf.setId(rawMaterialDetails.getRmId());
					commonConf.setName(rawMaterialDetails.getRmName());
					commonConf.setRmUomId(rawMaterialDetails.getRmUomId());

					commonConfList.add(commonConf);
					commonConfs.add(commonConf);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Common Rm List1:" + commonConfList.toString());

		} else {
			// if rmType=2,call Semi finished service
			ItemSfHeaderList itemHeaderDetailList = rest.getForObject(Constants.url + "rawMaterial/getItemSfHeaders",
					ItemSfHeaderList.class);

			System.out.println("ItemSfHeaderList Details : " + itemHeaderDetailList.toString());

			for (ItemSfHeader itemSfHeader : itemHeaderDetailList.getItemSfHeaderList()) {
				CommonConf commonConf = new CommonConf();

				commonConf.setId(itemSfHeader.getSfId());
				commonConf.setName(itemSfHeader.getSfName());
				commonConf.setRmUomId(itemSfHeader.getSfUomId());

				commonConfList.add(commonConf);
				commonConfs.add(commonConf);

			}
			System.out.println("Common Rm List2:" + commonConfList.toString());

		}

		return commonConfList;
	}

	@RequestMapping(value = "/getMatUom", method = RequestMethod.GET)
	public @ResponseBody RawMaterialUom getMatUom(HttpServletRequest request, HttpServletResponse response) {

		RawMaterialUom uomObject = null;
		CommonConf cf = new CommonConf();
		String matNameId = request.getParameter("rm_material_name");

		int unitId = 0;
		for (int i = 0; i < commonConfs.size(); i++) {

			if (commonConfs.get(i).getId() == Integer.parseInt(matNameId)) {

				unitId = commonConfs.get(i).getRmUomId();

			}

		}

		RestTemplate rest = new RestTemplate();

		System.out.println("rm mat name " + matNameId);

		RawMaterialUomList rawMaterialUomList = rest.getForObject(Constants.url + "rawMaterial/getRmUomList",
				RawMaterialUomList.class);

		List<RawMaterialUom> uomList = rawMaterialUomList.getRawMaterialUom();

		for (int i = 0; i < uomList.size(); i++) {

			RawMaterialUom uom = uomList.get(i);

			if (uom.getUomId() == unitId) {
				uomObject = uomList.get(i);
			}

			System.out.println("raw mat uom new  = " + uomObject.toString());

		}

		return uomObject;

	}

	

	@RequestMapping(value = "/manBomAddItem", method = RequestMethod.GET)
	public @ResponseBody List<BillOfMaterialDetailed> manBomAddItem(HttpServletRequest request,
			HttpServletResponse response) {

		BillOfMaterialDetailed bomDetail = new BillOfMaterialDetailed();

		int materialType = Integer.parseInt(request.getParameter("mat_type"));

		int materialNameId = Integer.parseInt(request.getParameter("mat_name_id"));

		String uom = request.getParameter("uom");

		float qty = Float.parseFloat(request.getParameter("qty"));

		String matName = request.getParameter("mat_name");

		bomDetail.setRmId(materialNameId);
		bomDetail.setRmName(matName);
		bomDetail.setRmReqQty(qty);
		bomDetail.setRmType(materialType);
		bomDetail.setUom(uom);
		bomDetail.setReturnQty(0);


		bomDetailList.add(bomDetail);

		return bomDetailList;

	}

	@RequestMapping(value = "/deleteBomDetail", method = RequestMethod.GET)
	public @ResponseBody List<BillOfMaterialDetailed> deleteBomDetail(HttpServletRequest request,
			HttpServletResponse response) {

		int key = Integer.parseInt(request.getParameter("key"));

		bomDetailList.remove(key);

		return bomDetailList;
	}

	@RequestMapping(value = "/insertBomHeader", method = RequestMethod.GET)
	public String insertBomHeader(HttpServletRequest request, HttpServletResponse response) {

		BillOfMaterialHeader bomHeader = new BillOfMaterialHeader();
		try {
			
			HttpSession session = request.getSession();
			UserResponse userResponse = (UserResponse) session.getAttribute("UserDetail");

			int deptId = userResponse.getUser().getDeptId();

			int userId = userResponse.getUser().getId();

			System.out.println(" inside Header Insert NNNNNNNNN ");

			System.out.println(" bomDetailList " + bomDetailList.toString());

			int fromDept = Integer.parseInt(request.getParameter("fromDept"));

			int toDept = Integer.parseInt(request.getParameter("toDept"));

			int headerId = Integer.parseInt(request.getParameter("headerId"));

			String prodDate = request.getParameter("prodDate");

			String fromDeptName = request.getParameter("fromDeptName");

			String toDeptName = request.getParameter("toDeptName");

			String uom = request.getParameter("uom");

			Date date = new Date();

			Date prodOrMixDate = null;

			System.out.println("after date " + prodDate);

			SimpleDateFormat dtFormat = new SimpleDateFormat("dd-MM-yyyy");

			try {
				prodOrMixDate = dtFormat.parse(prodDate);
			} catch (ParseException e1) {

				System.out.println("Exce In Date conversion");
				e1.printStackTrace();
			}

			
			if(isMix) {
				
				bomHeader.setIsProduction(0);
				
			}
			else {
				bomHeader.setIsProduction(1);

			}
			
			bomHeader.setRejApproveDate(date);
			bomHeader.setRejApproveUserId(0);
			bomHeader.setRejDate(date);
			bomHeader.setRejUserId(0);
			
			bomHeader.setApprovedDate(date);
			bomHeader.setApprovedUserId(0);
			bomHeader.setDelStatus(0);
			bomHeader.setFromDeptId(fromDept);
			bomHeader.setFromDeptName(fromDeptName);
			bomHeader.setIsManual(1);
			bomHeader.setIsPlan(0);
			bomHeader.setProductionDate(prodOrMixDate);
			bomHeader.setProductionId(headerId);
			bomHeader.setReqDate(date);
			bomHeader.setSenderUserid(userId);
			bomHeader.setStatus(0);
			bomHeader.setToDeptId(toDept);
			bomHeader.setToDeptName(toDeptName);

			bomHeader.setBillOfMaterialDetailed(bomDetailList);

			RestTemplate restTemplate = new RestTemplate();

			Info info = restTemplate.postForObject(Constants.url + "saveBom", bomHeader, Info.class);

			System.out.println("After Insert Bom Header " + info.getMessage());
		} catch (Exception e) {
			System.out.println("Ex in manual bom insert " + e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/manualBom";

	}

}

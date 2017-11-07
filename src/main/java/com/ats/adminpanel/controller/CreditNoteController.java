package com.ats.adminpanel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.model.creditnote.GetGrnGvnForCreditNote;
import com.ats.adminpanel.model.creditnote.GetGrnGvnForCreditNoteList;

@Controller
public class CreditNoteController {
	
	
	
	
	GetGrnGvnForCreditNoteList getGrnGvnForCreditNoteList;
	
	List<GetGrnGvnForCreditNote> getGrnGvnForCreditNote;
	
	@RequestMapping(value = "/showInsertCreditNote", method = RequestMethod.GET)
	public ModelAndView showInsertCreditNote(HttpServletRequest request, HttpServletResponse response) {
		
		Constants.mainAct = 8;
		Constants.subAct = 85;

		ModelAndView model = new ModelAndView("creditNote/generateCreditNote");
		
		
	

		try {
					
			RestTemplate restTemplate = new RestTemplate();
			
			getGrnGvnForCreditNoteList=restTemplate.getForObject(Constants.url+"grnGvnDetailForCreditNote" ,GetGrnGvnForCreditNoteList.class );
			
			
			
			 getGrnGvnForCreditNote=new ArrayList<>();
			 
			 getGrnGvnForCreditNote=getGrnGvnForCreditNoteList.getGetGrnGvnForCreditNotes();
			 

				System.out.println("grn gvn for credit note  : "+getGrnGvnForCreditNote.toString());
							
			 
			 
			 
		} catch (Exception e) {

			System.out.println("Error in Getting grngvn for credit details " + e.getMessage());

			e.printStackTrace();
		}
		
		model.addObject("creditNoteList",getGrnGvnForCreditNote);

		return model;

	}


}

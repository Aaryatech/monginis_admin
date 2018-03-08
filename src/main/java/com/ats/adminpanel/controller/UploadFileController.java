package com.ats.adminpanel.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.commons.VpsImageUpload;
import com.ats.adminpanel.model.MRule; 
 
@Controller
@Scope("session")
public class UploadFileController {
	
	List<MRule> fileList = new ArrayList<>();
	String document1;
	String document2;
	String document3;
	String document4;
	
	@RequestMapping(value = "/uploadRuleFile", method = RequestMethod.GET)
	public ModelAndView bmsstock(HttpServletRequest request, HttpServletResponse response) { 
		
		ModelAndView model = new ModelAndView("uploadfile/uploadRuleFile");
		try { 
			RestTemplate rest = new RestTemplate();
			MRule[] list = rest.getForObject(Constants.url + "getRuleFile",
					MRule[].class);
			fileList = new ArrayList<>(Arrays.asList(list));
			System.out.println("fileList"+fileList);
			document1=fileList.get(0).getFileName();
			document2=fileList.get(1).getFileName();
			document3=fileList.get(2).getFileName();
			document4=fileList.get(3).getFileName();
			 
			model.addObject("document1", document1);
			model.addObject("document2", document2);
			model.addObject("document3", document3);
			model.addObject("document4", document4);
			model.addObject("date1", fileList.get(0).getDate());
			model.addObject("date2", fileList.get(1).getDate());
			model.addObject("date3", fileList.get(2).getDate());
			model.addObject("date4", fileList.get(3).getDate());
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		

		return model;

	}
	
	@RequestMapping(value = "/viewRuleDocumentFile/{flag}", method = RequestMethod.GET)
	public void viewDocumentFile(@PathVariable int flag,HttpServletRequest request, HttpServletResponse response) {

		File file = null;
		 if(flag==1)
		 {
			 file = new File(Constants.LOGIS_BILL_URL+document1);
		 }
		 else if(flag==2)
		 {
			file = new File(Constants.LOGIS_BILL_URL+document2);
		 }
		  
			System.out.println("file"+file);
			if(file != null) {

                String mimeType = URLConnection.guessContentTypeFromName(file.getName());

                if (mimeType == null) {

                    mimeType = "application/pdf";

                }

                response.setContentType(mimeType);

                response.addHeader("content-disposition", String.format("inline; filename=\"%s\"", file.getName()));
 

                response.setContentLength((int) file.length());

                InputStream inputStream = null;
				try {
					inputStream = new BufferedInputStream(new FileInputStream(file));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

                try {
                    FileCopyUtils.copy(inputStream, response.getOutputStream());
                } catch (IOException e) {
                    System.out.println("Excep in Opening a Pdf File");
                    e.printStackTrace();
                }
            }
 
	}
	
	 @RequestMapping("/download/{flag}")
	    public void downloadPDFResource(@PathVariable int flag, HttpServletRequest request,
	                                     HttpServletResponse response)
	    {
	         
	        Path file = null;
	        String fileName = null;
	        if(flag==3)
	        {
	        	 file = Paths.get(Constants.LOGIS_BILL_URL, document3);
	        	 fileName=document3;
	        }
	        else if(flag==4)
	        {
	        	file = Paths.get(Constants.LOGIS_BILL_URL, document4);
	        	fileName=document4;
	        }
	        if (Files.exists(file))
	        {
	            response.setContentType("application/pdf");
	            response.addHeader("Content-Disposition", "attachment; filename="+fileName);
	            try
	            {
	                Files.copy(file, response.getOutputStream());
	                response.getOutputStream().flush();
	            }
	            catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	    }
	 
	
	@RequestMapping(value = "/submitUploadFile", method = RequestMethod.POST)
	public String submitUploadFile(@RequestParam("attachFile1") List<MultipartFile> attachFile1,@RequestParam("attachFile2") List<MultipartFile> attachFile2,
			@RequestParam("attachFile3") List<MultipartFile> attachFile3,@RequestParam("attachFile4") List<MultipartFile> attachFile4,
			HttpServletRequest request, HttpServletResponse response) { 
		try
		{
			RestTemplate rest = new RestTemplate();
			String document1 = request.getParameter("document1");
			String document2 = request.getParameter("document2");
			String document3 = request.getParameter("document3");
			String document4 = request.getParameter("document4");
			SimpleDateFormat formate = new SimpleDateFormat("dd-MM-yyyy");
			VpsImageUpload upload = new VpsImageUpload();
			String docFile1 = null;
			String docFile2 = null;
			String docFile3 = null;
			String docFile4 = null;
			try {
				docFile1 = attachFile1.get(0).getOriginalFilename();
				docFile2 = attachFile2.get(0).getOriginalFilename();
				docFile3 = attachFile3.get(0).getOriginalFilename();
				docFile4 = attachFile4.get(0).getOriginalFilename(); 
				
				upload.saveUploadedFiles(attachFile1, Constants.LOGIS_BILL_PDF_TYPE,
						attachFile1.get(0).getOriginalFilename());
				upload.saveUploadedFiles(attachFile2, Constants.LOGIS_BILL_PDF_TYPE,
						attachFile2.get(0).getOriginalFilename());
				upload.saveUploadedFiles(attachFile3, Constants.LOGIS_BILL_PDF_TYPE,
						attachFile3.get(0).getOriginalFilename());
				upload.saveUploadedFiles(attachFile4, Constants.LOGIS_BILL_PDF_TYPE,
						attachFile4.get(0).getOriginalFilename()); 
				if(fileList.size()>0)
				{
					 
						 if(docFile1!=null && docFile1.length()>0)
						 {
							 fileList.get(0).setFileName(docFile1);
							 fileList.get(0).setDate(formate.format(new Date()));
						 }
						 else
							 fileList.get(0).setFileName(document1);
						 
						 if(docFile2!=null && docFile2.length()>0)
						 {
							 fileList.get(1).setFileName(docFile2);
							 fileList.get(1).setDate(formate.format(new Date()));
						 }
						 else
							 fileList.get(1).setFileName(document2);
						 
						 if(docFile3!=null && docFile3.length()>0)
						 {
							 fileList.get(2).setFileName(docFile3);
							 fileList.get(2).setDate(formate.format(new Date()));
						 }
						 else
							 fileList.get(2).setFileName(document3);
						 
						 if(docFile4!=null && docFile4.length()>0)
						 {
							 fileList.get(3).setFileName(docFile4);
							 fileList.get(3).setDate(formate.format(new Date()));
						 }
						 else
							 fileList.get(3).setFileName(document4);
						 System.out.println("update " + fileList);
						 fileList = rest.postForObject(Constants.url + "postRuleFile",fileList,
									List.class);
					 
				}
				else
				{
					MRule mRule1 = new MRule();
					mRule1.setFileName(docFile1);
					mRule1.setDate(formate.format(new Date()));
					fileList.add(mRule1);
					
					mRule1 = new MRule();
					mRule1.setFileName(docFile2);
					mRule1.setDate(formate.format(new Date()));
					fileList.add(mRule1);
					
					mRule1 = new MRule();
					mRule1.setFileName(docFile3);
					mRule1.setDate(formate.format(new Date()));
					fileList.add(mRule1);
					
					mRule1 = new MRule();
					mRule1.setFileName(docFile4);
					mRule1.setDate(formate.format(new Date()));
					fileList.add(mRule1);
					 fileList = rest.postForObject(Constants.url + "postRuleFile",fileList,
								List.class);
				}
			  

			} catch (IOException e) {

				System.out.println("Exce in File Upload In GATE ENTRY  Insert " + e.getMessage());
				e.printStackTrace();
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		

		return "redirect:/uploadRuleFile";

	}

}

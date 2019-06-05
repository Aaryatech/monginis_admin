package com.ats.adminpanel.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ats.adminpanel.commons.Constants;
import com.ats.adminpanel.commons.VpsImageUpload;
import com.ats.adminpanel.model.SpCake;
import com.ats.adminpanel.model.album.Album;
import com.ats.adminpanel.model.flavours.AllFlavoursListResponse;
import com.ats.adminpanel.model.flavours.Flavour;
import com.ats.adminpanel.model.masters.GetSpCkSupplement;
import com.ats.adminpanel.model.message.AllMessageResponse;
import com.ats.adminpanel.model.message.Info;
import com.ats.adminpanel.model.message.Message;
import com.ats.adminpanel.model.modules.ErrorMessage;

@Controller
public class MessageController {

	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

	@RequestMapping(value = "/addNewMessage", method = RequestMethod.GET)
	public ModelAndView addMessage(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("message/addNewMessage");
		// ModelAndView model = new ModelAndView("orders/orders");
		Constants.mainAct = 1;
		Constants.subAct = 119;
		return mav;
	}

	@RequestMapping(value = "/addNewAlbum", method = RequestMethod.GET)
	public ModelAndView addNewAlbum(HttpServletRequest request, HttpServletResponse response) {
		RestTemplate restTemplate = new RestTemplate();
		ModelAndView mav = new ModelAndView("album/addNewAlbum");
		// ModelAndView model = new ModelAndView("orders/orders");
		Constants.mainAct = 1;
		Constants.subAct = 119;

		List<GetSpCkSupplement> spSuppList = restTemplate.getForObject(Constants.url + "/getSpCakeSuppList",
				List.class);
		System.out.println("spSuppList" + spSuppList.toString());
		mav.addObject("spList", spSuppList);

		return mav;
	}

	@RequestMapping(value = "/showAlbums", method = RequestMethod.GET)
	public ModelAndView showAlbums(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Show Message Request");
		Constants.mainAct = 1;
		Constants.subAct = 119;

		RestTemplate restTemplate = new RestTemplate();
		Album[] messageResponse = restTemplate.getForObject(Constants.url + "/getAlbumList", Album[].class);

		ModelAndView mav = new ModelAndView("album/listsAlbum");
		List<Album> message = new ArrayList<Album>(Arrays.asList(messageResponse));

		mav.addObject("message", message);
		System.out.println("List Of Messages:" + message.toString());

		AllFlavoursListResponse allFlavoursListResponse = restTemplate.getForObject(Constants.url + "showFlavourList",
				AllFlavoursListResponse.class);

		List<Flavour> flavoursList = new ArrayList<Flavour>();
		flavoursList = allFlavoursListResponse.getFlavour();
		mav.addObject("flavoursList", flavoursList);

		System.out.println(flavoursList.toString());

		mav.addObject("url", Constants.Album_IMAGE_URL);
		return mav;
	}

	@RequestMapping(value = "/showMessages", method = RequestMethod.GET)
	public ModelAndView showMessages(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Show Message Request");
		Constants.mainAct = 1;
		Constants.subAct = 119;

		RestTemplate restTemplate = new RestTemplate();
		AllMessageResponse messageResponse = restTemplate.getForObject(Constants.url + "showMessageList",
				AllMessageResponse.class);

		ModelAndView mav = new ModelAndView("message/listsMessage");
		List<Message> message = new ArrayList<Message>();
		message = messageResponse.getMessage();
		mav.addObject("message", message);
		System.out.println("List Of Messages:" + message.toString());

		mav.addObject("url", Constants.MESSAGE_IMAGE_URL);
		return mav;
	}

	@RequestMapping(value = "/addMessageProcess", method = RequestMethod.POST)
	public String addNewMessage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("msg_image") List<MultipartFile> file) {
		ModelAndView mav = new ModelAndView("message/addNewMessage");

		try {
			String msgFrdt = request.getParameter("msg_frdt");
			String msgTodt = request.getParameter("msg_todt");
			// String msgImage=request.getParameter("msg_image");
			String msgHeader = request.getParameter("msg_header");
			String msgDetails = request.getParameter("msg_details");
			int isActive = Integer.parseInt(request.getParameter("is_active"));

			// String msgImage= ImageS3Util.uploadMessageImage(file);

			VpsImageUpload upload = new VpsImageUpload();

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			System.out.println(sdf.format(cal.getTime()));
			long lo = cal.getTimeInMillis();
			System.out.println(sdf.format(cal.getTime()));

			// msgImage = String.valueOf(lo);

			String curTimeStamp = String.valueOf(lo);

			try {

				upload.saveUploadedFiles(file, Constants.MESSAGE_IMAGE_TYPE,
						curTimeStamp + "-" + file.get(0).getOriginalFilename().replace(' ', '_'));
				System.out.println("upload method called " + file.toString());

			} catch (IOException e) {

				System.out.println("Exce in File Upload In Item Insert " + e.getMessage());
				e.printStackTrace();
			}

			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("msgFrdt", msgFrdt);
			map.add("msgTodt", msgTodt);
			map.add("msgImage", curTimeStamp + "-" + file.get(0).getOriginalFilename().replace(' ', '_'));
			map.add("msgHeader", msgHeader);
			map.add("msgDetails", msgDetails);
			map.add("isActive", isActive);
			Info errorResponse = rest.postForObject(Constants.url + "insertMessage", map, Info.class);
			// System.out.println(errorResponse.toString());

			/*
			 * if(errorResponse.getError()) {
			 * 
			 * return "redirect:/addNewMessage";
			 * 
			 * 
			 * }else {
			 * 
			 * 
			 * return "redirect:/showMessages";
			 * 
			 * 
			 * }
			 */
		} catch (Exception e) {
			System.out.println("exce in msg con: " + e.getMessage());
			e.printStackTrace();

		}

		return "redirect:/showMessages";

	}

	@RequestMapping(value = "/addAlbumProcess", method = RequestMethod.POST)
	public String addAlbumProcess(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("photo1") List<MultipartFile> file1, @RequestParam("photo2") List<MultipartFile> file2) {
		ModelAndView mav = new ModelAndView("message/addNewMessage");

		try {
			String albumCode = request.getParameter("albumCode");
			String albumName = request.getParameter("albumName");
			// String msgImage=request.getParameter("msg_image");
			String desc = request.getParameter("desc");
			String[] flavourIds = request.getParameterValues("flavourId");

			int isActive = Integer.parseInt(request.getParameter("is_active"));
			int spId = Integer.parseInt(request.getParameter("spId"));
			float minWt = Float.parseFloat(request.getParameter("minWt"));
			float maxWt = Float.parseFloat(request.getParameter("maxWt"));

			VpsImageUpload upload = new VpsImageUpload();

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			System.out.println(sdf.format(cal.getTime()));
			long lo = cal.getTimeInMillis();
			System.out.println(sdf.format(cal.getTime()));

			// msgImage = String.valueOf(lo);

			String curTimeStamp = String.valueOf(lo);

			try {

				upload.saveUploadedFiles(file1, Constants.ALBUM_IMAGE_TYPE,
						curTimeStamp + "-" + file1.get(0).getOriginalFilename().replace(' ', '_'));
				System.out.println("upload method called " + file1.toString());

			} catch (IOException e) {

				System.out.println("Exce in File Upload In Item Insert " + e.getMessage());
				e.printStackTrace();
			}

			try {

				upload.saveUploadedFiles(file2, Constants.ALBUM_IMAGE_TYPE,
						curTimeStamp + "-" + file2.get(0).getOriginalFilename().replace(' ', '_'));
				System.out.println("upload method called " + file2.toString());

			} catch (IOException e) {

				System.out.println("Exce in File Upload In Item Insert " + e.getMessage());
				e.printStackTrace();
			}

			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < flavourIds.length; i++) {
				sb = sb.append(flavourIds[i] + ",");

			}
			String flavourIdList = sb.toString();
			flavourIdList = flavourIdList.substring(0, flavourIdList.length() - 1);

			RestTemplate rest = new RestTemplate();

			System.out.println(albumCode);
			System.out.println(albumName);

			System.out.println(desc);
			System.out.println(isActive);
			System.out.println(maxWt);
			System.out.println(minWt);
			System.out.println(curTimeStamp + "-" + file1.get(0).getOriginalFilename().replace(' ', '_'));
			System.out.println(curTimeStamp + "-" + file2.get(0).getOriginalFilename().replace(' ', '_'));

			System.out.println(spId);
			Album album = new Album();

			album.setAlbumCode(albumCode);
			album.setAlbumName(albumName);
			album.setDelStatus(0);
			album.setDesc(desc);
			album.setFlavourId(flavourIdList);
			album.setIsActive(isActive);
			album.setMaxWt(maxWt);
			album.setMinWt(minWt);
			album.setPhoto1(curTimeStamp + "-" + file1.get(0).getOriginalFilename().replace(' ', '_'));
			album.setPhoto2(curTimeStamp + "-" + file2.get(0).getOriginalFilename().replace(' ', '_'));
			album.setSpId(spId);

			System.out.println("albumalbumalbumalbumalbumalbum" + album.toString());
			Album errorResponse = rest.postForObject(Constants.url + "saveAlbum", album, Album.class);
			System.out.println(errorResponse.toString());

		} catch (Exception e) {
			System.out.println("exce in msg con: " + e.getMessage());
			e.printStackTrace();

		}

		return "redirect:/addNewAlbum";

	}

	@RequestMapping(value = "/deleteMessage/{msgId}", method = RequestMethod.GET)
	public String deleteEvent(@PathVariable int msgId) {

		// String id=request.getParameter("id");

		ModelAndView mav = new ModelAndView("message/listsMessage");

		RestTemplate rest = new RestTemplate();
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("id", msgId);

		ErrorMessage errorResponse = rest.postForObject(Constants.url + "deleteMessage", map, ErrorMessage.class);
		System.out.println(errorResponse.toString());

		if (errorResponse.getError()) {
			return "redirect:/showMessages";

		} else {
			return "redirect:/showMessages";

		}
	}

	@RequestMapping(value = "/updateMessage/{msgId}", method = RequestMethod.GET)
	public ModelAndView updateMessage(@PathVariable int msgId) {
		ModelAndView mav = new ModelAndView("message/editMessage");
		RestTemplate restTemplate = new RestTemplate();
		Message message = restTemplate.getForObject(Constants.url + "getMessage?msgId={msgId}", Message.class, msgId);

		// System.out.println("HI"+message.toString());
		mav.addObject("message", message);

		String msgFrDate = message.getMsgFrdt();

		int intisActive = message.getIsActive();
		String isActive = String.valueOf(intisActive);

		mav.addObject("isActive", isActive);
		mav.addObject("url", Constants.MESSAGE_IMAGE_URL);
		return mav;

	}

	// updateMessageProcess

	@RequestMapping(value = "/updateMessage/updateMessageProcess", method = RequestMethod.POST)

	public String updateMessage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("msg_image") List<MultipartFile> file) {
		System.out.println("HI");
		try {

			ModelAndView model = new ModelAndView("message/listMessage");

			RestTemplate restTemplate = new RestTemplate();
			int id = Integer.parseInt(request.getParameter("msgId"));
			String msgFrdt = request.getParameter("msg_frdt");
			String msgTodt = request.getParameter("msg_todt");
			// String msgImage=request.getParameter("msg_image");
			String msgHeader = request.getParameter("msg_header");
			String msgDetails = request.getParameter("msg_details");
			int isActive = Integer.parseInt(request.getParameter("is_active"));

			String msgImage = request.getParameter("prevImage");

			if (!file.get(0).getOriginalFilename().equalsIgnoreCase("")) {

				System.out.println("Empty image");
				// msgImage= ImageS3Util.uploadMessageImage(file);

				VpsImageUpload upload = new VpsImageUpload();

				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

				long lo = cal.getTimeInMillis();
				System.out.println(sdf.format(cal.getTime()));

				msgImage = String.valueOf(lo);

				try {

					upload.saveUploadedFiles(file, Constants.MESSAGE_IMAGE_TYPE,
							msgImage + "-" + file.get(0).getOriginalFilename().replace(' ', '_'));
					System.out.println("upload method called " + file.toString());

				} catch (IOException e) {

					System.out.println("Exce in File Upload In Item Insert " + e.getMessage());
					e.printStackTrace();
				}
			}

			// String msgImage= ImageS3Util.uploadMessageImage(file);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("msgFrdt", msgFrdt);
			map.add("msgTodt", msgTodt);
			map.add("msgImage", msgImage + "-" + file.get(0).getOriginalFilename().replace(' ', '_'));
			map.add("msgHeader", msgHeader);
			map.add("msgDetails", msgDetails);
			map.add("isActive", isActive);
			map.add("id", id);
			System.out.println("Image Url " + Constants.MESSAGE_IMAGE_URL);

			model.addObject("url", Constants.MESSAGE_IMAGE_URL);

			ErrorMessage messageResponse = restTemplate.postForObject(Constants.url + "updateMessage", map,
					ErrorMessage.class);

		} catch (Exception e) {

			System.out.println("error in msg update" + e.getMessage());
		}
		return "redirect:/showMessages";

	}

}

package com.ats.adminpanel.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmailController {

	@Autowired
	private JavaMailSender mailSender;

	static ModelAndView modelViewObj;

	@RequestMapping(value = {  "/emailForm" }, method = RequestMethod.GET)
	public ModelAndView showEmailForm(ModelMap model) {

		modelViewObj = new ModelAndView("Email");
		return modelViewObj;

	}

	@RequestMapping(value = { "/sendEmail" }, method = RequestMethod.POST)
	public String sendEmail(HttpServletRequest request, final @RequestParam CommonsMultipartFile attachFile) {

		// reads form input
		final String emailTo = request.getParameter("mailTo");
		final String subject = request.getParameter("subject");
		final String message = request.getParameter("message");

		// for logging
		System.out.println("emailTo: " + emailTo);
		System.out.println("subject: " + subject);
		System.out.println("message: " + message);
		System.out.println("attachFile: " + attachFile.getOriginalFilename());
		System.out.println("attachFile: size " + attachFile.getSize());

		mailSender.send(new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				messageHelper.setTo(emailTo);
				messageHelper.setSubject(subject);
				messageHelper.setText(message);

			
				String attachName = attachFile.getOriginalFilename();
				if ( attachFile.getSize()>0) {
					System.out.println("Attaching file to mail");
					messageHelper.addAttachment(attachName, new InputStreamSource() {

						@Override
						public InputStream getInputStream() throws IOException {
							return attachFile.getInputStream();
						}
					});
				}

			}

		});

		return "success";

	}
}

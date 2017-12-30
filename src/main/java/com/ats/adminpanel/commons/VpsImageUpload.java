package com.ats.adminpanel.commons;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class VpsImageUpload {

	public static final String FR_FOLDER = "/opt/apache-tomcat-8.5.6/webapps/webapi/uploads/FR";
	public static final String ITEM_FOLDER = "/opt/apache-tomcat-8.5.6/webapps/webapi/uploads/ITEM";

	public static final String MSG_FOLDER = "/opt/apache-tomcat-8.5.6/webapps/webapi/uploads/MSG";
	
	public static final String M_SP_CAKE_FOLDER = "/opt/apache-tomcat-8.5.6/webapps/webapi/uploads/MSPCAKE";
	
	public static final String RAW_MAT_IMAGE_FOLDER = "/opt/apache-tomcat-8.5.6/webapps/webapi/uploads/RAWMAT";

	public static final String GATE_ENTRY_IMAGE_FOLDER = "/opt/apache-tomcat-8.5.6/webapps/webapi/uploads/GATEENTRY";
	


	private static final String FIELDMAP_FOLDER = null;
	private static final String KYC_FOLDER = null;

	private static String curTimeStamp = null;

	public void saveUploadedFiles(List<MultipartFile> files, int imageType, String imageName) throws IOException {

		for (MultipartFile file : files) {

			if (file.isEmpty()) {

				continue;

			}

			Path path = Paths.get(FR_FOLDER + imageName);

			byte[] bytes = file.getBytes();

			if (imageType == 1) {
				System.out.println("Inside Image Type =1");

				path = Paths.get(FR_FOLDER + imageName);

				System.out.println("Path= " + path.toString());

			} else if (imageType == 2) {

				path = Paths.get(ITEM_FOLDER + imageName);

			} else if (imageType == 3) {

				path = Paths.get(MSG_FOLDER + imageName);

			}else if (imageType == 4) {

				path = Paths.get(M_SP_CAKE_FOLDER + imageName);

			}
			else if (imageType == 6) {

				path = Paths.get(RAW_MAT_IMAGE_FOLDER + imageName);

			}

			else if (imageType == 7) {

				path = Paths.get(GATE_ENTRY_IMAGE_FOLDER + imageName);

			}


			Files.write(path, bytes);

		}

	}

}

package com.ats.adminpanel.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;


/* 
  <!-- For AWS -->
 <dependency>
  <groupId>com.amazonaws</groupId>
  <artifactId>aws-java-sdk</artifactId>
  <version>1.9.30</version>
 </dependency>
 
*/


// String name=	ImageS3Util.uploadFrImage(file);

public class ImageS3Util {

	private static final String ACCESS_KEY = "AKIAJSAPFBF6RIAMCGDA";
	private static final String SECRET_KEY = "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";
	private static final String END_POINT_URL = "https://s3-ap-south-1.amazonaws.com";// e.g http://s3.amazonaws.com
	private static final String BUCKET = "monginis";
	private static final String FR_IMAGE_LOCATION = "uploads/fr";
	private static final String ITEMS_IMAGE_LOCATION = "uploads/items";
	private static final String MESSAGE_IMAGE_LOCATION = "uploads/message";
	private static final String PHOTOCAKE_IMAGE_LOCATION = "uploads/photoCake";
	private static final String SPECIAL_CAKE_IMAGE_LOCATION = "uploads/specialCake";

	private static final String S3_CACHE = "60"; // e.g 60
	private static AmazonS3 s3;

	
	
	public static String uploadFrImage(MultipartFile multipartFile) {
		String name="";
		try {
		 name=	uploadImageToAWSS3(multipartFile,FR_IMAGE_LOCATION);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Illegal State Excep: "+e.getMessage());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("IO Excep: "+e.getMessage());
						
		}
		return name;
		
	}

	
	public static String uploadItemImage(MultipartFile multipartFile) {
		String name="";
		try {
			name= uploadImageToAWSS3(multipartFile,ITEMS_IMAGE_LOCATION);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Illegal State Excep: "+e.getMessage());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("IO Excep: "+e.getMessage());
						
		}
		return name;

	}

	
	
	public static String uploadMessageImage(MultipartFile multipartFile) {
		String name="";
		try {
			name=	uploadImageToAWSS3(multipartFile,MESSAGE_IMAGE_LOCATION);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Illegal State Excep: "+e.getMessage());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("IO Excep: "+e.getMessage());
						
		}
		
		return name;

	}

	
	
	public static String uploadPhotoCakeImage(MultipartFile multipartFile) {
		String name="";
		try {
			name=uploadImageToAWSS3(multipartFile,PHOTOCAKE_IMAGE_LOCATION);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Illegal State Excep: "+e.getMessage());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("IO Excep: "+e.getMessage());
						
		}
		return name;

	}

	
	public static String uploadSpCakeImage(MultipartFile multipartFile) {
		String name="";
		
		try {
			name=uploadImageToAWSS3(multipartFile,SPECIAL_CAKE_IMAGE_LOCATION);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Illegal State Excep: "+e.getMessage());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("IO Excep: "+e.getMessage());
						
		}
		
		return name;

	}

		
	
	public static String uploadImageToAWSS3(MultipartFile multipartFile, String imageLocation)
			throws IllegalStateException, IOException {
		String fileName = null;
		try {
			AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
			java.security.Security.setProperty("networkaddress.cache.ttl", S3_CACHE);
			s3 = new AmazonS3Client(credentials);
			s3.setEndpoint(END_POINT_URL);
			InputStream stream = multipartFile.getInputStream();
			fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
			ObjectMetadata objectMetadata = new ObjectMetadata();

			byte[] bytes = IOUtils.toByteArray(stream);
			// metaData.setContentLength(bytes.length);
			objectMetadata.setContentLength(bytes.length);

			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

			PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET, imageLocation + "/" + fileName,
					byteArrayInputStream, objectMetadata);
			// skip if do not want to access the image directly from S3
			putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
			// skip if do not want to access the image directly from S3
			s3.putObject(putObjectRequest);

			
		} catch (AmazonServiceException e) {
			e.printStackTrace();
		}
		return fileName;
		
	}
}

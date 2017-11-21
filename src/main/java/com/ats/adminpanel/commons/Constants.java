package com.ats.adminpanel.commons;

import java.io.File;

public class Constants {
	public static final String url="http://localhost:8027/";
	//public static final String url = "http://mongiwebapi.ap-south-1.elasticbeanstalk.com/";

	public static final String SPCAKE_IMAGE_URL = "https://s3.ap-south-1.amazonaws.com/monginis/uploads/specialCake/";

	public static final String FR_IMAGE_URL = "https://s3.ap-south-1.amazonaws.com/monginis/uploads/fr/";

	public static final String PHOTO_CAKE_URL = "https://s3.ap-south-1.amazonaws.com/monginis/uploads/photoCake/";

	public static final String MESSAGE_IMAGE_URL = "https://s3.ap-south-1.amazonaws.com/monginis/uploads/message/";

	public static final String ITEM_IMAGE_URL = "https://s3.ap-south-1.amazonaws.com/monginis/uploads/items/";

	
	// navigation view
	
	public static int mainAct=0;
	public static int subAct=0;
	
	
	
	
	
	
	
	
	
	
	public static final String FR_PROPERTY = "/home/ats-11/mongiImage/FR";

	public static final String MSG_PROPERTY = "/home/ats-11/mongiImage/Message";

	public static final String ITEMS_PROPERTY = "/home/ats-11/mongiImage/Items";

	public static final String PHOTOCAKE_PROPERTY = "/home/ats-11/mongiImage/PhotoCake";

	public static final String SPECIALCAKE_PROPERTY = "/home/ats-11/mongiImage/SpecialCake";

	public static final File SPCAKE_DIR = new File(SPECIALCAKE_PROPERTY);
	public static final String SPCAKE_DIR_ABSOLUTE_PATH = SPCAKE_DIR.getAbsolutePath() + File.separator;

	public static final File FR_DIR = new File(FR_PROPERTY);
	public static final String FR_DIR_ABSOLUTE_PATH = FR_DIR.getAbsolutePath() + File.separator;

	public static final File MSG_DIR = new File(MSG_PROPERTY);
	public static final String MSG_DIR_ABSOLUTE_PATH = MSG_DIR.getAbsolutePath() + File.separator;

	public static final File ITEM_DIR = new File(ITEMS_PROPERTY);
	public static final String ITEM_DIR_ABSOLUTE_PATH = ITEM_DIR.getAbsolutePath() + File.separator;

	public static final File PHOTOCAKE_DIR = new File(PHOTOCAKE_PROPERTY);
	public static final String PHOTOCAKE_DIR_ABSOLUTE_PATH = PHOTOCAKE_DIR.getAbsolutePath() + File.separator;

	public static final String MESSAGE_IMAGE_URL2 = "http://monginisaurangabad.com/uploads/mongiImage/Message/";
	public static final String ITEM_IMAGE_URL2 = "http://monginisaurangabad.com/uploads/mongiImage/Items/";
	// public static final String
	// FR_IMAGE_URL="http://monginisaurangabad.com/uploads/mongiImage/FR/";
	public static final String SPCAKE_IMAGE_URL2 = "http://monginisaurangabad.com/uploads/mongiImage/SpecialCake/";

}

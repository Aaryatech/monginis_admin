package com.ats.adminpanel.commons;

import java.io.File;

public class Constants {
public static final String url="http://localhost:8098/";
	//public static final String url = "http://132.148.143.124:8080/webapi/";

	public static final String SPCAKE_IMAGE_URL = "http://132.148.143.124:8080/webapi/uploads/MSPCAKE/";
	public static final String FR_IMAGE_URL = "http://132.148.143.124:8080/webapi/uploads/FR/";


	public static final String MESSAGE_IMAGE_URL = "/http://132.148.143.124:8080/webapi/uploads/MSG/";

	public static final String ITEM_IMAGE_URL = "http://132.148.143.124:8080/webapi/uploads/ITEM/";


	public static final String RAW_MAT_IMG_URL ="http://132.148.143.124:8080/webapi/uploads/RAWMAT/";

	public static final String GATE_ENTRY_IMG_URL = "http://132.148.143.124:8080/webapi/uploads/GATEENTRY/";
	
	public static final String GVN_IMAGE_URL =  "http://132.148.143.124:8080/webapi/uploads/GVN/";

	// navigation view
	
	public static int mainAct=0;
	public static int subAct=0;

	public static int FR_IMAGE_TYPE=1;
	public static int ITEM_IMAGE_TYPE=2;
	public static int MESSAGE_IMAGE_TYPE=3;
	
	public static int SPCAKE_IMAGE_TYPE=4;
	
	public static int CUST_CHIOICE_IMAGE_TYPE=5;
	
	
	public static int RAW_MAT_IMAGE_TYPE=6;

	public static int GATE_ENTRY_IMAGE_TYPE=7;

	
		public static final String ReportURL ="http://localhost:8895/adminpanel/";
	//public static final String ReportURL ="http://132.148.143.124:8080/admin/";
	
	
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

	public static final String SETTING_KEY = "PB";

	//public static final String ReportURL = url+"/adminpanel/";

	public static final int DIS_BY_ACC = 7;

	public static final int AP_BY_ACC = 6;

	public static final int DIS_BY_STORE = 5;

	public static final int AP_BY_STORE = 4;

	public static final int AP_BY_GATE = 2;

	public static final int DIS_BY_GATE = 3;

}

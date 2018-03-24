package com.ats.adminpanel.commons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class Firebase {

	// Method to send Notifications from server to client end.
	public final static String AUTH_KEY_FCM = "AAAAvgBdT8Y:APA91bEQg2VdTdB7GfMksze2J5sWVNsOHa6cGBjRJkMqBK05Zx1N0hW9tRhrE1dJ13CuUaKvtefSusRX1sOhDiiLpyrQuqpXobMx80U-FdqM4UWd_sCmhGLIdGAvpBS3v1IX5676xmUz";

	public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

	
	public static String sendPushNotifForCommunication(String deviceToken, String title, String body,String tag) throws IOException {

		// System.out.println("Parameters : " + deviceToken + "\nTitle : " +
		// title + "\nDesc : " + body);
		String result = "";
		URL url = new URL(API_URL_FCM);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);

		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);
		conn.setRequestProperty("Content-Type", "application/json");
System.out.println("deviceToken"+deviceToken.toString());
		JSONObject json = new JSONObject();
		JSONObject info = new JSONObject();
		try {

			json.put("to", deviceToken.trim());
			info.put("title", title.trim());
			info.put("tag",tag);
			info.put("body", body.trim()); // Notification
			info.put("sound", "default");
			info.put("vibrate", "true");
			json.put("data", info);

		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		try {
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(json.toString());
			wr.flush();

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			// System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				// System.out.println(output);
			}
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			result = "failure";
		}
		System.out.println("------------------------------------------------------------------------");
		System.out.println("FCM Notification is sent successfully");

		return result;
	}
	public static String sendPushNotifForCommunicationWithFr(String deviceToken, String title, String body,String tag,String frName) throws IOException {

		// System.out.println("Parameters : " + deviceToken + "\nTitle : " +
		// title + "\nDesc : " + body);
		String result = "";
		URL url = new URL(API_URL_FCM);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);

		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);
		conn.setRequestProperty("Content-Type", "application/json");

		JSONObject json = new JSONObject();
		JSONObject info = new JSONObject();
		try {

			json.put("to", deviceToken.trim());
			info.put("title", title.trim());
			info.put("tag",tag);
			info.put("frName",frName);
			info.put("body", body.trim()); // Notification
			info.put("sound", "default");
			info.put("vibrate", "true");
			json.put("data", info);

		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		try {
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(json.toString());
			wr.flush();

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			// System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				// System.out.println(output);
			}
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			result = "failure";
		}
		System.out.println("------------------------------------------------------------------------");
		System.out.println("FCM Notification is sent successfully");

		return result;
	}
	public static void sendPushNotification1(List<String> deviceTokenList) {
		Sender sender = new Sender(AUTH_KEY_FCM);
		Message msg = new Message.Builder().addData("New Bill Generated", "Approval Pending").build();
		try {
			MulticastResult result = sender.send(msg, deviceTokenList, 5);
			for (Result r : result.getResults()) {
				if (r.getMessageId() != null)
					System.out.println("Push Notification Sent Successfully");
				else
					System.out.println("ErrorCode " + r.getErrorCodeName());
			}
		} catch (IOException e) {
			// System.out.println("Error " + e.getLocalizedMessage());
		}
	}
	

	public String getDeviceToken(int empId) {
		String result = null;

		return result;

	}

	public ArrayList<Integer> getFireBaseEmpId() {
		ArrayList<Integer> arrEmpId = new ArrayList<>();

		return arrEmpId;
	}


}

package com.loveofworld.mvc.model.message;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

/*
import javapns.back.FeedbackServiceManager;
import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;
*/

//2016. 06. 24. : Cho Yongkyun : 최신 버전의 라이브러리로 변경해야 함.
public class PushMessage_iOS{

	/*
	public int RUN_MODE_DEVELOPMENT = 1;
	public int RUN_MODE_PRODUCTION = 2;
	public String APNS_DEVELOPMENT_GATEWAY = "gateway.sandbox.push.apple.com";
	public String APNS_PRODUCTION_GATEWAY = "gateway.push.apple.com";
	public String APNS_DEVELOPMENT_FEEDBACK = "feedback.sandbox.push.apple.com";
	public String APNS_PRODUCTION_FEEDBACK = "feedback.push.apple.com";
	
	// Change Here.
	public String APNS_DEVELOPMENT_KEY = this.getClass().getResource("/com/vinflux/mobile/system/properties/aps_development_server.p12").getPath();
	//public String APNS_DEVELOPMENT_KEY = "/aps_development_server.p12";
	public String APNS_PRODUCTION_KEY = "./keystore/your_production_key.p12";
	public String APNS_DEVELOPMENT_KEY_PASSWORD = "vinflux2013";
	public String APNS_PRODUCTION_KEY_PASSWORD = "YOUR DEVELOPMENT PASSWORD";
	
	public void sendMessage(int runMode, String deviceToken, String alertMessage, int badgeCount, String soundFile) throws Exception {
		
		System.out.println("===== :: ====== " + APNS_DEVELOPMENT_KEY);
		
		try {
			PayLoad payLoad = new PayLoad();
			payLoad.addAlert(alertMessage);
			payLoad.addBadge(badgeCount);
			payLoad.addSound(soundFile);


			PushNotificationManager pushManager = PushNotificationManager.getInstance();
			pushManager.addDevice("iPhone", deviceToken);

			int port = 2195;
			String host = null;
			String certificatePath = null;
			String certificatePassword = null;
			if (runMode == RUN_MODE_DEVELOPMENT) {
				host = APNS_DEVELOPMENT_GATEWAY;
				certificatePath = APNS_DEVELOPMENT_KEY;
				certificatePassword = APNS_DEVELOPMENT_KEY_PASSWORD;
			} else if (runMode == RUN_MODE_PRODUCTION) {
				host = APNS_PRODUCTION_GATEWAY;
				certificatePath = APNS_PRODUCTION_KEY;
				certificatePassword = APNS_PRODUCTION_KEY_PASSWORD;
			}
			
			pushManager.initializeConnection(host, port, certificatePath, certificatePassword, SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);

			Device client = pushManager.getDevice("iPhone");
			pushManager.sendNotification(client, payLoad);
			pushManager.stopConnection();

			pushManager.removeDevice("iPhone");
		} catch (Exception ex) {
			ex.printStackTrace(); 
		}
	}
	
	public ArrayList<String> sendFeedback(int runMode) {
		ArrayList<String> deviceTokens = new ArrayList<String> ();
		
		try {
			int port = 2196;
			String host = null;
			String certificatePath = null;
			String certificatePassword = null;
			if (runMode == RUN_MODE_DEVELOPMENT) {
				host = APNS_DEVELOPMENT_FEEDBACK;
				certificatePath = APNS_DEVELOPMENT_KEY;
				certificatePassword = APNS_DEVELOPMENT_KEY_PASSWORD;
			} else if (runMode == RUN_MODE_PRODUCTION) {
				host = APNS_PRODUCTION_FEEDBACK;
				certificatePath = APNS_PRODUCTION_KEY;
				certificatePassword = APNS_PRODUCTION_KEY_PASSWORD;
			}
			
			FeedbackServiceManager feedbackManager = FeedbackServiceManager.getInstance();
			LinkedList<Device> devices = feedbackManager.getDevices(host, port, certificatePath, certificatePassword, SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);
			
			if (devices.size() > 0) {
				ListIterator<Device> itr = devices.listIterator();
				while (itr.hasNext()) {
					Device device = itr.next();
					deviceTokens.add(device.getToken());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return deviceTokens;
	}
	*/

    
}

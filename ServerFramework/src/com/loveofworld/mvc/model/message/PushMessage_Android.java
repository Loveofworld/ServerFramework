package com.loveofworld.mvc.model.message;

import java.net.URLEncoder;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;



public class PushMessage_Android {

	
	private static final String APIKEY = "AIzaSyAgKY3Njk3cCOY_fBJUbQuLIIJxathdlOs"; //GCM에서 발급받은 ApiKey를 입력
	
	public Result sendMessage(String title, String msg, String regId) {
        try {
           
            Sender sender = new Sender(APIKEY);
            
            /**
             * message Build 부분에서 addData로 추가한 값은 어플리케이션의
             * onMessage(context, intent)에서 Intent로 전달되며
             * intent.getExtras().getString("title")형태로 얻어와 사용 가능하다.
             */
            Message message = new Message.Builder()
            .addData("TITLE"  , URLEncoder.encode(title, "UTF-8"))
            .addData("MESSAGE", URLEncoder.encode(msg, "UTF-8"))
            .build();
 
            //발송할 메시지, 발송할 타깃(RegistrationId, Retry 횟수)
            Result result = sender.send(message, regId, 3);
            if (result.getMessageId() != null) {
                System.out.println("Send success");
            } else {
                String error = result.getErrorCodeName();
                System.out.println("Send fail : " + error);
            }
            
            return result;
 
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }

    }
	
}

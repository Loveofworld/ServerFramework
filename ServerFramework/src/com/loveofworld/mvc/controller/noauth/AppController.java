package com.loveofworld.mvc.controller.noauth;

import java.util.ArrayList;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.android.gcm.server.Result;
import com.loveofworld.mvc.controller.abstracts.ViewControllerSevice;
import com.loveofworld.mvc.model.message.PushMessage_Android;
import com.loveofworld.mvc.model.message.PushMessage_iOS;
import com.loveofworld.system.util.Log;

//Service Name : Main
@Controller
public class AppController extends ViewControllerSevice{
	
	@RequestMapping(value="App/SESAME.do", method=RequestMethod.GET)
	public ModelAndView AppController(HttpServletRequest request){
 		Log.getInstance().printLog(this, "==================================================== APP Excute : Start !!");
		
		Log.getInstance().printLog(this, "REQUEST PARAMETER : " + request.getParameter("RCM"));
		
		String[] recommandInfo = request.getParameter("RCM").split("UU");
		
		
		ModelAndView mav = new ModelAndView("ExcuteApp.jsp");
		mav.addObject("RPRICKEY", recommandInfo[0]);
		mav.addObject("RCM"     , recommandInfo[1]);
		
		Log.getInstance().printLog(this, "==================================================== APP Excute : End !!");
		
		return  mav;
 	}
	
	
	@RequestMapping(value="App/Push_iOS.do", method=RequestMethod.GET)
	public ModelAndView Push_iOS(HttpServletRequest request){
 	
		Log.getInstance().printLog(this, "==================================================== PUSH Excute : Start !!");

		//2016. 06. 24. : Cho Yongkyun : 최신 버전의 라이브러리로 변경해야 함.
		/*
		PushMessage_iOS pushMessage = new PushMessage_iOS();
		try {
			pushMessage.sendMessage(
					1,
					//"eee43d75120dd7d76da07ae7ff44d05da31629687085a400af2c2c9cf4d0320a",
					"97e3122c647efa6bd92534e1a6fcff21953f7757044e8d9fa097576642188251",
					"Hello~ Dolly&Molly",
					1,
					"default");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<String> deviceTokens = pushMessage.sendFeedback(1);
		for (String deviceToken : deviceTokens) {
			System.out.println(deviceToken);
		}
		*/
		Log.getInstance().printLog(this, "==================================================== PUSH Excute : End !!");
		
		return  null;
 	}
	
	@RequestMapping(value="App/Push_Android.do", method=RequestMethod.GET)
	public ModelAndView Push_Android(HttpServletRequest request){
 	
		Log.getInstance().printLog(this, "==================================================== PUSH Excute : Start !!");

		PushMessage_Android pushMessage = new PushMessage_Android();
		Result result = pushMessage.sendMessage(
				"GCM Test",
				"Hello World~~~!!",
				"APA91bFUyWXtvD3EtlohY9ct1LdWMtma1MFGRfWXPLNBMH9BHdNldFxkAWcgz4aqOAyT7h9RpO3_6yQ6SInosNdQwPH1EK3UDPn-SrOVltANG6xhfdp-fgryoBwfihZQf-9bpWc_y3ErwXFzgllm0MNnvjyJDXUoJg");

		Log.getInstance().printLog(this, "==================================================== PUSH Excute : End !!");
		
		return  null;
 	}
	
	
	
	
	
}

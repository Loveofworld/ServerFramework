package com.loveofworld.mvc.controller.noauth.sample;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/** 2016. 05. 11. : Cho Yongkyun : 해당 사용안하는지 정확하게 파악이 필요.
import org.apache.commons.lang.exception.ExceptionUtils;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
*/

import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Controller;

import com.loveofworld.mvc.model.data.Params;
import com.loveofworld.system.util.Log;


@Controller
public class AmfContrrollerSample {

	@RemotingInclude // BDS
	//@RequestMapping(value="/messagebroker/amf", method=RequestMethod.GET)
	//@ResponseBody
	public Map amfHandlerAdapter(HttpServletRequest request, Params params) {
		
		Log.getInstance().printLog(this, "??��?? ???");
		System.out.println("alksjdlkajsdlajsdlajsdlja;lsdja;lskdj;alksjd;lakjsd;lkajsd;lkajsda");
		return null;
	}
}

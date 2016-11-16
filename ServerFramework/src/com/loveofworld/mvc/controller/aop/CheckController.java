package com.loveofworld.mvc.controller.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.loveofworld.mvc.service.RequestAuth;
import com.loveofworld.system.ConstValue;
import com.loveofworld.system.MessageValue;
import com.loveofworld.system.util.Log;
import com.loveofworld.system.util.Util;

@Aspect
public class CheckController {

	@Pointcut("execution(* *..mvc.controller..*(..))")
	public void checkExcutingController() {}
	
	@Around("checkExcutingController()")
	public Object trace_checkExcutingController(ProceedingJoinPoint joinPoint) {
		
		//String signatureString = joinPoint.getSignature().toShortString();
		String signatureString = joinPoint.getSignature().toLongString();
		Log.getInstance().printLog(this, signatureString+"Start");
		long start = System.currentTimeMillis();

		Object result = null;

		HttpServletRequest request = null;
        HttpServletResponse response = null;
        for ( Object o : joinPoint.getArgs() ){ 
            if ( o instanceof HttpServletRequest ) {
                request = (HttpServletRequest)o;
            } 
            if ( o instanceof HttpServletResponse ) {
                response = (HttpServletResponse)o;
            } 
        }
        
        String requestUrl = request.getRequestURL().toString();
        Log.getInstance().printLog(this, "Request URL : " + requestUrl);
        int requestType = 0;
        
        if(requestUrl.contains(".json")){
        	requestType = ConstValue.REQUEST_JSON;
        	Log.getInstance().printLog(this, "Request Type : JSON");
        }
        else{
        	requestType = ConstValue.REQUEST_VIEW;
        	Log.getInstance().printLog(this, "Request Type : VIEW");
        }
        
        RequestAuth requestAuth = new RequestAuth();
        int authResult = requestAuth.AUTH_FAIL;
        
        
        Log.getInstance().printLog(this, "Request URL : signatureString : " + signatureString);
        
        
        if(signatureString.contains(".noauth")){
        	authResult = requestAuth.AUTH_PASS;
        }
        else{
        	authResult = requestAuth.isCheckAuth(request);
        }
        
    	if(authResult == requestAuth.AUTH_SUCCESS){
    		
    		
    		Log.getInstance().printExceptionLog(this, "Session AUTH SUCCESS");
			try {
				result = joinPoint.proceed();
			} 
			catch (Throwable e) {
				// TODO Auto-generated catch block
				Log.getInstance().printExceptionLog(this, Log.getInstance().getStackTrace(e));
				Log.getInstance().printExceptionLog(this, "======= " + e.getLocalizedMessage());
				
				//result = Util.getInstance().getReturnObject(requestType, request, ConstValue.ERROR_CODE_FAIL, Log.getInstance().getStackTrace(e.fillInStackTrace()));
				result = Util.getInstance().getReturnObject(requestType, request, ConstValue.ERROR_CODE_FAIL, MessageValue.MSG_ERROR);
			}
			
		}
		else if(authResult == requestAuth.AUTH_NODATA){
			Log.getInstance().printExceptionLog(this, "Session AUTH No Data");
			result = Util.getInstance().getReturnObject(requestType, request, ConstValue.ERROR_CODE_FAIL_AUTH, MessageValue.MSG_FAIL_AUTH_NODATA);			
		}
		else if(authResult == requestAuth.AUTH_FAIL){
			Log.getInstance().printExceptionLog(this, "Session AUTH Fail");
			result = Util.getInstance().getReturnObject(requestType, request, ConstValue.ERROR_CODE_FAIL_AUTH, MessageValue.MSG_FAIL_AUTH);			
		}
		else if(authResult == requestAuth.AUTH_PASS){
			Log.getInstance().printExceptionLog(this, "Session AUTH PASS");
			try {
				result = joinPoint.proceed();
			} 
			catch (Throwable e) {
				// TODO Auto-generated catch block
				Log.getInstance().printExceptionLog(this, Log.getInstance().getStackTrace(e));
				//result = Util.getInstance().getReturnObject(requestType, request, ConstValue.ERROR_CODE_FAIL, Log.getInstance().getStackTrace(e.fillInStackTrace()));
				result = Util.getInstance().getReturnObject(requestType, request, ConstValue.ERROR_CODE_FAIL, MessageValue.MSG_ERROR);
			}
		}
		
		long finish = System.currentTimeMillis();
		Log.getInstance().printLog(this, signatureString+":Running Time :" + (finish - start)+"ms");
		Log.getInstance().printLog(this, signatureString+":End");
	
		return result;	
	}

}

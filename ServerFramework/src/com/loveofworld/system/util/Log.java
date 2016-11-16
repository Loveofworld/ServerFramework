package com.loveofworld.system.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;




public class Log {

	private static Log instance;
	
	private Log() {
		// TODO Auto-generated constructor stub
	}
	
	public static Log getInstance(){
		if(instance == null){
			synchronized (Log.class){
				if(instance == null){
					instance = new Log();
					BasicConfigurator.configure();
				}
			}
		}
		
		return instance;
	}
	
	public void printLog(Object object, String message){
		Logger log = Logger.getLogger(object.getClass().getName());
		StringBuffer messageStringBuffer = new StringBuffer();
		messageStringBuffer.append(message);
		log.info(messageStringBuffer.toString());
		
	}
	
	public void printLog(String objectName, String message){
		Logger log = Logger.getLogger(objectName);
		StringBuffer messageStringBuffer = new StringBuffer();
		messageStringBuffer.append(message);
		log.info(messageStringBuffer.toString());
		
	}
	
	public void printExceptionLog(Object object, Throwable throwable){
		getInstance().printExceptionLog(object, getInstance().getStackTrace(throwable.fillInStackTrace()));
	}
	
	public void printExceptionLog(Object object, String errorMessage){
		getInstance().printLog(object, "Occurred at Excpetion !!");
		getInstance().printLog(object, errorMessage);	
	}
	
	public String getStackTrace(Throwable throwable) {
	    Writer writer = new StringWriter();
	    PrintWriter printWriter = new PrintWriter(writer);
	    throwable.printStackTrace(printWriter);
	    return writer.toString();
	}

}

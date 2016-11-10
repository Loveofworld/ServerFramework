package com.loveofworld.system;

public class ThrowException extends Exception{

	public ThrowException(Object object, String Err_code, String message){
		super(object.getClass().toString() + " : " + Err_code + " : " + message);
		
	}
	
	public ThrowException(Object object, String message){
		super(object.getClass().toString() + " : " + ConstValue.ERROR_CODE_FAIL + " : " + message);
	}
	
	
	public ThrowException(Object object){
		super(object.getClass().toString() + " : " + ConstValue.ERROR_CODE_FAIL + " : Throw Exception");
	}
	
}


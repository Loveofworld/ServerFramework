package com.loveofworld.test.soap;

import java.util.Date;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(
		style = SOAPBinding.Style.DOCUMENT,
		use =  SOAPBinding.Use.LITERAL,
		parameterStyle = SOAPBinding.ParameterStyle.WRAPPED
		)
public interface GlisLogifocusWmsInterface {

    public boolean inputLegacyInfomation( Date   arg0
    		   							, String arg1
    		   							, String arg2
    		   							, String arg3
    		   							, String arg4
    		   							, String arg5
    		   							, String arg6
    		   							, String arg7
    		   							, String arg8
    		   							, String arg9
    		   							, String arg10
    		   							);
}

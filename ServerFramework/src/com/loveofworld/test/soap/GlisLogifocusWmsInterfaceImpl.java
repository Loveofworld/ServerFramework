package com.loveofworld.test.soap;
import java.util.Date;

import javax.jws.WebService;

import com.loveofworld.system.util.Log;

@WebService(endpointInterface="com.vinflux.test.soap.GlisLogifocusWmsInterface")
public class GlisLogifocusWmsInterfaceImpl implements GlisLogifocusWmsInterface {

	@Override
	public boolean inputLegacyInfomation( Date agu0
										, String agu1
										, String agu2
										, String agu3
										, String agu4
										, String agu5
										, String agu6
										, String agu7
										, String agu8										
										, String agu9
										, String agu10
										){
		// TODO Auto-generated method stub
		Log.getInstance().printLog(this,"WebService : RESTFul : Provider Message : agu0 : " + agu0);
		Log.getInstance().printLog(this,"WebService : RESTFul : Provider Message : agu0 : " + agu1);
		Log.getInstance().printLog(this,"WebService : RESTFul : Provider Message : agu0 : " + agu2);
		Log.getInstance().printLog(this,"WebService : RESTFul : Provider Message : agu0 : " + agu3);
		
		boolean resultState = false;
		if(agu0 == null){
			resultState = false;
		}
		else{
			resultState = true;
		}
		return resultState;
	}

}

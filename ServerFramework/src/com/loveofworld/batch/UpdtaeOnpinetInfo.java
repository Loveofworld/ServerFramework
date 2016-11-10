package com.loveofworld.batch;

import java.util.List;
import java.util.Map;

import com.loveofworld.batch.interfaces.ExcuteBatchModule;
import com.loveofworld.mvc.common.abstracts.CommonService;
import com.loveofworld.mvc.service.GasStationService;
import com.loveofworld.system.util.Log;

public class UpdtaeOnpinetInfo extends CommonService implements ExcuteBatchModule{

	@Override
	public void excute() {
		// TODO Auto-generated method stub
		
		//Log.getInstance().printLog(this, "Batch Call : Thread ID : " + Thread.currentThread().getId());
		long startTime = System.currentTimeMillis();
		
		GasStationService gasStationService = new GasStationService();
		
		Log.getInstance().printLog(this, "===============================================");
		Log.getInstance().printLog(this, "Batch Call : UpdtaeOnpinetInfo : Start Time : " + startTime);
		
		
		boolean isSuccess = gasStationService.setGasPriceInfo();
		
		if(isSuccess){
			Log.getInstance().printLog(this, "Batch Call : UpdtaeOnpinetInfo : Success");
		}
		else{
			Log.getInstance().printLog(this, "Batch Call : UpdtaeOnpinetInfo : Fail");
		}
		
		Log.getInstance().printLog(this, "-----------------------------------------------");
		List<Map<String, Object>> resultInfoList = gasStationService.getGasDailyInfo();
		for(int index = 0 ; index < resultInfoList.size() ; index++){
			Map<String, Object> resultInfo = resultInfoList.get(index);
			
			Log.getInstance().printLog(this, "DATA ["+index+"] : " + resultInfo.toString());
		}
		
		Log.getInstance().printLog(this, "-----------------------------------------------");
		
		
		
		
		Log.getInstance().printLog(this, "Batch Call : UpdtaeOnpinetInfo : End Time : " + (System.currentTimeMillis() - startTime));
		Log.getInstance().printLog(this, "===============================================");
		
		
		
	}

}

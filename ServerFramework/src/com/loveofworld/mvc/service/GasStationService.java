package com.loveofworld.mvc.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import com.loveofworld.external.control.MapWebServiceControl;
import com.loveofworld.external.control.OpinetInfo;
import com.loveofworld.mvc.common.abstracts.CommonService;
import com.loveofworld.system.util.Log;
import com.loveofworld.system.util.SequenceGen;



public class GasStationService extends CommonService{
	
	
	public List<Map<String, Object>> getGasDailyInfo(){
		return getCloudMySqlDAO().selectList("SelectGasDailyInfo", null);
	}
	
	public List<Map<String, Object>> getGasStaioninfoList(){
		return getCloudMySqlDAO().selectList("SelectGasInfoList", null);
	}
	
	
	public boolean setGasPriceInfo(){
		
		OpinetInfo opinetInfo = new OpinetInfo();
		
		ArrayList<Map<String, Object>> opinetDataList = opinetInfo.getOpinetData_CSV();
		
		Log.getInstance().printLog(this, "====================================================");
		
		for(int index = 0 ; index < opinetDataList.size() ; index++){
		//for(int index = 0 ; index < 10 ; index++){
			
			Map<String, Object> opinetData = opinetDataList.get(index);
			opinetData.put("USER_ID"   , "ADMIN");
		
			
			Map<String, Object> db_GasStationInfo  = this.cloudMySqlDAO.selectInfo("SelectGasStationInfo", opinetData);
			
			if(db_GasStationInfo != null){
				
				opinetData.put("GASSTATION_NO", db_GasStationInfo.get("GASSTATION_NO"));
				opinetData.put("IS_TODAY_DATA", "TRUE");
				
				Map<String, Object> db_GasPriceInfo = this.cloudMySqlDAO.selectInfo("SelectGasPriceInfo", opinetData);
				
				if(db_GasPriceInfo != null){
					opinetData.put("UPDATE_DATE", db_GasPriceInfo.get("PRICE_UPDATE_DATE"));
					boolean isSuccessUpdateGasPriceInfo = this.cloudMySqlDAO.updateInfo("UpdateGasPriceDailyInfo", opinetData);
					if(isSuccessUpdateGasPriceInfo != false){
						Log.getInstance().printLog(this, "Data [" + index +"] : Update Gas Price Succsee : " + opinetData.toString());
					}
					else{
						Log.getInstance().printLog(this, "Data [" + index +"] : Update Gas Price Info Fail : " + opinetData.toString());
					}
					
				}
				else{
					opinetData.put("GASPRICE_NO", SequenceGen.getInstance().getSeq(15));
					boolean isSuccessInsertGasPriceInfo = this.cloudMySqlDAO.insertInfo("InsertGasPriceInfo", opinetData);
					if(isSuccessInsertGasPriceInfo != false){
						Log.getInstance().printLog(this, "Data [" + index +"] : Insert Gas Price Succsee : " + opinetData.toString());
					}
					else{
						Log.getInstance().printLog(this, "Data [" + index +"] : Insert Gas Price Info Fail : " + opinetData.toString());
					}
				}
				
				continue;
			}
			
			MapWebServiceControl mapWebServiceControl = new MapWebServiceControl();
			Map<String, Object> resultGeocoder = mapWebServiceControl.getGeoCoding((String)opinetData.get("STATION_ADDRESS"));
			if((Integer)resultGeocoder.get("status") == MapWebServiceControl.CODE_RESULT_SUCCESS){
				opinetData.put("LAT", resultGeocoder.get("lat"));
				opinetData.put("LNG", resultGeocoder.get("lng"));
				
				Map<String, Object> location = new HashMap<String, Object>();
				location.put("lat", opinetData.get("LAT"));
				location.put("lng", opinetData.get("LNG"));
				
				Map<String, Object> addPlaceInfo = new HashMap<String, Object>();
				addPlaceInfo.put("location", location);
				addPlaceInfo.put("name"    , opinetData.get("STATION_NAME"));
				addPlaceInfo.put("phone_number", "000-0000-0000");
				
				List<NameValuePair> websiteInfo = new ArrayList<NameValuePair>();
				websiteInfo.add(new BasicNameValuePair("q"  , (String)opinetData.get("STATION_NAME")));
				String str_WebsiteInfo = URLEncodedUtils.format(websiteInfo, "utf-8");
				
				addPlaceInfo.put("website"     , "https://www.google.co.kr/?gws_rd=ssl#newwindow=1&q="+str_WebsiteInfo);
				addPlaceInfo.put("language", "ko");
				addPlaceInfo.put("address", opinetData.get("STATION_ADDRESS"));
				
				ArrayList<String> typeArray = new ArrayList<String>();
				typeArray.add("gas_station");
				
				addPlaceInfo.put("types", typeArray);
				addPlaceInfo.put("language", "ko");
				
				Map<String, Object> resultAddPlace = mapWebServiceControl.addPlacesInfo(addPlaceInfo);
				
				if((Integer)resultAddPlace.get("status") == MapWebServiceControl.CODE_RESULT_SUCCESS){
					opinetData.put("PLACE_ID", resultAddPlace.get("place_id"));
					Log.getInstance().printLog(this, "Data [" + index +"] : " + opinetDataList.get(index).toString());
				}
				else{
					Log.getInstance().printLog(this, "Data [" + index +"] : Add Place Fail : " + (String)resultGeocoder.get("message") + " : " + opinetData.toString());
				}

				
				this.cloudMySqlDAO.setTransaction(true);
				opinetData.put("GASSTATION_NO", SequenceGen.getInstance().getSeq(15));
				boolean isSuccessInsertGasStationInfo = this.cloudMySqlDAO.insertInfo("InsertGasStationInfo", opinetData);
				if(isSuccessInsertGasStationInfo != false){
					
					opinetData.put("GASPRICE_NO", SequenceGen.getInstance().getSeq(15));
					
					boolean isSuccessInsertGasPriceInfo = this.cloudMySqlDAO.insertInfo("InsertGasPriceInfo", opinetData);
					
					if(isSuccessInsertGasPriceInfo != false){
						Log.getInstance().printLog(this, "Data [" + index +"] : Insert Gas Price Succsee : " + opinetData.toString());
						this.cloudMySqlDAO.commit(false);
					}
					else{
						Log.getInstance().printLog(this, "Data [" + index +"] : Insert Gas Price Info Fail : " + opinetData.toString());
					}
				}
				else{
					Log.getInstance().printLog(this, "Data [" + index +"] : Insert Gas Station Info Fail : " + opinetData.toString());
				}
		
			}
			else if(!((Integer)resultGeocoder.get("status") == MapWebServiceControl.CODE_RESULT_REQUEST_LIMIT)){
				Log.getInstance().printLog(this, "Data [" + index +"] : Geocoding Fail : " + (String)resultGeocoder.get("message") + " : " + opinetData.toString());
				//return false;
			}
			else{
				Log.getInstance().printLog(this, "Data [" + index +"] : Geocoding Fail : " + (String)resultGeocoder.get("message") + " : " + opinetData.toString());
			}
			
		}
		
		Log.getInstance().printLog(this, "====================================================");
		
		
		return true;
	}

}

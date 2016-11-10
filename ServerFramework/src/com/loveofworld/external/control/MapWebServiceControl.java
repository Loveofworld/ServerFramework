package com.loveofworld.external.control;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.loveofworld.system.util.Log;

public class MapWebServiceControl{

	private String str_Appkey = "AIzaSyDmvgaK-LvTxNF6R7VPAsCPHAIvMFxIDIc";
	
	final public static int CODE_TYPE_CELLTOWER = 80000;
	final public static int CODE_TYPE_WIFI      = 80001;
		
	final public static int CODE_RESULT_SUCCESS        = 90001;
	final public static int CODE_RESULT_FAIL           = 90002;
	final public static int CODE_RESULT_REQUEST_LIMIT  = 90002;
	
	
	
	/**
	 * �ۼ����� : 2015.03.04
	 * �ۼ���  : �����
	 * �Լ����� : Google Map Api�� Geocoder�� �̿��Ͽ� ����, �浵 ��ġ������ �̿��Ͽ� �ּ� ������ ��ȯ
	 * �Է����� : Map<String, Object> result  
	  		    = result.get("lat")       - ��������
				= result.get("lng")       - �浵����
	 * ��ȯ���� : Map<String, Object> result  
	  		    = result.get("address")   - �ּ�����
				= result.get("status")    - ó�� �����ڵ� MapWebServiceControl�� ������� (CODE_RESULT_X..X)
				= result.get("message")   - ó�� ���¸޼�
	 */
	public Map<String, Object> getReverseGeoCoding(Map<String, Object> latlng){
		
		
		String url_GeoCoding = "https://maps.googleapis.com/maps/api/geocode/json?";
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("sensor"  , "true"));
		params.add(new BasicNameValuePair("language", "ko"));
		params.add(new BasicNameValuePair("latlng"  , latlng.get("lat") + "," + latlng.get("lng")));
		
		
		String paramString = URLEncodedUtils.format(params, "utf-8");
		  
		url_GeoCoding = url_GeoCoding + paramString;
		
		Log.getInstance().printLog(this, "Geocoding Request URl : " + url_GeoCoding);
		
		HttpClient http = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url_GeoCoding);
		
		HttpResponse responsePost;
		try {
			responsePost = http.execute(httpGet);
			HttpEntity resEntity = responsePost.getEntity();
			String str_ResponseData = EntityUtils.toString(resEntity);
			
			JSONObject root = (JSONObject) JSONValue.parse(str_ResponseData);
			JSONArray results = (JSONArray) root.get("results");
	
			Map<String, Object> result = new HashMap<String, Object>();
			
			if(results.size() > 0){
				result.put("address", ((JSONObject)results.get(0)).get("formatted_address"));
				result.put("status", MapWebServiceControl.CODE_RESULT_SUCCESS);
				result.put("message", "SUCCESS");
			}
			else{
				
				if(((String)root.get("status")).equals("OVER_QUERY_LIMIT")){
					result.put("address", "None");
					result.put("status", MapWebServiceControl.CODE_RESULT_REQUEST_LIMIT);
					result.put("message", (String)root.get("status"));
				}
				else{
					result.put("address", "None");
					result.put("status", MapWebServiceControl.CODE_RESULT_FAIL);
					result.put("message", (String)root.get("status"));
				}
			}

			return result;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
			
		}
		
	
	}
	

	
	

	/**
	 * �ۼ����� : 2015.03.04
	 * �ۼ���  : �����
	 * �Լ����� : Google Map Api�� Geocoder�� �̿��Ͽ� ����, �浵 ��ġ������ �̿��Ͽ� �ּ� ������ ��ȯ
	 * �Է����� : String address - �ּ���  
	 * ��ȯ���� : Map<String, Object> result  
  	  		    = result.get("lat")   - ��������
	  		    = result.get("lng")   - �浵����
	 */
	public Map<String, Object> getGeoCoding(String address){
		
		String url_GeoCoding = "https://maps.googleapis.com/maps/api/geocode/json?";
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("sensor"  , "true"));
		params.add(new BasicNameValuePair("language", "ko"));
		params.add(new BasicNameValuePair("address", address));
		
		
		String paramString = URLEncodedUtils.format(params, "utf-8");
		  
		url_GeoCoding = url_GeoCoding + paramString;
		
		Log.getInstance().printLog(this, "Geocoding Request URl : " + url_GeoCoding);
		
		HttpClient http = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url_GeoCoding);
		
		
		HttpResponse responsePost;
		try {
			responsePost = http.execute(httpGet);
			HttpEntity resEntity = responsePost.getEntity();
			String str_ResponseData = EntityUtils.toString(resEntity);
			
			JSONObject root = (JSONObject) JSONValue.parse(str_ResponseData);
			JSONArray results = (JSONArray) root.get("results");
			
			
			Map<String, Object> result = new HashMap<String, Object>();

			
			if(results.size() > 0){
				JSONObject geometry = (JSONObject) ((JSONObject)results.get(0)).get("geometry");
				JSONObject location = (JSONObject)geometry.get("location");
				result.put("lat", location.get("lat"));
				result.put("lng", location.get("lng"));
				result.put("status", MapWebServiceControl.CODE_RESULT_SUCCESS);
				result.put("message", "SUCCESS");

			}
			else{
				
				if(((String)root.get("status")).equals("OVER_QUERY_LIMIT")){
					result.put("lat", 0.0);
					result.put("lng", 0.0);
					result.put("status", MapWebServiceControl.CODE_RESULT_REQUEST_LIMIT);
					result.put("message", (String)root.get("status"));
				}
				else{
					result.put("lat", 0.0);
					result.put("lng", 0.0);
					result.put("status", MapWebServiceControl.CODE_RESULT_FAIL);
					result.put("message", (String)root.get("status"));
				}
			

			}

			return result;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
			
		}
	}
	
	

	
	public Map<String, Object> addPlacesInfo(Map<String, Object> addPlaceInfo){
		
		String url_Places = "https://maps.googleapis.com/maps/api/place/add/json?key=" + this.str_Appkey;
		
		
		String str_RequestBody = JSONObject.toJSONString(addPlaceInfo);
		
		
		Log.getInstance().printLog(this, "Place Add Request URL  : " + url_Places);
		Log.getInstance().printLog(this, "Place Add Request Body : " + str_RequestBody);
		
		HttpClient http = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url_Places);
		
		StringEntity stringEntity = null;
		stringEntity = new StringEntity(str_RequestBody, "utf-8");
		stringEntity.setContentType("application/json");
		httpPost.setEntity(stringEntity);
	
		HttpResponse responsePost;
		try {
			responsePost = http.execute(httpPost);
			HttpEntity resEntity = responsePost.getEntity();
			String str_ResponseData = EntityUtils.toString(resEntity);
			
			JSONObject root = (JSONObject) JSONValue.parse(str_ResponseData);
			
			Map<String, Object> placeInfo = new HashMap<String, Object>();
			
			if(root.get("status").equals("OK")){
				placeInfo.put("place_id", (String)root.get("place_id"));
				placeInfo.put("status", MapWebServiceControl.CODE_RESULT_SUCCESS);
				placeInfo.put("message", "SUCCESS");
			}
			else{
				
				if(((String)root.get("status")).equals("OVER_QUERY_LIMIT")){
					placeInfo.put("status", MapWebServiceControl.CODE_RESULT_REQUEST_LIMIT);
					placeInfo.put("message", (String)root.get("status"));
				}
				else{
					placeInfo.put("status", MapWebServiceControl.CODE_RESULT_FAIL);
					placeInfo.put("message", (String)root.get("status"));
				}
			}
			
			return placeInfo;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
	
	
	
	/*

	
	
	
	public Map<String, Object> getPlacesInfoNearbySearch(String type, String keyword, Map<String, Object> locatioInfo){
		
		String url_Places = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?key=" + this.str_Appkey;
		
		if(keyword != null){
			url_Places = url_Places + "&keyword=" + Uri.encode(keyword);
		}
		
		if(type != null){
			url_Places = url_Places + "&keyword=" + type;
		}
		
		url_Places = url_Places + "&location=" + locatioInfo.get("lat") + "," + locatioInfo.get("lng");
		url_Places = url_Places + "&radius=" + locatioInfo.get("radius");
		url_Places = url_Places + "&language=" + "ko";
		
		String url_GeoLocationEncoding = Uri.encode(url_Places);
		
		Map<String, Object> requestBody = new HashMap<String, Object>();
		String str_RequestBody = JSONObject.toJSONString(requestBody);
		
		Log.d("GeoLocarion", "Request URL : " + url_Places);
		Log.d("GeoLocarion", "Request URL Encoding: " + url_GeoLocationEncoding);
		Log.d("GeoLocarion", "Request Body : " + str_RequestBody);
		
		
		
		HttpClient http = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url_Places);
		
		StringEntity stringEntity = null;
		try {
			stringEntity = new StringEntity(str_RequestBody, "utf-8");
			stringEntity.setContentType("application/json");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		httpPost.setEntity(stringEntity);
	
		HttpResponse responsePost;
		try {
			responsePost = http.execute(httpPost);
			HttpEntity resEntity = responsePost.getEntity();
			String str_ResponseData = EntityUtils.toString(resEntity);
			Log.d("GeoLocation", "Response Data : " + str_ResponseData);
			
			JSONObject root = (JSONObject) JSONValue.parse(str_ResponseData);
			
			return setPlacesInfoResult(root);
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}

	
	
	
	
	public Map<String, Object> getPlacesInfoTextSearch(String keyword, Map<String, Object> locatioInfo){
		
		String url_Places = "https://maps.googleapis.com/maps/api/place/textsearch/json?key=" + this.str_Appkey;
		
		url_Places = url_Places + "&query=" + Uri.encode(keyword);
		url_Places = url_Places + "&location=" + locatioInfo.get("lat") + "," + locatioInfo.get("lng");
		url_Places = url_Places + "&radius=" + locatioInfo.get("radius");
		url_Places = url_Places + "&language=" + "ko";
		
		String url_GeoLocationEncoding = Uri.encode(url_Places);
		
		Map<String, Object> requestBody = new HashMap<String, Object>();
		String str_RequestBody = JSONObject.toJSONString(requestBody);
		
		Log.d("GeoLocarion", "Request URL : " + url_Places);
		Log.d("GeoLocarion", "Request URL Encoding: " + url_GeoLocationEncoding);
		Log.d("GeoLocarion", "Request Body : " + str_RequestBody);
		
		
		
		HttpClient http = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url_Places);
		
		StringEntity stringEntity = null;
		try {
			stringEntity = new StringEntity(str_RequestBody, "utf-8");
			stringEntity.setContentType("application/json");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		httpPost.setEntity(stringEntity);
	
		HttpResponse responsePost;
		try {
			responsePost = http.execute(httpPost);
			HttpEntity resEntity = responsePost.getEntity();
			String str_ResponseData = EntityUtils.toString(resEntity);
			Log.d("GeoLocation", "Response Data : " + str_ResponseData);
			
			JSONObject root = (JSONObject) JSONValue.parse(str_ResponseData);
			
			return setPlacesInfoResult(root);
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}

	
	

	public Map<String, Object> getPlacesInfoRadarSearch(String type, Map<String, Object> locatioInfo){
		
		String url_Places = "https://maps.googleapis.com/maps/api/place/radarsearch/json?key=" + this.str_Appkey;
		
		//url_Places = url_Places + "&keyword=" + Uri.encode(keyword);
		url_Places = url_Places + "&location=" + locatioInfo.get("lat") + "," + locatioInfo.get("lng");
		url_Places = url_Places + "&radius=" + locatioInfo.get("radius");
		url_Places = url_Places + "&types=" + type;
		url_Places = url_Places + "&language=" + "ko";
		
		String url_GeoLocationEncoding = Uri.encode(url_Places);
		
		Map<String, Object> requestBody = new HashMap<String, Object>();
		String str_RequestBody = JSONObject.toJSONString(requestBody);
		
		Log.d("GeoLocarion", "Request URL : " + url_Places);
		Log.d("GeoLocarion", "Request URL Encoding: " + url_GeoLocationEncoding);
		Log.d("GeoLocarion", "Request Body : " + str_RequestBody);
		
		
		
		HttpClient http = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url_Places);
		
		StringEntity stringEntity = null;
		try {
			stringEntity = new StringEntity(str_RequestBody, "utf-8");
			stringEntity.setContentType("application/json");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		httpPost.setEntity(stringEntity);
	
		HttpResponse responsePost;
		try {
			responsePost = http.execute(httpPost);
			HttpEntity resEntity = responsePost.getEntity();
			String str_ResponseData = EntityUtils.toString(resEntity);
			Log.d("GeoLocation", "Response Data : " + str_ResponseData);
			
			JSONObject root = (JSONObject) JSONValue.parse(str_ResponseData);
			
			return setPlacesInfoResult(root);
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
	
	
	private Map<String, Object> setPlacesInfoResult(JSONObject root){

		ArrayList<Map<String, Object>> placeInfoList = new ArrayList<Map<String, Object>>();
		Map<String, Object> resultData = new HashMap<String, Object>();
		
		if(root.get("status").equals("OK")){

			JSONArray results = (JSONArray) root.get("results");
				
			for(int index = 0 ; index < results.size() ; index++){
				JSONObject result = (JSONObject)results.get(index);

				JSONObject location = (JSONObject)((JSONObject)result.get("geometry")).get("location");
				
				Map<String, Object> placeInfo = new HashMap<String, Object>();
				placeInfo.put("lat", location.get("lat"));
				placeInfo.put("lng", location.get("lng"));
				placeInfo.put("name", result.get("name"));
				
				String addressData = (String)result.get("formatted_address");
				if(addressData == null){
					addressData = (String)result.get("vicinity");
				}
				placeInfo.put("address", addressData);
				
				placeInfo.put("icon", (String)result.get("icon"));
				
				placeInfo.put("place_id", (String)result.get("place_id"));
				
				placeInfoList.add(placeInfo);
			}
			
			
			resultData.put("status", MapWebServiceControl.CODE_RESULT_SUCCESS);
			resultData.put("addressList", placeInfoList);
			
			return resultData;
			
		}
		else{
			
			resultData.put("status", MapWebServiceControl.CODE_RESULT_SUCCESS);
			resultData.put("addressList", placeInfoList);
			return resultData;
		}
	}
	
	
	
	
	

	public Map<String, Object> getPlacesDetail(String place_id){
			
		String url_Places = "https://maps.googleapis.com/maps/api/place/details/json?key=" + this.str_Appkey;
		url_Places = url_Places + "&placeid=" + place_id;
		url_Places = url_Places + "&language=" + "ko";
		
		String url_GeoLocationEncoding = Uri.encode(url_Places);
		
		Map<String, Object> requestBody = new HashMap<String, Object>();
		String str_RequestBody = JSONObject.toJSONString(requestBody);
		
		Log.d("GeoLocarion", "Request URL : " + url_Places);
		Log.d("GeoLocarion", "Request URL Encoding: " + url_GeoLocationEncoding);
		Log.d("GeoLocarion", "Request Body : " + str_RequestBody);
		
		
		HttpClient http = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url_Places);
		
		StringEntity stringEntity = null;
		try {
			stringEntity = new StringEntity(str_RequestBody, "utf-8");
			stringEntity.setContentType("application/json");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		httpPost.setEntity(stringEntity);
	
		HttpResponse responsePost;
		try {
			responsePost = http.execute(httpPost);
			HttpEntity resEntity = responsePost.getEntity();
			String str_ResponseData = EntityUtils.toString(resEntity);
			Log.d("GeoLocation", "Response Data : " + str_ResponseData);
			
			JSONObject root = (JSONObject) JSONValue.parse(str_ResponseData);
			
			Map<String, Object> placeInfo = new HashMap<String, Object>();
			
			if(root.get("status").equals("OK")){
				JSONObject result = (JSONObject)root.get("result");
			      
				JSONObject location = (JSONObject)((JSONObject)result.get("geometry")).get("location");
				placeInfo.put("lat", location.get("lat"));
				placeInfo.put("lng", location.get("lng"));
				
				placeInfo.put("name", result.get("name"));
				placeInfo.put("address", result.get("formatted_address"));
				placeInfo.put("tel", result.get("formatted_phone_number"));
				placeInfo.put("place_id", result.get("place_id"));
				placeInfo.put("icon"    , result.get("icon"));
				placeInfo.put("status", MapWebServiceControl.CODE_RESULT_SUCCESS);
				placeInfo.put("message", "SUCCESS");
			}
			else{
				placeInfo.put("status", MapWebServiceControl.CODE_RESULT_FAIL);
				placeInfo.put("message", (String)root.get("error_message"));
			}
			
			return placeInfo;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
	
	
	
	
	
	public Map<String, Object> addPlacesInfo(Map<String, Object> addPlaceInfo){
		
		String url_Places = "https://maps.googleapis.com/maps/api/place/add/json?key=" + this.str_Appkey;
		
		
		String str_RequestBody = JSONObject.toJSONString(addPlaceInfo);
		
		Log.d("GeoLocarion", "Request URL : " + url_Places);
		Log.d("GeoLocarion", "Request Body : " + str_RequestBody);
		
		
		HttpClient http = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url_Places);
		
		StringEntity stringEntity = null;
		try {
			stringEntity = new StringEntity(str_RequestBody, "utf-8");
			stringEntity.setContentType("application/json");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		httpPost.setEntity(stringEntity);
	
		HttpResponse responsePost;
		try {
			responsePost = http.execute(httpPost);
			HttpEntity resEntity = responsePost.getEntity();
			String str_ResponseData = EntityUtils.toString(resEntity);
			Log.d("GeoLocation", "Response Data : " + str_ResponseData);
			
			JSONObject root = (JSONObject) JSONValue.parse(str_ResponseData);
			
			Map<String, Object> placeInfo = new HashMap<String, Object>();
			
			if(root.get("status").equals("OK")){
				placeInfo.put("place_id", (String)root.get("place_id"));
				placeInfo.put("status", MapWebServiceControl.CODE_RESULT_SUCCESS);
				placeInfo.put("message", "SUCCESS");
			}
			else{
				placeInfo.put("status", MapWebServiceControl.CODE_RESULT_FAIL);
				placeInfo.put("message", (String)root.get("error_message"));
			}
			
			return placeInfo;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
	
	 */
	
		
}




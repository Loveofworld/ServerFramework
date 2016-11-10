package com.loveofworld.external.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import au.com.bytecode.opencsv.CSVReader;

public class OpinetInfo {

	public OpinetInfo(){
	}
	
	public ArrayList<Map<String, Object>> getOpinetData_CSV(){

		ArrayList<Map<String, Object>>  dataList = new ArrayList<Map<String, Object>>();
		
		try {
			
			String url = "http://www.opinet.co.kr/user/main/main_download_excel.do"
					+ "?LPG_CD=A"
					+ "&PAGE_DIV=PAGE_DIV_5"
					+ "&DATE_DIV_CD="
					+ "&SIDO_NM="
					+ "&SIGUN_NM="
					;
			
			//URL obj_Url = new URL(url);
			//URLConnection conn = obj_Url.openConnection();
			
			
			URLConnection connection = new URL(url).openConnection();
			connection.setRequestProperty("Accept-Charset", "EUC-KR");
			connection.setConnectTimeout(3000);
			InputStream response = connection.getInputStream();
			
			if(response != null){
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response, "EUC-KR"));
				CSVReader cSVReader = new CSVReader(bufferedReader);
				
				String[] values = cSVReader.readNext();
				
				int count = 0;
				while ( values != null ) {
	            
	            	values = cSVReader.readNext();
	            	if(values != null){
	            		
	            		if(count > 4){
			                String addressInfo = null;
	            		    //OpinetData opinetData= new OpinetData();
			                Map<String, Object> opinetData = new HashMap<String, Object>();
		            		if(values.length >= 9){

		            			int charIndex = values[2].indexOf("(");
				                if(charIndex != -1){
				                	addressInfo = values[2].substring(0, charIndex);
				                }
				                else{
				                	addressInfo = values[2];
				                }
				                
				                opinetData.put("LOCATION_AREA",values[0]);
				                opinetData.put("STATION_NAME",values[1]);
				                opinetData.put("STATION_ADDRESS",addressInfo);
				                opinetData.put("STATION_COMPANY",values[3]);
				                opinetData.put("IS_SELF",values[4]);
				                opinetData.put("PRICE_GAS_HIGH",values[5]);
				                opinetData.put("PRICE_GAS",values[6]);
				                opinetData.put("PRICE_DIESEL",values[7]);
				                opinetData.put("PRICE_LAMPOIL",values[8]);
				                
			            		dataList.add(opinetData);
		            		}
		            		
		            	}
	            	}
	                count = count + 1;
	            }
				
				cSVReader.close();
				
			}
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
			
		return dataList;
	}
}

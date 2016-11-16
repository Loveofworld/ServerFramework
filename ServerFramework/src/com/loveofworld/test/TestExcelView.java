package com.loveofworld.test;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import com.loveofworld.system.util.Log;
import com.loveofworld.system.util.Util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class TestExcelView{
//public class TestExcelView extends AbstractExcelView{
//	
//	@Override
//	protected void buildExcelDocument(Map<String, Object> model,
//			HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		// TODO Auto-generated method stub
//		String fileName = createFileName();
//		setFileNameToResponse(request, response, fileName);        
//      
//		HSSFSheet sheet = workbook.createSheet("회원목록");
//		sheet.setDefaultColumnWidth((short)12);
////엑셀 첫번째 목록 줄		
//		short currentRow = 0;
//		HSSFCell USER_NO = getCell(sheet, currentRow, 0);
//		setText(USER_NO, "회원번호");
//
//		HSSFCell USER_ID = getCell(sheet, currentRow, 1);
//		setText(USER_ID, "회원 ID");
//		
//		HSSFCell USER_NM = getCell(sheet, currentRow, 2);
//		setText(USER_NM, "회원이름");
//		
//		HSSFCell USER_ENG_NM = getCell(sheet, currentRow, 3);
//		setText(USER_ENG_NM, "회원영어이름");
//        
//		HSSFCell USER_DEPT_CD = getCell(sheet, currentRow, 4);
//		setText(USER_DEPT_CD, "회원부서");
//		
//		HSSFCell USER_LEVEL_CD = getCell(sheet, currentRow, 5);
//		setText(USER_LEVEL_CD, "회원직급");
//		
//		HSSFCell ZIP_CD = getCell(sheet, currentRow, 6);
//		setText(ZIP_CD, "우편번호");
//		
//		HSSFCell DETAIL_ADDR = getCell(sheet, currentRow, 7);
//		setText(DETAIL_ADDR, "세부주소");
//
//		HSSFCell USER_TEL_NO = getCell(sheet, currentRow, 9);
//		setText(USER_TEL_NO, "전화번호");
//		
//		HSSFCell USER_MOB_NO = getCell(sheet, currentRow, 8);
//		setText(USER_MOB_NO, "휴대번화");
////		
//		for(int i = 0 ; i < 65535 ; i++){
//			HSSFRow dataRow = sheet.createRow(i);
//			dataRow.createCell(0).setCellValue("USER_NO()" + i);
//			dataRow.createCell(1).setCellValue("USER_ID()" + i);
//			dataRow.createCell(2).setCellValue("USER_NM()" + i);
//			dataRow.createCell(3).setCellValue("USER_ENG_NM()" + i);
//			dataRow.createCell(4).setCellValue("USER_DEPT_CD()" + i);
//			dataRow.createCell(5).setCellValue("USER_LEVEL_CD()" + i);
//			dataRow.createCell(6).setCellValue("ZIP_CD()" + i);
//			dataRow.createCell(7).setCellValue("DETAIL_ADDR()" + i);
//			dataRow.createCell(9).setCellValue("USER_TEL_NO()" + i);
//			dataRow.createCell(8).setCellValue("USER_MOB_NO()" + i);
//			
//			Log.getInstance().printLog(this, "엑셀다운 : " + i);
//			MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
//			Log.getInstance().printLog(this, "메모리 검사 : " + memoryBean.getHeapMemoryUsage() );
//		}
//        
//	}
//	
//	private void setFileNameToResponse(HttpServletRequest request, HttpServletResponse response, String fileName) {
//		String userAgent = request.getHeader("User-Agent");
//		if (userAgent.indexOf("MSIE 5.5") >= 0) {
//			response.setContentType("doesn/matter");
//			response.setHeader("Content-Disposition","filename=\""+fileName+"\"");
//		}else{
//			response.setHeader("Content-Disposition","attachment; filename=\""+fileName+"\"");
//		}
//	}
//
//	private String createFileName() {
//		SimpleDateFormat fileFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
//		return new StringBuilder("MemberList").append("-").append(fileFormat.format(new Date())).append(".xlsx").toString();
//	}
//
//	
//	private void setFileNameToResponse(HttpServletRequest request, HttpServletResponse response, String fileName) {
//		String userAgent = request.getHeader("User-Agent");
//		if (userAgent.indexOf("MSIE 5.5") >= 0) {
//			response.setContentType("doesn/matter");
//			response.setHeader("Content-Disposition","filename=\""+fileName+"\"");
//		}else{
//			response.setHeader("Content-Disposition","attachment; filename=\""+fileName+"\"");
//		}
//	}
//
//	private String createFileName() {
//		SimpleDateFormat fileFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
//		return new StringBuilder("MemberList").append("-").append(fileFormat.format(new Date())).append(".xlsx").toString();
//	}
//
//	
//	
//	@Override
//	protected Workbook createWorkbook() {
//		// TODO Auto-generated method stub
//		XSSFWorkbook wb = new XSSFWorkbook();
//		return wb;
//	}
//
//	@Override
//	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response){
//		// TODO Auto-generated method stub
//		String fileName = createFileName();
//		setFileNameToResponse(request, response, fileName);        
//         
//		XSSFWorkbook wb = (XSSFWorkbook)workbook;
//	    
//		XSSFSheet sheet = wb.createSheet("회원목록");
//		
//		XSSFRow titleRow = sheet.createRow(0);
//		
//		XSSFCell USER_NO = titleRow.createCell(0);
//		USER_NO.setCellValue("회원번호");
//
//		XSSFCell USER_ID = titleRow.createCell(1);
//		USER_ID.setCellValue("회원 ID");
//		
//		XSSFCell USER_NM = titleRow.createCell(2);
//		USER_NM.setCellValue("회원이름");
//		
//		XSSFCell USER_ENG_NM = titleRow.createCell(3);
//		USER_ENG_NM.setCellValue("회원영어이름");
//        
//		XSSFCell USER_DEPT_CD = titleRow.createCell(4);
//		USER_DEPT_CD.setCellValue("회원부서");
//		
//		XSSFCell USER_LEVEL_CD = titleRow.createCell(5);
//		USER_LEVEL_CD.setCellValue("회원직급");
//		
//		XSSFCell ZIP_CD = titleRow.createCell(6);
//		ZIP_CD.setCellValue("우편번호");
//		
//		XSSFCell DETAIL_ADDR = titleRow.createCell(7);
//		DETAIL_ADDR.setCellValue("세부주소");
//
//		XSSFCell USER_TEL_NO = titleRow.createCell(9);
//		USER_TEL_NO.setCellValue("전화번호");
//		
//		XSSFCell USER_MOB_NO = titleRow.createCell(8);
//		USER_MOB_NO.setCellValue("휴대번화");
//		
//		
//		for(int i = 0 ; i < 70000 ; i++){
//			XSSFRow dataRow = sheet.createRow(i);
//			dataRow.createCell(0).setCellValue("USER_NO()" + i);
//			dataRow.createCell(1).setCellValue("USER_ID()" + i);
//			dataRow.createCell(2).setCellValue("USER_NM()" + i);
//			dataRow.createCell(3).setCellValue("USER_ENG_NM()" + i);
//			dataRow.createCell(4).setCellValue("USER_DEPT_CD()" + i);
//			dataRow.createCell(5).setCellValue("USER_LEVEL_CD()" + i);
//			dataRow.createCell(6).setCellValue("ZIP_CD()" + i);
//			dataRow.createCell(7).setCellValue("DETAIL_ADDR()" + i);
//			dataRow.createCell(9).setCellValue("USER_TEL_NO()" + i);
//			dataRow.createCell(8).setCellValue("USER_MOB_NO()" + i);
//			
//			Util.getInstance().printLog(this, "엑셀다운 : " + i);
//		}
//		
//	}
    
  }

package com.loveofworld.system.util;

import java.util.HashMap;

import org.apache.commons.lang.RandomStringUtils;

public class SequenceGen {

private static SequenceGen instance;
	
	private final long MULTIPLIER = 1664525L;
	private final long INCREMENT  = 1013904223L;
	private String SERVER_CODENAME = "00";
	
	
	
//	private long beforeSeqNum = 0L;
	
//	private long firstSeqNum  = 0L;
	
	private int MAX_LENGTH = 15;
	

	private HashMap<String, SequenceData> sequenceDataMap;
	
	private SequenceGen() {
		// TODO Auto-generated constructor stub
		sequenceDataMap = new HashMap<String, SequenceData>();
		SERVER_CODENAME = "00";
	}
	
	public static synchronized SequenceGen getInstance(){
		if(instance == null){
			synchronized (SequenceGen.class){
				if(instance == null){
					instance = new SequenceGen();
				}
			}
		}
		
		return instance;
	}
	
	public String getSeq(int length){
		
	
		StringBuffer sequenceNumberStringBuffer = new StringBuffer();
		
		String prefixSeq = null;
		
		if(length > MAX_LENGTH){
			prefixSeq = createRandomNumber(MAX_LENGTH);
			sequenceNumberStringBuffer.append(RandomStringUtils.randomNumeric(length - MAX_LENGTH));
		}
		else{
			prefixSeq = createRandomNumber(length);
		}
		
		sequenceNumberStringBuffer.append(prefixSeq);
		
		return sequenceNumberStringBuffer.toString();
	}
	
	public String getSeq(String prefix, int length){
		String timeSequenceNumber = getInstance().getSeq(length - prefix.length());
		return prefix + timeSequenceNumber;
	
	}
	
	private String createRandomNumber(int length){
	
		synchronized(getInstance()){
			
			SequenceData sequenceData = sequenceDataMap.get(String.valueOf(length));
			String randomNumberString = SERVER_CODENAME + String.valueOf(System.nanoTime()/1000);
			
			if(sequenceData == null){
				sequenceData = new SequenceData();
				sequenceData.seqLength = length;
				sequenceDataMap.put(String.valueOf(length), sequenceData);
			}
			
			StringBuffer maskValue = new StringBuffer();
			for(int index = 0 ; index < length ; index++){
				maskValue.append("9");
			}
			
			long valueCount = Long.parseLong(maskValue.toString());
			long randomNumber = 0L;
			long sequencevalue = 0L;
			
			if(sequenceData.beforeSeqNum != 0){
				randomNumber = sequenceData.beforeSeqNum;
				sequencevalue = ((MULTIPLIER * randomNumber) + INCREMENT) % valueCount;
		
				if(sequenceData.firstSeqNum == sequencevalue){
					randomNumber = Long.parseLong(randomNumberString);
					sequencevalue = ((MULTIPLIER * randomNumber) + INCREMENT) % valueCount;
					sequenceData.firstSeqNum = sequencevalue;
				}
			}
			else{
				randomNumber = Long.parseLong(randomNumberString);
				sequencevalue = ((MULTIPLIER * randomNumber) + INCREMENT) % valueCount;
				sequenceData.firstSeqNum = sequencevalue;
			}
			
			if(sequencevalue < 0){
				sequencevalue = sequencevalue * -1;
			}
			
			sequenceData.beforeSeqNum = sequencevalue;
			
			return String.format("%0"+length+"d", sequencevalue);
			
		}
	}	
	
	
	class SequenceData {
		private long beforeSeqNum = 0L;
		private long firstSeqNum  = 0L;
		private int  seqLength    = 0;
	}
	
	
}

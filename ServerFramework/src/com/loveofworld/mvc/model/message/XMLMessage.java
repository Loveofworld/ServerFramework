package com.loveofworld.mvc.model.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.loveofworld.mvc.model.abstracts.MessageService;

import java.util.ArrayList;

@XmlRootElement(name = "Response")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLMessage extends MessageService {

	@XmlElement(name = "DATA")
	private XMLMessageData xMLMessageData;

	public XMLMessage(){
		this.xMLMessageData = new XMLMessageData();
	}
	
	public XMLMessageData getxMLMessageData() {
		return xMLMessageData;
	}

	public void setxMLMessageData(XMLMessageData xMLMessageData) {
		this.xMLMessageData = xMLMessageData;
	}
	
	public void setMapDataList(ArrayList<Map<String, Object>> mapDataList) {
		this.xMLMessageData.setMapDataList(mapDataList);;
	}


	@Override
	public Map<String, Object> convertResponseMessage() {
		// TODO Auto-generated method stub
		
		Map<String, Object> mapMessage = new HashMap<String, Object>();
		mapMessage.put("STATE", getSTATUS());
		mapMessage.put("MSG"  , getMSG());
		mapMessage.put("DATA" , this.xMLMessageData.getMapDataList());

		return mapMessage;
	}

}



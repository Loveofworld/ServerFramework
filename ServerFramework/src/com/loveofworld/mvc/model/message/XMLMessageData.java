package com.loveofworld.mvc.model.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlAccessorType(XmlAccessType.FIELD)
public class XMLMessageData {
	
	@XmlJavaTypeAdapter(value = Adapter.class)
	@XmlElement(name = "MapData")
	private ArrayList<Map<String, Object>> mapDataList;
	
	public ArrayList<Map<String, Object>> getMapDataList() {
		return mapDataList;
	}

	public void setMapDataList(ArrayList<Map<String, Object>> mapDataList) {
		this.mapDataList = mapDataList;
	}
	
}


class Adapter extends XmlAdapter<KeyValue[], Map<String, Object>> {
	
    @Override
    public Map<String, Object> unmarshal(KeyValue[] v) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        for (KeyValue keyValue : v) {
            map.put(keyValue.getKey(), keyValue.getValue());
        }
        return map;
    }

    @Override
    public KeyValue[] marshal(Map<String, Object> v) throws Exception {
        Set<String> keys = v.keySet();
        List<KeyValue> results = new ArrayList<KeyValue>();
        for (String key : keys) {
            results.add(new KeyValue(key, v.get(key).toString()));
        }
        return results.toArray(new KeyValue[0]);
    }
}


@XmlAccessorType(XmlAccessType.FIELD)
class KeyValue {
    
	@XmlElement
	private String key;

	@XmlValue
	private String value;
    
    public KeyValue() {
    	super();
    }

    public KeyValue(String key, String value) {
    	super();
        this.key = key;
        this.value = value;
    }

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}

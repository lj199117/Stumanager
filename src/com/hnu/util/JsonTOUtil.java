package com.hnu.util;
  
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
  
public class JsonTOUtil {  
    private static Log log = LogFactory.getLog(JsonTOUtil.class);  
    public static String object2json(Object obj) {  
        StringBuilder json = new StringBuilder();  
        if (obj == null) {  
            json.append("\"\"");  
        } else if(obj instanceof Date){
        	//日期格式话
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        	obj = sdf.format(obj).toString();
        	 json.append("\"").append(obj).append("\""); 
        } else if (obj instanceof String || obj instanceof Integer || obj instanceof Float || obj instanceof Boolean || obj instanceof Short || obj instanceof Double || obj instanceof Long || obj instanceof BigDecimal || obj instanceof BigInteger || obj instanceof Byte) {  
            json.append("\"").append(string2json(obj.toString())).append("\"");  
        } else if (obj instanceof Object[]) {  
            json.append(array2json((Object[]) obj));  
        } else if (obj instanceof List) {  
            json.append(list2json((List<?>) obj));  
        } else if (obj instanceof Map) {  
            json.append(map2json((Map<?, ?>) obj));  
        } else if (obj instanceof Set) {  
            json.append(set2json((Set<?>) obj));  
        } else {  
            json.append(bean2json(obj));  
        }  
        return json.toString();  
    }  
    public static String bean2json(Object bean) {  
        StringBuilder json = new StringBuilder();  
        json.append("{");  
        PropertyDescriptor[] props = null;  
        try {  
            props = Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors();  
        } catch (IntrospectionException e) {  
        }  
        if (props != null) {  
            for (int i = 0; i < props.length; i++) {  
                try {  
                    String name = object2json(props[i].getName());  
                    String value = object2json(props[i].getReadMethod().invoke(bean));  
                    json.append(name);  
                    json.append(":");  
                    json.append(value);  
                    json.append(",");  
                } catch (Exception e) {  
                }  
            }  
            json.setCharAt(json.length() - 1, '}');  
        } else {  
            json.append("}");  
        }  
        return json.toString();  
    }  
    public static String list2json(List<?> list) {  
        StringBuilder json = new StringBuilder();  
        json.append("[");  
        if (list != null && list.size() > 0) {  
            for (Object obj : list) {  
                json.append(object2json(obj));  
                json.append(",");  
            }  
            json.setCharAt(json.length() - 1, ']');  
        } else {  
            json.append("]");  
        }  
        return json.toString();  
    }  
    public static String array2json(Object[] array) {  
        StringBuilder json = new StringBuilder();  
        json.append("[");  
        if (array != null && array.length > 0) {  
            for (Object obj : array) {  
                json.append(object2json(obj));  
                json.append(",");  
            }  
            json.setCharAt(json.length() - 1, ']');  
        } else {  
            json.append("]");  
        }  
        return json.toString();  
    }  
    public static String map2json(Map<?, ?> map) {  
        StringBuilder json = new StringBuilder();  
        json.append("{");  
        if (map != null && map.size() > 0) {  
            for (Object key : map.keySet()) {  
                json.append(object2json(key));  
                json.append(":");  
                json.append(object2json(map.get(key)));  
                json.append(",");  
            }  
            json.setCharAt(json.length() - 1, '}');  
        } else {  
            json.append("}");  
        }  
        return json.toString();  
    }  
    
    public static void main(String[] args) {
    	String jsonString = "{\"userId\":\"ljadsf\",\"userName\":\"\",\"deptName\":\"\",\"beginTime\":\"2015-08-13 19:57:28\",\"endTime\":\"2015-08-13 19:57:28\"}";
    	HashMap<String, String> map = json2HashMap(jsonString);
    	System.out.println(map.get("userId"));
    	System.out.println(map.get("beginTime"));
	}
    
    public static HashMap<String, String> json2HashMap(Object object){  
//System.out.println(object);
//System.out.println(object.toString());
        HashMap<String, String> data = new HashMap<String, String>();  
        // 将json字符串转换成jsonObject  
        JSONObject jsonObject = JSONObject.fromObject(object.toString());  
        Iterator it = jsonObject.keys();  
        // 遍历jsonObject数据，添加到Map对象  
        while (it.hasNext()){  
            String key = String.valueOf(it.next());  
            String value = (String) jsonObject.get(key);  
            data.put(key, value);  
        }  
        return data;  
    }  
    
    public static List json2list(String jsonString,Class pojoClass){
    	List list = null;
    	try {
			JSONArray jsonarray = JSONArray.fromObject("[" + jsonString + "]");
			System.out.println(jsonarray);
			list = (List) JSONArray.toList(jsonarray, pojoClass); // System.out.println(list);
		} catch (Exception e) { 
			// TODO: handle exception
			e.printStackTrace();
		}
    	return list;
    }
    
    public static Object json2object(String jsonString,Class pojoClass){
    	return json2list(jsonString, pojoClass).get(0);
    }
    
    public static String set2json(Set<?> set) {  
        StringBuilder json = new StringBuilder();  
        json.append("[");  
        if (set != null && set.size() > 0) {  
            for (Object obj : set) {  
                json.append(object2json(obj));  
                json.append(",");  
            }  
            json.setCharAt(json.length() - 1, ']');  
        } else {  
            json.append("]");  
        }  
        return json.toString();  
    }  
    
    public static Date dateFormatJson(Date date){
    	
    	
    	
    	return new Date();
    }
    
    public static String string2json(String s) {  
        if (s == null)  
            return "";  
        StringBuilder sb = new StringBuilder();  
        for (int i = 0; i < s.length(); i++) {  
            char ch = s.charAt(i);  
            switch (ch) {  
                case '"' :  
                    sb.append("\\\"");  
                    break;  
                case '\\' :  
                    sb.append("\\\\");  
                    break;  
                case '\b' :  
                    sb.append("\\b");  
                    break;  
                case '\f' :  
                    sb.append("\\f");  
                    break;  
                case '\n' :  
                    sb.append("\\n");  
                    break;  
                case '\r' :  
                    sb.append("\\r");  
                    break;  
                case '\t' :  
                    sb.append("\\t");  
                    break;  
                case '/' :  
                    sb.append("\\/");  
                    break;  
                default :  
                    if (ch >= '\u0000' && ch <= '\u001F') {  
                        String ss = Integer.toHexString(ch);  
                        sb.append("\\u");  
                        for (int k = 0; k < 4 - ss.length(); k++) {  
                            sb.append('0');  
                        }  
                        sb.append(ss.toUpperCase());  
                    } else {  
                        sb.append(ch);  
                    }  
            }  
        }  
        return sb.toString();  
    }  
    
}  
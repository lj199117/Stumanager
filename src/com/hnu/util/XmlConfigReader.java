package com.hnu.util;

import java.io.InputStream;
import java.util.Iterator;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

/**
 * @author Administrator
 *
 */
public class XmlConfigReader {

//	public static void main(String[] args) {
//		
//	}

	private JdbcConfig jc = new JdbcConfig();
	
	public JdbcConfig getJdbcConfig(){
		return jc;
	}
	private static XmlConfigReader instance = null;
	
	public static synchronized XmlConfigReader getInstance(){
		if(instance == null){
			instance = new XmlConfigReader();
		}
		return instance;
	}
	
	private XmlConfigReader () {
		Document doc = null;
        try {
	        SAXReader reader = new SAXReader();
	       
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("system-config.xml");
            doc = reader.read(is);
            Element dbElementItem = (Element)doc.selectObject("/config/db-info/db-name");
            jc.setDatebasename(dbElementItem.getStringValue());
            Element driverElementItem = (Element)doc.selectObject("/config/db-info/driver-name");
            jc.setDriver(driverElementItem.getStringValue());
            Element urlElementItem = (Element)doc.selectObject("/config/db-info/url");
            jc.setUrl(urlElementItem.getStringValue());
            Element usernameElementItem = (Element)doc.selectObject("/config/db-info/username");
            jc.setUsername(usernameElementItem.getStringValue());
            Element pwdElementItem = (Element)doc.selectObject("/config/db-info/password");
            jc.setPassword(pwdElementItem.getStringValue());
            
        } catch (DocumentException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}

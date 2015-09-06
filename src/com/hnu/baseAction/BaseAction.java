package com.hnu.baseAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.hnu.util.JsonTOUtil;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware ,SessionAware{

	private static final long serialVersionUID = 1L;

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;
	public int start;
	public int limit; //分页 使用
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}
	
	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

    public void setServletRequest(HttpServletRequest request) {  
        this.request = request;  
    }  
  
    public void setServletResponse(HttpServletResponse response) {  
        this.response = response;  
    }

	public void setSession(Map session) {
		// TODO Auto-generated method stub
		//this.session = session;
	}  
	
	 public HttpServletRequest getRequest() {
	  return ServletActionContext.getRequest();
	 }
	 
	 public HttpServletResponse getResponse() {
	  return ServletActionContext.getResponse();
	 }
    
	 public HttpSession getSession() {
		 return getRequest().getSession();
	 }
	 
	
    /**
     * @param list
     * @param count
     */
    public void WriteStringJson (List list, int count) {  
        PrintWriter writer = null;  
        try {  
            response.setContentType("text/html; charset=utf-8");  
            writer = response.getWriter();  
            HashMap hMap = new HashMap();
    		hMap.put("total",count);
    		hMap.put("data", list);
    		String jsonString = JsonTOUtil.map2json(hMap);
            writer.print(jsonString);  
            writer.flush();  
        } catch (IOException e) {  
        } finally {  
            if (writer != null) {  
                writer.close();  
            }  
        }  
    }  
    
}

package com.hnu.action;

import java.sql.Connection;
import java.util.List;

import com.hnu.baseAction.BaseAction;
import com.hnu.pojo.Student;
import com.hnu.service.StuManagerService;
import com.hnu.serviceImp.StuManagerServiceImp;
import com.hnu.util.DBUtil;

public class StuManagerAction extends BaseAction {
	private StuManagerService stuManagerService = new StuManagerServiceImp();
	
	public void getUserList(){
		Connection conn = DBUtil.getConn();
		List<Student> stus = this.stuManagerService.getStudents(conn, getStart(), getLimit());
		this.WriteStringJson(stus,1);
	}
}

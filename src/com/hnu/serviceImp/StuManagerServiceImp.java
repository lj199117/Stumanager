package com.hnu.serviceImp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hnu.pojo.Student;
import com.hnu.service.StuManagerService;
import com.hnu.util.DBUtil;

public class StuManagerServiceImp implements StuManagerService {

	@Override
	public List<Student> getStudents(Connection conn, int start, int limit) {
		// TODO Auto-generated method stub
		String sql =  "select * from student limit "+start+","+limit;
		ResultSet rs = DBUtil.getRS(conn, sql);
		List<Student> userList = new ArrayList<Student>();
		try {
			while(rs.next()){
				Student s = new Student();
				s.setStuId(rs.getInt(1));
				s.setStuName(rs.getString(2));
				s.setRemark(rs.getString(3));
				userList.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public int delStudent() {
		// TODO Auto-generated method stub
		return 0;
	}

	

}

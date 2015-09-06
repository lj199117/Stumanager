package com.hnu.service;

import java.sql.Connection;
import java.util.List;

import com.hnu.pojo.Student;

public interface StuManagerService {
	/**
	 * @return
	 */
	List<Student> getStudents(Connection conn,int start,int limit);
	
	/**
	 * 
	 */
	int delStudent();
}

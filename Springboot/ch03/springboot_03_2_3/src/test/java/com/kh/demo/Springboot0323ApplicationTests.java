package com.kh.demo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Springboot0323ApplicationTests {
	
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void whenCountAllCoursesThenExpectFiveCourses() throws SQLException{
		ResultSet rs = null;
		int noOfCourses = 0;
		
		try(PreparedStatement ps = dataSource.getConnection().prepareStatement("SELECT COUNT(1) FROM COURSES")){
			rs = ps.executeQuery();
			while(rs.next()) {
				noOfCourses = rs.getInt(1);
			}
			assertThat(noOfCourses).isEqualTo(5L);
		} finally {
			if(rs != null) {
				rs.close();
			}
		}
	}

}

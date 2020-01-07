package com.example.demo;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Demo1ApplicationTests {

	@Autowired
	DataSource datasource;
	
	@Test
	void contextLoads() throws SQLException {
		
	Connection con= datasource.getConnection();
	
	System.out.println(con);
	
	con.close();
	
	
	
	
	}

}

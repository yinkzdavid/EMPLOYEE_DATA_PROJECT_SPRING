package com.employee_data;

import static org.assertj.core.api.Assertions.assertThat;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.employee.test.config.DBTestConfig;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@ContextConfiguration(classes= DBTestConfig.class)
@RunWith(SpringRunner.class)
public class DBConnectionTest {

	
	@Autowired
	private Environment env;
	
	@Autowired @Qualifier("testDatasSource")
	private ComboPooledDataSource testDataSource;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void dbConnectionTest() throws SQLException {
		
		assertThat(env).isNotNull();

		
		String jdbcUrl = testDataSource.getJdbcUrl();
		String user = testDataSource.getUser();
		String password = testDataSource.getPassword();
		String driver = testDataSource.getDriverClass();
		
//		String driver = env.getProperty("test.driver");
		
		System.out.println(jdbcUrl + " " + user + " " + password);
		
		Connection dbCon = null;
		
		try {
			
			assertThat(jdbcUrl).isNotNull();
			
			dbCon = DriverManager.getConnection(jdbcUrl, user, password);
			assertThat(dbCon).isNotNull();
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
			
		}
//		finally {
//			dbCon.close();
//		}
	}
}

/**
 * 
 */
package com.employee_data;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.activation.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.employee.dao.EmployeeDao;
import com.employee.entity.Employee;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author Yinkz
 *
 */
@Sql(scripts= {"classpath:/db/create-table.sql", "classpath:/db/insert-employees.sql"})
@ContextConfiguration(locations= {"classpath:data-context.xml"})
@RunWith(SpringRunner.class)
public class EmployeeDaoImplTest {
	
	
	
	@Autowired
	private EmployeeDao employeeDaoImpl;


	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	
	
	@Test
	public void saveEmployeeToDBTest() {
		
		Employee newEmployee = new Employee();
		newEmployee.setFirstName("Mary");
		newEmployee.setLastName("Black");
		newEmployee.setEmail("mary@yahoo.com");
		newEmployee.setPhoneNumber("07083878990");
		
		Date employeeDate = Date.valueOf("2000-7-24");
		newEmployee.setDateOfBirth(employeeDate);
		
		assertThat(employeeDaoImpl).isNotNull();
		
		employeeDaoImpl.saveEmployee(newEmployee);
		
		int id = newEmployee.getEmployeeId();
		
		//System.out.println("New Employee Id --> " + id);
		
		
		Employee existingEmployee = employeeDaoImpl.getById(id);
		assertThat(existingEmployee).isNotNull();
		
		
	}
	
	@Test
	public void getEmployeeByEmailTest() {
		assertThat(employeeDaoImpl).isNotNull();
		
		Employee savedEmployee = employeeDaoImpl.getByEmail("jon@mail.com");
		assertThat(savedEmployee).isNotNull();
		
		assertThat(savedEmployee.getEmployeeId()).isEqualTo(5);
		
		System.out.println(savedEmployee);
	}

}

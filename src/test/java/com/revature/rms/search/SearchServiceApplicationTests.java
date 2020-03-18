package com.revature.rms.search;

import com.revature.rms.search.clients.EmployeeClient;
import com.revature.rms.search.dtos.*;
import com.revature.rms.search.entites.campus.*;
import com.revature.rms.search.entites.employee.Department;
import com.revature.rms.search.entites.employee.Employee;
import com.revature.rms.search.entites.employee.ResourceMetadata;
import com.revature.rms.search.services.ETLService;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class SearchServiceApplicationTests {


	ETLService sut;
	EmployeeClient employeeClient;
	Building building;
	BuildingDto buildingDto;
	EmployeeDto employeeDto;
	EmployeeDto employeeDto1;
	//Employee employee;
	Employee employee1;

	@Before
	public void setUp(){
		employeeDto = mock(EmployeeDto.class);
		employeeDto1 = mock(EmployeeDto.class);
		//employee = mock(Employee.class);
		employee1 = mock(Employee.class);
		employeeClient = mock(EmployeeClient.class);
	}

	@Test
	public void getEmployeeByIdTest(){
		EmployeeDto expectedResult = new EmployeeDto(1, "Wezley", "Singleton", "wsingleton@revature.com", "Training Lead", "Training", new ResourceMetadataDto());
		Employee employee = new Employee(1, "Wezley", "Singleton", "wsingleton@revature.com", "Training Lead", Department.TRAINING, new ResourceMetadata());
		when(employeeClient.getEmployeeById(Mockito.any())).thenReturn(employee);
		when(employee.extractEmployee()).thenReturn(expectedResult);
		Mockito.when(sut.getEmployeeById(1)).thenReturn(expectedResult);
		assertEquals(sut.getEmployeeById(1), expectedResult);
	}

	@Test
	public void getBuildingDataTest(){
		Building building = new Building();
		BuildingDto buildingDto = new BuildingDto();
		when(building.extractBuilding()).thenReturn(buildingDto);
		when(sut.getBuildingData(building)).thenReturn(buildingDto);
		assertEquals(buildingDto, sut.getBuildingData(building));
	}

	@Ignore
	void contextLoads() {
	}
}

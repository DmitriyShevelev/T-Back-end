package telran.employees.controller.random;

import java.util.*;
import java.util.stream.Stream;

import telran.employees.dto.Employee;
import telran.employees.services.EmployeesMethods;
import telran.employees.services.EmployeesMethodsMapsImpl;

import java.io.*;
import java.time.LocalDate;

public class RandomEmployeesCreation {
static Properties props = new Properties();
static String[] departments;
	public static void main(String[] args) throws Exception {
		
		fillProperties();
		fillDepartments();
		EmployeesMethods employeesMethods = EmployeesMethodsMapsImpl.getEmptyEmployees();
		Stream.generate(() -> new Employee(getRandomId(),
				getRandomSalary(), getRandomDate(), getRandomDepartment()))
		.distinct().limit(getNEmployees()).forEach(employeesMethods::addEmployee);
		employeesMethods.save(getFilePath());
		

	}
	private static String getFilePath() {
		
		return props.getProperty("filePath", "employees.data");
	}
	private static long getNEmployees() {
		
		return Integer.parseInt(props.getProperty("nEmployees", "100"));
	}
	private static void fillDepartments() {
		departments = props.getProperty("departments").split("[, ]+");
		
	}
	private static <T> T getRandomElement(T[] array) {
		int index = (int) (Math.random() * array.length);
		return array[index];
	}
	private static String getRandomDepartment() {
		
		return getRandomElement(departments);
	}
	private static LocalDate getRandomDate() {
		int year = getRandomNumber("minYear", "maxYear");
		
		return LocalDate.ofYearDay(year, (int) (1 + Math.random() * 365));
	}
	private static int getRandomSalary() {
		
		return getRandomNumber("minSalary", "maxSalary");
	}
	private static long getRandomId() {
		
		
		return getRandomNumber("minId", "maxId");
	}
	private static int getRandomNumber(String minProps, String maxProps) {
		int min = Integer.parseInt(props.getProperty(minProps));
		int max = Integer.parseInt(props.getProperty(maxProps));
		return (int) (min + Math.random() * (max - min));
	}
	private static void fillProperties() throws FileNotFoundException, IOException {
		InputStream propsStream = new FileInputStream("random-config.properties");
		props.load(propsStream);
	}

}

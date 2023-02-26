package telran.employees.controller;

import java.util.List;

import telran.employees.dto.SalaryRangeEmployees;
import telran.employees.services.EmployeesMethods;

public class SalaryDistributionAppl {

	private static final String FILE_PATH_DEFAULT = "employees.data";
	private static final int INTERVAL_DEFAULT = 1000;

	public static void main(String[] args) {
		//args[0] - file path if no, file path is employees.data
		//args[1] - interval of the salary values, if no 1000 will be set
		//creating EmployeesMethods
		//exceptions handling
		//output:
		//minimal salary: <value>, maximal salary: <value>
		//each line - result of toString for Employee
		String filePath = args.length > 0 ? args[0] : FILE_PATH_DEFAULT;
		int interval = 0;
		try {
			interval = args.length > 1 ? Integer.parseInt(args[1]) : INTERVAL_DEFAULT;
		} catch (NumberFormatException e) {
			System.out.println("Interval should be integer number");
			return;
		}
		printDistribution(filePath, interval);

	}

	private static void printDistribution(String filePath, int interval) {
		try {
			EmployeesMethods employees = EmployeesMethods.getEmployees(filePath);
			List<SalaryRangeEmployees> distribution = employees.distributionSalary(interval);
			print(distribution);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	private static void printInterval(SalaryRangeEmployees sre) {
		System.out.printf("minimal salary: %d, maximal salary: %d\n", sre.minSalary, sre.maxSalary);
		sre.employees.forEach(System.out::println);
	}

	private static void print(List<SalaryRangeEmployees> distribution) {
		distribution.forEach(SalaryDistributionAppl::printInterval);
		
	}

}

package telran.employees.controller;

import java.util.Arrays;

import telran.employees.controller.actions.EmployeesActions;
import telran.employees.services.EmployeesMethods;
import telran.employees.services.EmployeesMethodsMapsImpl;
import telran.view.*;

public class EmployeesConsoleAppl {

	private static final String DEFAULT_FILE_PATH = "employees.data";

	public static void main(String[] args) throws Exception {
		String filePath = //pv1//args.length > 0 ? args[0] : DEFAULT_FILE_PATH;
		InputOutput io = new ConsoleInputOutput();
		EmployeesMethods employeesService = null;
		try {
			employeesService = EmployeesMethods.getEmployees(filePath);
		} catch (Exception e) {
			employeesService = EmployeesMethodsMapsImpl.getEmptyEmployees();
			io.writeObjectLine("No persistent file, new DB is created");
		}
		Item[] items = EmployeesActions.getEmployeesItems(employeesService,
				Arrays.asList("QA", "Development", "Audit", "Management"));
		Menu menu = new Menu("Employees Menu", items);
		try {
			menu.perform(io);
		} catch (EndOfInputException e) {
					try {
						employeesService.save(filePath);
					} 
				
			catch (Exception e1) {
				io.writeObjectLine(e1.getMessage());
			}
		}
	}
}
		

	


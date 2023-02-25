package telran.employees.controller.actions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import telran.employees.dto.Employee;
import telran.employees.dto.EmployeesCodes;
import telran.employees.services.EmployeesMethods;
import telran.view.*;

public class EmployeesActions {
private static EmployeesMethods employeesService;
private static List<String> departments;

private EmployeesActions() {
	
}
public static Item[] getEmployeesItems(EmployeesMethods employeesService, 
		List<String> departments) throws Exception {
	EmployeesActions.departments = departments;
	EmployeesActions.employeesService = employeesService;
	
	return getItems();
}
private static Item[] getItems() throws Exception {
	Item adminMenu = getAdministratorMenu();
	Item userMenu = getUserMenu();
	Item exitAndSave = Item.of("Exit and Save", EmployeesActions::saveWithExit, true);
	Item [] items = {adminMenu, userMenu, exitAndSave, Item.exit()};
	return items;
}
private static Item getUserMenu() throws Exception {
	ArrayList<Item> items = getUserItems();
	items.add(Item.exit());
	
	return new Menu("User's Actions Menu", items);
}
private static Item getAdministratorMenu() throws Exception {
	ArrayList<Item> items = getAdministratorItems();
	items.add(Item.exit());
	
	return new Menu("Administartor Actions Menu", items);
}
private static ArrayList<Item> getUserItems() throws Exception {
	ArrayList<Item> userItems = new ArrayList<>();
	userItems.add(Item.of("Display all employees", t -> {
		try {
			displayAllEmployees(t);
		} catch (Exception e) {
			t.writeObjectLine(e.getMessage());
		}
	}));
	userItems.add(Item.of("Display employee", t -> {
		try {
			displayEmployee(t);
		} catch (Exception e1) {
			t.writeObjectLine(userItems);
		}
	}));
	userItems.add(Item.of("Query employees by salary values",
			t -> {
				try {
					employeesSalary(t);
				} catch (Exception e) {
					t.writeObjectLine(e.getMessage());
				}
			}));
	userItems.add(Item.of("Query employees by age values", t -> {
		try {
			employeesAge(t);
		} catch (Exception e) {
			t.writeObjectLine(e.getMessage());
		}
	}));
	userItems.add(Item.of("Query employees by department", t -> {
		try {
			employeesDepartment(t);
		} catch (Exception e) {
			e.getMessage();
		}
	}));
	userItems.add(Item.of("Display departments and average salary", t -> {
		try {
			departmentSalary(t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}));
	return userItems;
}
static private void displayEmployees(Iterable<Employee> employees, InputOutput io) {
	if(employees.iterator().hasNext()) {
		employees.forEach(io::writeObjectLine);
	} else {
		io.writeObjectLine("No employees");
	}
}
static private void displayAllEmployees(InputOutput io) throws Exception {
	displayEmployees(employeesService.getAllEmployees(),io);
}
static private void displayEmployee(InputOutput io) throws Exception {
	
	Employee empl = employeesService.getEmployee(getId(io));
	io.writeObjectLine(empl == null ? "Employee not found " : empl);
}
static private void employeesSalary(InputOutput io) throws Exception {
	int salaryFrom = io.readInt("Enter salary from");
	int salaryTo = io.readInt("Enter salary to", salaryFrom + 1, Integer.MAX_VALUE);
	displayEmployees( employeesService.getEmployeesBySalary(salaryFrom, salaryTo), io );
}
static private void employeesAge(InputOutput io) throws Exception {
	int ageFrom = io.readInt("Enter age from");
	int ageTo = io.readInt("Enter age to", ageFrom + 1, Integer.MAX_VALUE);
	displayEmployees( employeesService.getEmployeesByAge(ageFrom, ageTo), io );
}
static private void employeesDepartment(InputOutput io) throws Exception {
	displayEmployees(employeesService.getEmployeesByDepartment(getDepartment(io)),io);
}
static private void departmentSalary(InputOutput io) throws Exception {
	io.writeObjectLine("Department\tSalary");
	employeesService.getDepartmentsSalary()
	.forEach((k, v) -> io.writeObjectLine(String.format("%s\t%d", k, v)));
}
private static ArrayList<Item> getAdministratorItems() throws Exception {
	ArrayList<Item> res = new ArrayList<>();
	res.add(Item.of("Add Employee", t -> {
		try {
			addEmployee(t);
		} catch (Exception e) {
			t.writeObjectLine(e.getMessage());
		}
	}));
	res.add(Item.of("Remove Employee", t -> {
		try {
			removeEmployee(t);
		} catch (Exception e) {
			t.writeObjectLine(e.getMessage());
		}
	}));
	res.add(Item.of("Update Salary", t -> {
		try {
			updateSalary(t);
		} catch (Exception e) {
			t.writeObjectLine(e.getMessage());
		}
	}));
	res.add(Item.of("Update Department", t -> {
		try {
			updateDepartment(t);
		} catch (Exception e) {
			t.writeObjectLine(e.getMessage());
		}
	}));
	return res;
}
private static void saveWithExit(InputOutput io) {
	String filePath = io.readString("Enter file name for saving data");
	try {
		employeesService.save(filePath);
	} catch (Exception e) {
		throw new RuntimeException("Wrong file path");
	}
}
private static void addEmployee(InputOutput io) throws Exception {
	Employee empl = new Employee(getId(io),getSalary(io),getBirthDate(io), getDepartment(io));
	EmployeesCodes res = employeesService.addEmployee(empl);
	io.writeObjectLine(res.toString());
}
private static String getDepartment(InputOutput io) {
	
	return io.readStringOption(" Enter Department " + departments, new TreeSet<String>(departments));
}
private static LocalDate getBirthDate(InputOutput io) {
	
	return io.readDate("Enter Birthdate");
}
private static int getSalary(InputOutput io) {
	
	return io.readInt("Enter salary", 5000, 30000);
}
private static long getId(InputOutput io) {
	
	return io.readLong("Enter id");
}
private static void removeEmployee(InputOutput io) throws Exception {
	long id = getId(io);
	io.writeObjectLine(employeesService.removeEmployee(id));
}
private static void updateSalary(InputOutput io) throws Exception {
	long id = getId(io);
	int salary = getSalary(io);
	io.writeObjectLine(employeesService.updateSalary(id, salary));
}
private static void updateDepartment(InputOutput io) throws Exception {
	long id = getId(io);
	String department = getDepartment(io);
	io.writeObjectLine(employeesService.updateDepartment(id, department));
}

}

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
private static InputOutput io;
private EmployeesActions() {
	
}
public static Item[] getEmployeesItems(EmployeesMethods employeesService, 
		List<String> departments, InputOutput io) {
	EmployeesActions.departments = departments;
	EmployeesActions.employeesService = employeesService;
	EmployeesActions.io = io;
	return getItems();
}
private static Item[] getItems() {
	List<Item> adminItems = getAdministratorItems();
	List<Item> userItems = getUserItems();
	adminItems.addAll(userItems);
	adminItems.add(Item.of("Exit and Save", EmployeesActions::saveWithExit, true));
	return adminItems.toArray(new Item[0]);
}
private static List<Item> getUserItems() {
	// TODO Auto-generated method stub
	return null;
}
private static List<Item> getAdministratorItems() {
	List<Item> res = new ArrayList<>();
	res.add(Item.of("Add Employee", EmployeesActions::addEmployee));
	res.add(Item.of("Remove Employee", EmployeesActions::removeEmployee));
	res.add(Item.of("Update Salary", EmployeesActions::updateSalary));
	res.add(Item.of("Update Department", EmployeesActions::updateDepartment));
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
private static void addEmployee(InputOutput io) {
	Employee empl = new Employee(getId(),getSalary(),getBirthDate(), getDepartment());
	EmployeesCodes res = employeesService.addEmployee(empl);
	io.writeObjectLine(res.toString());
}
private static String getDepartment() {
	
	return io.readStringOption(" Enter Department " + departments, new TreeSet<String>(departments));
}
private static LocalDate getBirthDate() {
	
	return io.readDate("Enter Birthdate");
}
private static int getSalary() {
	
	return io.readInt("Enter salary", 5000, 30000);
}
private static long getId() {
	
	return io.readLong("Enter id");
}
private static void removeEmployee(InputOutput io) {
	long id = getId();
	io.writeObjectLine(employeesService.removeEmployee(id));
}
private static void updateSalary(InputOutput io) {
	long id = getId();
	int salary = getSalary();
	io.writeObjectLine(employeesService.updateSalary(id, salary));
}
private static void updateDepartment(InputOutput io) {
	long id = getId();
	String department = getDepartment();
	io.writeObjectLine(employeesService.updateDepartment(id, department));
}

}

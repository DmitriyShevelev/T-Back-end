package telran.employees.services;

import telran.employees.dto.Employee;
import telran.employees.dto.EmployeesCodes;

import java.time.LocalDate;
import java.util.*;

public class EmployeesMethodsMapsImpl implements EmployeesMethods {
private HashMap<Long, Employee> employees = new HashMap<>();
private HashMap<String, List<Employee>> employeesDep = new HashMap<>();
private TreeMap<Integer, List<Employee>> employeesSalary = new TreeMap<>();
private TreeMap<Integer, List<Employee>> employeesAge = new TreeMap<>(); //key - birth year

	@Override
	public EmployeesCodes addEmployee(Employee empl) {
		var res = employees.putIfAbsent(empl.getId(), empl);
		if (res != null) {
			return EmployeesCodes.ALREADY_EXISTS;
		}
		addEmployeeDep(empl);
		addEmployeeSalary(empl);
		addEmployeeAge(empl);
		return EmployeesCodes.OK;
	}

	private void addEmployeeAge(Employee empl) {
		employeesAge.computeIfAbsent(empl.getBirthDate().getYear(),
				e -> new LinkedList<>()).add(empl);
		
	}

	private void addEmployeeSalary(Employee empl) {
		employeesSalary.computeIfAbsent(empl.getSalary(),
				e -> new LinkedList<Employee>()).add(empl);
		
	}

	private void addEmployeeDep(Employee empl) {
		employeesDep.computeIfAbsent(empl.getDepartment(),
				e -> new LinkedList<Employee>()).add(empl);
		
	}

	@Override
	public EmployeesCodes removeEmployee(long id) {
		Employee empl = employees.remove(id);
		if (empl == null) {
			return EmployeesCodes.NOT_FOUND;
		}
		removeEmployeeDep(empl);
		removeEmployeeSalary(empl);
		removeEmployeeAge(empl);
		return EmployeesCodes.OK;
	}

	private void removeEmployeeAge(Employee employee) {
		employeesAge.compute(employee.getBirthDate().getYear(), (k, v) -> {
		    v.remove(employee);
		    
		    if (v.size() == 0) {
			return null;
		    }
		    
		    return v;
		});
		
	}

	private void removeEmployeeSalary(Employee employee) {
		employeesSalary.compute(employee.getSalary(), (k, v) -> {
		    v.remove(employee);
		    
		    if (v.size() == 0) {
			return null;
		    }
		    
		    return v;
		});
		
	}

	private void removeEmployeeDep(Employee employee) {
		employeesDep.compute(employee.getDepartment(), (k, v) -> {
		    v.remove(employee);
		    
		    if (v.size() == 0) {
			return null;
		    }
		    
		    return v;
		});
		
	}

	@Override
	public EmployeesCodes updateSalary(long id, int newSalary) {
		var replacingEmployee = getEmployee(id);
		if (replacingEmployee == null) { return EmployeesCodes.NOT_FOUND; }
		if (replacingEmployee.getSalary() == newSalary) {
			return EmployeesCodes.NO_UPDATE_REQUIRED;
		}
		Employee newEmployee = new Employee(id, newSalary, replacingEmployee.getBirthDate(), replacingEmployee.getDepartment());
		replaceEmployee(replacingEmployee, newEmployee);
		return EmployeesCodes.OK;
	}
	private void replaceEmployee(Employee replacingEmploeey, Employee newEmploeey) {
		removeEmployee(replacingEmploeey.getId());
		addEmployee(newEmploeey);
	}

	@Override
	public EmployeesCodes updateDepartment(long id, String newDepartment) {
		var replacingEmployee = getEmployee(id);
		if (replacingEmployee == null) { return EmployeesCodes.NOT_FOUND; }
		if (replacingEmployee.getDepartment().equals(newDepartment)) {
			return EmployeesCodes.NO_UPDATE_REQUIRED;
		}
		Employee newEmploeey = new Employee(id, replacingEmployee.getSalary(), replacingEmployee.getBirthDate(), newDepartment);
		replaceEmployee(replacingEmployee, newEmploeey);
		return EmployeesCodes.OK;
	}

	@Override
	public Employee getEmployee(long id) {
		
		return employees.get(id);
	}

	@Override
	public Iterable<Employee> getEmployeesBySalary
	(int fromInclusive, int toExclusive) {
		 var result = new LinkedList<Employee>();
	        employeesSalary.subMap(fromInclusive, toExclusive)
	                .values()
	                .forEach(result::addAll);
	        return result;
	}

	@Override
	public Iterable<Employee> getEmployeesByAge
	(int fromInclusive, int toExclusive) {
		
		List<Employee> result = new LinkedList<>();
		var currentYear = LocalDate.now().getYear();
		var minYear = currentYear - toExclusive;
		var maxYear =   currentYear - fromInclusive 	;	
		employeesAge.subMap(minYear,false, maxYear, true)
		.values().forEach(result::addAll);
		return result;
	}

	@Override
	public Iterable<Employee> getEmployeesByDepartment(String department) {
		var res = employeesDep.get(department);
		return res == null ? Collections.emptyList() : res;
	}

	@Override
	public Iterable<Employee> getAllEmployees() {
		
		return employees.values();
	}

}

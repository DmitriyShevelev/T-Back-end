package telran.employees.net;

import java.util.List;
import java.util.Map;

import telran.employees.dto.Employee;
import telran.employees.dto.EmployeesCodes;
import telran.employees.dto.SalaryRangeEmployees;
import telran.employees.dto.UpdateSalaryData;
import telran.employees.dto.UpdateDepartmentData;
import telran.employees.services.EmployeesMethods;
import telran.net.TcpJavaClient;
import static telran.employees.api.RequestTypesApi.*;

public class EmployeesProxyTcpJava extends TcpJavaClient implements EmployeesMethods {

	public EmployeesProxyTcpJava(String host, int port) throws Exception {
		super(host, port);
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public EmployeesCodes addEmployee(Employee empl) throws Exception {
		
		return send(ADD_EMPLOYEE, empl);
	}

	@Override
	public EmployeesCodes removeEmployee(long id) throws Exception {
		
		return send(REMOVE_EMPLOYEE, id);
	}

	@Override
	public EmployeesCodes updateSalary(long id, int newSalary) throws Exception {
		
		return send(UPDATE_SALARY, new UpdateSalaryData(id, newSalary));
	}

	@Override
	public EmployeesCodes updateDepartment(long id, String newDepartment) throws Exception {
		return send(UPDATE_DEPARTMENT, new UpdateDepartmentData(id, newDepartment));
	}

	@Override
	public Employee getEmployee(long id) throws Exception {
		
		return send(GET_EMPLOYEE, id);
	}

	@Override
	public Iterable<Employee> getAllEmployees() throws Exception {
		
		return send(GET_ALL_EMPLOYEES, "");
	}

	@Override
	public Iterable<Employee> getEmployeesBySalary(int fromInclusive, int toExclusive) throws Exception {
		
		return send (GET_EMPLOYEES_SALARY, new int[] {fromInclusive, toExclusive});
	}

	@Override
	public Iterable<Employee> getEmployeesByAge(int fromInclusive, int toExclusive) throws Exception {
		return send (GET_EMPLOYEES_AGE, new int[] {fromInclusive, toExclusive});
	}

	@Override
	public Iterable<Employee> getEmployeesByDepartment(String department) throws Exception {
		
		return send(GET_EMPLOYEES_DEPARTMENT, department);
	}

	@Override
	public Map<String, Integer> getDepartmentsSalary() throws Exception {
		return send(GET_DEPARTMENTS_SALARY, "");
	}

	@Override
	public List<SalaryRangeEmployees> distributionSalary(int interval) throws Exception {
		
		return send(DISTRIBUTION_SALARY, interval);
	}

	@Override
	public void save(String fileName) throws Exception {
		throw new Exception("client can't send request for saving");

	}

}

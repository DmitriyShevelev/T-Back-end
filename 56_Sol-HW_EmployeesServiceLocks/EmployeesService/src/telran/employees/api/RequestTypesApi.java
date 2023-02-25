package telran.employees.api;



public interface RequestTypesApi {

	String ADD_EMPLOYEE = "employees/add";
	String REMOVE_EMPLOYEE = "employees/remove";
	String UPDATE_SALARY = "employees/salary/update";
	String UPDATE_DEPARTMENT = "employees/department/update";
	String GET_EMPLOYEE = "employees/get";
	String GET_ALL_EMPLOYEES = "employees";
	String GET_EMPLOYEES_AGE = "employees/age/get";
	String GET_EMPLOYEES_DEPARTMENT = "employees/department/get";
	String GET_EMPLOYEES_SALARY = "employees/salary/get";
	String GET_DEPARTMENTS_SALARY = "employees/departments/salary/get";
	String DISTRIBUTION_SALARY = "employees/distribution/salary";
}

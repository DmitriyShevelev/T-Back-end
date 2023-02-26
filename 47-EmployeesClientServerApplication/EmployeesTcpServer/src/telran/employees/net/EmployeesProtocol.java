package telran.employees.net;

import telran.employees.dto.*;
import telran.employees.services.EmployeesMethods;
import telran.net.ApplProtocolJava;
import telran.net.dto.*;

import static telran.employees.api.RequestTypesApi.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EmployeesProtocol implements ApplProtocolJava {
EmployeesMethods employeesMethods;

	public EmployeesProtocol(EmployeesMethods employeesMethods) {
	
	this.employeesMethods = employeesMethods;
}

	@Override
	public ResponseJava getResponse(RequestJava request) {

		switch(request.requestType) {
		case ADD_EMPLOYEE: return employees_add(request.data);
		case REMOVE_EMPLOYEE:return employees_remove(request.data);
		case UPDATE_SALARY:return employees_salary_update(request.data);
		case UPDATE_DEPARTMENT: return employees_department_update(request.data);
		case GET_EMPLOYEE : return employees_get(request.data);
		case GET_ALL_EMPLOYEES: return employees(request.data);
		case GET_EMPLOYEES_AGE: return employees_age_get(request.data);
		case GET_EMPLOYEES_DEPARTMENT: return employees_department_get(request.data);
		case GET_EMPLOYEES_SALARY: return employees_salary_get(request.data);
		case GET_DEPARTMENTS_SALARY: return employees_departments_salary_get(request.data);
		case DISTRIBUTION_SALARY: return employees_distribution_salary(request.data);
		default: return new ResponseJava(ResponseCode.WRONG_REQUEST_TYPE, request.requestType);
		}
		
	}

	private ResponseJava employees_distribution_salary(Serializable data) {
		// TODO Auto-generated method stub
		return null;
	}

	private ResponseJava employees_departments_salary_get(Serializable data) {
		// TODO Auto-generated method stub
		return null;
	}

	private ResponseJava employees_salary_get(Serializable data) {
		// TODO Auto-generated method stub
		return null;
	}

	private ResponseJava employees_department_get(Serializable data) {
		// TODO Auto-generated method stub
		return null;
	}

	private ResponseJava employees_age_get(Serializable data) {
		// TODO Auto-generated method stub
		return null;
	}

	private ResponseJava employees(Serializable data) {
		
		try {
			return new ResponseJava(ResponseCode.OK,
					new ArrayList<Employee>((Collection<Employee>)employeesMethods.getAllEmployees()));
		} catch (Exception e) {
			return getWrongDataResponse(e);
		}
	}

	private ResponseJava employees_get(Serializable data) {
		try {
			return new ResponseJava(ResponseCode.OK, employeesMethods.getEmployee((long)data));
		} catch (Exception e) {
			return getWrongDataResponse(e);
		}
	}

	private ResponseJava employees_department_update(Serializable data) {
		UpdateDepartmentData depData = (UpdateDepartmentData)data;
		
		try {
			return new ResponseJava(ResponseCode.OK,
					employeesMethods.updateDepartment(depData.id, depData.department));
		} catch (Exception e) {
			return getWrongDataResponse(e);
		}
	}

	private ResponseJava employees_salary_update(Serializable data) {
		// TODO Auto-generated method stub
		return null;
	}

	private ResponseJava employees_remove(Serializable data) {
		// TODO Auto-generated method stub
		return null;
	}

	private ResponseJava employees_add(Serializable data) {
		
		try {
			return new ResponseJava(ResponseCode.OK, employeesMethods.addEmployee((Employee)data));
		} catch (Exception e) {
			return getWrongDataResponse(e);
		}
	}

	private ResponseJava getWrongDataResponse(Exception e) {
		
		return new ResponseJava(ResponseCode.WRONG_REQUEST_DATA, e);
	}

}

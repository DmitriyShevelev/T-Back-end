import static telran.employees.api.RequestTypesApi.ADD_EMPLOYEE;
import static telran.employees.api.RequestTypesApi.DISTRIBUTION_SALARY;
import static telran.employees.api.RequestTypesApi.GET_ALL_EMPLOYEES;
import static telran.employees.api.RequestTypesApi.GET_DEPARTMENTS_SALARY;
import static telran.employees.api.RequestTypesApi.GET_EMPLOYEE;
import static telran.employees.api.RequestTypesApi.GET_EMPLOYEES_AGE;
import static telran.employees.api.RequestTypesApi.GET_EMPLOYEES_DEPARTMENT;
import static telran.employees.api.RequestTypesApi.GET_EMPLOYEES_SALARY;
import static telran.employees.api.RequestTypesApi.REMOVE_EMPLOYEE;
import static telran.employees.api.RequestTypesApi.UPDATE_DEPARTMENT;
import static telran.employees.api.RequestTypesApi.UPDATE_SALARY;

import java.io.IOException;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

import telran.employees.dto.Employee;
import telran.employees.dto.UpdateDepartmentData;
import telran.employees.dto.UpdateSalaryData;
import telran.net.TcpJavaClient;


public class Worker extends Thread {
	
	private static final int N_DEPARTMENTS = 10;
	TcpJavaClient client ;
	int nRuns;
	static String departments[] = getDepartments();

 public Worker(String host, int port, int nRuns) throws Exception {
	client =  new TcpJavaClient(host, port);
	this.nRuns = nRuns;
	
}
@Override
 public void run() {
	Runnable sendMethods[] = {this::addEmployee, this::removeEmployee,
			this::replaceEmployee, this::getEmployees, this::departmentSalary,
			this::distributionSalary
			};
	ThreadLocalRandom tlr = ThreadLocalRandom.current();
	for(int i = 0; i < nRuns; i++) {
		int ind = tlr.nextInt(sendMethods.length);
		sendMethods[ind].run();
	}
	try {
		client.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
private static  String[] getDepartments() {
	String[] res = new String[N_DEPARTMENTS];
	for (int i = 0; i < res.length; i++) {
		res[i] = "dep" + (i + 1);
	}
	return res;
}
 void addEmployee() {
	Employee empl = getEmployee();
	try {
		client.send(ADD_EMPLOYEE, empl);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}
 void removeEmployee() {
	try {
		client.send(REMOVE_EMPLOYEE, getEmplId());
	} catch (Exception e) {
		e.printStackTrace();
	}
}
 void replaceEmployee() {
	try {
		client.send(UPDATE_DEPARTMENT, new UpdateDepartmentData(getEmplId(), getDep()));
		client.send(UPDATE_SALARY, new UpdateSalaryData(getEmplId(), getSalary()));
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}
 void getEmployees() {
	try {
		client.send(GET_ALL_EMPLOYEES, null);
		client.send(GET_EMPLOYEE, getEmplId());
		client.send(GET_EMPLOYEES_AGE, new int[] {20, 30});
		client.send(GET_EMPLOYEES_SALARY, new int[] {5000, 10000});
		client.send(GET_EMPLOYEES_DEPARTMENT, getDep());
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}
void departmentSalary() {
	try {
		client.send(GET_DEPARTMENTS_SALARY, null);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
}
 void distributionSalary() {
	try {
		client.send(DISTRIBUTION_SALARY, 1000);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
}
private  Employee getEmployee() {
	return new Employee(getEmplId(), getSalary(), LocalDate.now(), getDep());
}
private  String getDep() {
	int ind = getRandomNumber(0,N_DEPARTMENTS);
	return departments[ind];
}
private  int getRandomNumber(int min, int bound) {
	
	return min + ThreadLocalRandom.current().nextInt(bound);
}
private  int getSalary() {
	
	return getRandomNumber(5000, 25000);
}
private  long getEmplId() {
	
	return getRandomNumber(1, 100);
}

}


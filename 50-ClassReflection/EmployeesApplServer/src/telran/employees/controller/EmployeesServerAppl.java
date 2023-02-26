package telran.employees.controller;

import telran.employees.net.EmployeesProtocol;
import telran.employees.services.EmployeesMethods;
import telran.employees.services.EmployeesMethodsMapsImpl;
import telran.net.*;

public class EmployeesServerAppl {

	public static void main(String[] args) throws Exception {
		Runnable server =
				new UdpServerJava(5000, new EmployeesProtocol
						(EmployeesMethods.getEmployees("employees.data")));
		server.run();

	}

}

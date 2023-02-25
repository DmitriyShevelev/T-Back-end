package telran.employees.controller;

import telran.employees.net.EmployeesProtocol;
import telran.employees.services.EmployeesMethods;
import telran.employees.services.EmployeesMethodsMapsImpl;
import telran.net.TcpJavaServer;

public class EmployeesTcpServerAppl {

	public static void main(String[] args) throws Exception {
		TcpJavaServer server =
				new TcpJavaServer(5000, new EmployeesProtocol(EmployeesMethodsMapsImpl.getEmptyEmployees()));
		server.run();

	}

}

package telran.employees.controller;

import java.util.*;
import java.io.*;
import telran.employees.net.EmployeesProtocol;
import telran.employees.services.EmployeesMethods;
import telran.employees.services.EmployeesMethodsMapsImpl;
import telran.net.*;

public class EmployeesServerAppl {

	private static final String DEFAULT_CONFIG_PATH = "server.properties";
	private static final String PROTOCOL = "protocol";
	private static final String DEFAULT_PROTOCOL = "Tcp";
	private static final String PORT = "port";
	private static final String DEFAULT_PORT = "5000";
	private static final String EMPLOYEES_DATA_PATH = "employees-data-path";
	private static final String DEFAULT_DATA_PATH = "employees.data";
	private static final String SERVER_CLASS_NAME_PREFIX = "telran.net.";
	private static final String SERVER_CLASS_NAME_SUFFIX = "JavaServer";

	public static void main(String[] args)  {
		EmployeesMethods employees = null;
		try {
			String configPath = args.length > 0 ? args[0] : DEFAULT_CONFIG_PATH;
			Properties props = new Properties();
			FileInputStream file = new FileInputStream(configPath);
			props.load(file);
			employees = EmployeesMethods.getEmployees(DEFAULT_DATA_PATH);
			JavaServer server = getServerInstance(props, employees);
			Thread serverThread = new Thread(server);
					
			serverThread.start();
			Scanner scanner = new Scanner(System.in);
			while(true) {
				System.out.println("Type shutdown for stopping the server");
				String line = scanner.nextLine();
				if (line.equals("shutdown")) {
					server.shutdown();
					break;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Configuration file not found");
		} catch (IOException e) {
			System.out.println("Configuration can not be read ");
		} catch (Exception e) {
			System.out.println("Server can not start, reason: " + e.getMessage());
		}
		try {
			employees.save(DEFAULT_DATA_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static JavaServer getServerInstance(Properties props, EmployeesMethods employees) throws Exception {
		String protocol = props.getProperty(PROTOCOL, DEFAULT_PROTOCOL);
		int port = Integer.parseInt(props.getProperty(PORT, DEFAULT_PORT));
		String employeesDataPath = props.getProperty(EMPLOYEES_DATA_PATH, DEFAULT_DATA_PATH);
		return (JavaServer) Class.forName(getServerClassName(protocol))
				.getConstructor(int.class, ApplProtocolJava.class)
				.newInstance(port, new EmployeesProtocol(employees ));
	}

	private static String getServerClassName(String protocol) {
		
		return SERVER_CLASS_NAME_PREFIX + protocol + SERVER_CLASS_NAME_SUFFIX;
	}

}

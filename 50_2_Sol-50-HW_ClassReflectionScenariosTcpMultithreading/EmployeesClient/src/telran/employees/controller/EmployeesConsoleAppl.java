package telran.employees.controller;

import java.util.*;
import java.io.*;
import telran.employees.controller.actions.EmployeesActions;
import telran.employees.net.EmployeesProxyNetJava;
import telran.employees.services.EmployeesMethods;
import telran.employees.services.EmployeesMethodsMapsImpl;
import telran.net.NetJavaClient;
import telran.net.TcpJavaClient;
import telran.net.UdpJavaClient;
import telran.view.*;

public class EmployeesConsoleAppl {

	private static final String DEFAULT_PORT = "5000";
	private static final String DEFAULT_HOST = "localhost";
	private static final String DEFAULT_CONFIG_PATH = "client.properties";
	private static final String PORT = "port";
	private static final String PROTOCOL = "protocol";
	private static final String DEFAULT_PROTOCOL = "Tcp";
	private static final String CLASS_NAME_PREFIX = "telran.net.";
	private static final String CLASS_NAME_SUFFIX = "JavaClient";
	private static final String HOST = "host";

	public static void main(String[] args) {
		String configPath = args.length > 0 ? args[0] : DEFAULT_CONFIG_PATH;
		InputOutput io = new ConsoleInputOutput();
		try {

			EmployeesMethods employeesService = null;
			Properties props = new Properties();
			FileInputStream file = new FileInputStream(configPath);
			props.load(file);
			employeesService = getEmployeesServiceInstance(props);

			Item[] items = EmployeesActions.getEmployeesItems(employeesService,
					Arrays.asList("QA", "Development", "Audit", "Management"));
			Menu menu = new Menu("Employees Menu", items);
			menu.perform(io);
		} catch (FileNotFoundException e) {
			io.writeObjectLine("Configuration file not found");
		} catch (IOException e) {
			io.writeObjectLine("Can not read configuration file");
		} catch (Exception e) {
			io.writeObjectLine("Client can start, reason: " + e.getMessage());
		}
	}

	private static EmployeesMethods getEmployeesServiceInstance(Properties props) throws Exception {
		int port = Integer.parseInt(props.getProperty(PORT, DEFAULT_PORT));
		String protocol = props.getProperty(PROTOCOL, DEFAULT_PROTOCOL);
		String host = props.getProperty(HOST, DEFAULT_HOST);
		return new EmployeesProxyNetJava(getClientInstance(protocol, port, host));
	}

	private static NetJavaClient getClientInstance(String protocol, int port, String host) throws Exception {
		return (NetJavaClient) Class.forName(getProtocolClassName(protocol)).getConstructor(String.class, int.class)
				.newInstance(host, port);
	}

	private static String getProtocolClassName(String protocol) {

		return CLASS_NAME_PREFIX + protocol + CLASS_NAME_SUFFIX;
	}
}


import telran.net.*;
import static telran.employees.api.RequestTypesApi.*;

import java.util.List;

import telran.employees.dto.Employee;
public class ClientAppl {

private static final int N_THREADS = 1000;
private static final String HOST = "localhost";
private static final int PORT = 4000;
private static final int N_RUNS = 100;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		
		Worker[] workers = new Worker[N_THREADS];
		startWorkers(workers);
		waitWorkers(workers);
		TcpJavaClient client =  new TcpJavaClient(HOST, PORT);
		((List<Employee>)client.send(GET_ALL_EMPLOYEES, null)).stream().sorted((e1, e2) ->
		Long.compare(e1.getId(), e2.getId())).forEach(System.out::println);
		 client.close();
		

	}
	private static void waitWorkers(Worker[] workers) throws Exception {
		for(Worker worker: workers) {
			worker.join();
		}
		
	}
	private static void startWorkers(Worker[] workers) throws Exception {
		for (int i = 0; i < workers.length; i++) {
			workers[i] = new Worker(HOST, PORT, N_RUNS);
			workers[i].start();
		}
		
	}
	
	
}

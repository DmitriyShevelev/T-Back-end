package telran.numbers.net;

import static telran.numbers.net.ClientTcp.getExpression;

import telran.net.TcpJavaClient;

public class CalculatorOperationsTcpJava extends TcpJavaClient implements ClientTcp {

	public CalculatorOperationsTcpJava(String host, int port) throws Exception {
		super(host, port);
		
	}

	@Override
	public int add(int op1, int op2) {

		return processRequest(getExpression(op1, op2, op2 < 0 ? "-" : "+"));
	}

	

	private int processRequest(String expression) {
		try {
			return send("", expression);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public int subtract(int op1, int op2) {
		return processRequest(getExpression(op1, op2, op2 < 0 ? "+" : "-"));
	}

	@Override
	public int divide(int op1, int op2) {
		int res = processRequest(getExpression(op1, op2, "/"));
		
		return op2 < 0 ? -res : res;
	}

	@Override
	public int multiply(int op1, int op2) {
		int res =  processRequest(getExpression(op1, op2, "*"));
		return op2 < 0? -res : res;
	}

	@Override
	public int compute(String expression) {

		return processRequest(expression);
	}

}

package telran.numbers.net;

import telran.net.ApplProtocolJava;
import telran.net.dto.RequestJava;
import telran.net.dto.ResponseCode;
import telran.net.dto.ResponseJava;
import telran.numbers.CalculatorOperations;

public class CalculatorProtocol implements ApplProtocolJava {
	CalculatorOperations calculatorOperations;
	public CalculatorProtocol (CalculatorOperations calculatorOperations) {
		this.calculatorOperations = calculatorOperations;
	}

	@Override
	public ResponseJava getResponse(RequestJava request) {
		try {
			int res = calculatorOperations.compute((String)request.data);
			return new ResponseJava(ResponseCode.OK, res);
		} catch (Exception e) {
			return new ResponseJava(ResponseCode.WRONG_REQUEST_DATA, e);
		}
		
	}

}

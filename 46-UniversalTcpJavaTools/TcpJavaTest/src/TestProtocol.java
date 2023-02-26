import telran.net.ApplProtocolJava;
import telran.net.dto.*;

public class TestProtocol implements ApplProtocolJava {

	@Override
	public ResponseJava getResponse(RequestJava request) {
		try {
			switch(request.requestType) {
			case "reverse": return new ResponseJava(ResponseCode.OK, new StringBuilder((String)request.data).reverse().toString());
			case "length": return new ResponseJava(ResponseCode.OK,((String)request.data).length() );
			default: return new ResponseJava(ResponseCode.WRONG_REQUEST_TYPE,
					new IllegalArgumentException(request.requestType + " not exists"));
			}
		} catch (Exception e) {
			return new ResponseJava(ResponseCode.WRONG_REQUEST_DATA, e);
		}
		
	}

}

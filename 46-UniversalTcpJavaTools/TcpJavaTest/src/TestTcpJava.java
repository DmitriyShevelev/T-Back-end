import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.net.TcpJavaClient;

class TestTcpJava extends TcpJavaClient {

	protected TestTcpJava() throws Exception  {
		super("localhost", 5000);
		
	}

	

	@Test
	void reverse() throws Exception{
		assertEquals("olleh", send("reverse","hello"));
	}
	@Test
	void length() throws Exception{
		int response = send("length","hello");
		assertEquals(5, response);
	}
	@Test
	void wrongRequest() {
		try {
			send("jjjjj","hello");
			fail("there should be Exception");
			
		} catch(IllegalArgumentException e) {
			
		} catch (Exception e) {
			fail("there should be IllegalArgumentException");
		}
	}
	@AfterEach
	void disconnect() throws IOException {
		close();
	}


}

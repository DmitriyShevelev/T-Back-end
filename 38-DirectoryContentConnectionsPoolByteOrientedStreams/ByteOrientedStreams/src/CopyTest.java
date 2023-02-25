import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CopyTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() throws Exception {
		CopyAppl.copy("myFile.txt", "myFile");
		assertEquals(Files.getAttribute(Paths.get("myFile.txt"), "size"),
				Files.getAttribute(Paths.get("myFile"), "size"));
	}

}

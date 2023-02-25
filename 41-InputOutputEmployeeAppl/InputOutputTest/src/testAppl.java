import java.time.LocalDate;

import telran.employees.dto.Employee;
import telran.view.*;
public class testAppl {

	public static void main(String[] args) {
		InputOutput inputOutput = new ConsoleInputOutput();
		Employee empl = inputOutput.readObject("Enter employee <id>#<salary>#<dep>#<bod ISO>",
				"Wrong employee, please repeat according to format", str -> {
					String[] fields = str.split("#");
					return new Employee(Long.parseLong(fields[0]),
							Integer.parseInt(fields[1]), LocalDate.parse(fields[3]), fields[2]);
				});
		inputOutput.writeObjectLine(empl);

	}

}

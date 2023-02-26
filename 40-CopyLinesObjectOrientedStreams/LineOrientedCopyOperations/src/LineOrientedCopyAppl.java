import java.io.*;

public class LineOrientedCopyAppl {

    public static void main(String[] args) throws IOException {
	if (args.length < 2) {
	    System.out.println("Should be specified two params");
	}
	BufferedReader reader = getReader(args[0]);

	try (
		
		PrintWriter writer = getWriter(args[1]);
	) {
	    while (true) {
		String line = reader.readLine();

		if (line == null) {
		    break;
		}

		writer.println(line);
	    }
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
    }

    private static PrintWriter getWriter(String to) throws IOException {
	return to.equals("console") ? new PrintWriter(System.out) : new PrintWriter(to);
    }

    private static BufferedReader getReader(String from) throws IOException {
	InputStreamReader input = from.equals("console") ? new InputStreamReader(System.in)
			: new FileReader(from);
	
	return new BufferedReader(input);
    }
}
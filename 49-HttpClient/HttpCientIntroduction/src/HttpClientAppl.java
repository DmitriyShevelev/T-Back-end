import java.net.URI;

import java.net.http.*;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;



public class HttpClientAppl {

	public static void main(String[] args) throws Exception {
		HttpClient client = HttpClient.newHttpClient();
//		HttpRequest request = HttpRequest.newBuilder
//				(new URI("http://data.fixer.io/api/latest?access_key=81ebf276e1ed808b58591b5fb05c34eb"))
//				.build();
//		HttpResponse<Path> response = client.send(request, BodyHandlers.ofFile(Paths.get("rates.txt")));
//		System.out.println(response.body());
		HttpRequest request = HttpRequest.newBuilder(new URI("http://www.google.com")).build();
		HttpResponse<Stream<String>> response = client.send(request,
				BodyHandlers.ofLines());
		response.body().forEach(System.out::println);

	}

}

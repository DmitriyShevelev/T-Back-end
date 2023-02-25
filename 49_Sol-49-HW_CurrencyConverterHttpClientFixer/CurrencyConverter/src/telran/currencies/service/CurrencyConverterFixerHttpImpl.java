package telran.currencies.service;

import java.util.*;
import java.util.stream.Collectors;
import java.io.*;
import java.net.URI;
import java.net.http.*;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
public class CurrencyConverterFixerHttpImpl extends AbstractCurrencyConverter {
	private static final String API_KEY = "apiKey";
	private static final String EXP_SEC = "expSec";
	private static final String BASE_URL_DEFAULT = "http://data.fixer.io/api/latest?access_key=";
	private static final String BASE_URL_PROP = "baseUrl";
	private static final String DEFAULT_EXP_SEC = "60";
	private static final String TIMESTAMP_FIELD = "timestamp";
	private static final String RATES_FIELD = "rates";
	static private long expSec;
	private static HttpClient httpClient = HttpClient.newHttpClient();
	private static HttpRequest request;
	private long expStamp;
	protected CurrencyConverterFixerHttpImpl(Map<String, Double> rates) {
		super(rates);
		
	}
	public static CurrencyConverter getCurrencyConverter(String path) throws Exception{
		CurrencyConverterFixerHttpImpl currencyConverter = new CurrencyConverterFixerHttpImpl(null);
		Properties props = new Properties();
		try (InputStream propsStream = new FileInputStream(path)) {
			props.load(propsStream);
			String apiKey = props.getProperty(API_KEY);
			if (apiKey == null) {
				throw new Exception("Api Key is not provided");
			}
			String baseUrl = props.getProperty(BASE_URL_PROP, BASE_URL_DEFAULT);
			request = HttpRequest.newBuilder(new URI(baseUrl + apiKey)).build();
			expSec = Long.parseLong(props.getProperty(EXP_SEC, DEFAULT_EXP_SEC));
			
			
			currencyConverter.fillData();
			
			
		}
		return currencyConverter;
	}

	private void fillData()  {
		String responseBody = getResponseBody();
		fillTimeStamp(responseBody);
		fillRates(responseBody);
		
	}
	private void fillRates(String responseBody) {
		int indRatesStart = responseBody.indexOf(RATES_FIELD) + RATES_FIELD.length() + 2;
		int indRatesEnd = responseBody.indexOf("}", indRatesStart);
		String ratesStr = responseBody.substring(indRatesStart, indRatesEnd);
		ratesStr = ratesStr.replaceAll("[\"{}]+", "");
		rates = Arrays.stream(ratesStr.split(",")).map(s -> s.split(":")).collect(Collectors.toMap(a -> a[0],
				a -> Double.parseDouble(a[1])));
		
		
	}
	private void fillTimeStamp(String responseBody) {
		int indTimeStart = responseBody.indexOf(TIMESTAMP_FIELD)
				+ TIMESTAMP_FIELD.length() + 2;
		int indTimeEnd = responseBody.indexOf(',', indTimeStart);
		
		long timeStamp = Long.parseLong(responseBody, indTimeStart, indTimeEnd, 10);
		System.out.println("timeStamp: " + LocalDateTime.ofInstant(Instant.ofEpochSecond(timeStamp), ZoneId.systemDefault()));
		expStamp = timeStamp + expSec;
		System.out.println("expStamp: " + LocalDateTime.ofInstant(Instant.ofEpochSecond(expStamp), ZoneId.systemDefault()));
		
		
		
		
	}
	private String getResponseBody() {
		try {
			HttpResponse<String> response = httpClient.send(request,
					BodyHandlers.ofString());
			return response.body();
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}
	@Override
	protected void refresh() {
		if (System.currentTimeMillis() / 1000  > expStamp) {
			System.out.printf("%s  Refresh is done\n", LocalDateTime.now());
			fillData();
		}

	}

}

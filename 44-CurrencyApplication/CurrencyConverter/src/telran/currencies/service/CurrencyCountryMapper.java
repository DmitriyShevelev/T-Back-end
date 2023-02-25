package telran.currencies.service;
import java.util.*;
import java.util.stream.Collectors;
import java.io.*;
public abstract class CurrencyCountryMapper implements CurrencyConverter{
	protected Map<String, String> countryCurrency;
	protected Map<String, List<String>> currencyCountries;
	protected CurrencyCountryMapper() {
		createCountryCurrency();
		createCurrencyCountries();
	}
	private void createCurrencyCountries() {
		currencyCountries = countryCurrency.entrySet().stream()
				.collect(Collectors.groupingBy(Map.Entry::getValue,
						Collectors.mapping(Map.Entry::getKey,
								Collectors.toList())));
		
	}
	private void createCountryCurrency() {
		try(BufferedReader reader = new BufferedReader(new FileReader("codes-all_csv.txt"))){
			countryCurrency = reader.lines().map(line -> line.toUpperCase().split("\t"))
					.filter(a -> a.length > 2)
					.collect(Collectors.toMap(a -> a[0], a-> a[2],
							(k1, k2) -> k1));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

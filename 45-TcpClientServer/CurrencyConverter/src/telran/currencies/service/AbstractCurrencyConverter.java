package telran.currencies.service;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public abstract class  AbstractCurrencyConverter extends CurrencyCountryMapper {
protected Map<String, Double> rates;
protected AbstractCurrencyConverter(Map<String, Double> rates) {
	this.rates = rates;
}
	@Override
	public double convert(String currencyCodeCountryFrom, String currencyCodeCountryTo, 
			int amount) {
		refresh();
		double rateFrom = getRate(currencyCodeCountryFrom.toUpperCase());
		double rateTo = getRate(currencyCodeCountryTo.toUpperCase());
		return rateTo / rateFrom * amount;
	}protected  double getRate(String currencyCodeCountry) {
	try {
		return rates.containsKey(currencyCodeCountry) ? rates.get(currencyCodeCountry) : 
			rates.get(countryCurrency.get(currencyCodeCountry));
	} catch (Exception e) {
		throw new RuntimeException("Wrong either currency code or country name");
	}
}


	@Override
	public Map<String, List<String>> strongestCurrencies(int amount) {
		
		return weakestStrongest(amount, true);
	}
	private Map<String, List<String>> weakestStrongest(int amount, boolean isAsc) {
		
		return rates.entrySet().stream().filter(e -> currencyCountries.containsKey(e.getKey()))
				.sorted((e1, e2) -> getComparator(isAsc).compare(e1, e2))
				.limit(amount).collect(Collectors.toMap(e -> e.getKey(),
						e -> currencyCountries.get(e.getKey()), (a, b) -> a, LinkedHashMap::new));
		
	}
	private Comparator<Entry<String, Double>> getComparator(boolean isAsc) {
		Comparator<Entry<String, Double>> comp = Entry.comparingByValue();
		return isAsc ? comp : comp.reversed();
	}

	@Override
	public Map<String, List<String>> weakestCurrencies(int amount) {
		
		return weakestStrongest(amount, false);
	}
	protected abstract void refresh() ;

}

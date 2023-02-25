package telran.currencies.service;

import java.util.*;

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
		return rateFrom / rateTo * amount;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, List<String>> weakestCurrencies(int amount) {
		// TODO Auto-generated method stub
		return null;
	}
	protected abstract void refresh() ;

}

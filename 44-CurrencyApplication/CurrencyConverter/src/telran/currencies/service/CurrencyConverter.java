package telran.currencies.service;
import java.util.*;

public interface CurrencyConverter {
double convert(String currencyCodeCountryFrom, String currencyCodeCountryTo , int amount);
Map<String, List<String>> strongestCurrencies(int amount);
Map<String, List<String>> weakestCurrencies(int amount);
}

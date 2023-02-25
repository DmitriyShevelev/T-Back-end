package telran.currencies.controller.actions;

import java.util.*;

import telran.currencies.service.CurrencyConverter;
import telran.view.*;

public class CurrencyActions {
private static CurrencyConverter currencyConverter;
public static Item[] getCurrencyItems(CurrencyConverter currencyConverter) {
	CurrencyActions.currencyConverter = currencyConverter;
	return getItems();
}
private static Item[] getItems() {
	Item[] res = {
		Item.of("Convert", CurrencyActions::convert),
		Item.of("Display Strongest Currencies", CurrencyActions::strongest),
		Item.of("Display Weakest Currencies", CurrencyActions::weakest),
		Item.of("Display currency code of a given country", io -> {
			io.writeObjectLine(currencyConverter
					.getCurrencyCode(io.readString("Enter country")));
		}),
		Item.of("Display countries using a given currency", io -> {
			io.writeObjectLine(currencyConverter
					.getCountriesCurrency(io.readString("Entry currency code")));
		}),
		Item.exit()
		
	};
	return res;
}
private static void convert(InputOutput io) {
	String message = "Enter either currency code or country ";
	String currencyOrCountryFrom = io.readString(message + "from");
	String currencyOrCountryTo = io.readString(message + "to");
	int amount = io.readInt("Enter amount for converting", 1, Integer.MAX_VALUE);
	io.writeObjectLine(String.format("%.2f %s",
			currencyConverter.convert(currencyOrCountryFrom, currencyOrCountryTo, amount),
			currencyConverter.getCurrencyCode(currencyOrCountryTo)));
			
}
private static void strongest(InputOutput io) {
	
	displayCurrencyCountries(currencyConverter.strongestCurrencies(getAmountCurrencies(io)), io, "Strongest");
	
	
}
private static void displayCurrencyCountries(Map<String, List<String>> currecncyCountries, InputOutput io, String title) {
	io.writeObjectLine(title + " currencies");
	io.writeObjectLine("Currency\tCountries");
	currecncyCountries.forEach((k, v) -> io.writeObjectLine(String.format("%s\t\t%s", k, v)));
}
private static int getAmountCurrencies(InputOutput io) {
	
	return io.readInt("Enter amount of currencies", 1, Integer.MAX_VALUE);
}
private static void weakest(InputOutput io) {
	displayCurrencyCountries(currencyConverter.weakestCurrencies(getAmountCurrencies(io)), io, "Weakest");
}
}

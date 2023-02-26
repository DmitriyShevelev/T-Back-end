package telran.currencies.service;

import java.util.Map;
import java.io.*;
public class CurrencyConverterFileImpl extends AbstractCurrencyConverter {

	@SuppressWarnings("unchecked")
	public static CurrencyConverter getCurrencyConverter(String filePath) throws Exception {
		try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(filePath))) {
			Map<String, Double>rates = (Map<String, Double>) input.readObject();
			return new CurrencyConverterFileImpl(rates);
		}
		
	}
	private CurrencyConverterFileImpl(Map<String, Double> rates) {
		super(rates);
	
		
	}

	@Override
	protected void refresh() {
		

	}

}

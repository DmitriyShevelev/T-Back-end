package telran.currencies.controller;

import telran.currencies.controller.actions.CurrencyActions;
import telran.currencies.service.*;
import telran.view.*;

public class CurrencyAppl {

	public static void main(String[] args) throws Exception {
		InputOutput io = new ConsoleInputOutput();
		if (args.length == 0) {
			io.writeObjectLine("Path to the file containing either specific configuration or rates"
					+ " should be defined");
			return;
		}
		Menu menu = new Menu("Currency Application",
		CurrencyActions.getCurrencyItems(CurrencyConverterFixerHttpImpl.getCurrencyConverter(args[0])));
		menu.perform(io);
		

	}

}

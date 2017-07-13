package uk.co.engagetech.backend.service;

import org.springframework.stereotype.Component;

@Component
public class CurrencyConverterDummyImpl implements CurrencyConverter {

	@Override
	public Double convert(Double amount, String fromSymbol, String toSymbol) {

		if (!fromSymbol.equals("EUR") && !toSymbol.equals("GBP")) {
			throw new RuntimeException("Conversion  not implemented (from " + fromSymbol + ", to " + toSymbol + ").");
		}

		return amount / 1.1278;
	}

}

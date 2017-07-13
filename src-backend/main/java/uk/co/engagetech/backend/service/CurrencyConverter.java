package uk.co.engagetech.backend.service;

public interface CurrencyConverter {

	Double convert(Double amount, String fromSymbol, String toSymbol);
}

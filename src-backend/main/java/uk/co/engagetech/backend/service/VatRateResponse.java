package uk.co.engagetech.backend.service;

public class VatRateResponse {

	private double vatRate;

	public VatRateResponse(double vatRate) {
		super();
		this.vatRate = vatRate;
	}

	public double getVatRate() {
		return vatRate;
	}
}

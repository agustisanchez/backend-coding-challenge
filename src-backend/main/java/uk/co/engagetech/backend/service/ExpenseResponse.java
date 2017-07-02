package uk.co.engagetech.backend.service;

import java.util.Date;

public class ExpenseResponse extends ExpenseRequest {

	private Double vat;

	public ExpenseResponse(Date date, Double amount, Double vat, String reason) {
		super(date, amount, reason);
		this.vat = vat;
	}

	public Double getVat() {
		return vat;
	}

}

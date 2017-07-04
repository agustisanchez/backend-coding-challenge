package uk.co.engagetech.backend.service;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExpenseResponse extends ExpenseRequest {

	private Double vat;

	public ExpenseResponse(@JsonProperty("date") Date date, @JsonProperty("amount") Double amount,
			@JsonProperty("vat") Double vat, @JsonProperty("reason") String reason) {
		super(date, amount, reason);
		this.vat = vat;
	}

	public Double getVat() {
		return vat;
	}

}

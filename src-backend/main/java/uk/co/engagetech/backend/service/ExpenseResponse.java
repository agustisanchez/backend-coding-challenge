package uk.co.engagetech.backend.service;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExpenseResponse {

	// TODO Use new API
	private Date date;

	private Double amount;

	private String reason;

	private Double vat;

	public ExpenseResponse(@JsonProperty("date") Date date, @JsonProperty("amount") Double amount,
			@JsonProperty("vat") Double vat, @JsonProperty("reason") String reason) {
		this.date = date;
		this.amount = amount;
		this.reason = reason;
		this.vat = vat;
	}

	public Date getDate() {
		return date;
	}

	public Double getAmount() {
		return amount;
	}

	public String getReason() {
		return reason;
	}

	public Double getVat() {
		return vat;
	}

}

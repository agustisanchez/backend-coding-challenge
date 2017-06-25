package uk.co.engagetech.backend.domain;

import java.util.Date;

// Comment: I create immutable classes by default.
public class Expense {

	// TODO Use new API
	private Date date;

	private Double amount;

	private String reason;

	public Expense(Date date, Double amount, String reason) {
		super();
		this.date = date;
		this.amount = amount;
		this.reason = reason;
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

}

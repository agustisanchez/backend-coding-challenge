package uk.co.engagetech.backend.service;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

// Comment: I create immutable classes by default.
public class ExpenseRequest {

	// TODO Use new API
	private Date date;

	private String amount;

	private String reason;

	// TODO Define date format globally
	@JsonCreator
	public ExpenseRequest(@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy") @JsonProperty("date") Date date,
			@JsonProperty("amount") String amount, @JsonProperty("reason") String reason) {
		super();
		this.date = date;
		this.amount = amount;
		this.reason = reason;
	}

	public Date getDate() {
		return date;
	}

	public String getAmount() {
		return amount;
	}

	public String getReason() {
		return reason;
	}

}

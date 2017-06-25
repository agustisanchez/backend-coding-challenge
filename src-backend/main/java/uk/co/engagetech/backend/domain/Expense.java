package uk.co.engagetech.backend.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

// Comment: I create immutable classes by default.
public class Expense {

	// TODO Use new API
	private Date date;

	private Double amount;

	private String reason;

	// TODO Define date format globally
	@JsonCreator
	public Expense(@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy") @JsonProperty("date") Date date,
			@JsonProperty("amount") Double amount, @JsonProperty("reason") String reason) {
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

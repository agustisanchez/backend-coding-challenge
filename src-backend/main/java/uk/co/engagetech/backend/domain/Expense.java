package uk.co.engagetech.backend.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "expenses")
public class Expense {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	private Date date;

	private Double amount;

	// TODO Empty? minimum / maximum length
	@NotNull
	@Size(min = 1, max = 250)
	private String reason;

	@SuppressWarnings("unused")
	public Expense() {
	}

	public Expense(Date date, Double amount, String reason) {
		super();
		this.date = date;
		this.amount = amount;
		this.reason = reason;
	}

	public Long getId() {
		return id;
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

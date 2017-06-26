package uk.co.engagetech.backend.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import uk.co.engagetech.backend.domain.Expense;

@Component
public class ExpensesService {

	private List<Expense> expenses = new ArrayList<>();

	@PostConstruct
	public void init() {
		expenses.add(new Expense(new Date(), 145.987, "I'll explain later"));
		expenses.add(new Expense(new Date(), 45.87, "Trust me, I really need the money"));
		expenses.add(new Expense(new Date(), 500.34, "Taxi to Lutton airport"));
	}

	public Collection<Expense> list() {
		return Collections.unmodifiableCollection(expenses);
	}

	public Long add(Expense expense) {
		expenses.add(expense);
		// TODO From databse
		return (long) expenses.size();
	}

}

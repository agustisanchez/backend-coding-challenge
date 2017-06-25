package uk.co.engagetech.backend.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import uk.co.engagetech.backend.domain.Expense;

@RestController()
// TODO Move app context to common configuration
// TODO consumes, produces
@RequestMapping("/app/expenses")
@EnableAutoConfiguration
public class ExpensesController {

	private List<Expense> expenses = new ArrayList<>();

	@PostConstruct
	public void init() {
		expenses.add(new Expense(new Date(), 145.987, "I'll explain later"));
		expenses.add(new Expense(new Date(), 45.87, "Trust me, I really need the money"));
		expenses.add(new Expense(new Date(), 500.34, "Taxi to Lutton airport"));
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Expense> list() {
		return expenses;
	}

}

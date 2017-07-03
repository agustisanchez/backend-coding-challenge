package uk.co.engagetech.backend.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import uk.co.engagetech.backend.domain.Expense;

@Component
public class ExpensesService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${config.vatRate:0.2}")
	private double vatRate;

	@Autowired
	private ExpenseRepository expenseRepository;

	@PostConstruct
	public void init() {
		logger.info("Initialized with VAT rate {}.", vatRate);
	}

	public Collection<ExpenseResponse> list() {
		List<Expense> expenses = expenseRepository.findAllByOrderByDateAsc();
		return expenses.stream()
				.map(it -> new ExpenseResponse(it.getDate(), it.getAmount(), it.getAmount() * vatRate, it.getReason()))
				.collect(Collectors.toList());
	}

	@Transactional
	public Long add(ExpenseRequest expenseDTO) {
		Expense savedExpense = expenseRepository
				.save(new Expense(expenseDTO.getDate(), expenseDTO.getAmount(), expenseDTO.getReason()));
		return savedExpense.getId();
	}

}

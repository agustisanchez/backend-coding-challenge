package uk.co.engagetech.backend.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.co.engagetech.backend.domain.Expense;

@Component
public class ExpensesService {

	@Autowired
	private ExpenseRepository expenseRepository;

	@PostConstruct
	public void init() {
	}

	public Collection<ExpenseDTO> list() {
		List<Expense> expenses = expenseRepository.findAllByOrderByDateAsc();
		return expenses.stream().map(it -> new ExpenseDTO(it.getDate(), it.getAmount(), it.getReason()))
				.collect(Collectors.toList());
	}

	@Transactional
	public Long add(ExpenseDTO expenseDTO) {
		Expense savedExpense = expenseRepository
				.save(new Expense(expenseDTO.getDate(), expenseDTO.getAmount(), expenseDTO.getReason()));
		return savedExpense.getId();
	}

}

package uk.co.engagetech.backend.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.co.engagetech.backend.domain.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	List<Expense> findAllByOrderByDateAsc();
}
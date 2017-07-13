package uk.co.engagetech.backend.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import uk.co.engagetech.backend.service.ExpenseRequest;
import uk.co.engagetech.backend.service.ExpenseResponse;
import uk.co.engagetech.backend.service.ExpensesService;
import uk.co.engagetech.backend.service.VatRateResponse;

@RestController()
// TODO Move app context to common configuration
// TODO consumes, produces
@RequestMapping("/app/expenses")
@EnableAutoConfiguration
public class ExpensesController {

	@Autowired
	private ExpensesService expensesService;

	@RequestMapping(method = RequestMethod.GET, path = "/vatrate")
	public VatRateResponse vatRate() {
		return expensesService.vatRate();
	}

	@RequestMapping(method = RequestMethod.GET)
	public Collection<ExpenseResponse> list() {
		return expensesService.list();
	}

	@RequestMapping(path = "", method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody ExpenseRequest expense, UriComponentsBuilder ucb) {
		Long id = expensesService.add(expense);
		return ResponseEntity
				.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri())
				.build();
	}

}

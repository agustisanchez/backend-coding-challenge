package uk.co.engagetech.backend.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import uk.co.engagetech.backend.main.Application;
import uk.co.engagetech.backend.service.CurrencyConverter;
import uk.co.engagetech.backend.service.ExpenseRepository;
import uk.co.engagetech.backend.service.ExpenseRequest;
import uk.co.engagetech.backend.service.ExpenseResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@TestPropertySource("classpath:application-test.properties")
@WebAppConfiguration
public class ExpensesControllerTest {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Value("${config.vatRate:0.2}")
	private double vatRate;

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private CurrencyConverter currencyConverter;

	@Autowired
	private ExpenseRepository expenseRepo;

	private static Double amount = 10.0;

	private static ExpenseRequest expenseRequest = new ExpenseRequest(new Date(), amount.toString(), "Some reason");

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
		expenseRepo.deleteAll();
	}

	@Test
	public void addExpenseSuccessfully() throws Exception {
		String requestJson = objectMapper.writeValueAsString(expenseRequest);

		String controllerPath = controllerPath();

		String locationHeader = this.mockMvc.perform(post(controllerPath).contentType(contentType).content(requestJson))
				.andExpect(status().isCreated()).andReturn().getResponse().getHeader("location");
		String id = extractId(locationHeader);
		Assert.assertNotNull(id);

		String responseBody = this.mockMvc.perform(get(controllerPath).contentType(contentType))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		ExpenseResponse[] expensesArray = objectMapper.readValue(responseBody, ExpenseResponse[].class);

		Assert.assertEquals(1, expensesArray.length);
		ExpenseResponse expenseResponse = expensesArray[0];
		Assert.assertEquals(expenseRequest.getAmount(), String.valueOf(expenseResponse.getAmount()));
		Assert.assertEquals(amount * vatRate, expenseResponse.getVat(), 0.01);
	}

	@Test
	public void addExpenseWithInvalidInput() throws Exception {
		String requestJson = "{\"date\":\"date\"}";

		String controllerPath = controllerPath();

		this.mockMvc.perform(post(controllerPath).contentType(contentType).content(requestJson))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void addExpenseInEUR() throws Exception {
		ExpenseRequest expenseRequest = new ExpenseRequest(new Date(), amount.toString() + "EUR", "Some reason");
		String requestJson = objectMapper.writeValueAsString(expenseRequest);

		String controllerPath = controllerPath();

		this.mockMvc.perform(post(controllerPath).contentType(contentType).content(requestJson))
				.andExpect(status().isCreated());

		String responseBody = this.mockMvc.perform(get(controllerPath).contentType(contentType))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		ExpenseResponse[] expensesArray = objectMapper.readValue(responseBody, ExpenseResponse[].class);

		Assert.assertEquals(1, expensesArray.length);
		ExpenseResponse expenseResponse = expensesArray[0];
		Assert.assertEquals(currencyConverter.convert(amount, "EUR", "GBP"), expenseResponse.getAmount(), 0.01);

	}

	private String controllerPath() {
		String controllerPath = MvcUriComponentsBuilder.fromController(ExpensesController.class).build().getPath();
		return controllerPath;
	}

	private String extractId(String location) {
		int idx = location.lastIndexOf('/');
		String id = location.substring(idx + 1);
		return id;
	}

}

package com.juancalderondev.loanpayment.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juancalderondev.loanpayment.Models.Loan;
import com.juancalderondev.loanpayment.Services.LoanService;

@RestController
public class LoanController {
	@Autowired
	private LoanService loanService;
	
	@GetMapping("/api/loanpayment/create")
	public String createLoanPaymentTable(@RequestParam("periodRate") String periodRate,
										 @RequestParam("numberOfPayments") String numberOfPayments,
										 @RequestParam("amount") String amount) {
		final Double convertedPeriodRate = Double.parseDouble(periodRate);
		final Integer convertedNumOfPayments = Integer.parseInt(numberOfPayments);
		final Double convertedAmount = Double.parseDouble(amount);
		
		Loan createLoan = new Loan(convertedPeriodRate,convertedNumOfPayments,convertedAmount);
		createLoan.setPaymentTable(loanService.createPaymentTable(createLoan));
		
		
		
		try {
			// Create an instance of ObjectMapper (Jackson)
			ObjectMapper objectMapper = new ObjectMapper();
	
			// Convert the list to JSON
			String jsonString = objectMapper.writeValueAsString(createLoan.getPaymentTable());
			return jsonString;
		}catch(Exception e) {
			 e.printStackTrace();
		}
		// Set the JSON string as the response body
		//response.setContentType("application/json");
		//response.getWriter().write(jsonString);
		return null;
	}
	

}

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
	
	/**
	 * creates the table with the payment in every of the numberOfPayments
	 * @param periodRate interest rate for each payment period
	 * @param numberOfPayments total number of payments
	 * @param amount total amount of the loan
	 * @return
	 */
	@GetMapping("/api/loanpayment/create")
	public String createLoanPaymentTable(@RequestParam("periodRate") String periodRate,
										 @RequestParam("numberOfPayments") String numberOfPayments,
										 @RequestParam("amount") String amount) {
		//as the values are received as string, this transform to numeric
		final Double convertedPeriodRate = Double.parseDouble(periodRate);
		final Integer convertedNumOfPayments = Integer.parseInt(numberOfPayments);
		final Double convertedAmount = Double.parseDouble(amount);
		
		//new instance of the loan specs and creation of payment table
		Loan createLoan = new Loan(convertedPeriodRate,convertedNumOfPayments,convertedAmount);
		createLoan.setPaymentTable(loanService.createPaymentTable(createLoan));
		
		try {
			// Create an instance of ObjectMapper use to transform list to json object
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonString = objectMapper.writeValueAsString(createLoan.getPaymentTable());
	
			return jsonString;
		}catch(Exception e) {
			 e.printStackTrace();
		}
		return null;
	}
	

}

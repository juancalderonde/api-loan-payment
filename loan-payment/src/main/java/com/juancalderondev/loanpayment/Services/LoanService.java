package com.juancalderondev.loanpayment.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.juancalderondev.loanpayment.Models.Loan;



@Service
public class LoanService {
	
	/**
	 * this method creates a nested list containing the id of the payment, 
	 * the interest to be paid, the principal, the total payment and the remaining outstanding
	 * @param loanSpecs is the object containing the specs for the loan 
	 * @return a nested list with the loan payment table
	 */
	public List<Map<String, Object>> createPaymentTable(Loan loanSpecs){
		
		//variables to be used in the proccess
		List<Map<String,Object>> paymentTable = new ArrayList<>();
		Double mainPayment = loanSpecs.getAmount() / annuityCalculation(loanSpecs);
		int numberOfPayments = loanSpecs.getNumberOfPayments();
		Double interestToPay = 0.0;
		Double principalPay = 0.0;
		Double lastOustanding = 0.0;
		
		//fills the list object with the result of the payment table
		for (int i=0; i<=numberOfPayments;i++) {
			Map<String,Object> rowData = new HashMap<>();
			if(i == 0) {
				//starting point does not have any calculation
				rowData.put("period", 0);
				rowData.put("interest", 0);
				rowData.put("principal", 0);
				rowData.put("totalPayment", 0);
				rowData.put("outstanding", loanSpecs.getAmount());
				
			}else {
				lastOustanding = Double.parseDouble(paymentTable.get(i-1).get("outstanding").toString());
				interestToPay =  lastOustanding * loanSpecs.getPeriodRate();
				principalPay = mainPayment - interestToPay;
				rowData.put("period",i);
				rowData.put("interest", interestToPay);
				rowData.put("principal", principalPay);
				rowData.put("totalPayment", mainPayment);
				rowData.put("outstanding", lastOustanding - principalPay);
				
			}
			paymentTable.add(rowData);
		}
		
		return paymentTable;
	}
	
	/**
	 * calculates the annuity factor which can be calculated as [1-(1+i)^-n]/i
	 * this will allow to calculate the payment of the loan
	 * @param loanSpecs contains the loan specification for calculating the annuity
	 * @return annuity factor as described above.
	 */
	private Double annuityCalculation(Loan loanSpecs) {
		return (1-(Math.pow(1 + loanSpecs.getPeriodRate(), -loanSpecs.getNumberOfPayments() *1.0)))/loanSpecs.getPeriodRate();
	}
	

}

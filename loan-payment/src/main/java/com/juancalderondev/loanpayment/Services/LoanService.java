package com.juancalderondev.loanpayment.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.juancalderondev.loanpayment.Models.Loan;



@Service
public class LoanService {
	
	public List<Map<String, Object>> createPaymentTable(Loan loanSpecs){
		
		List<Map<String,Object>> paymentTable = new ArrayList<>();
		Double mainPayment = loanSpecs.getAmount() / annuityCalculation(loanSpecs);
		int numberOfPayments = loanSpecs.getNumberOfPayments();
		Double interestToPay = 0.0;
		Double principalPay = 0.0;
		Double lastOustanding = 0.0;
		
		
		for (int i=0; i<=numberOfPayments;i++) {
			Map<String,Object> rowData = new HashMap<>();
			if(i == 0) {
				rowData.put("periodo", 0);
				rowData.put("interest", 0);
				rowData.put("principal", 0);
				rowData.put("totalPayment", 0);
				rowData.put("outstanding", loanSpecs.getAmount());
				
			}else {
				lastOustanding = Double.parseDouble(paymentTable.get(i-1).get("outstanding").toString());
				interestToPay =  lastOustanding * loanSpecs.getPeriodRate();
				principalPay = mainPayment - interestToPay;
				rowData.put("periodo",i);
				rowData.put("interest", interestToPay);
				rowData.put("principal", principalPay);
				rowData.put("totalPayment", mainPayment);
				rowData.put("outstanding", lastOustanding - principalPay);
				
			}
			paymentTable.add(rowData);
		}
		
		return paymentTable;
	}
	
	
	private Double annuityCalculation(Loan loanSpecs) {
		return (1-(Math.pow(1 + loanSpecs.getPeriodRate(), -loanSpecs.getNumberOfPayments() *1.0)))/loanSpecs.getPeriodRate();
	}
	

}

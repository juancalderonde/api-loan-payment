package com.juancalderondev.loanpayment.Models;

import java.util.List;
import java.util.Map;

public class Loan {
	
	private List<Map<String, Object>> paymentTable;
	
	private Double periodRate;
	
	private Integer numberOfPayments;
	
	private Double amount;

	public Loan(Double periodRate,  Integer numberOfPayments, Double paymentAmount) {
		this.periodRate = periodRate;
		this.numberOfPayments = numberOfPayments;
		this.amount = paymentAmount;
	}

	public List<Map<String, Object>> getPaymentTable() {
		return paymentTable;
	}

	public void setPaymentTable(List<Map<String, Object>> paymentTable) {
		this.paymentTable = paymentTable;
	}

	public Double getPeriodRate() {
		return periodRate;
	}

	public void setPeriodRate(Double periodRate) {
		this.periodRate = periodRate;
	}

	public Integer getNumberOfPayments() {
		return numberOfPayments;
	}

	public void setNumberOfPayments(Integer numberOfPayments) {
		this.numberOfPayments = numberOfPayments;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	
	




	
	
}

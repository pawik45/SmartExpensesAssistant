package com.sea.model;

import java.util.ArrayList;

public class Category {
	
	private String name;
	private ArrayList<Expense> ExpensesList;
	private double limit;
	private double balance;
	
	public Category(String name,double limit) {
	
		this.limit=limit;
		this.name=name;
		ExpensesList = new ArrayList<Expense>();
	}
	
	public ArrayList<Expense> getExpensesList() {
		return this.ExpensesList;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getLimit() {
		return this.limit;
	}
	
	public double getBalance() {
		
		return this.balance;
	}
	
	public void AddExpense(String name,String date, double value) {
		
		ExpensesList.add(new Expense(name,date,value));

	}
	

	
}

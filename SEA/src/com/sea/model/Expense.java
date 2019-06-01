package com.sea.model;

public class Expense {
	
	private String name;
	private String date;
	private double value;
	
	public Expense(String name,String date,double value) {
		
		this.name=name;
		this.date=date;
		this.value=value;
		
	}
	
	
	public String getName() {
		return this.name;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public double getValue() {
		return this.value;
	}
	
	
	public void changeName(String name) {
		this.name=name;
	}
	
	public void changeDate(String date) {
		this.date=date;
	}
	
	public void changeValue(double value) {
		this.value=value;
	}
	
}

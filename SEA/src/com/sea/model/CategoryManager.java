package com.sea.model;

import java.util.ArrayList;

public class CategoryManager {
	
	private double generalBalance;
	private ArrayList<Category> categoryList;
	
	public CategoryManager() {
		
		categoryList = new ArrayList<Category>();
	}

	public double getGeneralBalance() {
		
		updateGeneralBalance();
		return this.generalBalance;
	}
	
	public ArrayList<Category> getCategoryList() {
		
		return categoryList;
	}
	
	public void updateGeneralBalance() {
		
		double newGeneralBalance = 0;
		
		for (int i = 0; i < categoryList.size(); i++) { 
			  
	           	newGeneralBalance = newGeneralBalance + categoryList.get(i).getBalance();
	    } 
		
		this.generalBalance = newGeneralBalance;
	}
	
	public void addCategory(String name,double limit) {
		
		categoryList.add(new Category(name,limit));
		updateGeneralBalance();
	}
}

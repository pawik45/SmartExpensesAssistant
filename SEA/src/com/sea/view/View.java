package com.sea.view;

import java.util.ArrayList;

import com.sea.model.Category;
import com.sea.model.CategoryManager;

public class View {

	public CategoryManager manager;
	
	public View(CategoryManager manager) {
		this.manager = manager;
	}
	
	public void displayCategoriesMenu(){
		System.out.println("\nSelect what you want doing: ");
		System.out.println("1 - Add new Category to your expenses.");
		System.out.println("2 - Display all categories.");
		System.out.println("3 - Change limit of category.");
		System.out.println("4 - It's all categories, move on.");
		System.out.println("5 - Quit.");
	}
	
	public void chosing(int i) {
		System.out.println("\nYou choose "+ i +".\n");
	}
	
	public void displayCategorySummary() {
		
		for (int i = 0; i < this.manager.getCategoryList().size(); i++) { 
			
			ArrayList<Category> list = this.manager.getCategoryList();
			
            System.out.println(i+". "+list.get(i).getName() + " balance: "+ list.get(i).getBalance() + " limit: " + list.get(i).getLimit());
			
		} 

	}
	
	public void createCategoryName() {
		System.out.println("Please enter name of category.");
	}
	
	public void createCategoryLimit() {
		System.out.println("Please enter limit of category.");
	}
	
	public void createNewCategoryLimit() {
		System.out.println("Please enter new limit of category.");
	}
	
	public void createCategoryAgain() {
		System.out.println("Please, try again");
	}
	
	public void selectCategoryNb() {
		System.out.println("\nPlease select number of category.");
	}
}

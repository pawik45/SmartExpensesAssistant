package com.sea.view;

import java.util.ArrayList;

import com.sea.model.Category;
import com.sea.model.CategoryManager;
import com.sea.model.Expense;

public class View {

	public CategoryManager manager;
	
	public View(CategoryManager manager) {
		this.manager = manager;
	}
	
	public  void displayWelcome(){
		System.out.println("Welcome to Smart Expenses Assistant!\n");
	}
	
	public void displaySelectTimeFrame(){
		System.out.println("If you want "
				+ "to control your expenses, choose the weekly control or "
				+ "monthly control option.\n"
				+ "1 - weekly \n"
				+ "2 - monthly \n"
				);
		
		
	}
	
	public void displayGeneralMenu(){
		System.out.println("\nSelect what you want to do: ");
		System.out.println("1 - Add new expense.");
		System.out.println("2 - Edit expense.");
		System.out.println("3 - Category menu.");
		System.out.println("4 - Display historical expenses.");
		System.out.println("5 - Check expenses balance.");
		System.out.println("6 - Quit.");
	}
	
	public void displayCategoriesMenu(){
		System.out.println("\nSelect what you want to do: ");
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
	public void createExpenseName() {
		System.out.println("Please enter name of expense.");
	}
	
	public void chooseExpenseCategory() {
		System.out.println("Please enter number of category of your expense.");
	}
	public void createExpenseDate() {
		System.out.println("Please enter date of your expense in format dd-mm-yyyy.");
	}
	
	public void createExpensePrice() {
		System.out.println("Please enter price of your expense.");
	}
	
	
	public void createExpenseAgain() {
		System.out.println("Please, try again");
	}
	
	public void selectCategoryNb() {
		System.out.println("\nPlease select number of category.");
	}
	
	public void warningLimit() {
		System.out.println("Warning: you past exceeded the limit.");
	}
	public void warningNearLimit(int catNum) {
		double diff = manager.getCategoryList().get(catNum).getLimit()-manager.getCategoryList().get(catNum).getBalance();
		System.out.println("Warning: you are near the limit. You have "+diff+" left");
	}
	
	public void printAllExpenses() {
		for (int i = 0; i < this.manager.getCategoryList().size(); i++) { 
			
			for(int j =0; j<this.manager.getCategoryList().get(i).getExpensesList().size();j++) {
				System.out.println(this.manager.getCategoryList().get(i).getExpensesList().get(j).getDate()+ " " +
				this.manager.getCategoryList().get(i).getExpensesList().get(j).getName()+ " "+
				this.manager.getCategoryList().get(i).getExpensesList().get(j).getValue());
			}
		}
			
	} 
	
	public void displayGeneralBalance() {
		
		System.out.println("the general balance of all expenses: " + Double.toString(manager.getGeneralBalance()));
	}
	
	public void displayExpensesSummary(int id) {
		
		ArrayList<Expense> categoryList = manager.getCategoryList().get(id).getExpensesList();
		
		for (int i = 0; i < categoryList.size(); i++) {
			
			System.out.println(i+". "+categoryList.get(i).getDate()+" "+categoryList.get(i).getName()+" value: "+categoryList.get(i).getValue());
		}
		
	}
	
	public void editExpense() {
		System.out.println("Please, write a number of expense, which you want editing. ");
	}
	
	public void chooseWeekly() {
		System.out.println("You choose weekly.");
	}
	
	public void chooseMonthly() {
		System.out.println("You choose monthly.");
	}
	
}

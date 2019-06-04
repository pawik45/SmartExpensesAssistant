package com.sea.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.sea.model.Category;
import com.sea.model.CategoryManager;
import com.sea.model.Expense;
import com.sea.view.View;

public class Controller {
	
	public View view;
	public CategoryManager manager;
	public Expense expense;
	public boolean quit = true;

	public Controller() throws IOException {

		boolean timeToQuit = false;

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		do {
			timeToQuit = quit;
		} while (!timeToQuit);

		manager = new CategoryManager();
		view = new View(manager);

		manager.addCategory("jedzenie", 400);
		manager.addCategory("ubrania", 300);
		manager.addCategory("uczelnia", 100);

		Category jedzenie = manager.getCategoryList().get(0);
		jedzenie.AddExpense("spo¿ywcze I", "12-06-19", 40);
		jedzenie.AddExpense("spo¿ywcze II", "13-06-19", 20);
		jedzenie.AddExpense("spo¿ywcze III", "14-06-19", 18.45);

		Category ubrania = manager.getCategoryList().get(1);
		ubrania.AddExpense("koszulka", "11-06-19", 50.50);
		ubrania.AddExpense("spodnie", "12-06-19", 120);
		ubrania.AddExpense("buty", "12-06-19", 185.99);

		//////////
		/*
		 * view.displayCategorySummary(); view.displayGeneralBalance();
		 * view.displayExpensesSummary(0); view.displayCategoryBalance(0);
		 */
		welcomeMenu(in);

	}
	
	public boolean categoryMenu(BufferedReader in) throws IOException {

		String action;
		// view.displayGeneralBalance();
		view.displayCategoriesMenu();

		action = in.readLine();
		if ((action.length() == 0) || action.toUpperCase().charAt(0) == '5') {

			return false;
		}

		switch (action.toUpperCase().charAt(0)) {

		case '1':
			view.chosing(1);
			CreateCategory(in);
			break;

		case '2':
			view.chosing(2);
			view.displayCategorySummary();
			categoryMenu(in);
			break;
			
		case '3':
			view.chosing(3);
			changeLimitCategory(in);
			break;

		case '4':
			view.chosing(4);
			generalMenu(in);
			break;

		}
		return false;

	}
	
	public void CreateCategory(BufferedReader in) throws IOException {

		String name;
		double limit = 0;
		view.createCategoryName();

		do {
			name = in.readLine().trim();
			if (name.length() < 1) {
				view.createCategoryAgain();
			}
		} while (name.length() < 1);

		view.createCategoryLimit();
		do {
			try {
				String stringLimit = in.readLine().trim();
				if (!stringLimit.equals("")) {
					limit = Double.parseDouble(stringLimit);
				}

				if (limit < 0) {
					view.createCategoryAgain();
					limit = 0;
				}
			} catch (NumberFormatException e) {
				view.createCategoryAgain();
			}
		} while (limit == 0);

		manager.addCategory(name, limit);
		categoryMenu(in);
	}
	
	public void welcomeMenu(BufferedReader in) throws IOException {
		view.displayWelcome();
		view.displaySelectTimeFrame();

		
		String action;
		boolean flag=true;
		do {
		action = in.readLine();
		
		switch (action.toUpperCase().charAt(0)) {

		case '1':
			System.out.println("You choose weekly.");
			flag=false;
			break;

		case '2':
			System.out.println("You choose monthly.");
			flag=false;
			break;
		case '3':
			break;

		}
		}while(flag);
		
		
		categoryMenu(in);
	}

	public void generalMenu(BufferedReader in) throws IOException {

		String action;
		view.displayGeneralMenu();

		action = in.readLine();
		if ((action.length() == 0) || action.toUpperCase().charAt(0) == '6') {

			
		}

		switch (action.toUpperCase().charAt(0)) {

		case '1':
			view.chosing(1);
			CreateExpense(in);
			break;

		case '2':
			view.chosing(2);
			EditExpense(in);
			break;

		case '3':
			view.chosing(3);
			categoryMenu(in);
			break;

		case '4':
			view.chosing(4);
			view.printAllExpenses();
			generalMenu(in);
			break;

		case '5':
			view.chosing(5);
			view.displayGeneralBalance();
			generalMenu(in);
			break;

		}
	

	}

	public void CreateExpense(BufferedReader in) throws IOException {

		String name;
		String date;
		double price = 0;
		int categoryNum=1000;

		view.createExpenseName();
		do {
			name = in.readLine().trim();
			if (name.length() < 1) {
				view.createExpenseAgain();
			}
		} while (name.length() < 1);

		view.createExpensePrice();
		do {
			try {
				String stringPrice = in.readLine().trim();
				if (!stringPrice.equals("")) {
					price = Double.parseDouble(stringPrice);
				}

				if (price < 0) {
					view.createExpenseAgain();
					price = 0;
				}
			} catch (NumberFormatException e) {
				view.createExpenseAgain();
			}
		} while (price == 0);

		view.createExpenseDate();

		do {
			date = in.readLine().trim();
			if (date.length() < 10) {
				view.createExpenseAgain();
			}
		} while (date.length() < 10);

		view.chooseExpenseCategory();

		view.displayCategorySummary();
		
		
		do {
			try {
				String newNum = in.readLine().trim();
				if (!newNum.equals("")) {
					categoryNum = Integer.parseInt(newNum);
				}
				if (categoryNum < 0) {
					view.createCategoryAgain();
					categoryNum =1000;
				}
				if (categoryNum > manager.getCategoryList().size()) {
					view.createCategoryAgain();
					categoryNum = 1000;
				}
			} catch (NumberFormatException e) {
				view.createCategoryAgain();
			}
		} while (categoryNum == 1000);
		
		manager.getCategoryList().get(categoryNum).AddExpense(name, date, price);
		
		if(manager.getCategoryList().get(categoryNum).getLimit()-manager.getCategoryList().get(categoryNum).getBalance()<=50) {
			view.warningNearLimit();
		}
		if(manager.getCategoryList().get(categoryNum).getLimit()<manager.getCategoryList().get(categoryNum).getBalance()) {
			view.warningLimit();
		}
		
		generalMenu(in);
	}
	
	public void changeLimitCategory(BufferedReader in) throws IOException {
		view.displayCategorySummary();
		
		double limit=0;
		int categoryNum=1000;
		view.selectCategoryNb();
		
		do {
			try {
				String newNum = in.readLine().trim();
				if (!newNum.equals("")) {
					categoryNum = Integer.parseInt(newNum);
				}
				if (categoryNum < 0) {
					view.createCategoryAgain();
					categoryNum =1000;
				}
				if (categoryNum > manager.getCategoryList().size()) {
					view.createCategoryAgain();
					categoryNum = 1000;
				}
			} catch (NumberFormatException e) {
				view.createCategoryAgain();
			}
		} while (categoryNum == 1000);
		

		view.createNewCategoryLimit();
		do {
			try {
				String stringLimit = in.readLine().trim();
				if (!stringLimit.equals("")) {
					limit = Double.parseDouble(stringLimit);
				}

				if (limit < 0) {
					view.createCategoryAgain();
					limit = 0;
				}
			} catch (NumberFormatException e) {
				view.createCategoryAgain();
			}
		} while (limit == 0);
		
		manager.getCategoryList().get(categoryNum).changeLimit(limit);
		categoryMenu(in);
	}
	
	public void EditExpense(BufferedReader in) throws IOException {
		view.displayCategorySummary();
		view.chooseExpenseCategory();
		int categoryNum=1000;
		String name;
		double price=0;
		String date;
		
		do {
			try {
				String newNum = in.readLine().trim();
				if (!newNum.equals("")) {
					categoryNum = Integer.parseInt(newNum);
				}
				if (categoryNum < 0) {
					view.createCategoryAgain();
					categoryNum =1000;
				}
				if (categoryNum > manager.getCategoryList().size()) {
					view.createCategoryAgain();
					categoryNum = 1000;
				}
			} catch (NumberFormatException e) {
				view.createCategoryAgain();
			}
		} while (categoryNum == 1000);
		
		view.displayExpensesSummary(categoryNum);
		view.editExpense();
		
		int expenseNum=1000;
		
		do {
			try {
				String newNum = in.readLine().trim();
				if (!newNum.equals("")) {
					expenseNum = Integer.parseInt(newNum);
				}
				if (expenseNum < 0) {
					view.createCategoryAgain();
					expenseNum =1000;
				}
				if (expenseNum > manager.getCategoryList().get(categoryNum).getExpensesList().size()) {
					view.createCategoryAgain();
					expenseNum = 1000;
				}
			} catch (NumberFormatException e) {
				view.createCategoryAgain();
			}
		} while (categoryNum == 1000);
		
		view.createExpenseName();
		
		do {
			name = in.readLine().trim();
			if (name.length() < 1) {
				view.createExpenseAgain();
			}
		} while (name.length() < 1);
		

		view.createExpenseDate();

		do {
			date = in.readLine().trim();
			if (date.length() < 10) {
				view.createExpenseAgain();
			}
		} while (date.length() < 10);
		
		view.createExpensePrice();
		do {
			try {
				String stringPrice = in.readLine().trim();
				if (!stringPrice.equals("")) {
					price = Double.parseDouble(stringPrice);
				}

				if (price < 0) {
					view.createExpenseAgain();
					price = 0;
				}
			} catch (NumberFormatException e) {
				view.createExpenseAgain();
			}
		} while (price == 0);
		
		manager.getCategoryList().get(categoryNum).getExpensesList().get(expenseNum).changeName(name);
		manager.getCategoryList().get(categoryNum).getExpensesList().get(expenseNum).changeDate(date);
		manager.getCategoryList().get(categoryNum).getExpensesList().get(expenseNum).changeValue(price);
		generalMenu(in);
	}

}

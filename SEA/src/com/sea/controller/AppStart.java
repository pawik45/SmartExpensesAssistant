package com.sea.controller;

import java.io.IOException;

import com.sea.model.CategoryManager;
import com.sea.view.View;

public class AppStart {

	public static void main(String[] args) throws IOException {
		
		
		CategoryManager manager = new CategoryManager();
		View view = new View(manager);
		
		Controller controler = new Controller(manager,view);

	}

}

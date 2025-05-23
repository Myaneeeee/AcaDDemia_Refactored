package main;

import app.AppManager;

public class Main {
	public Main() {
		AppManager appManager = new AppManager();
		appManager.runMenu();
	}
	
	public static void main(String[] args) {
		new Main();
	}
}

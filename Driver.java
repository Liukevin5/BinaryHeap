package Kevin_Ruoxi_Liu;

import java.util.Scanner;

public class Driver {
	UserInterface ui = new UserInterface();

	public void run() {

		int userChoice;
		LinkedNodesHeap<Customer> priorityQueue = new LinkedNodesHeap<Customer>();
		do {
			userChoice = ui.optionsMenu();
			switch (userChoice) {
			case 1:
				priorityQueue.add(newCustomer());
				break;
			case 2:
				System.out.println(priorityQueue.remove().getName());
				break;
			case 3:
				break;
			default:
				System.out.println("invalid choice");
				break;
			}
		} while (userChoice != 3);
	}

	private Customer newCustomer() {

		Customer cust = new Customer(ui.promptForPriority(), ui.promptForName());
		return cust;
	}
}

package Kevin_Ruoxi_Liu;

import java.util.LinkedList;
import java.util.Scanner;

public class UserInterface {
	Scanner sc = new Scanner(System.in);

	public int optionsMenu() {
		int userChoice;
		do {
			System.out.println("1: add new party   2: call next seat   3:exit");
			userChoice = sc.nextInt();
			sc.nextLine();
			if(userChoice != 1 && userChoice !=2 && userChoice !=3){
				System.out.println("invalid choice");
			}
		} while (userChoice != 1 && userChoice != 2 && userChoice != 3);
		
		return userChoice;
	}
	
	public String promptForName(){
		System.out.println("Please enter the name of the Party");
		String name = sc.nextLine();
		return name;
	}
	
	public int promptForPriority(){
		int userChoice;
		LinkedNodesHeap<Integer> priorityHeap = new LinkedNodesHeap<Integer>();
		do{
		System.out.println("Please enter all that apply to the Party");
		System.out.println("1: Vip   2: Advanced Calls   3: Seniors   4:Veterans  "
				+ " 5: 4 or more People");
		System.out.println("6: Families with Children   7: Other   8:Done");
		userChoice = sc.nextInt();
		sc.nextLine();
	
		if(userChoice < 1 || userChoice > 8){
			System.out.println("invalid input");
        }
		
		if(userChoice > 0 && userChoice < 8)
		priorityHeap.add(userChoice);
		
		} while(userChoice != 8);
		return priorityHeap.remove();
	}
	
}

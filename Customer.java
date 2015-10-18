package Kevin_Ruoxi_Liu;

public class Customer implements Comparable <Customer>{
	private int priority;
	private String name;
	public Customer(int x, String s){
		priority = x;
		name = s;
	}

	@Override
	public int compareTo(Customer o) {
		// TODO Auto-generated method stub
		if(priority == o.priority){
		return 0;
		} else if(priority > o.priority){
			return -1;
		} else {
			return 1;
		}
	}
	
	public int getPriority(){
		return priority;
	}
	
	public String getName(){
		return name;
	}

}


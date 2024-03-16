package demo;

import java.util.List;

public class Orders {
	
	private List<OrderDetails> orders;
	// list of order detaisl class which has the 2 variables namely country and orderderId
	public List<OrderDetails> getOrders() 
	{
		return orders;
	}

	public void setOrders(List<OrderDetails> orders)
	{
		this.orders = orders;
	} 
}

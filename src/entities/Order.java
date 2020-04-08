package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.enums.OrderStatus;

public class Order {

	private static SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat sdfDateAndHour = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	private Date moment;
	private OrderStatus status;

	private Client client;
	private List<OrderItem> orderItem = new ArrayList<>();

	public Order() {
	}

	public Order(Date moment, OrderStatus status, Client client) {
		this.moment = moment;
		this.status = status;
		this.client = client;
	}

	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void addItem(OrderItem item) {
		this.orderItem.add(item);
	}

	public void removeItem(OrderItem item) {
		this.orderItem.remove(item);
	}

	public Double total() {
		double sum = 0;
		for (OrderItem o : orderItem) {
			sum += o.subTotal();
		}
		return sum;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Order moment: " + sdfDateAndHour.format(moment) + "\n");
		sb.append("Order status: " + status + "\n");
		sb.append("Client: " + client.getName());
		sb.append(" (" + sdfDate.format(client.getBirthDate()) + ")");
		sb.append(" - " + client.getEmail() + "\n");
		sb.append("Order items: \n");
		for (OrderItem o : orderItem) {
			sb.append(o.getProduct().getName() + ", $");
			sb.append(String.format("%.2f", o.getProduct().getPrice()) + ", ");
			sb.append("Quantity: " + o.getQuantity() + ", ");
			sb.append("Subtotal: $" + String.format("%.2f", o.subTotal()) + "\n");
		}
		sb.append("Total price: $" + String.format("%.2f", total()));
		return sb.toString();
	}

}

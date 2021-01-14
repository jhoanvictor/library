package com.techlead.entities;

import com.techlead.enums.OrderStatus;

public class Order {

	private Long id;
	private Long bookCode;
	private Integer orderStatus;

	public Order() {
	}

	public Order(Long id, Long bookCode) {
		this.id = id;
		this.bookCode = bookCode;
		setOrderStatus(OrderStatus.PENDING);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBookCode() {
		return bookCode;
	}

	public void setBookCode(Long bookCode) {
		this.bookCode = bookCode;
	}

	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus OrderStatus) {
		if (OrderStatus != null) {
			this.orderStatus = OrderStatus.getCode();
		}
	}
}

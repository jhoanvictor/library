package com.techlead.enums;

public enum OrderStatus {
	
	APPROVED(1), REFUSED(2), PENDING(3);
	
	private Integer code;
	
	private OrderStatus(Integer code) {
		this.code = code;
	}
	
	public Integer getCode() {
		return this.code;
	}
	
	public static OrderStatus valueOf(Integer code) {
		for(OrderStatus status : OrderStatus.values()) {
			if(status.getCode() == code) {
				return status;
			}
		}
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}
	
}

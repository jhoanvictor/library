package com.techlead.enums;

public enum OrderStatus {
	
	PENDING(1), APPROVED(2), REFUSED(3);
	
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

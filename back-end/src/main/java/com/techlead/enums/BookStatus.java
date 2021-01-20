package com.techlead.enums;

public enum BookStatus {

	AVAILABLE(1), UNAVAILABLE(2);

	private Integer code;
	
	private BookStatus(Integer code) {
		this.code = code;
	}
	
	public Integer getCode() {
		return this.code;
	}
	
	public static BookStatus valueOf(Integer code) {
		for(BookStatus status : BookStatus.values()) {
			if(status.getCode() == code) {
				return status;
			}
		}
		throw new IllegalArgumentException("Invalid BookStatus code");
	}
	
}

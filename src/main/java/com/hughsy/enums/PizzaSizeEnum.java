package com.hughsy.enums;

public enum PizzaSizeEnum {

	SMALL("small", 5.0), MEDIUM("medium", 7.0), LARGE("large", 9.0), X_LARGE("x large", 11.0);

	private String description;
	private Double price;

	private PizzaSizeEnum(String description, Double price) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}
}

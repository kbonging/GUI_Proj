package com;

public class MenuInfo {
	
	// 메뉴명
	private String name; 
	// 가격
	private int price;
	//수량
	private int quantity;
	
	public MenuInfo(String name, int price) {
		this.name = name;
		this.price = price;
		this.quantity = 1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}

package com;

public class MenuInfo {
	
	// 메뉴명
	private String name; 
	// 가격
	private int price;
	//수량
	private int quantity;
	// 이미지
	private String image;
	
	public MenuInfo(String name, int price, String image) {
		this.name = name;
		this.price = price;
		this.quantity = 1;
		this.image=image;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}

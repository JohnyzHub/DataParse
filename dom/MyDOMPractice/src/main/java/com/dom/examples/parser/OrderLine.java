package com.dom.examples.parser;

public class OrderLine {

	private String itemName;
	private Double unitPrice;
	private Integer quantity;

	public OrderLine() {
	}

	public OrderLine(String itemName, Double unitPrice, Integer quantity) {
		super();
		this.itemName = itemName;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderLine [itemName=" + itemName + ", unitPrice=" + unitPrice + ", quantity=" + quantity + "]";
	}

}
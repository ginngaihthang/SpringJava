package com.mmit.controller.request;

public class OrderProductData {

	private int productId;
	private int price;
	private int qty;
	public int getProductId() {
		return productId;
	}
	public int getQty() {
		return qty;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "OrderProductData [productId=" + productId + ", price=" + price + ", qty=" + qty + "]";
	}
	
	
	
	
}

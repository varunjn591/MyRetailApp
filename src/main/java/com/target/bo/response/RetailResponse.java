package com.target.bo.response;


public class RetailResponse extends BaseResponse{

	private static final long serialVersionUID = -2237085394092921378L;
	
	private String productId;
	private String name;
	private CurrentPrice current_price;

	public String getId() {
		return productId;
	}

	public void setId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CurrentPrice getCurrent_price() {
		return current_price;
	}

	public void setCurrent_price(CurrentPrice current_price) {
		this.current_price = current_price;
	}

}

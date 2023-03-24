package com.jingnan.data.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * orderItem实体类
 *
 *
 */
@Table(name="tb_order_item_cs")
public class OrderItemCs implements Serializable {

	@Id
	private String id;//ID
	private String name;//商品名称
	private Integer price;//单价
	private Integer num;//数量
	private Integer money;//总金额



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}




}

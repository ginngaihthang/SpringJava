package com.mmit.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;



@Entity
@Table(name = "orders")
public class Orders implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private User customer;
	
	@OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<OrderItem> orderItems = new ArrayList<>();
	
	
	private String shippingName;
	private String shippingPhone;
	private String shippingEmail;
	private String shippingAddress;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@CreationTimestamp
	private LocalDateTime created_at;
	@UpdateTimestamp
	private LocalDateTime status_updated_at;
	
	private int amount;
	
	public void addOrderItem(OrderItem item)
	{
		item.setOrder(this);
		orderItems.add(item);
	}
	
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getCustomer() {
		return customer;
	}
	public void setCustomer(User customer) {
		this.customer = customer;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public String getShippingName() {
		return shippingName;
	}
	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}
	public String getShippingPhone() {
		return shippingPhone;
	}
	public void setShippingPhone(String shippingPhone) {
		this.shippingPhone = shippingPhone;
	}
	public String getShippingEmail() {
		return shippingEmail;
	}
	public void setShippingEmail(String shippingEmail) {
		this.shippingEmail = shippingEmail;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public OrderStatus getStatus() {
		return status;
	}
	
	
	
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	public LocalDateTime getStatus_updated_at() {
		return status_updated_at;
	}
	public void setStatus_updated_at(LocalDateTime status_updated_at) {
		this.status_updated_at = status_updated_at;
	}
	
	public LocalDate getCreatedDate()
	{
		return created_at.toLocalDate();
	}
	
	public LocalDate getUpdatedDate()
	{
		return status_updated_at.toLocalDate();
	}

	
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", customer=" + customer + ", orderItems=" + orderItems + ", shippingName="
				+ shippingName + ", shippingPhone=" + shippingPhone + ", shippingEmail=" + shippingEmail
				+ ", shippingAddress=" + shippingAddress + ", status=" + status + ", created_at=" + created_at
				+ ", status_updated_at=" + status_updated_at + ", amount=" + amount + "]";
	}
	
	

	
	
}

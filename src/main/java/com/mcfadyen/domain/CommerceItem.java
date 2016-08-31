package com.mcfadyen.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * CommerceItemDomain
 *
 * @since 30 de ago de 2016
 * @author Guilherme de Araujo Carvalho <guilhermecarvalho@finchsolucoes.com.br>
 *
 */
@XmlRootElement
@Document(collection = "commerceitem")
public class CommerceItem implements Serializable {
	@Id
	private String id;
	@DBRef
	private Product product;
	private Integer quantity;
	private BigDecimal amount;

	public CommerceItem(String id, Product product, Integer quantity, BigDecimal amount) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
		this.amount = amount;
	}

	public CommerceItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}

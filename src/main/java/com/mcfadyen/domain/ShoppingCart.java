package com.mcfadyen.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * ShoppingCartDomain
 *
 * @since 30 de ago de 2016
 * @author Guilherme de Araujo Carvalho <guilhermecarvalho@finchsolucoes.com.br>
 *
 */
@XmlRootElement
@Document(collection = "shopping_cart")
public class ShoppingCart implements Serializable {

	private List<CommerceItem> items;
	private BigDecimal amount;

	public ShoppingCart() {
		super();
		this.amount = BigDecimal.ZERO;
		this.items = new ArrayList<>();
	}

	public List<CommerceItem> getItems() {
		return items;
	}

	public void setItems(List<CommerceItem> items) {
		this.items = items;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}

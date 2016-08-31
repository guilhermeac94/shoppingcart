package com.mcfadyen.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * ProductDomain
 *
 * @since 30 de ago de 2016
 * @author Guilherme de Araujo Carvalho <guilhermecarvalho@finchsolucoes.com.br>
 *
 */
@XmlRootElement
@Document( collection = "product" )
public class Product implements Serializable {


	@Id
    private String id;
    private String name;
    private String image;
    private BigDecimal price;

    public Product(){

    }

    public Product(String name, String image, BigDecimal price) {
		super();
		this.name = name;
		this.image = image;
		this.price = price;
	}


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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}

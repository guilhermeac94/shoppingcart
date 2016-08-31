package com.mcfadyen.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mcfadyen.domain.CommerceItem;
import com.mcfadyen.domain.Product;
import com.mcfadyen.domain.ShoppingCart;
import com.mcfadyen.service.ProductService;

/**
 * 
 * Classe responsável pelos serviços REST do Domain ShopppigCart
 *
 * @since 30 de ago de 2016
 * @author Guilherme de Araujo Carvalho <guilhermecarvalho@finchsolucoes.com.br>
 *
 */
@RestController
@RequestMapping("/shoppingcart")
public class ShoppingCartController {
	@Autowired
	private ProductService productService;
	
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json", value="/items")
    public ShoppingCart shoppingcartItemsPost(@Context HttpServletRequest request, @RequestParam("product_id") String productId, @RequestParam("quantity") Integer quantity ) {
    	HttpSession session = request.getSession();    	
    	ShoppingCart shoppingCart =(ShoppingCart) session.getAttribute("shoppingCart");
    	if(shoppingCart!=null){
    		if(shoppingCart.getAmount()==null){
    			shoppingCart = new ShoppingCart();
    		}
    		Product product = productService.findOne(productId);
	    	if(product!=null){
	    		
	    		if(shoppingCart.getItems()!=null && !shoppingCart.getItems().isEmpty()){
	    			boolean inside = false;
		    		for(CommerceItem c : shoppingCart.getItems() ){
		    			if(c.getProduct().getId().equals(productId)){
		    				c.setQuantity(c.getQuantity()+quantity);
		    				shoppingCart.setAmount(shoppingCart.getAmount().subtract(c.getAmount()));
		    				c.setAmount(product.getPrice().multiply(new BigDecimal(c.getQuantity())));
		    				shoppingCart.setAmount(shoppingCart.getAmount().add(c.getAmount()));
		    				inside = true;
		    				break;
		    			}
		    		}
		    		if(!inside){
		    			CommerceItem commerceItem = new CommerceItem();
		    			commerceItem.setId(UUID.randomUUID().toString());
			    		commerceItem.setAmount(product.getPrice().multiply(new BigDecimal(quantity)));
			    		commerceItem.setProduct(product);
			    		commerceItem.setQuantity(quantity);
			    		shoppingCart.getItems().add(commerceItem);
			    		shoppingCart.setAmount(shoppingCart.getAmount().add(commerceItem.getAmount()));
		    		}
	    		}else{
	    			CommerceItem commerceItem = new CommerceItem();
	    			commerceItem.setId(UUID.randomUUID().toString());
		    		commerceItem.setAmount(product.getPrice().multiply(new BigDecimal(quantity)));
		    		commerceItem.setProduct(product);
		    		commerceItem.setQuantity(quantity);
		    		shoppingCart.setItems(new ArrayList<>());
		    		shoppingCart.getItems().add(commerceItem);
		    		shoppingCart.setAmount(shoppingCart.getAmount().add(commerceItem.getAmount()));
	    		}
	    		
	    		request.setAttribute("shoppingCart", shoppingCart);
	    		return shoppingCart;
	    	}
    	}
    	return null;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ShoppingCart shoppingcartGet(@Context HttpServletRequest request){
    	HttpSession session = request.getSession();
    	if((ShoppingCart) session.getAttribute("shoppingCart")==null){
    		ShoppingCart shoppingCart= new ShoppingCart();
    		session.setAttribute("shoppingCart", shoppingCart);
    	}
    	return (ShoppingCart) session.getAttribute("shoppingCart");
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value ="/items/{id}")
    public void shoppingcartItemsIdDelete(@Context HttpServletRequest request,@PathVariable("id") String id) {
    	HttpSession session = request.getSession();    	
    	ShoppingCart shoppingCart =(ShoppingCart) session.getAttribute("shoppingCart");
    	if(shoppingCart!=null){
    		if(shoppingCart.getItems() != null && !shoppingCart.getItems().isEmpty()){
    			for(CommerceItem commerceItem : shoppingCart.getItems()){
    				if(commerceItem.getId().equals(id)){
    					shoppingCart.setAmount(shoppingCart.getAmount().subtract(commerceItem.getAmount()));
    					shoppingCart.getItems().remove(commerceItem);
    					break;
    				}
    			}
    			session.setAttribute("shoppingCart", shoppingCart);
    		}
    	}
    }
}

package com.target.retail;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.target.retail.bo.CurrentPrice;
import com.target.retail.exception.RetailException;
import com.target.retail.response.RetailResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RetailServiceImplTest {

	@Autowired
	RetailService retailService;
	
	@Test
	public void testGetProductDetails() {
		RetailResponse retail = retailService.getProductDetails("13860428");
		assertEquals("13.45", retail.getCurrent_price().getValue());
		assertEquals("The Big Lebowski (Blu-ray)", retail.getName());
	}
	
	@Test(expected = RetailException.class)
	public void testGetProductDetailsForExceptions() {
		retailService.getProductDetails("");
		retailService.getProductDetails("-");
		retailService.getProductDetails("@^*&!");
	}

	@Test(expected = RetailException.class)
	public void testUpdateProductPriceDetails() {
		CurrentPrice currPrice = new CurrentPrice();
		currPrice.setValue("-1");
		currPrice.setCurrency_code("USD");
		retailService.updateProductPriceDetails("123456", currPrice);
	}

}

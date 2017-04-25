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
		assertEquals("The Big Lebowski (Blu-ray)", retail.getName());
	}
	
	@Test(expected = RetailException.class)
	public void testGetProductDetailsForExceptions() {
		retailService.getProductDetails("");
		retailService.getProductDetails("-");
		retailService.getProductDetails("@^*&!");
	}

	@Test
	public void testUpdateProductPriceCorrectDetails() {
		CurrentPrice currPrice = new CurrentPrice();
		currPrice.setValue("100.0");
		currPrice.setCurrency_code("USD");
		retailService.updateProductPriceDetails("123123", currPrice);
	}
	
	@Test
	public void testUpdateProductForPrecisionPriceDetails() {
		CurrentPrice currPrice = new CurrentPrice();
		currPrice.setValue("123.56");
		currPrice.setCurrency_code("USD");
		retailService.updateProductPriceDetails("123123", currPrice);
	}
	
	@Test(expected = RetailException.class)
	public void testUpdateProductForNegativePriceDetails() {
		CurrentPrice currPrice = new CurrentPrice();
		currPrice.setValue("-1");
		currPrice.setCurrency_code("USD");
		retailService.updateProductPriceDetails("123123", currPrice);
	}
	
	@Test(expected = RetailException.class)
	public void testUpdateProductForEmptyPriceDetails() {
		CurrentPrice currPrice = new CurrentPrice();
		currPrice.setValue("");
		currPrice.setCurrency_code("USD");
		retailService.updateProductPriceDetails("123123", currPrice);
	}
	
	@Test(expected = RetailException.class)
	public void testUpdateProductForStringValuePriceDetails() {
		CurrentPrice currPrice = new CurrentPrice();
		currPrice.setValue("abcd$%&");
		currPrice.setCurrency_code("USD");
		retailService.updateProductPriceDetails("123123", currPrice);
	}
	
	@Test
	public void testUpdateProductForLongPrecisionPriceDetails() {
		CurrentPrice currPrice = new CurrentPrice();
		currPrice.setValue("0.123456789");
		currPrice.setCurrency_code("USD");
		retailService.updateProductPriceDetails("123123", currPrice);
	}
	
	@Test(expected = RetailException.class)
	public void testUpdateProductForEmptyCurrencyPriceDetails() {
		CurrentPrice currPrice = new CurrentPrice();
		currPrice.setValue("123");
		currPrice.setCurrency_code("");
		retailService.updateProductPriceDetails("123123", currPrice);
	}
	
	@Test(expected = RetailException.class)
	public void testUpdateProductForInvalidCurrencyPriceDetails() {
		CurrentPrice currPrice = new CurrentPrice();
		currPrice.setValue("123");
		currPrice.setCurrency_code("asdjk^#*");
		retailService.updateProductPriceDetails("123123", currPrice);
	}
	
	@Test(expected = RetailException.class)
	public void testUpdateProductForEmptyProductIdPriceDetails() {
		CurrentPrice currPrice = new CurrentPrice();
		currPrice.setValue("123");
		currPrice.setCurrency_code("USD");
		retailService.updateProductPriceDetails("", currPrice);
	}

}

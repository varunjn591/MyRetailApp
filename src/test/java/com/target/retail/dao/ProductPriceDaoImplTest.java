package com.target.retail.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.target.retail.bo.CurrentPrice;
import com.target.retail.exception.RetailException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ProductPriceDaoImplTest {

	@Autowired
	ProductPriceDao productPriceDao;
	
	CurrentPrice currPrice = new CurrentPrice();

	@Test
	public void testGetCurrentPrice() {
		CurrentPrice currPrice = productPriceDao.getCurrentPrice("123456");
		assertEquals(null, currPrice);
	}

	@Test()
	public void testGetCurrentPriceForNull() {
		CurrentPrice currPrice = productPriceDao.getCurrentPrice("-");
		assertEquals(null,currPrice);
		CurrentPrice currPrice2 = productPriceDao.getCurrentPrice("-1");
		assertEquals(null,currPrice2);
		CurrentPrice currPrice3 = productPriceDao.getCurrentPrice("");
		assertEquals(null,currPrice3);
	}

	@Test(expected = RetailException.class)
	public void testUpdateCurrentPriceInvalidValueException() {
		
		currPrice.setValue("-1");
		currPrice.setCurrency_code("USD");
		productPriceDao.updateCurrentPrice("123456", currPrice);
		
		currPrice.setValue("abc");
		currPrice.setCurrency_code("USD");
		productPriceDao.updateCurrentPrice("123456", currPrice);
		
		currPrice.setValue("^!$%@");
		currPrice.setCurrency_code("USD");
		productPriceDao.updateCurrentPrice("123456", currPrice);

	}
	
	@Test(expected = RetailException.class)
	public void testUpdateCurrentPriceBlankValueException() {
		currPrice.setValue("");
		currPrice.setCurrency_code("USD");
		productPriceDao.updateCurrentPrice("123456", currPrice);
	}
	
	@Test(expected = RetailException.class)
	public void testUpdateCurrentPriceInvalidCurrencyCodeException() {
		
		currPrice.setValue("1");
		currPrice.setCurrency_code("XSD");
		productPriceDao.updateCurrentPrice("123456", currPrice);
		
	}
	
	@Test(expected = RetailException.class)
	public void testUpdateCurrentPriceBlankCurrencyCodeException() {
		currPrice.setValue("1");
		currPrice.setCurrency_code("");
		productPriceDao.updateCurrentPrice("123456", currPrice);

		currPrice.setValue("1");
		currPrice.setCurrency_code("-1");
		productPriceDao.updateCurrentPrice("123456", currPrice);

		currPrice.setValue("1");
		currPrice.setCurrency_code("@#@");
		productPriceDao.updateCurrentPrice("123456", currPrice);

	}

}

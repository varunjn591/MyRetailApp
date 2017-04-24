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

	@Test
	public void testGetCurrentPrice() {
		CurrentPrice currPrice = productPriceDao.getCurrentPrice("13860428");
		assertEquals("13.45", currPrice.getValue());
		CurrentPrice currPrice2 = productPriceDao.getCurrentPrice("123456");
		assertEquals(null, currPrice2.getValue());
	}

	@Test(expected = RetailException.class)
	public void testGetCurrentPriceForException() {
		productPriceDao.getCurrentPrice("-");
		productPriceDao.getCurrentPrice("-1");
		productPriceDao.getCurrentPrice("");
	}

	@Test(expected = RetailException.class)
	public void testUpdateCurrentPriceException() {
		CurrentPrice currPrice = new CurrentPrice();
		currPrice.setValue("-1");
		currPrice.setCurrency_code("USD");
		productPriceDao.updateCurrentPrice("123456", currPrice);

		currPrice.setValue("");
		currPrice.setCurrency_code("USD");
		productPriceDao.updateCurrentPrice("123456", currPrice);

		currPrice.setValue("abc");
		currPrice.setCurrency_code("USD");
		productPriceDao.updateCurrentPrice("123456", currPrice);

		currPrice.setValue("^!$%@");
		currPrice.setCurrency_code("USD");
		productPriceDao.updateCurrentPrice("123456", currPrice);

		currPrice.setValue("1");
		currPrice.setCurrency_code("XSD");
		productPriceDao.updateCurrentPrice("123456", currPrice);

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

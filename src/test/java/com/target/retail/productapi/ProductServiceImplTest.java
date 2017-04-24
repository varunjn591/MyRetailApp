package com.target.retail.productapi;

import static org.junit.Assert.assertEquals;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.target.retail.exception.RetailException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ProductServiceImplTest {

	@Autowired
	ProductService productService;

	@Test
	public void testGetProductServiceResponse() throws JSONException {
		JSONObject response = productService.getProductServiceResponse("13860428");
		String title = response.getJSONObject("product").getJSONObject("item").getJSONObject("product_description").getString("title");
		assertEquals("The Big Lebowski (Blu-ray)", title);

	}
	
	@Test(expected = RetailException.class)
	public void testGetProductServiceResponse2(){
		productService.getProductServiceResponse("");
	}

}

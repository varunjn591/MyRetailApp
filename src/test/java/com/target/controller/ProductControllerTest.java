package com.target.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.target.bo.response.RetailResponse;
import com.target.service.RetailService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ProductControllerTest {

	@Autowired
	RetailService retailService;

	@Test
	public void test() {
		
		RetailResponse title = retailService.getProductDetails("123456");
		assertEquals("The Big Lebowski (Blu-ray)",title.getName());
	}

}

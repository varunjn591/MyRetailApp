package com.target.service.utility;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.target.bo.response.CurrentPrice;
import com.target.exception.ErrorCode;
import com.target.exception.RetailException;

@Component("requestValidator")
public class RequestValidator {

	private Set<String> currencyCodes;
	
	@PostConstruct
	public void init(){
		currencyCodes = new HashSet<String>(Arrays.asList("EUR","USD","AUD","CAD","JPY"));
	}

	public void validateCurrentPrice(CurrentPrice currentPrice){
		
		if(currentPrice == null){
			throw new RetailException(ErrorCode.BADREQUEST);
		}

		if(currentPrice.getValue() < 0 || !Double.isFinite(currentPrice.getValue())){
			throw new RetailException(ErrorCode.BADREQUEST, "Not a valid price");
		}

		if(!currencyCodes.contains((currentPrice.getCurrency_code()))){
			throw new RetailException(ErrorCode.BADREQUEST,"Bad currency code");
		}
		
	}
}

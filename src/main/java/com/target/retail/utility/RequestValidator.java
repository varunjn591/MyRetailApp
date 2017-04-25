package com.target.retail.utility;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.target.retail.bo.CurrentPrice;
import com.target.retail.exception.ErrorCode;
import com.target.retail.exception.RetailException;

@Component("requestValidator")
public class RequestValidator {

	private Set<String> currencyCodes;

	@PostConstruct
	public void init() {
		currencyCodes = new HashSet<String>(Arrays.asList("EUR", "USD", "AUD", "CAD", "JPY"));
	}

	public void validateCurrentPrice(CurrentPrice currentPrice) {

		String regex = "^[0-9]{1,13}(\\.[0-9]*)?";
		if (currentPrice == null) {
			throw new RetailException(ErrorCode.BADREQUEST);
		}

		if (currentPrice.getValue() == null || "".equals(currentPrice.getValue())) {
			throw new RetailException(ErrorCode.BADREQUEST, "Not a valid price");
		}
		
		if(!Pattern.matches(regex, currentPrice.getValue())){
			throw new RetailException(ErrorCode.BADREQUEST, "Contains invalid/ special characters");
		}

		if (Double.parseDouble(currentPrice.getValue()) < 0 || !Double.isFinite(Double.parseDouble(currentPrice.getValue()))) {
			throw new RetailException(ErrorCode.BADREQUEST, "Not a valid price");
		}

		if (!currencyCodes.contains((currentPrice.getCurrency_code())) || "".equals(currentPrice.getCurrency_code())) {
			throw new RetailException(ErrorCode.BADREQUEST, "Bad currency code");
		}

	}
}

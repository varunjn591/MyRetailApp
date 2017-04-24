package com.target.retail.utility;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

		if (currentPrice == null) {
			throw new RetailException(ErrorCode.BADREQUEST);
		}

		if (currentPrice.getValue() == null) {
			throw new RetailException(ErrorCode.BADREQUEST, "Not a valid price");
		}

		if (Double.parseDouble(currentPrice.getValue()) < 0 || !Double.isFinite(Double.parseDouble(currentPrice.getValue()))) {
			throw new RetailException(ErrorCode.BADREQUEST, "Not a valid price");
		}

		if (!currencyCodes.contains((currentPrice.getCurrency_code()))) {
			throw new RetailException(ErrorCode.BADREQUEST, "Bad currency code");
		}

	}
}

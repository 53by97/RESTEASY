package com.kvvssut.validation.validator;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.kvvssut.validation.constraint.ValidAmount;

public class AmountValidator implements ConstraintValidator<ValidAmount, BigDecimal> {

	@Override
	public void initialize(ValidAmount arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(BigDecimal value, ConstraintValidatorContext constraintValidatorContext) {
		System.out.println(value.toString());
		boolean valid = value.compareTo(new BigDecimal(0.00)) == 1 ? true : false;
		if (!valid) {
			// constraintValidatorContext.disableDefaultConstraintViolation();
			constraintValidatorContext.buildConstraintViolationWithTemplate(String.format(
			          "Has invalid amount- %s !!", value.toString()));
		}
		System.out.println(valid);
		return valid;
	}

}

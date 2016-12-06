package com.kvvssut.validation.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.kvvssut.validation.validator.AmountValidator;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = { AmountValidator.class })
@Documented
public @interface ValidAmount {
	String message() default "{Invalid_Amount}";

	Class<?> [] groups() default {};

	Class<? extends Payload> [] payload() default {};
}

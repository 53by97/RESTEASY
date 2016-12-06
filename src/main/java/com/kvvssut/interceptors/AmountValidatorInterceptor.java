package com.kvvssut.interceptors;

import java.util.Iterator;
import java.util.Set;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.kvvssut.validation.RequestResourceValidation;

public class AmountValidatorInterceptor {

	@Inject
	private Validator validator;

	@AroundInvoke
	public Object validateAmount(InvocationContext invocationContext) throws Exception {
		Object [] parameters = invocationContext.getParameters();

		for (Object parameter : parameters) {
			if (parameter instanceof RequestResourceValidation) {
				handleBeanValidation((RequestResourceValidation) parameter);
			}
		}

		System.out.println(invocationContext.getMethod().getName());
		System.out.println("aftr amt validator intercept : " + parameters[0]);
		invocationContext.setParameters(parameters);

		Object returnVal = null;
		try {
			returnVal = invocationContext.proceed();
		} catch (Exception e) {
			throw e;
		}

		return returnVal;
	}

	private void handleBeanValidation(RequestResourceValidation parameter) throws Exception {
		Set<ConstraintViolation<RequestResourceValidation>> violations = this.validator
		          .validate(parameter);
		if (violations != null) {
			System.out.println("printing violations field");
			Iterator<ConstraintViolation<RequestResourceValidation>> iterator = violations
			          .iterator();
			while (iterator.hasNext()) {
				ConstraintViolation<RequestResourceValidation> constraintViolation = iterator
				          .next();
				System.out.println(constraintViolation.getPropertyPath().toString() + " " + constraintViolation.getMessageTemplate());
				throw new Exception("Invalid parameter");
			}
		}
	}
}

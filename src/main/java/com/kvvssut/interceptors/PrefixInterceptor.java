package com.kvvssut.interceptors;

import javax.annotation.PostConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class PrefixInterceptor {

	@PostConstruct
	public void postC(InvocationContext invocationContext) {
		System.out.println("constructing it");
	}

	@AroundInvoke
	public Object prefixSalutation(InvocationContext invocationContext) throws Exception {
		Object [] parameters = invocationContext.getParameters();
		parameters[0] = "Mr." + (String) parameters[0];

		System.out.println(invocationContext.getMethod().getName());
		System.out.println("aftr prefix intercept : " + parameters[0]);
		invocationContext.setParameters(parameters);

		Object returnVal = null;
		try {
			returnVal = invocationContext.proceed();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return returnVal;
	}
}

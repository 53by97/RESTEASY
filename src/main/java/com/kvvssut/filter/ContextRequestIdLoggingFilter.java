package com.kvvssut.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.MDC;

@WebFilter("/*")
public class ContextRequestIdLoggingFilter implements Filter {

	private static final String REST_REQUEST_ID = "restRequestId";
	private static final int TIME_SUBSTRING_START_INDEX = 2;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
	          throws IOException, ServletException {
		String url = ((HttpServletRequest) request).getPathTranslated();
		System.out.println("Url: " + url);

		addToContext(REST_REQUEST_ID, getRequestId());

		filterChain.doFilter(request, response);

	}

	private String getRequestId() {
		synchronized (this) {
			return "log+"
			          + (System.currentTimeMillis() + "").substring(TIME_SUBSTRING_START_INDEX)
			          + "-" + Thread.currentThread().getId();
		}
	}

	private static void addToContext(String key, String value) {
		MDC.put(key, value);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}

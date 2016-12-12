package com.singplayground.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class CustomFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		System.out.println("******* custom filter **********");

		if (SecurityContextHolder.getContext() != null) {
			System.out.println("*****");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			System.out.println(auth);

			if (auth != null) {
				System.out.println("the nam of the session : " + auth.getName());

			}
			//String name = auth.getName(); //get logged in username
			//System.out.println("name : " + name);
		} else {
			System.out.println("######### SecurityContextHolder.getContext() is null");
		}

		//System.out.println("name : " + name);

		chain.doFilter(request, response);
	}

}

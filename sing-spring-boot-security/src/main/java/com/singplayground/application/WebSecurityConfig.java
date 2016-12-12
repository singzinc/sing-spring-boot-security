package com.singplayground.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;

import com.singplayground.filter.CsrfHeaderFilter;
import com.singplayground.filter.CustomFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/home").permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout().permitAll();
		http.headers().httpStrictTransportSecurity();
		http.headers().xssProtection();
		http.headers().contentTypeOptions();

		http.headers().contentSecurityPolicy(
				"	default-src 'self'	*.facebook.com *.google.com *.twitter.com; "
						+ "	script-src	'self' 'unsafe-eval' 'unsafe-inline' www.google-analytics.com ajax.googleapis.com;"
						+ "	style-src	'self'	'unsafe-inline' fonts.googleapis.com;" + "img-src	'self' 'unsafe-eval' 'unsafe-inline' www.google-analytics.com data:;");

		http.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
		http.addFilterBefore(new CustomFilter(), CsrfHeaderFilter.class);

		//http.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
		//http.addFilter(new CsrfHeaderFilter());
	}

	/*
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	}
	*/

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		AuthenticationProvider authenticationProvider = new CustomAuthenticationProvider();
		return authenticationProvider;
	}
	/*
		@Bean
		@Order(Ordered.LOWEST_PRECEDENCE)
		public String userInsertingMdcFilter() {
			System.out.println("------ test ----");
			return "test";
		}
	*/
	/*
		@Bean
		public FilterRegistrationBean registerRequestLogFilter(Filter filter) {
			FilterRegistrationBean reg = new FilterRegistrationBean(filter);
			reg.setOrder(3);
			return reg;
		}
		*/
	/*
	@Bean
	public RequestFilter beforeSpringSecurityFilter() {

		return new RequestFilter();
	}

	@Bean
	RequestFilter afterSpringSecurityFilter() {
		return new RequestFilter();
	}
	*/

}
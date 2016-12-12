package com.singplayground.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.singplayground.model.CustomUser;
import com.singplayground.model.Role;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private final Log logger = LogFactory.getLog(CustomAuthenticationProvider.class);

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		logger.info("start CustomAuthenticationProvider..........");
		boolean auth = false;
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();

		System.out.println("------ username " + name);
		CustomUser user = new CustomUser();

		try {
			// do something for checking 

			if ((name.equals("aaa") && password.equals("password")) || (name.equals("bbb") && password.equals("password"))) {
				auth = true;
				user.setUsername(name);
				user.setPassword(password);

				user.setEmail("testing@abc.com");
				System.out.println("username : " + name);
				System.out.println("password : " + password);

				List<Role> temp = new ArrayList();

				Role role = new Role();
				role.setName("temp");
				temp.add(role);
				user.setAuthorities(temp);

				//user.setUsername("");
				user.setMemberId(Long.parseLong("1"));

				Date currentTime = new Date();

			} else {
				auth = false;
			}
			//user.getSession().setAttribute("currentMemberId", user.getMemberId());

			//auth = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (auth) {
			List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
			return new UsernamePasswordAuthenticationToken(user, password, grantedAuths);
		} else {
			return null;
		}
	}

	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}

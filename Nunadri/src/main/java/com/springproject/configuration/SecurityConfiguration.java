package com.springproject.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.springproject.impl.UserDetailsServiceImpl;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Override
	protected void configure(HttpSecurity security) throws Exception {
	
		security.csrf().disable();

		security.authorizeRequests().antMatchers("/login","/signup","/findid","/findpwd","/login/error","/").permitAll()
									.antMatchers("admin/**").hasAnyRole("ADMIN")
									.anyRequest().authenticated();
		
		security.formLogin()
		.loginPage("/login")
		.defaultSuccessUrl("/", true)
		.usernameParameter("id")
		.passwordParameter("pwd")
//		.usernameParameter("nickname")
		.failureUrl("/login/error")
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/");

//		security.logout().invalidateHttpSession(true).logoutSuccessUrl("/login.do");
		
		security.userDetailsService(userDetailsServiceImpl);
	}
	
	@Autowired
	public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
		
	}
	  
	@Override
	public void configure(WebSecurity web) throws Exception {
	       web.ignoring().antMatchers("/css/**", "/js/**", "/assets/**"); //static 디렉터리 하위 파일은 인증을 무시하도록 설정
	   }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	//업데이트 관련
	 	@Bean
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
}

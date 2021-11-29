package com.springproject.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.springproject.configuration.oauth.PrincipalOauth2UserService;
import com.springproject.impl.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

//import com.springproject.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private PrincipalOauth2UserService principalOauth2UserService;
	
   @Autowired
  private UserDetailsServiceImpl userDetailsServiceImpl;
   
   @Override
   protected void configure(HttpSecurity security) throws Exception {
            
      security.authorizeRequests()
                           .antMatchers("/", "/login", "/signup", "/findid", "/findpwd","/login/error").permitAll()
                           .antMatchers("/admin/**").hasRole("ADMIN")
                           .anyRequest().authenticated();
//                           .antMatchers("/manager/**").hasRole("MANAGER")
//                           .antMatchers("admin/**").hasAnyRole("ADMIN");
//      
//    
      security.csrf().disable();

      security.formLogin()
      .loginPage("/login")
      .defaultSuccessUrl("/", true)
      .usernameParameter("id")
      .passwordParameter("pwd")
      .failureUrl("/login/error")
      .and()
      .logout()
      .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
      .logoutSuccessUrl("/")
      .and()
      .oauth2Login()
      .loginPage("/login") //구글 로그인된뒤의 후처리가 필요함
      .userInfoEndpoint()
      .userService(principalOauth2UserService);
      
      
   
      
//      security.logout()
//      .invalidateHttpSession(true)
//      .logoutSuccessUrl("/main.do");
      
      security.userDetailsService(userDetailsServiceImpl);
   }
   
//   @Autowired
//   public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
//      
//   }
   
   @Override
   public void configure(WebSecurity web) throws Exception {
       web.ignoring().antMatchers("/css/**", "/js/**", "/assets/**", "/favicon.ico", "/resources/**", "/error"); //static 디렉터리 하위 파일은 인증을 무시하도록 설정
   }
   
 
   @Bean
   public PasswordEncoder passwordEncoder() {
      return PasswordEncoderFactories.createDelegatingPasswordEncoder();
   }   
   
}
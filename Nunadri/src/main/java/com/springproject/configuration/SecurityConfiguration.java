package com.springproject.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.springproject.impl.UserDetailsServiceImpl;

//import com.springproject.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
   @Autowired
  private UserDetailsServiceImpl userDetailsServiceImpl;
   
   @Override
   protected void configure(HttpSecurity security) throws Exception {
            
//     security.authorizeRequests()
//                      .antMatchers("/", "/login", "/signup", "/findid", "/findpwd","/login/error").permitAll()
//                      .antMatchers("/admin/**").hasRole("ADMIN")
//                      .anyRequest().authenticated();
//                           .antMatchers("/manager/**").hasRole("MANAGER")
//                           .antMatchers("admin/**").hasAnyRole("ADMIN");
      
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
      .logoutSuccessUrl("/");
      
   
      
//      security.logout()
//      .invalidateHttpSession(true)
//      .logoutSuccessUrl("/main.do");
      
      security.userDetailsService(userDetailsServiceImpl);
   }
   
//   @Autowired
//   public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
//      
//   }
   
   
   @Bean
   @Override
   public AuthenticationManager authenticationManagerBean() throws Exception {
       return super.authenticationManagerBean();
   }
   
   @Override
   public void configure(WebSecurity web) throws Exception {
       web.ignoring().antMatchers("/css/**", "/js/**", "/assets/**"); //static 디렉터리 하위 파일은 인증을 무시하도록 설정
   }
   
 
   @Bean
   public PasswordEncoder passwordEncoder() {
      return PasswordEncoderFactories.createDelegatingPasswordEncoder();
   }
   
//   @Override
//   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//       //userDetailsService를 구현하고 있는 객체로 memberService를 지정해주며, 비밀번호 암호화를 위해 passwordEncoder 지정
//       auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
//   }
}
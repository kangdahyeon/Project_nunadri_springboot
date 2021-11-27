package com.springproject.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
            
      security.authorizeRequests()
                           .antMatchers("/", "/login", "/signup", "/findid", "/findpwd","/login/error").permitAll()
                           .antMatchers("/admin/**").hasRole("ADMIN");
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
   
   @Override
   public void configure(WebSecurity web) throws Exception {
       web.ignoring().antMatchers("/css/**", "/js/**", "/assets/**"); //static �뵒�젆�꽣由� �븯�쐞 �뙆�씪�� �씤利앹쓣 臾댁떆�븯�룄濡� �꽕�젙
   }
   
 
   @Bean
   public PasswordEncoder passwordEncoder() {
      return PasswordEncoderFactories.createDelegatingPasswordEncoder();
   }
   
//   @Override
//   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//       //userDetailsService瑜� 援ы쁽�븯怨� �엳�뒗 媛앹껜濡� memberService瑜� 吏��젙�빐二쇰ŉ, 鍮꾨�踰덊샇 �븫�샇�솕瑜� �쐞�빐 passwordEncoder 吏��젙
//       auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
//   }
}
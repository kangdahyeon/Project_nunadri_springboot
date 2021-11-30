package com.springproject.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springproject.mapper.MemberMapper;
import com.springproject.vo.MemberVO;
import com.springproject.vo.SecurityUser;
//import com.springproject.vo.SecurityUser;				

/*시큐리티가 /login 주소 요청이 오면 낚아채서 로그인 진행
 * 로그인 진행이 완료되면 시큐리티 session을 만들어줌(SecurityContenxtHolder)
 */


@Service											/*UserDetailsService : 데이터베이스에서 회원 정보를 가져오는 역할
													loadUserByUsername() 메소드가 존재하며, 회원정보를 조회하여
													사용자의 정보와 권한을 갖는 UserDetails 인터페이스를 반환
													스프링 시큐리티에서 UserDetailService를 구현하고 있는 클래스를 통해 로그인 기능을 구현*/
public class UserDetailsServiceImpl implements UserDetailsService {
   @Autowired
   private MemberMapper memberMapper;
   
   
   @Override				//UserDetails : 회원의 정보를 담기 위해서 사용하는 인터페이스, 직접 구현하거나 스프링 시큐리티 User 클래스 사용
   public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
      MemberVO member = memberMapper.findId(id);
    
      if(member == null) {
         throw new UsernameNotFoundException(id + "           ");
      } else {

    	  SecurityUser user = 
    			  new SecurityUser(member.getId(),
    					  			member.getPwd(),
    					  			member.getNickname(),
//    					  			member.getAddress(),
    					  			AuthorityUtils.createAuthorityList(member.getRole().toString()));
    	 
    	  return user;
    	  
      }
   }
}
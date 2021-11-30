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

/*��ť��Ƽ�� /login �ּ� ��û�� ���� ����ä�� �α��� ����
 * �α��� ������ �Ϸ�Ǹ� ��ť��Ƽ session�� �������(SecurityContenxtHolder)*/


@Service											/*UserDetailsService : �����ͺ��̽����� ȸ�� ������ �������� ����
														loadUserByUsername() �޼ҵ尡 �����ϸ�, ȸ�������� ��ȸ�Ͽ�
														������� ������ ������ ���� UserDetails �������̽��� ��ȯ
														������ ��ť��Ƽ���� UserDetailService�� �����ϰ� �ִ� Ŭ������ ���� �α��� ����� ����*/
public class UserDetailsServiceImpl implements UserDetailsService {
   @Autowired
   private MemberMapper memberMapper;
   
   
   @Override				//UserDetails : ȸ���� ������ ��� ���ؼ� ����ϴ� �������̽�, ���� �����ϰų� ������ ��ť��Ƽ User Ŭ���� ���
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
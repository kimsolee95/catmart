package com.shoptest.catmart.member.service;

import com.shoptest.catmart.member.dto.MemberInputDto;
import org.springframework.stereotype.Service;

public interface MemberService {

  /**
   * 회원가입
   * */
  boolean joinMember(MemberInputDto parameter);



}

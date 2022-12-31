package com.shoptest.catmart.member.service.impl;

import com.shoptest.catmart.member.domain.Member;
import com.shoptest.catmart.member.dto.MemberInputDto;
import com.shoptest.catmart.member.repository.MemberRepository;
import com.shoptest.catmart.member.service.MemberService;
import com.shoptest.catmart.member.type.Kind;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;

  @Override
  public boolean joinMember(MemberInputDto parameter) {

    Optional<Member> optionalMember = memberRepository.findByEmail(parameter.getEmail());
    if (optionalMember.isPresent()) {
      return false;
    }

    String encPassword = BCrypt.hashpw(parameter.getPassword(), BCrypt.gensalt());

    Member member = Member.builder()
        .email(parameter.getEmail())
        .name(parameter.getName())
        .password(encPassword)
        .address(parameter.getAddress())
        .zipcode(parameter.getZipcode())
        .addressDetail(parameter.getAddressDetail())
        .memberKind(Kind.CUSTOMER)
        .createdAt(LocalDateTime.now())
        .build();

    memberRepository.save(member);
    return true;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Optional<Member> optionalMember = memberRepository.findByEmail(username);
    if (!optionalMember.isPresent()) {
      //throw
      throw new UsernameNotFoundException("회원 정보가 없습니다.");
    }

    Member member = optionalMember.get();

    //고객 권한 부여
    List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
    grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_CUSTOMER"));

    //관리자 - 판매자 권한 부여
    if (Kind.ADMIN_SELLER.equals(member.getMemberKind())) {
      grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN_SELLER"));
    }

    return new User(member.getEmail(), member.getPassword(), grantedAuthorityList); //id(email), pw, auth
  }
}

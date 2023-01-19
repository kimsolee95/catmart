package com.shoptest.catmart.member.service.impl;

import com.shoptest.catmart.common.exception.CartException;
import com.shoptest.catmart.common.exception.MemberException;
import com.shoptest.catmart.common.exception.type.CartErrorCode;
import com.shoptest.catmart.common.exception.type.MemberErrorCode;
import com.shoptest.catmart.member.domain.Member;
import com.shoptest.catmart.member.dto.MemberAddressDto;
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
      throw new MemberException(MemberErrorCode.USER_EMAIL_ALREADY_EXIST); //error message 출력 page return(바로 뷰 매칭해서 보여주는 컨트롤러에서 사용하는 서비스이기 때문에 error page 리턴)
    }

    String encPassword = BCrypt.hashpw(parameter.getPassword(), BCrypt.gensalt());

    Member member = Member.builder()
        .email(parameter.getEmail())
        .name(parameter.getName())
        .password(encPassword)
        .phoneNumber(parameter.getPhoneNumber())
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
  public MemberAddressDto selectMemberAddress(String email) {
    Member member = memberRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("회원 정보가 없습니다."));

    MemberAddressDto memberAddressDto = new MemberAddressDto();
    memberAddressDto.setName(member.getName());
    memberAddressDto.setPhoneNumber(member.getPhoneNumber());
    memberAddressDto.setAddress(member.getAddress());
    memberAddressDto.setAddressDetail(member.getAddressDetail());
    memberAddressDto.setZipcode(member.getZipcode());
    return memberAddressDto;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Member member = memberRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("회원정보가 없습니다."));

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

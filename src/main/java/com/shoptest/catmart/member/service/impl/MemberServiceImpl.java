package com.shoptest.catmart.member.service.impl;

import com.shoptest.catmart.member.domain.Member;
import com.shoptest.catmart.member.dto.MemberInputDto;
import com.shoptest.catmart.member.repository.MemberRepository;
import com.shoptest.catmart.member.service.MemberService;
import com.shoptest.catmart.member.type.Kind;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
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
}

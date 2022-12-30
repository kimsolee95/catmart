package com.shoptest.catmart.member.dto;

import lombok.Getter;
import lombok.Setter;
/**
 * 회원 DTO
 * */
@Getter
@Setter
public class MemberInputDto {

  /* 이메일 */
  private String email;

  /* 이름 */
  private String name;

  /* 비밀번호 */
  private String password;

  /* 주소 */
  private String address;

  /* 우편번호 */
  private String zipcode;

  /* 상세주소 */
  private String addressDetail;

}

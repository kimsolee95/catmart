package com.shoptest.catmart.member.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 회원_주소 및 연락처 DTO
 * */
@Getter
@Setter
public class MemberAddressDto {

  /* 이름 */
  private String name;

  /* 주소 */
  private String address;

  /* 핸드폰 번호 */
  private String phoneNumber;

  /* 우편번호 */
  private String zipcode;

  /* 상세주소 */
  private String addressDetail;
}

package com.shoptest.catmart.member.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 회원 DTO
 * */
@Getter
@Setter
@ToString
public class MemberInputDto {

  /* 이메일 */
  @NotNull(message = "이메일을 입력해주세요.")
  private String email;

  /* 이름 */
  @NotNull(message = "이름을 입력해주세요.")
  private String name;

  /* 핸드폰번호 */
  @NotNull(message = "핸드폰번호를 입력해주세요.")
  private String phoneNumber;

  /* 비밀번호 */
  @Size(min = 5, max = 14, message="비밀번호는 5자리 이상 14자리 이하로 입력해주세요.")
  private String password;

  /* 주소 */
  @NotNull(message = "우편번호 찾기를 통해서 주소를 입력해주세요.")
  private String address;

  /* 우편번호 */
  @NotNull(message = "우편 번호 찾기를 통해서 우편번호를 입력해주세요.")
  private String zipcode;

  /* 상세주소 */
  @NotNull(message = "상세주소는 꼭 입력해주세요.")
  private String addressDetail;

}

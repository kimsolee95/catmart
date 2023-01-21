package com.shoptest.catmart.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
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
@ApiModel(value="MemberInputDto", description = "회원가입용 input DTO")
public class MemberInputDto {

  /* 이메일 */
  @ApiModelProperty(value="이메일")
  @NotBlank(message = "이메일을 입력해주세요.")
  private String email;

  /* 이름 */
  @ApiModelProperty(value="이름")
  @NotBlank(message = "이름을 입력해주세요.")
  private String name;

  /* 핸드폰번호 */
  @ApiModelProperty(value="핸드폰번호")
  @NotBlank(message = "핸드폰번호를 입력해주세요.")
  private String phoneNumber;

  /* 비밀번호 */
  @ApiModelProperty(value="비밀번호")
  @Size(min = 5, max = 14, message="비밀번호는 5자리 이상 14자리 이하로 입력해주세요.")
  private String password;

  /* 주소 */
  @ApiModelProperty(value="주소")
  @NotBlank(message = "우편번호 찾기를 통해서 주소를 입력해주세요.")
  private String address;

  /* 우편번호 */
  @ApiModelProperty(value="우편번호")
  @NotBlank(message = "우편 번호 찾기를 통해서 우편번호를 입력해주세요.")
  private String zipcode;

  /* 상세주소 */
  @ApiModelProperty(value="상세주소")
  @NotBlank(message = "상세주소는 꼭 입력해주세요. (상품지 배송 기본주소입니다.)")
  private String addressDetail;

}

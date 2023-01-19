package com.shoptest.catmart.common.exception.model;

import com.shoptest.catmart.common.exception.type.CartErrorCode;
import com.shoptest.catmart.common.exception.type.MemberErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  회원 서비스 에러 공통 응답 객체
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberErrorResponse {

  private MemberErrorCode errorCode;
  private String errorMessage;
}

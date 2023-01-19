package com.shoptest.catmart.common.exception.customexception;

import com.shoptest.catmart.common.exception.type.MemberErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * 회원 업무 - custom exception
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberException extends RuntimeException {

  private MemberErrorCode memberErrorCode;
  private String errorMessage;

  public MemberException(MemberErrorCode memberErrorCode) {
    this.memberErrorCode = memberErrorCode;
    this.errorMessage = memberErrorCode.getDescription();
  }

}

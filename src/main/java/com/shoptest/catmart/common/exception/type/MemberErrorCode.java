package com.shoptest.catmart.common.exception.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberErrorCode {

  USER_EMAIL_ALREADY_EXIST("해당 이메일을 가진 사용자가 이미 존재합니다. 다른 이메일을 사용해주세요");

  private final String description;
}

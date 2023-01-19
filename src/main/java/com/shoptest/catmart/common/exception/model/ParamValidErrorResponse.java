package com.shoptest.catmart.common.exception.model;

import lombok.Getter;
import lombok.Setter;
/**
 *  parameter DTO Valid error 공통 응답 객체
 * */
@Getter
@Setter
public class ParamValidErrorResponse {

  private String code;
  private String errorMessage;

  public ParamValidErrorResponse(String code, String errorMessage) {
    this.code = code;
    this.errorMessage = errorMessage;
  }

}
